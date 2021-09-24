package com.github.kamunyan.savelocation;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class SLListener implements Listener {
    private static final SaveLocation plugin = SaveLocation.getInstance();

    @EventHandler
    public void onItemInteract(PlayerInteractEvent e) {
        if (e.getAction() == Action.LEFT_CLICK_AIR || e.getAction() == Action.LEFT_CLICK_BLOCK) {
            return;
        }
        if (e.getItem() == null) {
            return;
        }
        var player = e.getPlayer();
        if (e.getItem().getType() == Material.WOODEN_SWORD) {
            var itemMeta = e.getItem().getItemMeta();
            if (itemMeta.hasCustomModelData() && itemMeta.getCustomModelData() == 50) {
                var location = player.getLocation().clone();
                saveLocation(player, location);
            }
        }
    }

    private void saveLocation(Player player, Location location) {
        var map = new HashMap<String, Double>();
        map.put("x", Math.floor(location.getX()) + 0.5);
        map.put("y", Math.floor(location.getY()) + 1.0);
        map.put("z", Math.floor(location.getZ()) + 0.5);
        plugin.getLocationList().add(map);

        String str = "{ x: " + map.get("x") + ",y: " + map.get("y") + ",z: " + map.get("z") + " }";
        player.sendMessage("Saved Location." + str);
    }
}
