package ru.alexeylesin.kingbungeecore.utils;

import net.md_5.bungee.api.config.*;
import ru.alexeylesin.kingbungeecore.*;

public final class ServerUtil
{
    public static ServerInfo getServer(final String server) {
        return Main.getInstance().getProxy().getServerInfo(server);
    }
}
