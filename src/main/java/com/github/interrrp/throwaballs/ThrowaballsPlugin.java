package com.github.interrrp.throwaballs;

import org.bukkit.plugin.java.JavaPlugin;

public class ThrowaballsPlugin extends JavaPlugin {
    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new FireballEventListener(), this);
    }
}
