package com.eol.branch.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import com.eol.ProjectEchoesOfLumina;
import com.eol.branch.mobgeneration.AbstractEolMobObject;
import com.eol.utils.PrintUtils;

public class EolMobDeathEvent implements Listener
{
	@EventHandler
	public void onDeath(EntityDeathEvent event) 
	{
		
		if (!event.getEntity().getPersistentDataContainer().has(MobGenerateEvent.mobKey) || !event.getEntity().getPersistentDataContainer().has(AbstractEolMobObject.uniqueEolMobKey))
		{
			return;
		}
		
		if (ProjectEchoesOfLumina.debug == true) 
		{
			String string = event.getEntity().getCustomName().toString();
			PrintUtils.EolConsoleDebug("&fEolMobDeathEvent: &r&f" + string + " || &cDied Successfully");
		}
		return;
		
	}
}
