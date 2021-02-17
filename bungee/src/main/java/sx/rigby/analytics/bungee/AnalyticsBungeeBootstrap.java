package sx.rigby.analytics.bungee;

import net.md_5.bungee.api.plugin.Plugin;

public class AnalyticsBungeeBootstrap extends Plugin {
    private final AnalyticsBungeePlugin plugin;

    public AnalyticsBungeeBootstrap() {
        this.plugin = new AnalyticsBungeePlugin(this);
    }

    @Override
    public void onEnable() {
        plugin.enable();
    }

    @Override
    public void onDisable() {
        plugin.disable();
    }
}
