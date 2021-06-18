package com.github.poxiton;

import com.github.poxiton.listeners.RPGStatsListener;

import org.bukkit.plugin.java.JavaPlugin;

public class RPGStats extends JavaPlugin {

    StatsManager manager = new StatsManager(this);

    @Override
    public void onEnable() {
        this.getCommand("rpgitem").setExecutor(new GiveItemCommand(manager));
        getServer().getPluginManager().registerEvents(new RPGStatsListener(this, manager), this);
    }
}