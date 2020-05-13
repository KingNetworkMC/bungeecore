package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import ru.alexeylesin.kingbungeecore.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.api.*;

public class WhereCommand extends Command
{
    public WhereCommand() {
        super("where", (String)null, new String[] { "whe" });
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        if (!(sender instanceof ProxiedPlayer)) {
            sender.sendMessage(ChatColor.RED + "This command only for players!");
        }
        else {
            if (!sender.hasPermission("kingbungeecore.where")) {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
                return;
            }
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Where.Where").replace("&", "§").replace("{server}", ((ProxiedPlayer)sender).getServer().getInfo().getName()));
        }
    }
}
