package sx.rigby.analytics.bukkit.impl;

import lombok.RequiredArgsConstructor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import sx.rigby.analytics.bukkit.platform.BukkitSender;
import sx.rigby.analytics.common.command.CommandHandler;

@RequiredArgsConstructor
public class AnalyticsBukkitCommand implements CommandExecutor {
    private final CommandHandler handler;

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String label, String[] args) {
        BukkitSender sender = new BukkitSender(commandSender);
        handler.handle(sender, args);
        return true;
    }
}
