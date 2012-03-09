package com.wwsean08.clear;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class PreviewHolder {
	private Player observer;
	private Player beingWatched;
	private ItemStack[] originalInventory;
	private boolean continuous;
	public PreviewHolder(Player watcher, Player watched, ItemStack[] inven, boolean cont){
		observer = watcher;
		beingWatched = watched;
		originalInventory = inven;
		continuous = cont;
	}
	
	/**
	 * a getter for the player observing another players inventory
	 * @return the observer
	 */
	public Player getObserver(){
		return observer;
	}
	
	/**
	 * a getter for the player being observed
	 * @return the player being observed
	 */
	public Player getObserved(){
		return beingWatched;
	}
	
	/**
	 * a getter for the observers original inventory
	 * @return the observers original inventory
	 */
	public ItemStack[] getOriginalInventory(){
		return originalInventory;
	}
	
	/**
	 * a getter for whether or not the preview is a continuous preview
	 * @return the preview mode
	 */
	public boolean getContinuous(){
		return continuous;
	}
	
	/**
	 * A setter for what player is being observed
	 * @param watching the new player being observed
	 */
	public void setObserved(Player watching){
		beingWatched = watching;
	}
	
	/**
	 * A setter for wether or not it is a continuous preview
	 * @param mode the type of preview
	 */
	public void setContinuous(boolean mode){
		continuous = mode;
	}
}
