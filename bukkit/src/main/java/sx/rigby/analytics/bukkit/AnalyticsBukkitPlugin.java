package sx.rigby.analytics.bukkit;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.PluginCommand;
import sx.rigby.analytics.bukkit.impl.AnalyticsBukkitCommand;
import sx.rigby.analytics.bukkit.impl.AnalyticsBukkitListener;
import sx.rigby.analytics.common.AnalyticsPlugin;

import java.io.File;

@RequiredArgsConstructor
public class AnalyticsBukkitPlugin extends AnalyticsPlugin {
    private final AnalyticsBukkitBootstrap bootstrap;

    @Override
    protected void registerCommand() {
        PluginCommand command = bootstrap.getCommand("da");
        if (command != null) {
            AnalyticsBukkitCommand analyticsBukkitCommand = new AnalyticsBukkitCommand(commandHandler);
            command.setExecutor(analyticsBukkitCommand);
        }
    }

    @Override
    protected void registerEvent() {
        AnalyticsBukkitListener listener = new AnalyticsBukkitListener(eventExecutor);
        bootstrap.getServer().getPluginManager().registerEvents(listener, bootstrap);
    }

    @Override
    protected File getDataFolder() {
        return bootstrap.getDataFolder();
    }
}
