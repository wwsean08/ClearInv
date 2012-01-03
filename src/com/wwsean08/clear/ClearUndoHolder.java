package com.wwsean08.clear;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class ClearUndoHolder {
	private String affected;
	private ArrayList<ItemStack> oldInv;
	public ClearUndoHolder(String affectedPlayer, ArrayList<ItemStack> inv){
		affected = affectedPlayer;
		oldInv = inv;
	}
	public String getPlayer(){
		return affected;
	}
	public ArrayList<ItemStack> getOldInventory(){
		return oldInv;
	}
}
