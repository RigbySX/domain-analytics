package sx.rigby.analytics.bukkit;

import org.bukkit.plugin.java.JavaPlugin;

public class AnalyticsBukkitBootstrap extends JavaPlugin {
    private final AnalyticsBukkitPlugin plugin;

    public AnalyticsBukkitBootstrap() {
        this.plugin = new AnalyticsBukkitPlugin(this);
    }

    @Override
    public void onDisable() {
        plugin.disable();
    }

    @Override
    public void onEnable() {
        plugin.enable();
    }
}
