package com.github.interrrp.throwaballs;

import org.bukkit.configuration.Configuration;

public class FireballServiceFactory {
    public static FireballService fromConfig(Configuration config) {
        float speed = (float) config.getDouble("speed", 1.0);
        float power = (float) config.getDouble("power", 20.0);
        boolean isIncendiary = config.getBoolean("is-incendiary", false);

        return new FireballService(speed, power, isIncendiary);
    }
}
