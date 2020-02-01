package me.sin.endervaults.Objects;

import me.sin.endervaults.Utils.particle.ParticleEffects;

public class Particle {
    private double amount;
    private double speed;
    private ParticleEffects particle;
    public Particle(double amount, double speed, ParticleEffects particle) {
        this.amount = amount;
        this.particle = particle;
        this.speed = speed;
    }

    public double getAmount() {
        return amount;
    }

    public double getSpeed() {
        return speed;
    }

    public ParticleEffects getParticle() {
        return particle;
    }
}
