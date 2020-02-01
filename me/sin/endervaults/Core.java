package me.sin.endervaults;

import me.sin.endervaults.Objects.Hologram;
import me.sin.endervaults.Objects.MonthlyCrate;
import me.sin.endervaults.Objects.Particle;
import me.sin.endervaults.Objects.Reward;
import me.sin.endervaults.Utils.MLogger;
import me.sin.endervaults.Utils.nbt.NBTItem;
import me.sin.endervaults.Utils.particle.ParticleEffects;

import org.bukkit.*;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.plugin.java.JavaPlugin;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class Core extends JavaPlugin {
    private static Core instance;
    private static YamlConfiguration cnfYml;
    public static List<Location> armorsstands = new ArrayList<>();
    private static HashMap<String, MonthlyCrate> crates = new HashMap<>();
    @Override
    public void onEnable(){
        initCommands();
        initListeners();
        MLogger.log("Loading crates...");
        initCrates();
        Bukkit.getConsoleSender().sendMessage(Listener.colour("&8[&d&nEnder Vaults&8] &a&nEnabled&a!"));;
        Bukkit.getConsoleSender().sendMessage(Listener.colour("&8[&d&nEnder Vaults&8] &dThank you for buying!!"));
    }

    public static Core getInstance() {
        return instance;
    }
    public Core() {
        instance = this;
    }
    public enum AsTypes {
        GROUND,
        ITEM
    }
    public Entity spawnAmorStand(Location loc, Class name) {
        Entity ent = loc.getWorld().spawn(loc, name);
        return ent;
    }
    public void initCommands() {
        new Cmd(this);
    }
    public void initListeners() {
        getServer().getPluginManager().registerEvents(new Listener(), this);
    }
    public void initCrates() {
        if(!getDataFolder().exists()) {
            getDataFolder().mkdirs();
        }
        File crate_file = new File(getDataFolder(), "config.yml");
        if(!crate_file.exists()) {
            MLogger.log("&cCan't find config.yml, creating...");
            this.saveResource("config.yml", true);
        }
        cnfYml = YamlConfiguration.loadConfiguration(crate_file);
        if(cnfYml.getString("Crates") == null) {
            MLogger.log("&cCan't find any crates, turning off...");
            Bukkit.getPluginManager().disablePlugin(this);
        }
        //getting crate
        for(String key : cnfYml.getConfigurationSection("Crates").getKeys(false)) {
             // key = crate name
            String original_key = key;
            key = "Crates." + key;
            String stringMaterial = null;
            String stringName = null;
            String stringEndParticle = null;
            String stringStartParticle = null;
            List<String> unclore = new ArrayList<>();
            stringName = cnfYml.getString(key + ".item.name");
            stringMaterial = cnfYml.getString(key + ".item.material");
            unclore = cnfYml.getStringList(key + ".item.lore");

            stringStartParticle = cnfYml.getString(key + ".start_particles.particle_effect");
            stringEndParticle = cnfYml.getString(key + ".end_particles.particle_effect");
            String permParticle = cnfYml.getString(key + ".perm_particles.particle_effect");
            List<ParticleEffects> effects = Arrays.asList(ParticleEffects.values());
            if(stringStartParticle == null) {
                MLogger.log("&cUnknown start particle name in crate " + key);
                continue;
            }
            if(stringEndParticle == null) {
                MLogger.log("&cUnknown end particle name in crate " + key);
                continue;
            }
            if(stringName == null) {
                MLogger.log("&cUnknown item name in crate " + key);
                continue;
            }
            if(stringMaterial == null || Material.getMaterial(stringMaterial) ==null) {
                MLogger.log("&cUnknown item material in crate " + key);
                continue;
            }
            Integer selected = cnfYml.getInt(key + ".randomSelected");
            List<String> lore = new ArrayList<>();
            unclore.forEach(s -> lore.add(c(s)));
            ItemStack crate_item = new ItemStack(Material.getMaterial(stringMaterial));
            ItemMeta crate_meta = crate_item.getItemMeta();
            crate_meta.setLore(lore);
            crate_meta.setDisplayName(c(stringName));
            crate_item.setItemMeta(crate_meta);
            NBTItem crate_nbt = new NBTItem(crate_item);
            crate_nbt.setString("MonthlyCrate", original_key);
            crate_item = crate_nbt.getItem();

            //getting rewards
            List<Reward> guaranteed = new ArrayList<>();
            for(String reward_key : cnfYml.getConfigurationSection(key + ".guaranteed_rewards").getKeys(false)) {

                String guaranted_key = key + ".guaranteed_rewards." + reward_key + ".";
                List<String> commands = cnfYml.getStringList(guaranted_key + "commands");
                Sound sound = Sound.valueOf(cnfYml.getString(guaranted_key + "sound"));
                Hologram hologram = new Hologram(Material.valueOf(cnfYml.getString(guaranted_key + "hologram.material_to_show")), cnfYml.getBoolean(guaranted_key + "hologram.enchanted"), cnfYml.getString(guaranted_key + "hologram.text"));
                Particle particle = new Particle(cnfYml.getDouble(guaranted_key + "particles.amount"), cnfYml.getDouble(guaranted_key + "particles.speed"), ParticleEffects.valueOf(cnfYml.getString(guaranted_key + "particles.particle_effect")));
                Reward reward = new Reward(commands, particle,hologram,sound);
                guaranteed.add(reward);
            }
            List<Reward> random = new ArrayList<>();
            for(String reward_key : cnfYml.getConfigurationSection(key + ".random_selected").getKeys(false)) {

                String guaranted_key = key + ".random_selected." + reward_key + ".";
                List<String> commands = cnfYml.getStringList(guaranted_key + "commands");
                Sound sound = Sound.valueOf(cnfYml.getString(guaranted_key + "sound"));
                Hologram hologram = new Hologram(Material.valueOf(cnfYml.getString(guaranted_key + "hologram.material_to_show")), cnfYml.getBoolean(guaranted_key + "hologram.enchanted"), cnfYml.getString(guaranted_key + "hologram.text"));Particle particle = new Particle(cnfYml.getDouble(guaranted_key + "particles.amount"), cnfYml.getDouble(guaranted_key + "particles.speed"), ParticleEffects.valueOf(cnfYml.getString(guaranted_key + "particles.particle_effect")));
                Reward reward = new Reward(commands, particle,hologram,sound);
                random.add(reward);
            }
            Particle start_particles = new Particle(cnfYml.getDouble(key + ".start_particles.amount"), cnfYml.getDouble(key + ".start_particles.speed"), ParticleEffects.valueOf(stringStartParticle.toUpperCase()));
            Particle end_particles = new Particle(cnfYml.getDouble(key + ".end_particles.amount"), cnfYml.getDouble(key + ".end_particles.speed"), ParticleEffects.valueOf(stringEndParticle.toUpperCase()));
            Particle perm_particles = new Particle(cnfYml.getDouble(key + ".perm_particles.amount"), cnfYml.getDouble(key + ".perm_particles.speed"), ParticleEffects.valueOf(permParticle.toUpperCase()));
            Sound start_sound = Sound.valueOf(cnfYml.getString(key + ".start_sound"));
            Sound end_sound = Sound.valueOf(cnfYml.getString(key + ".end_sound"));
            MonthlyCrate crate = new MonthlyCrate(guaranteed, random,crate_item,start_particles,end_particles, selected,start_sound,end_sound, perm_particles);
            crates.put(original_key, crate);
        }
        if(crates.size() == 1) {
            MLogger.log("Loaded " + crates.size() + " crate!");
        } else {
            MLogger.log("Loaded " + crates.size() + " crates!");
        }
        MLogger.log("Loaded " + crates.size() + " crates!");

    }
    String c(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

    public HashMap<String, MonthlyCrate> getCrates() {
        return crates;
    }
    @Override
    public YamlConfiguration getConfig() {
        return YamlConfiguration.loadConfiguration(new File(getDataFolder(), "config.yml"));
    }
    public void reload() {
        MLogger.log("Reloading...");
        crates.clear();
        initCrates();
    }
}