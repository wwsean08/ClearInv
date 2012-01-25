package com.wwsean08.clear;

import org.bukkit.Bukkit;
import org.bukkit.event.Event;
import org.bukkit.event.Event.Priority;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ClearPlayerListener implements Listener{
	Clear plugin;
	public ClearPlayerListener(Clear instance){
		plugin = instance;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event){
		plugin.preview.unpreview(event.getPlayer());
	}
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent event){
		plugin.preview.unpreview(event.getPlayer());
	}
}
