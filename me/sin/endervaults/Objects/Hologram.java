package me.sin.endervaults.Objects;

import org.bukkit.Material;

public class Hologram {
    private Material material;
    private Boolean enchanted;
    private String text;
    public Hologram(Material material, boolean enchanted, String text) {
        this.enchanted = enchanted;
        this.material = material;
        this.text = text;
    }

    public Boolean getEnchanted() {
        return enchanted;
    }

    public Material getMaterial() {
        return material;
    }

    public String getText() {
        return text;
    }
}
