package me.sin.endervaults;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.sin.endervaults.Utils.Utils;
import me.sin.endervaults.Utils.cmd.AbstractCommand;
import me.sin.endervaults.Utils.cmd.Arguments;
import me.sin.endervaults.Utils.cmd.Sender;
import me.sin.endervaults.Utils.cmd.info.Command;

@Command(name = "endervaults", aliases = "ev", permission = "endervaults.give")
public class Cmd extends AbstractCommand {
    public Cmd(Core pl) {
        super(pl);
    }

    @Override
    public void execute(Sender sender, Arguments args) {
        if(args.length == 0) {
        	
//        	String stringPlayerName = args.get(0);
//       	    Player p = Bukkit.getPlayer(stringPlayerName);
        	
        	if (sender.hasPermission("endervaults.admin")) {
        	sender.sendMessage(cl("&r"));
            sender.sendMessage(cl("&d&l(!) &d&nEnder Vaults&d:"));
            sender.sendMessage(cl("&d&l * &7/endervaults give [crate] [player] [amount] &d- &7Gives a player an EnderVault."));
            sender.sendMessage(cl("&r"));
            sender.sendMessage(cl("&d&l * &7/endervaults reload &d- &7Reloads the plugin."));
            sender.sendMessage(cl("&r"));
            List<String> names = new ArrayList<>(Core.getInstance().getCrates().keySet());
            sender.sendMessage(cl("&d&nCrates&d:&a " + Utils.getWithCommas(names, "&a", "&7")));
            sender.sendMessage(cl("&r"));
        	} 
        	
        } else if(args.length == 1) {
//        	if (sender.hasPermission("endervaults.reload")) {
            if(args.get(0).equalsIgnoreCase("reload")) {
            	
            	FileConfiguration config = Core.getInstance().getConfig();
                
            	sender.sendMessage(cl(config.getString("plugin-reloaded")));
                Core.getInstance().reload();
                return;
            }
        } else if(args.length == 3) {
            if(args.get(0).equalsIgnoreCase("give")) {
                String stringCrateName = args.get(1);
                String stringPlayerName = args.get(2);
                if(Core.getInstance().getCrates().get(stringCrateName) == null) {
                	FileConfiguration config = Core.getInstance().getConfig();
                    sender.sendMessage(cl(config.getString("unknown-crate")));
                    return;
                }
                Player p = Bukkit.getPlayer(stringPlayerName);
                if(p == null) {
                	FileConfiguration config = Core.getInstance().getConfig();
                    sender.sendMessage(cl(config.getString("unknown-player")));
                    return;
                }
                ItemStack item = Core.getInstance().getCrates().get(stringCrateName).getItem();
                p.getInventory().addItem(item);
                String message = Core.getInstance().getConfig().getString("received_message");
                message = message.replaceAll("%item_name%", item.getItemMeta().getDisplayName());
                message = message.replaceAll("%amount%", "1");
                p.sendMessage(cl(message));
            }
        } else if(args.length == 4) {
            if(args.get(0).equalsIgnoreCase("give")) {
                String stringCrateName = args.get(1);
                String stringPlayerName = args.get(2);
                String stringAmount = args.get(3);
                if(Core.getInstance().getCrates().get(stringCrateName) == null) {
                    sender.sendMessage(cl(Core.getInstance().getConfig().getString("unknown-crate")));
                    return;
                }
                Player p = Bukkit.getPlayer(stringPlayerName);
                if(p == null) {
                    sender.sendMessage(cl(Core.getInstance().getConfig().getString("unknown-player")));
                    return;
                }
                ItemStack item = Core.getInstance().getCrates().get(stringCrateName).getItem();
                for(int i = 0; i < Integer.valueOf(stringAmount);i++) {
                    p.getInventory().addItem(item);
                }
                String message = Core.getInstance().getConfig().getString("received_message");
                message = message.replaceAll("%item_name%", item.getItemMeta().getDisplayName());
                message = message.replaceAll("%amount%", stringAmount);
                p.sendMessage(cl(message));
                
                
            }
        }
    }

}

