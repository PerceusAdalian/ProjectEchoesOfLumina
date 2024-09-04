package com.perceus.eol.branch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.perceus.eol.branch.mmo.PlayerHud;
import com.perceus.eol.branch.mobgeneration.HealthBar;

public class PlayerJoinEventHandler implements Listener
{
	@EventHandler
	public void onJoin(PlayerJoinEvent event) 
	{
		HealthBar.addPlayer(event.getPlayer());
		PlayerHud.createDisplay(event.getPlayer());
	}
}
