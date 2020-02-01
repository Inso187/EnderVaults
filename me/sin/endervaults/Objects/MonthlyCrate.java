package me.sin.endervaults.Objects;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.inventory.ItemStack;

import me.sin.endervaults.Utils.particle.ParticleEffects;

import java.util.ArrayList;
import java.util.List;

public class MonthlyCrate {
    private List<Reward> guaranteed_rewards = new ArrayList<>();
    private Particle perm_particle;
    private Particle start_particle;
    private Particle end_particle;
    private List<Reward> random_selected_rewards = new ArrayList<>();
    private ItemStack item;
    private int selected;
    private Sound start_sound;
    private Sound end_sound;
    private Material mat;

    public MonthlyCrate(List<Reward> guaranteed_rewards, List<Reward> random_selected_rewards, ItemStack item, Particle start_particle, Particle end_particle, Integer selected, Sound start_sound,Sound end_sound, Particle perm) {
        this.guaranteed_rewards = guaranteed_rewards;
        this.item = item;
        this.random_selected_rewards = random_selected_rewards;
        this.end_particle = end_particle;
        this.start_particle = start_particle;
        this.selected = selected;
        this.mat = item.getType();
        this.start_sound = start_sound;
        this.end_sound = end_sound;
        this.perm_particle = perm;
    }

    public ItemStack getItem() {
        return item;
    }

    public List<Reward> getRandomSelected() {
        return random_selected_rewards;
    }

    public List<Reward> getGuaranteedRewards() {
        return guaranteed_rewards;
    }

    public Particle getEnd_particle() {
        return end_particle;
    }

    public Particle getStart_particle() {
        return start_particle;
    }

    public int getSelected() {
        return selected;
    }
    public Material getMat() {
        return mat;
    }

    public Sound getEnd_sound() {
        return end_sound;
    }

    public Sound getStart_sound() {
        return start_sound;
    }

    public Particle getPerm_particle() {
        return perm_particle;
    }
}
