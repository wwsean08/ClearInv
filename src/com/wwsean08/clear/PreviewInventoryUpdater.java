package com.wwsean08.clear;

import org.bukkit.entity.Player;

public class PreviewInventoryUpdater implements Runnable{
	Player previewer, previewing;
	public PreviewInventoryUpdater(Player a, Player b){
		previewer = a;
		previewing = b;
	}
	
	@Override
	public void run() {
		previewer.getInventory().clear();
		previewer.getInventory().setContents(previewing.getInventory().getContents());
	}
}
