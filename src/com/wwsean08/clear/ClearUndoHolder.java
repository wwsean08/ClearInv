package com.wwsean08.clear;

import java.util.ArrayList;

import org.bukkit.inventory.ItemStack;

public class ClearUndoHolder {
	private String affected;
	private ArrayList<ItemStack> oldInv;

	/**
	 * the only constructor for an undo holder
	 * @param affectedPlayer the player who's inventory we are storing
	 * @param inv the inventory we are storing
	 */
	public ClearUndoHolder(String affectedPlayer, ArrayList<ItemStack> inv){
		affected = affectedPlayer;
		oldInv = inv;
	}
	/**
	 * a getter for the affected player
	 * @return the player affected by the command we are about to undo
	 */
	public String getPlayer(){
		return affected;
	}
	/**
	 * getter for the contents of their old inventory
	 * @return the old inventory
	 */
	public ArrayList<ItemStack> getOldInventory(){
		return oldInv;
	}
}
