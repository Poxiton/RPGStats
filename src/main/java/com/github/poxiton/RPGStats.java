package com.github.poxiton;

import com.github.poxiton.listeners.RPGStatsListener;

import org.bukkit.plugin.java.JavaPlugin;

public class RPGStats extends JavaPlugin {

  @Override
  public void onEnable() {
    StatsManager manager = new StatsManager(this);

    this.getCommand("rpgitem").setExecutor(new GiveItemCommand(manager));
    getServer().getPluginManager().registerEvents(new RPGStatsListener(this, manager), this);
  }

}