package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.api.*;
import ru.alexeylesin.kingbungeecore.*;
import ru.alexeylesin.kingbungeecore.utils.*;

import java.util.*;

public class HubCommand extends Command
{
    public HubCommand(final String name) {
        super("hub", (String)null, new String[] { name });
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatColor.RED + "This command only for players!");
        }
        else {
            final List<String> hubs = (List<String>)ConfigUtil.getConfig().getStringList("Hubs");
            boolean b = false;
            for (final String hub : hubs) {
                if (ServerUtil.getServer(hub) == null) {
                    b = true;
                    return;
                }
            }
            if (b) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Hub.NeDostupnoNiOdnogo").replace("&", "§"));
                return;
            }
            if (hubs.contains(((ProxiedPlayer)sender).getServer().getInfo().getName())) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Hub.Already").replace("&", "§"));
                return;
            }
            final String hub2 = hubs.get(new Random().nextInt(hubs.size()));
            ((ProxiedPlayer)sender).connect(ServerUtil.getServer(hub2));
        }
    }
}
