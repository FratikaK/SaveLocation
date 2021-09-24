package com.github.kamunyan.savelocation;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.*;

public final class SaveLocation extends JavaPlugin {

    private static SaveLocation instance;
    private List<Map<String, Double>> locationList;

    @Override
    public void onEnable() {
        instance = this;
        locationList = new ArrayList<>();

        getServer().getPluginManager().registerEvents(new SLListener(), this);
        Objects.requireNonNull(getCommand("sl")).setExecutor(new SLCommand());
        saveDefaultConfig();
        getLogger().info(ChatColor.AQUA + "SaveLocation Start!");
    }

    @Override
    public void onDisable() {
    }

    public static SaveLocation getInstance() {
        return instance;
    }

    public List<Map<String, Double>> getLocationList() {
        return locationList;
    }
}
