package com.wwsean08.clear;

import org.bukkit.entity.Player;

public class ClearRunnable implements Runnable{
	PreviewCommand plugin;
	Player affected;
	/**
	 * the only constructor which allows us to schedule a task
	 * @param instance is the class that is calling us
	 * @param player is the player who is going to be passed to unpreview.
	 */
	public ClearRunnable(PreviewCommand instance, Player player){
		plugin = instance;
		affected = player;
	}
	
	@Override
	public void run() {
		plugin.unpreview(affected);
	}
}
