package com.eol.utils;

import org.bukkit.Bukkit;
import org.bukkit.event.player.PlayerInteractEvent;

import com.eol.ProjectEchoesOfLumina;

public class ItemCollector 
{
	public static void remove(PlayerInteractEvent e) 
	{
		if (ProjectEchoesOfLumina.debug == false) 
		{
			Bukkit.getScheduler().runTaskLater(ProjectEchoesOfLumina.instance, ()->
			{
				e.getItem().setAmount(e.getItem().getAmount() - 1);
			}, 1);			
		}
	}
}

//Imported From Project Nexus