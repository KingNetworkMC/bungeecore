package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.*;
import net.md_5.bungee.api.plugin.*;
import ru.alexeylesin.kingbungeecore.Main;
import ru.alexeylesin.kingbungeecore.utils.ConfigUtil;

public class BungeeCommand extends Command
{
    public BungeeCommand() {
        super("gplugins", (String)null, new String[] { "gpl", "bpl", "bplugins", "Bungee" });
    }
    
    public void execute(final CommandSender sender, final String[] args) {
    	ConfigUtil.getConfig().getString("Messages.BungeeInfo.Information").replace("&", "§");
        final StringBuilder builder = new StringBuilder();
        if (Main.getInstance().getConfig().getBoolean("Messages.BungeeInfo.ShowPlugins")) {
        builder.append("§6Информация §8| §fПлагины §7(").append(ProxyServer.getInstance().getPluginManager().getPlugins().size()).append(")§e: §a");
        for (final Plugin plugin : ProxyServer.getInstance().getPluginManager().getPlugins()) {
            builder.append(plugin.getDataFolder().getName()).append("§f, §a");
        }
        builder.toString();
        }
    }
}
