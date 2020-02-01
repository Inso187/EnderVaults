package me.sin.endervaults.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class MLogger {
    public static void log(String text) {
        Bukkit.getConsoleSender().sendMessage(c("&8[&d&nEnder Vaults&8]&7 " + text));
    }
    static String c(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
}
