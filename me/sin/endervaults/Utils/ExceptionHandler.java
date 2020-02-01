package me.sin.endervaults.Utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ExceptionHandler {
    public static void handle(Exception ex, Class class_name) {
        send("&8--------------------------------------");
        send("");
        send("&8[&cMonthly Crates &lERROR&8] &bException: " + ex.getMessage() + ", at: " + class_name.getName());
        for(StackTraceElement ste : ex.getStackTrace()) {
            String line = ste.toString();
            if(line.contains("aq.oop.monthlycrates")) {
                send("&3" + line);
            }
        }
        send("");
        send("&8--------------------------------------");
    }
    static void send(String text) {
        Bukkit.getConsoleSender().sendMessage(ChatColor.translateAlternateColorCodes('&', text));
    }
}
