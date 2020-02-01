package me.sin.endervaults;

import me.sin.endervaults.Objects.MonthlyCrate;
import me.sin.endervaults.Threads.RotateArmorStand;
import me.sin.endervaults.Utils.Utils;
import me.sin.endervaults.Utils.nbt.NBTItem;
import me.sin.endervaults.Utils.particle.ParticleEffects;

import org.bukkit.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerChatTabCompleteEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;

public class Listener implements org.bukkit.event.Listener {
    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if(e.isCancelled()){
            return;
        }
        NBTItem nbt = new NBTItem(e.getItemInHand());
        if(nbt.hasKey("MonthlyCrate")) {
            e.getPlayer().getInventory().remove(e.getItemInHand());
            e.setCancelled(true);
            MonthlyCrate crate = Core.getInstance().getCrates().get(nbt.getString("MonthlyCrate"));
            RotateArmorStand RAS = new RotateArmorStand(e.getBlock().getLocation(), crate, e.getPlayer());
        }
    }
    @EventHandler
    public void onIntereact(PlayerInteractAtEntityEvent event) {
        if(Core.armorsstands.contains(event.getRightClicked().getLocation())) {
            event.setCancelled(true);
        }
    }

public static String colour(String colour)
{
  return ChatColor.translateAlternateColorCodes('&', colour);
}

public static ChatColor get(int minute) {
	// TODO Auto-generated method stub
	return null;
}
}
