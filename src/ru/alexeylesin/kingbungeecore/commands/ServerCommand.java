package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.api.*;
import ru.alexeylesin.kingbungeecore.*;
import ru.alexeylesin.kingbungeecore.utils.*;

public class ServerCommand extends Command
{
    public ServerCommand() {
        super("server", (String)null, new String[] { "serv" });
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatColor.RED + "This command only for players!");
            return;
        }
        if (!sender.hasPermission("kingbungeecore.server")) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
            return;
        }
        if (args.length == 0) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Server.Usage").replace("&", "§"));
            return;
        }
        if (ProxyServer.getInstance().getServerInfo(args[0]) == null) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Server.NoExists").replace("&", "§").replace("{server}", args[0]));
            return;
        }
        if (((ProxiedPlayer)sender).getServer().getInfo().getName().toLowerCase().equals(args[0].toLowerCase())) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Server.AlreadyConnected").replace("&", "§").replace("{server}", args[0]));
            return;
        }
        sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Server.Server").replace("&", "§").replace("{server}", args[0]));
        ((ProxiedPlayer)sender).connect(ServerUtil.getServer(args[0]));
    }
}
