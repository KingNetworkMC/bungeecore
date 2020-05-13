package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import net.md_5.bungee.api.connection.*;
import ru.alexeylesin.kingbungeecore.*;
import ru.alexeylesin.kingbungeecore.utils.*;
import net.md_5.bungee.api.*;

public class MessageCommand extends Command
{
    public MessageCommand() {
        super("msg", (String)null, new String[] { "message", "m", "t", "tell" });
    }
    
    public void execute(final CommandSender sender, final String[] args) {
        if (sender instanceof ProxiedPlayer) {
            if (args.length > 1) {
                final ProxiedPlayer msgSender = (ProxiedPlayer)sender;
                final ProxiedPlayer player = Main.getInstance().getProxy().getPlayer(args[0]);
                if (player == null || player.getServer() == null) {
                    msgSender.sendMessage(Main.getInstance().getConfig().getString("Messages.NoOnline").replace("&", "§"));
                }
                else {
                    final StringBuilder builder = new StringBuilder();
                    for (int i = 1; i < args.length; ++i) {
                        builder.append(args[i]).append(' ');
                    }
                    final String msg = builder.toString();
                    MessageUtil.sendMessage(msgSender, player, msg);
                }
            }
            else {
                sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Message.Usage").replace("&", "§"));
            }
        }
        else {
            sender.sendMessage(ChatColor.RED + "This command only for players!");
        }
    }
}
