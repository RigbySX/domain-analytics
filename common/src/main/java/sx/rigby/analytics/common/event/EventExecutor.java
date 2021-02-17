package sx.rigby.analytics.common.event;

import lombok.RequiredArgsConstructor;
import sx.rigby.analytics.common.platform.EventWrapper;
import sx.rigby.analytics.common.storage.DataStorage;
import sx.rigby.analytics.common.util.AsyncHelper;

import java.util.UUID;

@RequiredArgsConstructor
public class EventExecutor {
    private final DataStorage dataStorage;

    public void execute(EventWrapper wrapper) {
        UUID uniqueId = wrapper.getUniqueId();
        String hostname = wrapper.getHostname();
        this.handle(uniqueId, hostname);
    }

    private void handle(UUID uniqueId, String hostname) {
        AsyncHelper.executor().submit(() ->
                dataStorage.insert(hostname, uniqueId));
    }
}
