package ru.alexeylesin.kingbungeecore.utils;

import java.nio.file.*;
import java.io.*;
import net.md_5.bungee.config.*;
import ru.alexeylesin.kingbungeecore.*;

public final class ConfigUtil
{
    private static Configuration config;
    
    public static void saveDefaultConfig() {
        try {
            if (!Main.getInstance().getDataFolder().exists()) {
                Main.getInstance().getDataFolder().mkdir();
            }
            final File file = new File(Main.getInstance().getDataFolder(), "config.yml");
            if (!file.exists()) {
                Files.copy(Main.getInstance().getResourceAsStream("config.yml"), file.toPath(), new CopyOption[0]);
            }
            loadConfig();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void reloadConfig() {
        loadConfig();
    }
    
    public static void loadConfig() {
        try {
            ConfigUtil.config = ConfigurationProvider.getProvider((Class)YamlConfiguration.class).load(new File(Main.getInstance().getDataFolder(), "config.yml"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static Configuration getConfig() {
        return ConfigUtil.config;
    }
}
