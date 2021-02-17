package sx.rigby.analytics.common.command;

import lombok.RequiredArgsConstructor;
import sx.rigby.analytics.common.platform.Sender;
import sx.rigby.analytics.common.storage.DataStorage;
import sx.rigby.analytics.common.util.AsyncHelper;
import sx.rigby.analytics.common.util.WrappedValue;

import java.util.concurrent.CompletableFuture;

import static java.lang.String.format;

@RequiredArgsConstructor
public class CommandHandler {
    private static final String PERMISSION = "analytics.use";
    private static final String NO_PERM_MSG = "&cYou do not have permission to execute this command.";
    private static final String USAGE_MSG = "&c/da <hostname>";
    private static final String OUTPUT_MSG = "Stats for %s\nUnique: %s\nTotal: %s";

    private final DataStorage dataStorage;

    public void handle(Sender sender, String[] args) {
        if (!sender.hasPermission(PERMISSION)) {
            sender.sendMessage(NO_PERM_MSG);
            return;
        }

        if (args.length != 1) {
            sender.sendMessage(USAGE_MSG);
            return;
        }

        String hostname = args[0];

        CompletableFuture<WrappedValue> future = CompletableFuture.supplyAsync(
                () -> new WrappedValue(dataStorage.getUniqueJoins(hostname), dataStorage.getTotalJoins(hostname)),
                AsyncHelper.executor());
        future.thenAccept((result) -> sender.sendMessage(format(OUTPUT_MSG, hostname, result.getUniqueJoins(), result.getTotalJoins())));
    }
}
