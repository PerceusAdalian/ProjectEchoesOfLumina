package com.perceus.eol.branch.crp.material;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.eol.branch.crp.enums.Rarity;

public class CreationCatalyst_1 extends CreationCatalystObject
{

	public CreationCatalyst_1() 
	{
		super("Creation Catalyst I", "creation_catalyst_1", Material.NETHER_STAR, Rarity.ONE, true, true, "");
	}
	
	//add the @EventHandler annotation when you need to 
	@EventHandler
	@Override
	public void Cast(PlayerInteractEvent event) 
	{
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			return;
		}
		event.setCancelled(true);
		
		event.getPlayer().sendMessage("Yo shit work bb <3~!");
		return;
	}
}
