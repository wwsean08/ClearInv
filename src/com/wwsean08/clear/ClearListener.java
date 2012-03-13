package com.wwsean08.clear;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.painting.PaintingPlaceEvent;
import org.bukkit.event.player.PlayerBucketEmptyEvent;
import org.bukkit.event.player.PlayerBucketFillEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class ClearListener implements Listener{
	Clear plugin;

	/**
	 * the public constructor for the player listener
	 * @param instance is the class that called us
	 */
	public ClearListener(Clear instance){
		plugin = instance;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	/**
	 * a player quit event happened so we gotta make sure they weren't previewing an inventory
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event){
		plugin.preview.unpreview(event.getPlayer());
	}

	/**
	 * a player got kicked so make sure they weren't previewing an inventory
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent event){
		plugin.preview.unpreview(event.getPlayer());
	}
}