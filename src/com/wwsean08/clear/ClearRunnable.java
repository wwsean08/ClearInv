package com.wwsean08.clear;

import org.bukkit.entity.Player;

public class ClearRunnable implements Runnable{
	Clear plugin;
	Player affected;
	public ClearRunnable(Clear instance, Player player){
		plugin = instance;
		affected = player;
	}
	
	@Override
	public void run() {
		plugin.unpreview(affected);
	}
}
