package me.sin.endervaults.Threads;

import me.sin.endervaults.Core;
import me.sin.endervaults.Objects.MonthlyCrate;
import me.sin.endervaults.Objects.Particle;
import me.sin.endervaults.Objects.Reward;
import me.sin.endervaults.Utils.Utils;
import me.sin.endervaults.Utils.particle.ParticleEffects;

import org.bukkit.*;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.ArmorStand;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.EulerAngle;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class RotateArmorStand extends Thread {
    private ArmorStand asText;
    private ArmorStand ground;
    private Location Originalloc;
    private BukkitTask id;
    private Location particleLoc;
    private ArmorStand itemAs;
    private Player p;
    private MonthlyCrate crate;
    private boolean isRunning = false;
    List<Reward> rewards = new ArrayList<>();
    public RotateArmorStand(Location loc, MonthlyCrate crate, Player p) {
        Location groundLoc = loc.clone();
        groundLoc.add(0.5, -1.23, 0.5);
        float eyeYaw = p.getEyeLocation().getYaw() + 180;
        groundLoc.setYaw(eyeYaw);
        ArmorStand ground = (ArmorStand) Core.getInstance().spawnAmorStand(groundLoc, ArmorStand.class);
        ground.setVisible(false);
        ground.setGravity(false);
        ground.setMarker(true);
        this.p = p;

        //Skull
        ground.setHelmet(new ItemStack(crate.getMat()));
        ArmorStand reward_armorstand = (ArmorStand) Core.getInstance().spawnAmorStand(loc.clone().add(0.5,0.2,0.5), ArmorStand.class);
        reward_armorstand.setGravity(false);
        reward_armorstand.setArms(true);
        reward_armorstand.setVisible(false);
        Core.armorsstands.add(reward_armorstand.getLocation());
        reward_armorstand.setCustomNameVisible(true);
        this.asText = reward_armorstand;
        this.ground = ground;
        //item as
        Location item_loc = loc.clone().add(0.5,0,0.5);
        item_loc.add(0.3,0,0);
        ArmorStand item_armorstand = (ArmorStand) Core.getInstance().spawnAmorStand(item_loc, ArmorStand.class);
        item_armorstand.setArms(true);
        item_armorstand.setGravity(false);
        item_armorstand.setItemInHand(new ItemStack(Material.TNT));
        item_armorstand.setMarker(true);
        ground.setMarker(true);
        this.rewards = Utils.selectRandomElements(crate.getRandomSelected(),crate.getSelected());
        this.rewards.addAll(crate.getGuaranteedRewards());
        this.itemAs = item_armorstand;
        this.Originalloc = loc;
        this.particleLoc = loc.clone().add(0.5,0.2,0.5);
        crate.getStart_particle().getParticle().send(Bukkit.getOnlinePlayers(), particleLoc,0,0,0,crate.getStart_particle().getSpeed(),(int)Math.round(crate.getStart_particle().getAmount()));
        ground.getLocation().getWorld().playSound(ground.getLocation(), crate.getStart_sound(), 1.0f,1.0f);
        start();
        this.crate = crate;
    }
    @Override
    public void run() {
        isRunning = true;
        startPermParticles(particleLoc, crate.getPerm_particle());
        for(Reward reward : rewards) {
            ItemStack reward_item = new ItemStack(reward.getHologram().getMaterial());
            if(reward.getHologram().getEnchanted()) {
                ItemMeta meta = reward_item.getItemMeta();
                meta.addEnchant(Enchantment.DAMAGE_ALL, 1,true);
                reward_item.setItemMeta(meta);
            }

            asText.setCustomName(c(reward.getHologram().getText()));
            int z,y,x;
            z = itemAs.getLocation().getBlockZ();
            y = itemAs.getLocation().getBlockY();
            x = itemAs.getLocation().getBlockX();
            reward.getParticle().getParticle().send(Bukkit.getOnlinePlayers(),particleLoc,0,0,0,reward.getParticle().getSpeed(),(int)reward.getParticle().getAmount());
            ground.getLocation().getWorld().playSound(ground.getLocation(), reward.getSound(), 1.0f,1.0f);
            Thread rn = new Thread() {
                int x = -360;
                boolean first = true;
                @Override
                public void run() {
                    while (x < 0) {
                        if (x == 0) {
                            itemAs.setItemInHand(new ItemStack(Material.AIR));
                            this.interrupt();
                            return;
                        }
                        if(x == -400 && first) {
                            itemAs.getLocation().getWorld().playSound(ground.getLocation(), Sound.CLICK,1.0f, 1.0f);
                            x = x + 20;
                            itemAs.setRightArmPose(new EulerAngle(4.738200870301956, Math.toRadians(x), 0));
                            Bukkit.broadcastMessage("first");
                            first = false;
                            itemAs.setItemInHand(reward_item);
                            try {
                                sleep(15);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        } else {
                            itemAs.setVisible(false);
                            itemAs.setRightArmPose(new EulerAngle(4.738200870301956, Math.toRadians(x), 0));
                            itemAs.setBodyPose(new EulerAngle(0, Math.toRadians(x), 0));
                            itemAs.setHeadPose(new EulerAngle(0, Math.toRadians(x), 0));
                            itemAs.setItemInHand(reward_item);
                            x = x + 2;
                            try {
                                sleep(15);
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }
            };
            rn.start();
            while (rn.isAlive()) {
            }
            try {
                sleep(15);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
            continue;
        }
        isRunning = false;
        ground.getLocation().getWorld().playSound(ground.getLocation(), crate.getEnd_sound(), 1.0f,1.0f);
        crate.getEnd_particle().getParticle().send(Bukkit.getOnlinePlayers(),particleLoc,0,0,0,crate.getEnd_particle().getSpeed(),(int)crate.getEnd_particle().getAmount());
        Core.armorsstands.remove(itemAs.getLocation());
        itemAs.remove();
        ground.remove();
        asText.remove();
        //giving rewards

        for(Reward reward : rewards) {
            for(String cmd : reward.getCommands()) {
                cmd = cmd.replaceAll("%player%", p.getName());
                Bukkit.dispatchCommand(Bukkit.getConsoleSender(), cmd);
            }
        }

        this.interrupt();
        return;
    }
    public void setRotaing(Boolean bol) {
        isRunning = bol;
    }

    public boolean isRunning() {
        return isRunning;
    }
    static String c(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }
    public void startPermParticles(Location loc, Particle particle) {
        id = new BukkitRunnable() {
            @Override
            public void run() {
                if(isRunning == false) {
                    cancel();
                }
                particle.getParticle().send(Bukkit.getOnlinePlayers(), particleLoc,0,0,0,crate.getPerm_particle().getSpeed(),(int)Math.round(crate.getPerm_particle().getAmount()));
            }
        }.runTaskTimer(Core.getInstance(),0,10);
    }
}
