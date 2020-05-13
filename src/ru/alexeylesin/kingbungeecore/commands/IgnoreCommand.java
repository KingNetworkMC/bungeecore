package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.api.*;
import ru.alexeylesin.kingbungeecore.*;
import ru.alexeylesin.kingbungeecore.utils.*;

public class IgnoreCommand extends Command
{
    public IgnoreCommand() {
        super("ignore", (String)null, new String[0]);
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatColor.RED + "This command only for players!");
            return;
        }
        final ProxiedPlayer player = (ProxiedPlayer)sender;
        if (args.length != 1) {
            player.sendMessage(Main.getInstance().getConfig().getString("Messages.Ignore.Usage").replace("&", "§"));
        }
        else {
            MessageUtil.ignore(player.getName(), args[0]);
        }
    }
}
