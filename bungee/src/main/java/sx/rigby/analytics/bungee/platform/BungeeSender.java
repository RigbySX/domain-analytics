package sx.rigby.analytics.bungee.platform;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.CommandSender;
import sx.rigby.analytics.bungee.util.MessageParser;
import sx.rigby.analytics.common.platform.Sender;

@RequiredArgsConstructor
public class BungeeSender implements Sender {
    private final CommandSender cs;

    @Override
    public boolean hasPermission(@NonNull String permission) {
        return cs.hasPermission(permission);
    }

    @Override
    public void sendMessage(@NonNull String message) {
        cs.sendMessage(MessageParser.parse(message));
    }
}
