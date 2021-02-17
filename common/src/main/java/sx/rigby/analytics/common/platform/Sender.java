package sx.rigby.analytics.common.platform;

import lombok.NonNull;

public interface Sender {

    boolean hasPermission(@NonNull String permission);

    void sendMessage(@NonNull String message);
}
