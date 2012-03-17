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
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerEggThrowEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;

public class PreviewListener implements Listener{
	Clear plugin;
	PreviewCommand preview;

	/**
	 * the public constructor for the listener
	 * @param instance is the class that called us
	 */
	public PreviewListener(Clear instance){
		plugin = instance;
		preview = plugin.preview;
		Bukkit.getPluginManager().registerEvents(this, plugin);
	}

	/**
	 * a player quit event happened so we gotta make sure they weren't previewing an inventory
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerQuit(PlayerQuitEvent event){
		preview.unpreview(event.getPlayer());
	}

	/**
	 * a player got kicked so make sure they weren't previewing an inventory
	 * @param event
	 */
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onPlayerKick(PlayerKickEvent event){
		preview.unpreview(event.getPlayer());
	}

	/**
	 * used for when in continuous preview mode, if they place a block, check if someone was previewing them
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onBlockPlace(BlockPlaceEvent event){
		synchronized(preview.getPreviewList()){
			for(PreviewHolder a : preview.getPreviewList()){
				if(a.getObserved().getName().equals(event.getPlayer().getName())){
					if(a.getContinuous()){
						InventoryRunnable IR = new InventoryRunnable(a.getObserver(), a.getObserved());
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, IR);
					}
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
		synchronized(preview.getPreviewList()){
			for(PreviewHolder a : preview.getPreviewList()){
				if(a.getObserved().getName().equals(event.getPlayer().getName())){
					if(a.getContinuous()){
						InventoryRunnable IR = new InventoryRunnable(a.getObserver(), a.getObserved());
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, IR);
					}
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
		synchronized(preview.getPreviewList()){
			for(PreviewHolder a : preview.getPreviewList()){
				if(a.getObserved().getName().equals(event.getPlayer().getName())){
					if(a.getContinuous()){
						InventoryRunnable IR = new InventoryRunnable(a.getObserver(), a.getObserved());
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, IR);
					}
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
		synchronized(preview.getPreviewList()){
			for(PreviewHolder a : preview.getPreviewList()){
				if(a.getObserved().getName().equals(event.getPlayer().getName())){
					if(a.getContinuous()){
						InventoryRunnable IR = new InventoryRunnable(a.getObserver(), a.getObserved());
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, IR);
					}
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
		synchronized(preview.getPreviewList()){
			for(PreviewHolder a : preview.getPreviewList()){
				if(a.getObserved().getName().equals(event.getPlayer().getName())){
					if(a.getContinuous()){
						InventoryRunnable IR = new InventoryRunnable(a.getObserver(), a.getObserved());
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, IR);
					}
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
		synchronized(preview.getPreviewList()){
			for(PreviewHolder a : preview.getPreviewList()){
				if(a.getObserved().getName().equals(event.getPlayer().getName())){
					if(a.getContinuous()){
						InventoryRunnable IR = new InventoryRunnable(a.getObserver(), a.getObserved());
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, IR);
					}
				}
			}
		}
	}

	/**
	 * used for when we are in continuous preview mode
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPaintingPlace(PaintingPlaceEvent event){
		synchronized(preview.getPreviewList()){
			for(PreviewHolder a : preview.getPreviewList()){
				if(a.getObserved().getName().equals(event.getPlayer().getName())){
					if(a.getContinuous()){
						InventoryRunnable IR = new InventoryRunnable(a.getObserver(), a.getObserved());
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, IR);
					}
				}
			}
		}
	}

	/**
	 * used for when we are in continuous preview mode
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerBucketFill(PlayerBucketFillEvent event){
		synchronized(preview.getPreviewList()){
			for(PreviewHolder a : preview.getPreviewList()){
				if(a.getObserved().getName().equals(event.getPlayer().getName())){
					if(a.getContinuous()){
						InventoryRunnable IR = new InventoryRunnable(a.getObserver(), a.getObserved());
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, IR);
					}
				}
			}
		}
	}

	/**
	 * used for when we are in continuous preview mode
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerBucketEmpty(PlayerBucketEmptyEvent event){
		synchronized(preview.getPreviewList()){
			for(PreviewHolder a : preview.getPreviewList()){
				if(a.getObserved().getName().equals(event.getPlayer().getName())){
					if(a.getContinuous()){
						InventoryRunnable IR = new InventoryRunnable(a.getObserver(), a.getObserved());
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, IR);
					}
				}
			}
		}
	}

	/**
	 * used for when we are in continuous preview mode
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerEggThrow(PlayerEggThrowEvent event){
		synchronized(preview.getPreviewList()){
			for(PreviewHolder a : preview.getPreviewList()){
				if(a.getObserved().getName().equals(event.getPlayer().getName())){
					if(a.getContinuous()){
						InventoryRunnable IR = new InventoryRunnable(a.getObserver(), a.getObserved());
						Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, IR);
					}
				}
			}
		}
	}

	/**
	 * used for when we are in continuous preview mode
	 * @param event
	 */
	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerCommandPreprocessEmpty(PlayerCommandPreprocessEvent event){
		if(event.getMessage().startsWith("/i ") || event.getMessage().startsWith("/item ") || event.getMessage().startsWith("/give ")){
			if(event.getMessage().startsWith("/give")){
				String[] args = event.getMessage().split(" ");
				synchronized(preview.getPreviewList()){
					for(PreviewHolder a : preview.getPreviewList()){
						if(a.getObserved().getName().equals(args[1])){
							if(a.getContinuous()){
								InventoryRunnable IR = new InventoryRunnable(a.getObserver(), a.getObserved());
								Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, IR);
							}
						}
					}
				}
			}else{
				synchronized(preview.getPreviewList()){
					for(PreviewHolder a : preview.getPreviewList()){
						if(a.getObserved().getName().equals(event.getPlayer().getName())){
							if(a.getContinuous()){
								InventoryRunnable IR = new InventoryRunnable(a.getObserver(), a.getObserved());
								Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, IR);
							}
						}
					}
				}
			}
		}
	}
}