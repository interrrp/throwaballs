package com.github.interrrp.throwaballs;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class FireballEventListener implements Listener {
    private FireballService fireballService = new FireballService(1.0f, 20.0f, false);

    @EventHandler
    public void onBlockIgnite(BlockIgniteEvent event) {
        fireballService.onBlockIgnite(event);
    }

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        Action action = event.getAction();
        ItemStack item = event.getItem();
        Player player = event.getPlayer();

        if (fireballService.shouldLaunch(action, item)) {
            fireballService.launch(player);
        }
    }
}
