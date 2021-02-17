package sx.rigby.analytics.bukkit.platform;

import lombok.RequiredArgsConstructor;
import org.bukkit.event.player.PlayerLoginEvent;
import sx.rigby.analytics.common.platform.EventWrapper;
import sx.rigby.analytics.common.util.StringUtil;

import java.util.UUID;

@RequiredArgsConstructor
public class BukkitEventWrapper implements EventWrapper {
    private final PlayerLoginEvent event;

    @Override
    public UUID getUniqueId() {
        return event.getPlayer().getUniqueId();
    }

    @Override
    public String getHostname() {
        return StringUtil.parse(event.getHostname());
    }
}
