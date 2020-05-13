package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;

import java.io.*;
import net.md_5.bungee.api.*;
import net.md_5.bungee.config.*;
import ru.alexeylesin.kingbungeecore.utils.*;

public class ReloadCommand extends Command
{
    public ReloadCommand() {
        super("bereload", (String)null, new String[] { "greload", "preload", "berl", "grl", "prl" });
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        if (!sender.hasPermission("kingbungeecore.reload")) {
            sender.sendMessage(ConfigUtil.getConfig().getString("Messages.NoPermissions").replace("&", "§"));
            return;
        }
        sender.sendMessage(ConfigUtil.getConfig().getString("Messages.Reload").replace("&", "§"));
        try {
            final Configuration config = YamlConfiguration.getProvider((Class)YamlConfiguration.class).load(new File("config.yml"));
            YamlConfiguration.getProvider((Class)YamlConfiguration.class).save(config, new File("config.yml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        ConfigUtil.reloadConfig();
        ProxyServer.getInstance().getPluginManager().enablePlugins();
    }
}
