package com.wwsean08.clear;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerChangedWorldEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class ClearPlayerListener implements Listener{
	Clear plugin;

	/**
	 * the public constructor for the player listener
	 * @param instance is the class that called us
	 */
	public ClearPlayerListener(Clear instance){
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

	/**
	 * used for when we are in continuous preview mode
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockPlace(BlockPlaceEvent event){
		for(PreviewHolder a : plugin.preview.previewList){
			if(a.getObserved().getName().equals(event.getPlayer().getName())){
				if(a.getContinuous()){
					a.getObserver().getInventory().clear();
					a.getObserver().getInventory().setContents(event.getPlayer().getInventory().getContents());
				}
			}
		}
	}

	/**
	 * used for when we are in continuous preview mode
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onInventoryClose(InventoryCloseEvent event){
		for(PreviewHolder a : plugin.preview.previewList){
			if(a.getObserved().getName().equals(event.getPlayer().getName())){
				if(a.getContinuous()){
					a.getObserver().getInventory().clear();
					a.getObserver().getInventory().setContents(event.getPlayer().getInventory().getContents());
				}
			}
		}
	}

	/**
	 * used for when we are in continuous preview mode
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerChangedWorld(PlayerChangedWorldEvent event){
		for(PreviewHolder a : plugin.preview.previewList){
			if(a.getObserved().getName().equals(event.getPlayer().getName())){
				if(a.getContinuous()){
					a.getObserver().getInventory().clear();
					a.getObserver().getInventory().setContents(event.getPlayer().getInventory().getContents());
				}
			}
		}
	}

	/**
	 * used for when we are in continuous preview mode
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerPickupItem(PlayerPickupItemEvent event){
		for(PreviewHolder a : plugin.preview.previewList){
			if(a.getObserved().getName().equals(event.getPlayer().getName())){
				if(a.getContinuous()){
					a.getObserver().getInventory().clear();
					a.getObserver().getInventory().setContents(event.getPlayer().getInventory().getContents());
				}
			}
		}
	}

	/**
	 * used for when we are in continuous preview mode
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerRespawn(PlayerRespawnEvent event){
		for(PreviewHolder a : plugin.preview.previewList){
			if(a.getObserved().getName().equals(event.getPlayer().getName())){
				if(a.getContinuous()){
					a.getObserver().getInventory().clear();
					a.getObserver().getInventory().setContents(event.getPlayer().getInventory().getContents());
				}
			}
		}
	}

	/**
	 * used for when we are in continuous preview mode
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerItemDrop(PlayerDropItemEvent event){
		for(PreviewHolder a : plugin.preview.previewList){
			if(a.getObserved().getName().equals(event.getPlayer().getName())){
				if(a.getContinuous()){
					a.getObserver().getInventory().clear();
					a.getObserver().getInventory().setContents(event.getPlayer().getInventory().getContents());
				}
			}
		}
	}
}