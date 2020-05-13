package ru.alexeylesin.kingbungeecore;

import net.md_5.bungee.config.*;
import ru.alexeylesin.kingbungeecore.commands.*;
import ru.alexeylesin.kingbungeecore.events.*;
import ru.alexeylesin.kingbungeecore.events.EventListener;
import ru.alexeylesin.kingbungeecore.utils.*;
import net.md_5.bungee.api.plugin.*;
import java.util.*;

public class Main extends Plugin
{
    private static Main instance;
    
    public Main() {
        Main.instance = this;
    }
    
    public Configuration getConfig() {
        return ConfigUtil.getConfig();
    }
    
    public void onEnable() {
        ConfigUtil.saveDefaultConfig();
        final boolean server = this.getConfig().getBoolean("Messages.Server.Allow");
        final boolean find = this.getConfig().getBoolean("Messages.Find.Allow");
        final boolean ping = this.getConfig().getBoolean("Messages.Ping.Allow");
        final boolean ignore = this.getConfig().getBoolean("Messages.Ignore.Allow");
        final boolean ignoreall = this.getConfig().getBoolean("Messages.IgnoreAll.Allow");
        final boolean message = this.getConfig().getBoolean("Messages.Message.Allow");
        final boolean where = this.getConfig().getBoolean("Messages.Where.Allow");
        final boolean send = this.getConfig().getBoolean("Messages.Send.Allow");
        final boolean online = this.getConfig().getBoolean("Messages.Online.Allow");
        final boolean alert = this.getConfig().getBoolean("Messages.Alert.Allow");
        final boolean hub = this.getConfig().getBoolean("Messages.Hub.Allow");
        if (alert) {
            this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new AlertCommand());
        }
        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new BungeeCommand());
        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new EndCommand());
        if (find) {
            this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new FindCommand());
        }
        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new RulesCommand());
        if (ignoreall) {
            this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new IgnoreallCommand());
        }
        if (ignore) {
            this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new IgnoreCommand());
        }
        if (message) {
            this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new MessageCommand());
        }
        if (online) {
            this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new OnlineCommand());
        }
        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new ReportCommand());
        if (send) {
            this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new SendCommand());
        }
        if (server) {
            this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new ServerCommand());
        }
        if (where) {
            this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new WhereCommand());
        }
        if (ping) {
            this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new PingCommand());
        }
        this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new ReloadCommand());
        if (hub) {
            for (final String s : ConfigUtil.getConfig().getStringList("HubAliases")) {
                this.getProxy().getPluginManager().registerCommand((Plugin)this, (Command)new HubCommand(s));
            }
        }
        this.getProxy().getPluginManager().registerListener((Plugin)this, (Listener)new EventListener());
    }
    
    public static Main getInstance() {
        return Main.instance;
    }
}
