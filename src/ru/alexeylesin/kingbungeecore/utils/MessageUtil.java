package ru.alexeylesin.kingbungeecore.utils;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import java.util.HashMap;
import java.util.List;
import java.util.Set;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.ClickEvent;
import net.md_5.bungee.api.chat.HoverEvent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import ru.alexeylesin.kingbungeecore.Main;

public class MessageUtil {
  public static Set<String> ignoreAll = Sets.newHashSet();
  
  private static HashMap<String, String> msgs = Maps.newHashMap();
  
  public static HashMap<String, List<String>> ignore = Maps.newHashMap();
  
  public static void sendMessage(ProxiedPlayer sender, ProxiedPlayer recivier, String message) {
    if (ignoreAll.contains(recivier.getName().toLowerCase())) {
      sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Ignore.Target")
          .replace("&", "§"));
      return;
    } 
    if (isIgnore(recivier.getName().toLowerCase(), sender.getName().toLowerCase())) {
      sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Ignore.Target")
          .replace("&", "§"));
      return;
    } 
    if (sender.getName().toLowerCase().equals(recivier.getName().toLowerCase())) {
      sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Message.Yourself")
          .replace("&", "§"));
      return;
    } 
    final TextComponent text = new TextComponent(Main.getInstance().getConfig().getString("Messages.Message.To").replace("&", "§").replace("{target}", recivier.getDisplayName()).replace("{from}", sender.getDisplayName()).replace("{message}", message));
    text.setHoverEvent(new HoverEvent(HoverEvent.Action.SHOW_TEXT, new BaseComponent[] { (new TextComponent("" + sender
              .getDisplayName()))
            .duplicate() }));
    text.setClickEvent(new ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/msg " + sender.getName() + " "));
    sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Message.From")
        .replace("&", "§")
        .replace("{target}", recivier.getDisplayName())
        .replace("{from}", sender.getDisplayName())
        .replace("{message}", message));
    recivier.sendMessage(text.duplicate());
    msgs.put(recivier.getName().toLowerCase(), sender.getName().toLowerCase());
  }
  
  public static void ignore(String player, String ignores) {
    if (player.toLowerCase().equals(ignores.toLowerCase())) {
      ProxyServer.getInstance().getPlayer(player).sendMessage(Main.getInstance().getConfig().getString("Messages.Ignore.Yourself")
          .replace("&", "§"));
    } else if (!isIgnore(player, ignores)) {
      List<String> list = ignore.get(player.toLowerCase());
      list.add(ignores);
      ignore.put(player.toLowerCase(), list);
      ProxyServer.getInstance().getPlayer(player).sendMessage(Main.getInstance().getConfig().getString("Messages.Ignore.Ignore")
          .replace("&", "§")
          .replace("{player}", ignores));
    } else {
      List<String> list = ignore.get(player.toLowerCase());
      list.remove(ignores);
      ignore.put(player.toLowerCase(), list);
      ProxyServer.getInstance().getPlayer(player).sendMessage(Main.getInstance().getConfig().getString("Messages.Ignore.UnIgnore")
          .replace("&", "§")
          .replace("{player}", ignores));
    } 
  }
  
  private static boolean isIgnore(String player, String ignores) {
    if (!ignore.containsKey(player.toLowerCase()))
      return false; 
    List<String> ignored = ignore.get(player.toLowerCase());
    return ignored.contains(ignores.toLowerCase());
  }
}
