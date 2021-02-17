package sx.rigby.analytics.bungee.impl;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.event.PreLoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import sx.rigby.analytics.bungee.platform.BungeeEventWrapper;
import sx.rigby.analytics.common.event.EventExecutor;

@RequiredArgsConstructor
public class AnalyticsBungeeListener implements Listener {
    private final EventExecutor executor;

    @EventHandler
    public void onLogin(PreLoginEvent event) {
        BungeeEventWrapper wrapped = new BungeeEventWrapper(event);
        executor.execute(wrapped);
    }
}
