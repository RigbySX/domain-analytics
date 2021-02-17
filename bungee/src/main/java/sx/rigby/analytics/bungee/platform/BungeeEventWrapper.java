package sx.rigby.analytics.bungee.platform;

import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.event.PreLoginEvent;
import sx.rigby.analytics.common.platform.EventWrapper;
import sx.rigby.analytics.common.util.StringUtil;

import java.util.UUID;

@RequiredArgsConstructor
public class BungeeEventWrapper implements EventWrapper {
    private final PreLoginEvent event;

    @Override
    public UUID getUniqueId() {
        return event.getConnection().getUniqueId();
    }

    @Override
    public String getHostname() {
        return StringUtil.parse(event.getConnection().getVirtualHost().getHostName());
    }
}
