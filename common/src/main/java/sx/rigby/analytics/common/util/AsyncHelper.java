package sx.rigby.analytics.common.util;

import lombok.experimental.UtilityClass;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@UtilityClass
public class AsyncHelper {
    private final ExecutorService executorService = Executors.newCachedThreadPool();

    public ExecutorService executor() {
        return executorService;
    }
}
