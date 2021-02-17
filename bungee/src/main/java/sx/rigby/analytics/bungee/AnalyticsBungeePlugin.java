package sx.rigby.analytics.bungee;

import lombok.RequiredArgsConstructor;
import sx.rigby.analytics.bungee.impl.AnalyticsBungeeCommand;
import sx.rigby.analytics.bungee.impl.AnalyticsBungeeListener;
import sx.rigby.analytics.common.AnalyticsPlugin;

import java.io.File;

@RequiredArgsConstructor
public class AnalyticsBungeePlugin extends AnalyticsPlugin {
    private final AnalyticsBungeeBootstrap bootstrap;

    @Override
    protected void registerCommand() {
        AnalyticsBungeeCommand command = new AnalyticsBungeeCommand(commandHandler);
        bootstrap.getProxy().getPluginManager().registerCommand(bootstrap, command);
    }

    @Override
    protected void registerEvent() {
        AnalyticsBungeeListener listener = new AnalyticsBungeeListener(eventExecutor);
        bootstrap.getProxy().getPluginManager().registerListener(bootstrap, listener);
    }

    @Override
    protected File getDataFolder() {
        return bootstrap.getDataFolder();
    }
}
