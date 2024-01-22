package com.github.interrrp.throwaballs;

import org.bukkit.Material;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.Vector;

public class FireballEventListener implements Listener {
    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        // Disable fireball ignition from fireballs
        // `Fireball.setIsIncendiary` works but with a long delay
        if (event.getCause() == BlockIgniteEvent.IgniteCause.FIREBALL) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        if (shouldLaunchFireball(action, item)) {
            launchFireball(player, item, 1.0f, 20.0f);
        }
    }

    private void launchFireball(Player player, ItemStack item, float speed, float power) {
        Vector eyeDirection = player.getEyeLocation().getDirection();

        Fireball fireball = player.launchProjectile(Fireball.class);
        fireball.setYield(power);
        fireball.setVelocity(eyeDirection.multiply(speed));
    }

    private boolean shouldLaunchFireball(Action action, ItemStack item) {
        return (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK)
                && item != null && item.getType() == Material.FIRE_CHARGE;
    }
}
