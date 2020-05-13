package ru.alexeylesin.kingbungeecore.commands;

import net.md_5.bungee.api.plugin.*;
import ru.alexeylesin.kingbungeecore.*;
import net.md_5.bungee.api.*;

public class ReportCommand extends Command
{
    public ReportCommand() {
        super("report", (String)null, new String[0]);
    }
    
    public void execute(final CommandSender sender, final String[] args) {
    	if (Main.getInstance().getConfig().getBoolean("Messages.Report.Allow")) {
        sender.sendMessage(Main.getInstance().getConfig().getString("Messages.Report.Message").replace("&", "§"));
    	}
    }
}
