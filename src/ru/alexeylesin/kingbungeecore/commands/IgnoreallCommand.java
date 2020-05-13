package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.api.*;
import ru.alexeylesin.kingbungeecore.*;
import ru.alexeylesin.kingbungeecore.utils.*;

public class IgnoreallCommand extends Command
{
    public IgnoreallCommand() {
        super("ignoreall");
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatColor.RED + "This command only for players!");
            return;
        }
        final ProxiedPlayer player = (ProxiedPlayer)sender;
        if (MessageUtil.ignoreAll.contains(player.getName().toLowerCase())) {
            MessageUtil.ignoreAll.remove(player.getName().toLowerCase());
            player.sendMessage(Main.getInstance().getConfig().getString("Messages.IgnoreAll.UnIgnore").replace("&", "§"));
        }
        else {
            MessageUtil.ignoreAll.add(player.getName().toLowerCase());
            player.sendMessage(Main.getInstance().getConfig().getString("Messages.IgnoreAll.Ignore").replace("&", "§"));
        }
    }
}
