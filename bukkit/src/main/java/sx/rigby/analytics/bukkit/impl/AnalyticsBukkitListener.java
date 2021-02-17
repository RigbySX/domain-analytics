package sx.rigby.analytics.bukkit.impl;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerLoginEvent;
import sx.rigby.analytics.bukkit.platform.BukkitEventWrapper;
import sx.rigby.analytics.common.event.EventExecutor;

@RequiredArgsConstructor
public class AnalyticsBukkitListener implements Listener {
    private final EventExecutor executor;

    @EventHandler
    public void onLogin(PlayerLoginEvent event) {
        BukkitEventWrapper wrapped = new BukkitEventWrapper(event);
        executor.execute(wrapped);
    }
}
