package sx.rigby.analytics.bungee.impl;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import sx.rigby.analytics.bungee.platform.BungeeSender;
import sx.rigby.analytics.common.command.CommandHandler;

public class AnalyticsBungeeCommand extends Command {
    private final CommandHandler handler;

    public AnalyticsBungeeCommand(CommandHandler handler) {
        super("da");
        this.handler = handler;
    }

    @Override
    public void execute(CommandSender commandSender, String[] args) {
        BungeeSender sender = new BungeeSender(commandSender);
        handler.handle(sender, args);
    }
}
