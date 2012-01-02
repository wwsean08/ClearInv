package com.wwsean08.clear;

import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerListener;
import org.bukkit.event.player.PlayerQuitEvent;

public class ClearPlayerListener extends PlayerListener{
	Clear plugin;
	public ClearPlayerListener(Clear instance){
		plugin = instance;
	}
	@Override
	public void onPlayerQuit(PlayerQuitEvent event){
		plugin.preview.unpreview(event.getPlayer());
	}
	@Override
	public void onPlayerKick(PlayerKickEvent event){
		plugin.preview.unpreview(event.getPlayer());
	}
}
