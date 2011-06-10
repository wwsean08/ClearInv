package com.wwsean08.clear;

import java.util.LinkedList;
import java.util.logging.Logger;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.plugin.java.JavaPlugin;

public class Clear extends JavaPlugin{
	Logger log = Logger.getLogger("Minecraft");
	private final double VERSION = 1.0;

	public void onEnable(){ 
		log.info("clear inventory version " + VERSION + " enabled");
	} 
	public void onDisable(){ 
		log.info("clear inventory version " + VERSION + " disabled");
	}
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args){
		Player player = (Player) sender;
		if(cmd.getName().equalsIgnoreCase("clear") || (cmd.getName().equalsIgnoreCase("clearinventory"))){
			if(args.length == 0){
				clearAll(player);
			}
			else if(args[0].equalsIgnoreCase("except")){
				clearExcept(player, args);
			}
			else if(args[0].equalsIgnoreCase("help")){
				sender.sendMessage(ChatColor.AQUA + "/clear or /clearinventory");
				sender.sendMessage(ChatColor.AQUA + "You can exclude items using the except keyword as the first argument like this:");
				sender.sendMessage(ChatColor.RED + "/clear except sand");
				sender.sendMessage(ChatColor.AQUA + "You can delete select items by naming them as arguments like this:");
				sender.sendMessage(ChatColor.RED + "/clear sand gravel");
			}
			else{
				clearItem(player, args);
			}
			return true;
		}
		return false;
	}
	/*
	 * Clears all the items out of the players inventory
	 * @param sender is the player who sent the command
	 * 
	 */
	public void clearAll(Player sender){
		sender.getInventory().clear();
		sender.sendMessage("Inventory Cleared");
	}
	/*
	 * clears all the items except for the ones specified by the player.
	 * @param sender is the player who sent the command
	 * @param args the list of items to exclude (either in number of name form).
	 * 
	 */
	public void clearExcept(Player sender, String[] args){
		LinkedList<Material> exception = new LinkedList<Material>();
		for(int i = 0; i < args.length; i++){
			Material newMaterial = Material.matchMaterial(args[i]);
			if(newMaterial != null){
				sender.sendMessage("adding " + newMaterial.toString() + " to exception list");
				exception.add(newMaterial);
			}
		}
		PlayerInventory inventory = sender.getInventory();
		for(int i = 0; i < 36; i++){
			boolean match = false;
			if(inventory.getItem(i).getType()!=null)
				for(int j = 0; j < exception.size(); j++){
					String itemName = inventory.getItem(i).getType().name();
					String exceptionName = exception.get(j).name();
					if(itemName == exceptionName){
						match = true;
					}
				}
			if(!match){
				inventory.clear(i);
			}
		}
	}
	/*
	 * clears all of the selected items by the player
	 * @param Sender the player who sent the comand
	 * @param args is the list or item(s) that the user wants to delete from their inventory
	 */
	public void clearItem(Player sender, String[] args){
		boolean removed = false;
		PlayerInventory inventory = sender.getInventory();
		String list = "";
		for (int i=0;i<args.length;i++){
			Material item = null;
			item = Material.matchMaterial(args[i]);
			if(item!=null){
				list += item.toString() + " ";
				inventory.remove(item);
				removed = true;
			}
			else{
				sender.sendMessage("no block found called: " + args[i]);
			}
		}
		if (removed)
			sender.sendMessage(list.toLowerCase() + "removed successfully from inventory");
	}
}
