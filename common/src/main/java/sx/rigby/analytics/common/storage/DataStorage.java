package sx.rigby.analytics.common.storage;

import java.util.UUID;

public interface DataStorage {

    boolean init();

    void close();

    void insert(String hostname, UUID uniqueId);

    int getUniqueJoins(String hostname);

    int getTotalJoins(String hostname);
}
