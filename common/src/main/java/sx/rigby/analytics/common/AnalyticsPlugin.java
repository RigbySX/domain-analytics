package sx.rigby.analytics.common;

import lombok.extern.java.Log;
import sx.rigby.analytics.common.command.CommandHandler;
import sx.rigby.analytics.common.event.EventExecutor;
import sx.rigby.analytics.common.storage.DataStorage;
import sx.rigby.analytics.common.storage.H2Storage;
import sx.rigby.analytics.common.util.AsyncHelper;

import java.io.File;
import java.util.logging.Level;

@Log
public abstract class AnalyticsPlugin {

    protected DataStorage dataStorage;
    protected CommandHandler commandHandler;
    protected EventExecutor eventExecutor;

    protected abstract File getDataFolder();

    protected abstract void registerCommand();
    protected abstract void registerEvent();

    public final void enable() {
        dataStorage = new H2Storage(getDataFolder());
        if (!dataStorage.init()) {
            log.log(Level.SEVERE, "[DomainAnalytics] Failed to connect to data storage. Disabling...");
            return;
        }

        commandHandler = new CommandHandler(dataStorage);
        eventExecutor = new EventExecutor(dataStorage);

        registerCommand();
        registerEvent();
    }

    public final void disable() {
        dataStorage.close();
        AsyncHelper.executor().shutdownNow();
    }
}
