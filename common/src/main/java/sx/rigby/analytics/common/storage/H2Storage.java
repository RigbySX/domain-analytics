package sx.rigby.analytics.common.storage;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import com.zaxxer.hikari.pool.HikariPool;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.h2.jdbcx.JdbcDataSource;
import sx.rigby.analytics.common.util.UuidUtil;

import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;

import static java.lang.String.format;

@Log
@RequiredArgsConstructor
public class H2Storage implements DataStorage {
    private static final String CREATE_TABLE =
            "CREATE TABLE IF NOT EXISTS `server_joins` (" +
                    "hostname VARCHAR(32) NOT NULL, " +
                    "uuid BINARY(16) NOT NULL, " +
                    "join_count INT NOT NULL, " +
                    "PRIMARY KEY (hostname, uuid))";
    private static final String GET_UNIQUE =
            "SELECT COUNT(*) from `server_joins` WHERE hostname = ?";
    private static final String GET_TOTAL =
            "SELECT SUM(join_count) FROM `server_joins` WHERE hostname = ?";
    private static final String INSERT =
            "INSERT INTO `server_joins` " +
                    "(hostname, uuid, join_count) VALUES(?, ?, 1) " +
                    "ON DUPLICATE KEY UPDATE join_count = join_count + 1";

    private final File dataFolder;

    private HikariDataSource hikariDataSource;

    @Override
    public boolean init() {
        HikariConfig config = new HikariConfig();
        config.setDataSourceClassName(JdbcDataSource.class.getName());
        config.addDataSourceProperty("URL", "jdbc:h2:" +
                dataFolder.getAbsolutePath() + "/database;MODE=MySQL");

        try {
            hikariDataSource = new HikariDataSource(config);
        } catch (HikariPool.PoolInitializationException e) {
            log.log(Level.SEVERE, "[DomainAnalytics] Unable to connect to plugin primary database.", e);
        }

        try (Connection conn = hikariDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(CREATE_TABLE)) {
            stmt.executeUpdate();
        } catch (SQLException e) {
            log.log(Level.SEVERE, "[DomainAnalytics] Failed to create primary plugin table.");
        }
        return hikariDataSource.isRunning();
    }

    @Override
    public void close() {
        if (hikariDataSource != null && hikariDataSource.isRunning()) {
            hikariDataSource.close();
        }
    }

    @Override
    public void insert(String hostname, UUID uuid) {
        try (Connection conn = hikariDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(INSERT)) {
            stmt.setString(1, hostname);
            stmt.setBytes(2, UuidUtil.wrap(uuid));
            stmt.execute();
        } catch (SQLException e) {
            log.log(Level.SEVERE, format("[DomainAnalytics] Unable to perform INSERT statement on hostname %s.", hostname), e);
        }
    }

    @Override
    public int getUniqueJoins(String hostname) {
        return get(hostname, GET_UNIQUE);
    }

    @Override
    public int getTotalJoins(String hostname) {
        return get(hostname, GET_TOTAL);
    }

    private int get(String hostname, String statement) {
        try (Connection conn = hikariDataSource.getConnection(); PreparedStatement stmt = conn.prepareStatement(statement)) {
            stmt.setString(1, hostname);

            try (ResultSet set = stmt.executeQuery()) {
                if (set.next()) {
                    return set.getInt(1);
                }
            }
        } catch (SQLException e) {
            log.log(Level.SEVERE, format("[DomainAnalytics] Unable to retrieve data for hostname %s.", hostname), e);
        }
        return 0;
    }
}
