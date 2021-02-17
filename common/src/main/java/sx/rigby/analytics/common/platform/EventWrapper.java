package sx.rigby.analytics.common.platform;

import java.util.UUID;

public interface EventWrapper {
    UUID getUniqueId();

    String getHostname();
}
