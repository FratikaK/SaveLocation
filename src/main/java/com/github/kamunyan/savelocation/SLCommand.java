package com.github.kamunyan.savelocation;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.jetbrains.annotations.NotNull;

public class SLCommand implements CommandExecutor {
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        boolean flag = false;
        if (args.length == 0){
            return false;
        }
        if (command.getName().equals("sl") && sender instanceof Player) {
            var player = (Player) sender;
            if (args[0].equals("give")) {
                var item = new ItemStack(Material.WOODEN_SWORD);
                var meta = item.getItemMeta();
                meta.setDisplayName(ChatColor.AQUA + "Location Sword");
                meta.setCustomModelData(50);
                item.setItemMeta(meta);
                player.getInventory().addItem(item);
                flag = true;
            }else if (args[0].equals("save")){
                var plugin = SaveLocation.getInstance();
                var config = plugin.getConfig();
                if (!config.contains("location")) {
                    config.createSection("location");
                }
                config.set("location", plugin.getLocationList());
                plugin.saveConfig();
                player.sendMessage(ChatColor.AQUA + "Saved Locations!");
                flag = true;
            }else if (args[0].equals("reset")){
                SaveLocation.getInstance().getLocationList().clear();
                player.sendMessage("Reset Location!");
                flag = true;
            }
            if (!flag){
                sendHelpMessage(player);
                return false;
            }
        }
        return flag;
    }
    private void sendHelpMessage(Player player){
        player.sendMessage("SaveLocation usage\n" +
                "sl give: give 'Location Sword'\n" +
                "sl save: Save the location to Config.\n" +
                "sl reset: Reset Location List.");
    }
}
