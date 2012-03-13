package com.wwsean08.clear;

import org.bukkit.entity.Player;

public class InventoryRunnable implements Runnable{
	Player previewer, previewing;
	public InventoryRunnable(Player a, Player b){
		previewer = a;
		previewing = b;
	}
	
	@Override
	public void run() {
		previewer.getInventory().clear();
		previewer.getInventory().setContents(previewing.getInventory().getContents());
	}
}
