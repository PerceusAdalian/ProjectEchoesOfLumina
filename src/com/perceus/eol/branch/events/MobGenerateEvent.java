package com.perceus.eol.branch.events;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntitySpawnEvent;
import org.bukkit.persistence.PersistentDataType;

import com.perceus.eol.ProjectEchoesOfLumina;
import com.perceus.eol.branch.mobgeneration.ArbitraryHealthContainer;
import com.perceus.eol.branch.mobgeneration.CommonEnemyLevelTable;
import com.perceus.eol.branch.mobgeneration.HealthBar;
import com.perceus.eol.utils.PrintUtils;

public class MobGenerateEvent implements Listener
{
	public static final NamespacedKey mobKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "mob_key");
	
	@EventHandler
	public void onSpawn(EntitySpawnEvent event) 
	{
		if (!(event.getEntity() instanceof Attributable)) 
		{
			return; 
		}
		
		int level = CommonEnemyLevelTable.getLevel(event.getEntity().getLocation().getBlock().getBiome());
		double healthModifier = (0.15d * level) + 1.0;
		
		ArbitraryHealthContainer.setBaseMaxHealth(event.getEntity(), (((Attributable) event.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * healthModifier));
		ArbitraryHealthContainer.setBaseMinHealth(event.getEntity());
		ArbitraryHealthContainer.setBaseMaxArmor(event.getEntity(), ((((Attributable) event.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * healthModifier)*0.5));
		ArbitraryHealthContainer.setBaseMinArmor(event.getEntity());
		
		String name = event.getEntityType().toString().toLowerCase();
		String[] split = name.split("_");
		name = "";
		for (String s : split) 
		{
			
			char[] chars = s.toCharArray();
			chars[0] = Character.toUpperCase(chars[0]);
			name += new String(chars);
			name += " ";
		}
		
		name = name.substring(0, name.length() - 1);
		
		event.getEntity().setCustomName(PrintUtils.ColorParser("&e{&f&lLvl&r&f: " + level + "&e} &f" + name));
		event.getEntity().setCustomNameVisible(true);
		
		HealthBar.createBossBar(event.getEntity(), false);
		//This block^ gets their name and organizes it to look pretty on their name plate, and sets their name plate.
		event.getEntity().getPersistentDataContainer().set(mobKey, PersistentDataType.INTEGER, level);
		
		if (ProjectEchoesOfLumina.debug == true) 
		{
			String string = event.getEntity().getCustomName().toString();
			PrintUtils.Print("&e{{&cEOL_DEBUG&e}} &fMobGenerateEvent: &f" + string + " || &aGenerated Successfully");
		}
		
	}
}
