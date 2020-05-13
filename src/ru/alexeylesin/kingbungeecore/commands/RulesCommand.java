package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import ru.alexeylesin.kingbungeecore.*;
import net.md_5.bungee.api.*;

public class RulesCommand extends Command
{
    public RulesCommand() {
        super("rules");
    }
    
    public void execute(final CommandSender sender, final String[] args) {
    	if (Main.getInstance().getConfig().getBoolean("Messages.Rules.Allow")) {
        sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Rules.Message").replace("&", "§"));
    	}
    }
}
