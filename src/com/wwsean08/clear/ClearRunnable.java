package com.wwsean08.clear;

import org.bukkit.entity.Player;

public class ClearRunnable implements Runnable{
	PreviewCommand plugin;
	Player affected;
	public ClearRunnable(PreviewCommand instance, Player player){
		plugin = instance;
		affected = player;
	}

	@Override
	public void run() {
		plugin.unpreview(affected);
	}
}
