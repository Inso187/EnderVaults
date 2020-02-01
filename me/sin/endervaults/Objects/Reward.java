package me.sin.endervaults.Objects;

import org.bukkit.Sound;

import java.util.ArrayList;
import java.util.List;

public class Reward {
    private List<String> commands = new ArrayList<>();
    private Particle particle;
    private Sound sound;
    private Hologram hologram;
    public Reward(List<String> commands, Particle particle, Hologram hologram, Sound sound) {
        this.commands = commands;
        this.particle = particle;
        this.hologram = hologram;
        this.sound = sound;
    }

    public List<String> getCommands() {
        return commands;
    }

    public Hologram getHologram() {
        return hologram;
    }

    public Particle getParticle() {
        return particle;
    }

    public Sound getSound() {
        return sound;
    }
}
