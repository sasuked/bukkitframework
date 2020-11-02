package io.github.sasuked;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandMap;
import org.bukkit.craftbukkit.v1_8_R3.CraftServer;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.util.Arrays;

public class BukkitFramework {
  
  public static CommandMap getCommandMap() {
    return ((CraftServer) Bukkit.getServer()).getCommandMap();
  }
  
  public static void registerCommands(String prefix, Command... commands) {
    getCommandMap().registerAll(prefix, Arrays.asList(commands));
  }
  
  public static void registerListeners(Plugin plugin, Listener... listeners) {
    PluginManager pluginManager = Bukkit.getPluginManager();
    
    for (Listener listener : listeners) {
      pluginManager.registerEvents(listener, plugin);
    }
  }
}
