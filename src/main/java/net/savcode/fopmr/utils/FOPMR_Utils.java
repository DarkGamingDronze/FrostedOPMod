package net.savcode.fopmr.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class FOPMR_Utils {
    
    public static int bcastMsg(String message) {
        return Bukkit.broadcastMessage(message);
    }

    public static int bcastMsg(String message, ChatColor color) {
        return Bukkit.broadcastMessage((color == null ? "" : color) + message);
    }

    public static String color(String string) {
        return ChatColor.translateAlternateColorCodes('&', string);
    }     
}
