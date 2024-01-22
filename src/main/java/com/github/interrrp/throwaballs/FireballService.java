package com.github.interrrp.throwaballs;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class FireballService {
    private float speed;
    private float power;
    private boolean isIncendiary;

    public FireballService(float speed, float power, boolean isIncendiary) {
        this.speed = speed;
        this.power = power;
        this.isIncendiary = isIncendiary;
    }

    public void onBlockIgnite(BlockIgniteEvent event) {
        if (!this.isIncendiary && event.getCause() == BlockIgniteEvent.IgniteCause.FIREBALL) {
            // Disable fireball ignition from fireballs
            // `Fireball.setIsIncendiary` works but with a long delay
            event.setCancelled(true);
        }
    }

    public void launch(Player player) {
        Vector eyeDirection = player.getEyeLocation().getDirection();

        Fireball fireball = player.launchProjectile(Fireball.class);
        fireball.setYield(power);
        fireball.setVelocity(eyeDirection.multiply(speed));
        fireball.setIsIncendiary(isIncendiary);
    }

    public boolean shouldLaunch(Action action, ItemStack item) {
        return (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
                && item != null && item.getType() == Material.FIRE_CHARGE;
    }
}
