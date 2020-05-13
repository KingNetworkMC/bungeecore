package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import ru.alexeylesin.kingbungeecore.*;
import net.md_5.bungee.api.*;
import net.md_5.bungee.api.connection.*;
import net.md_5.bungee.*;
import net.md_5.bungee.api.chat.*;
import java.util.*;

public class AlertCommand extends Command
{
    public AlertCommand() {
        super("alert", (String)null, new String[] { "bc", "broadcast" });
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        if (!sender.hasPermission("kingbungeecore.alert")) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoPermissions").replace("&", "§"));
            return;
        }
        if (args.length == 0) {
            sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Alert.Usage").replace("&", "§"));
        }
        else {
            final StringBuilder msg = new StringBuilder();
            for (final String arg : args) {
                msg.append(arg + " ");
            }
            for (final ProxiedPlayer players : Main.getInstance().getProxy().getPlayers()) {
                players.sendTitle(new BungeeTitle().title((BaseComponent)new TextComponent(Main.getInstance().getConfig().getString("Messages.Alert.Title.header").replace("&", "§").replace("{message}", msg.toString().replace("&", "§")))).subTitle((BaseComponent)new TextComponent(Main.getInstance().getConfig().getString("Messages.Alert.Title.footer").replace("&", "§").replace("{message}", msg.toString().replace("&", "§")))));
                players.sendMessage(Main.getInstance().getConfig().getString("Messages.Alert.Chat").replace("&", "§").replace("{message}", msg.toString().replace("&", "§")));
            }
        }
    }
}
