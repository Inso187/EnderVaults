package me.sin.endervaults.Utils;

import me.sin.endervaults.Core;
import me.sin.endervaults.Objects.Reward;
import me.sin.endervaults.Threads.RotateArmorStand;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.SkullMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Utils {
    public static List<Reward> selectRandomElements(List<Reward> list, int amount) {
        if(list.size() <= amount) {
            return list;
        }
        List<Reward> choosen_rewards = new ArrayList<>();
        for(int i = 0; i < amount;i++) {
            Random rand = new Random();
            Reward reward = list.get(rand.nextInt(list.size()));
            if(choosen_rewards.contains(reward)) {
                reward = getRandom(list);
            }
            choosen_rewards.add(reward);
        }
        return choosen_rewards;
    }
    public static Reward getRandom(List<Reward> given) {
        Random rand = new Random();
        return given.get(rand.nextInt(given.size()));
    }
    public static String getWithCommas(List<String> list, String main, String comma) {
        if(list.size() == 1) {
             return ChatColor.translateAlternateColorCodes('&', list.get(0));
        } else {
            String toRet = "";
            int time = 0;
            for(String string : list) {
                if(time == 0) {
                    toRet = toRet + main + string+ comma;
                } else {
                    toRet = toRet + ", " + main + string;
                }
            }
            return ChatColor.translateAlternateColorCodes('&', toRet);
        }
    }
}
