package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import ru.alexeylesin.kingbungeecore.*;
import net.md_5.bungee.api.*;

public class EndCommand extends Command
{
    public EndCommand() {
        super("end");
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        if (!sender.hasPermission("kingbungeecore.end")) {
            Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§");
        }
        else {
            if (!sender.hasPermission("kingbungeecore.end")) {
                Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§");
                return;
            }
            Main.getInstance().getProxy().stop();
        }
    }
}
