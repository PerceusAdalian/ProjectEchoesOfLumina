package com.eol.branch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.eol.branch.mobgeneration.HealthBar;

public class EolPlayerJoinEvent implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent event) 
	{
		HealthBar.addPlayer(event.getPlayer());
	}
}
