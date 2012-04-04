package com.wwsean08.clear;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ConcurrentModificationException;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Server;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class PreviewCommand implements CommandExecutor{
	Clear plugin;
	Server server;
	private ArrayList<PreviewHolder> previewList;


	public PreviewCommand(Clear instance){
		plugin = instance;
		server = Bukkit.getServer();
		previewList = new ArrayList<PreviewHolder>();
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String commandLabel, String[] args) {
		if(commandLabel.equalsIgnoreCase("preview")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				if(args.length == 0){
					unpreview(player);
				}
				//non-continuous preview
				else if(args.length == 1){
					if(server.matchPlayer(args[0]) != null){
						Player affected = server.matchPlayer(args[0]).get(0);
						if(plugin.usesSP){
							if(player.hasPermission("preview.normal")){
								preview(player, affected, 0);
							}else{
								sender.sendMessage("You do not have permission to use this command");
								plugin.log.warning(plugin.PREFIX + player.getDisplayName() + " Attempted to preview another players inventory");
							}
						}else if(player.isOp()){
							preview(player, affected, 0);
						}else{
							sender.sendMessage("You do not have permission to use this command");
							plugin.log.warning(plugin.PREFIX + player.getDisplayName() + " Attempted to preview another players inventory");
						}
					}
				}
				//continuous preview
				else if(args.length == 2){
					if(args[0].equalsIgnoreCase("-c")){
						if(server.matchPlayer(args[1]) != null){
							Player affected = server.matchPlayer(args[1]).get(0);
							if(plugin.usesSP){
								if(player.hasPermission("preview.continuous")){
									preview(player, affected, 1);
								}else{
									sender.sendMessage("You do not have permission to use this command");
									plugin.log.warning(plugin.PREFIX + player.getDisplayName() + " Attempted to preview another players inventory");
								}
							}else if(player.isOp()){
								preview(player, affected, 1);
							}else{
								sender.sendMessage("You do not have permission to use this command");
								plugin.log.warning(plugin.PREFIX + player.getDisplayName() + " Attempted to preview another players inventory");
							}
						}else{
							sender.sendMessage(ChatColor.RED + "Sorry, I couldn't find anyone by that name");
						}
					}
				}
			}
			/*
			 * This else is for the console
			 */
			else{
				if(args.length == 0){
					sender.sendMessage(ChatColor.GRAY + "Error: I need a name of who to preview");
				}else{
					Player player = server.getPlayer(args[0]);
					preview(player,sender);
				}
			}
		}
		else if(commandLabel.equalsIgnoreCase("unpreview") || commandLabel.equalsIgnoreCase("revert")){
			if(sender instanceof Player){
				Player player = (Player) sender;
				unpreview(player);
			}
		}
		return true;
	}

	/**
	 * is used to display a players inventory from the server console
	 * @param player the player whose inventory is being previewed
	 * @return a list of items to display to the server console
	 */
	public void preview(Player player, CommandSender sender) {
		if(player == null){
			sender.sendMessage("Sorry I couldn't find a player by that name");
			return;
		}
		ArrayList<String> inventoryList = new ArrayList<String>();
		ArrayList<Integer> itemNumbers = new ArrayList<Integer>();
		HashMap<Integer, Integer> ammount = new HashMap<Integer, Integer>();
		PlayerInventory inv = player.getInventory();
		ItemStack[] contents = inv.getContents();
		//loop thru the contents of the inventory in order to make our array list of items
		for(ItemStack a : contents){
			if(a != null){
				Integer value = a.getTypeId();
				if(itemNumbers.contains(Integer.valueOf(value))){
					int i = ammount.get(value);
					i += a.getAmount();
					ammount.put(value, i);
				}else{
					itemNumbers.add(value);
					ammount.put(value, a.getAmount());
				}
			}
		}
		//build an array list of strings with the ammount they have and the item
		for(Integer i : itemNumbers){
			StringBuilder out = new StringBuilder();
			if(plugin.danger.contains(i))
				out.append(ChatColor.RED);
			out.append(ammount.get(i) + "x ");
			for(int j = 0; j < plugin.items.size(); j++){
				if(plugin.items.get(j).getItem() == i.intValue()){
					out.append(plugin.items.get(j).getOutput());
					break;
				}
			}
			inventoryList.add(out.toString());
		}
		sender.sendMessage("Here is what is in " + player.getName() + "'s inventory:");
		for(String s : inventoryList){
			sender.sendMessage(s);
		}
	}

	/**
	 * Allows an admin to preview a players inventory
	 * @param previewer The admin that will be previewing the inventory
	 * @param previewee The player whose inventory we want to preview
	 * @param mode is wether or not it will be continuous mode or not which still has to be implemented
	 */
	public void preview(Player previewer, Player previewee, int mode){
		synchronized(getPreviewList()){
			for(PreviewHolder a : getPreviewList()){
				if(a.getObserver().getName().equals(previewer.getName())){
					a.setObserved(previewee);
					a.setMode(mode);
					previewer.getInventory().setContents(previewee.getInventory().getContents());
					previewer.sendMessage(ChatColor.GRAY + "You are now previewing " + previewee.getDisplayName());
					if(mode != 0){
						previewer.sendMessage(ChatColor.GRAY + "You will have a live preview of their inventory which will be kept up to date");
					}else{
						PreviewRunnable runner = new PreviewRunnable(this, previewer);
						server.getScheduler().scheduleSyncDelayedTask(plugin, runner, 6000);
					}
					return;
				}
			}
			PreviewHolder PH = new PreviewHolder(previewer, previewee, previewer.getInventory().getContents(), mode);
			previewList.add(PH);
			previewer.getInventory().setContents(previewee.getInventory().getContents());
			previewer.sendMessage(ChatColor.GRAY + "You are now previewing " + previewee.getDisplayName());
			if(mode != 0){
				previewer.sendMessage(ChatColor.GRAY + "You will have a live preview of their inventory which will be kept up to date");
			}else{
				PreviewRunnable runner = new PreviewRunnable(this, previewer);
				server.getScheduler().scheduleSyncDelayedTask(plugin, runner, 6000);
			}
		}
	}
	/**
	 * Restores the content of an admins inventory if they are previewing one
	 * @param previewer the admin who is getting their inventory back
	 */
	public void unpreview(Player previewer){
		try{
			synchronized(getPreviewList()){
				for(PreviewHolder a : getPreviewList()){
					if(a.getObserver().getName().equals(previewer.getName())){
						previewList.remove(a);
						previewer.getInventory().clear();
						previewer.getInventory().setContents(a.getOriginalInventory());
						previewer.sendMessage(ChatColor.GRAY + "Your inventory has been restored");
					}
				}
			}
		}catch(ConcurrentModificationException e){
			unpreview(previewer, 0);
		}
	}

	/**
	 * Restores the content of an admins inventory if they are previewing one
	 * @param previewer the admin who is getting their inventory back
	 * @param rounds is the ammount of times it has looped thru, after 10 it will give up
	 */
	public void unpreview(Player previewer, int rounds){
		try{
			synchronized(getPreviewList()){
				for(PreviewHolder a : getPreviewList()){
					if(a.getObserver().getName().equals(previewer.getName())){
						previewList.remove(a);
						previewer.getInventory().clear();
						previewer.getInventory().setContents(a.getOriginalInventory());
						previewer.sendMessage(ChatColor.GRAY + "Your inventory has been restored");
					}
				}
			}
		}catch(ConcurrentModificationException e){
			if(rounds++ >= 10){
				previewer.sendMessage(ChatColor.RED + "There was an error reverting your inventory");
				return;
			}
			unpreview(previewer, rounds);
		}
	}
	/**
	 * The list which holds who is previewing (it should be synchronized)
	 * @return a hopefully synchronized list
	 */
	public List<PreviewHolder> getPreviewList(){
		return Collections.synchronizedList(previewList);
	}
}