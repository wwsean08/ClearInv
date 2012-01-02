package com.wwsean08.clear;

import org.bukkit.inventory.ItemStack;

public class ClearUndoHolder {
	private String affected;
	private ItemStack[] oldInv;
	public ClearUndoHolder(String affectedPlayer, ItemStack[] inv){
		affected = affectedPlayer;
		oldInv = inv;
	}
	public String getPlayer(){
		return affected;
	}
	public ItemStack[] getOldInventory(){
		return oldInv;
	}
}
