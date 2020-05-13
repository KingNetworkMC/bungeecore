package ru.alexeylesin.kingbungeecore.events;

import com.google.common.collect.Maps;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;
import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.event.ProxyPingEvent;
import net.md_5.bungee.api.event.ServerConnectedEvent;
import net.md_5.bungee.api.event.ServerKickEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;
import ru.alexeylesin.kingbungeecore.Main;
import ru.alexeylesin.kingbungeecore.utils.ConfigUtil;
import ru.alexeylesin.kingbungeecore.utils.MessageUtil;
import ru.alexeylesin.kingbungeecore.utils.ServerUtil;

public class EventListener implements Listener {
  private Map<UUID, Integer> map = Maps.newHashMap();
  
  private int number = 0;
  
  private List<String> list = Main.getInstance().getConfig().getStringList("Motd.List");
  
  @EventHandler
  public void onJoin(ServerConnectedEvent e) {
    if (!MessageUtil.ignore.containsKey(e.getPlayer().getName().toLowerCase()))
      MessageUtil.ignore.put(e.getPlayer().getName().toLowerCase(), new ArrayList()); 
    if (Main.getInstance().getConfig().getBoolean("TabList.Allow"))
      e.getPlayer().setTabHeader((BaseComponent)new TextComponent(Main.getInstance().getConfig().getString("TabList.header")
            .replace("&", "§")
            .replace("{player}", e.getPlayer().getName())
            .replace("{server}", e.getServer().getInfo().getName())
            .replace("{ping}", String.valueOf(e.getPlayer().getPing()))
            .replace("{newline}", "\n")), (BaseComponent)new TextComponent(
            
            Main.getInstance()
            .getConfig().getString("TabList.footer")
            .replace("&", "§")
            .replace("{player}", e.getPlayer().getName())
            .replace("{server}", e.getServer().getInfo().getName())
            .replace("{ping}", String.valueOf(e.getPlayer().getPing()))
            .replace("{newline}", "\n"))); 
  }
  
  @EventHandler
  public void onPing(ProxyPingEvent e) {
    if (Main.getInstance().getConfig().getBoolean("Motd.Allow") && 
      Main.getInstance().getConfig().getBoolean("Motd.Random")) {
      int i = (new Random()).nextInt(this.list.size());
      e.getResponse().setDescription(((String)this.list.get(i))
          .replace("&", "§")
          .replace("{newline}", "\n"));
    } else {
      if (this.number > this.list.size() - 1)
        this.number -= this.list.size(); 
      this.map.clear();
      this.map.put(e.getConnection().getUniqueId(), Integer.valueOf(this.number));
      e.getResponse().setDescription(((String)this.list.get(((Integer)this.map.get(e.getConnection().getUniqueId())).intValue()))
          .replace("&", "§")
          .replace("{newline}", "\n"));
      this.number++;
    } 
  }
  
  @EventHandler
  public void onKick(ServerKickEvent e) {
    e.setCancelled(true);
    List<String> games = ConfigUtil.getConfig().getStringList("GameArenas");
    List<String> hubs = ConfigUtil.getConfig().getStringList("Lobbys");
    ProxiedPlayer player = e.getPlayer();
    if (e.getPlayer().getServer() != null)
      if (games.contains(player.getServer().getInfo().getName())) {
        if (ServerUtil.getServer(hubs.get(hubs.size() - 1)) == null) {
          e.setCancelServer(ServerUtil.getServer(ConfigUtil.getConfig().getString("Limbo")));
          player.sendMessage(ConfigUtil.getConfig().getString("Messages.Hub.LimboKick")
              .replace("&", "§")
              .replace("{reason}", e.getKickReason()));
          return;
        } 
        for (String hub : hubs) {
          if (ServerUtil.getServer(hub) != null) {
            player.connect(ServerUtil.getServer(hub));
            return;
          } 
        } 
      } else {
        e.setCancelServer(ServerUtil.getServer(ConfigUtil.getConfig().getString("Limbo")));
        player.sendMessage(ConfigUtil.getConfig().getString("Messages.Hub.LimboKick")
            .replace("&", "§")
            .replace("{reason}", e.getKickReason()));
      }  
  }
}
