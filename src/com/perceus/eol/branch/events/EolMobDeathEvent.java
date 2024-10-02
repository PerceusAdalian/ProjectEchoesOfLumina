package com.perceus.eol.branch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.perceus.eol.ProjectEchoesOfLumina;
import com.perceus.eol.utils.PrintUtils;

public class EolMobDeathEvent implements Listener
{
	@EventHandler
	public void onDeath(EntityDeathEvent event) 
	{
		
		if (!event.getEntity().getPersistentDataContainer().has(MobGenerateEvent.mobKey))
		{
			return;
		}
		
		if (ProjectEchoesOfLumina.debug == true) 
		{
			String string = event.getEntity().getCustomName().toString();
			PrintUtils.Print("&e{{&cEOL_DEBUG&e}} &fEolMobDeathEvent: &r&f" + string + " || &cDied Successfully");
		}
		return;
		
	}
}
