package com.perceus.eol.branch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import com.perceus.eol.branch.mobgeneration.HealthBar;

public class EolPlayerQuitEvent implements Listener
{
	@EventHandler
	public void onQuit(PlayerQuitEvent event) 
	{
		HealthBar.removePlayer(event.getPlayer());
	}
}
