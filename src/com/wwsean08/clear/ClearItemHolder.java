package com.wwsean08.clear;

public class ClearItemHolder {
	private String input;
	private int itemNumber;
	private int damage;
	private String output;

	public ClearItemHolder(String in, int item, int meta, String out){
		input = in;
		itemNumber = item;
		damage = meta;
		output = out;
	}
	public String getInput(){
		return input;
	}
	public int getItem(){
		return itemNumber;
	}
	public int getDamage(){
		return damage;
	}
	public String getOutput(){
		return output;
	}
}
