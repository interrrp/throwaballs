package com.github.interrrp.throwaballs;

import org.bukkit.plugin.java.JavaPlugin;

public class ThrowaballsPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        FireballService fireballService = FireballServiceFactory.fromConfig(getConfig());
        getServer().getPluginManager().registerEvents(new FireballEventListener(fireballService), this);
    }
}
