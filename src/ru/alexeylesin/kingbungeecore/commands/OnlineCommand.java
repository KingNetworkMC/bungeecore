package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import ru.alexeylesin.kingbungeecore.*;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.config.*;

public class OnlineCommand extends Command
{
    public OnlineCommand() {
        super("online", (String)null, new String[] { "glist" });
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        if (args.length == 1) {
            if (Main.getInstance().getProxy().getServerInfo(args[0]) == null) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Server.NoExists").replace("&", "§"));
            }
            else {
                final ServerInfo server = ProxyServer.getInstance().getServerInfo(args[0]);
                final int online = ProxyServer.getInstance().getServerInfo(args[0]).getPlayers().size();
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Online.Another").replace("&", "§").replace("{server}", server.getName()).replace("{online}", String.valueOf(online)));
            }
        }
        else {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Online.Online").replace("&", "§").replace("{online}", String.valueOf(ProxyServer.getInstance().getOnlineCount())));
        }
    }
}
