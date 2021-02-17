package sx.rigby.analytics.bukkit.platform;

import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.bukkit.command.CommandSender;
import sx.rigby.analytics.bukkit.util.MessageParser;
import sx.rigby.analytics.common.platform.Sender;

@RequiredArgsConstructor
public class BukkitSender implements Sender {
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
