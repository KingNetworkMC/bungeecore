package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import ru.alexeylesin.kingbungeecore.*;
import net.md_5.bungee.api.*;

public class FindCommand extends Command
{
    public FindCommand() {
        super("find", (String)null, new String[] { "search" });
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        if (!sender.hasPermission("kingbungeecore.find")) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
            return;
        }
        if (args.length == 0) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Find.Usage").replace("&", "§"));
        }
        else if (Main.getInstance().getProxy().getPlayer(args[0]) != null) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Find.Find").replace("&", "§").replace("{player}", args[0]).replace("{server}", Main.getInstance().getProxy().getPlayer(args[0]).getServer().getInfo().getName()));
        }
        else {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoOnline").replace("&", "§"));
        }
    }
}
