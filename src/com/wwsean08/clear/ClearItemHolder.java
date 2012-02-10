package com.wwsean08.clear;

public class ClearItemHolder {
	private String input;
	private int itemNumber;
	private int damage;
	private String output;

	/**
	 * the constructor to hold the data values in our item list
	 * @param in is the input name we will search by
	 * @param item is the items id number
	 * @param meta is any damage value associated with this item
	 * @param out is the name of the item (as defined by the csv)
	 */
	public ClearItemHolder(String in, int item, int meta, String out){
		input = in;
		itemNumber = item;
		damage = meta;
		output = out;
	}

	/**
	 * gets the input text for this particular item
	 * @return input text
	 */
	public String getInput(){
		return input;
	}

	/**
	 * gets the item id number
	 * @return the item id number
	 */
	public int getItem(){
		return itemNumber;
	}

	/**
	 * gets the damage value
	 * @return the damage value
	 */
	public int getDamage(){
		return damage;
	}

	/**
	 * gets the output text
	 * @return the output text
	 */
	public String getOutput(){
		return output;
	}
}
