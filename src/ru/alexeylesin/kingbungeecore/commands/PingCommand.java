package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import ru.alexeylesin.kingbungeecore.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.api.*;

public class PingCommand extends Command
{
    public PingCommand() {
        super("ping", (String)null, new String[0]);
    }
    
    @SuppressWarnings("deprecation")
	public void execute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatColor.RED + "This command only for players!");
            return;
        }
        if (args.length == 0) {
            Main.getInstance().getConfig().getString("Messages.Ping.Ping").replace("&", "§").replace("{ping}", String.valueOf(((ProxiedPlayer)sender).getPing()));
            return;
        }
        if (!sender.hasPermission("kingbungeecore.ping.other")) {
            Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§");
        }
        else {
            if (Main.getInstance().getProxy().getPlayer(args[0]) == null) {
                Main.getInstance().getConfig().getString("Messages.NoOnline").replace("&", "§");
                return;
            }
            Main.getInstance().getConfig().getString("Messages.Ping.Other").replace("&", "§").replace("{ping}", String.valueOf(Main.getInstance().getProxy().getPlayer(args[0]).getPing())).replace("{player}", args[0]);
        }
    }
}
