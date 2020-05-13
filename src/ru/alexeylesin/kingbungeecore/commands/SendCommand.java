package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import ru.alexeylesin.kingbungeecore.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.config.*;

public class SendCommand extends Command
{
    public SendCommand() {
        super("send");
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        String displayName = sender.getName();
        if (sender instanceof ProxiedPlayer) {
            displayName = ((ProxiedPlayer)sender).getDisplayName();
        }
        if (!sender.hasPermission("kingbungeecore.send")) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
            return;
        }
        if (args.length != 2) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Send.Usage").replace("&", "§"));
        }
        else {
            final ProxiedPlayer player = ProxyServer.getInstance().getPlayer(args[0]);
            if (player == null || player.getServer() == null) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoOnline").replace("&", "§"));
            }
            else if (ProxyServer.getInstance().getServerInfo(args[1]) == null) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Server.NoExists").replace("&", "§"));
            }
            else {
                final ServerInfo server = ProxyServer.getInstance().getServerInfo(args[1]);
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Send.From").replace("&", "§").replace("{player}", player.getName()).replace("{server}", server.getName()));
                player.sendMessage(Main.getInstance().getConfig().getString("Messages.Send.To").replace("&", "§").replace("{sender}", sender.getName()).replace("{server}", server.getName()));
                player.connect(server);
            }
        }
    }
}
