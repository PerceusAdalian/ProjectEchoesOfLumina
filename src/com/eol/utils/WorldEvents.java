package com.eol.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.util.RayTraceResult;

public class WorldEvents 
{
	public static List<Block> getNearbyBlocks(Location origin, int radius) 
	{
		List<Block> locations = new ArrayList<>();
		for (int iy = -radius; iy <= radius; iy++)
		{
			for (int ix = -radius; ix <= radius; ix++)
			{
				for (int iz = -radius; iz <= radius; iz++)
				{
					Location newLoc = origin.clone().add(ix,iy,iz);
					locations.add(newLoc.getBlock());
				}
			}
		}
		return locations;
	}
	
	public static Block rayTraceBlock(Player p, double range) 
	{
		Location eye = p.getEyeLocation();
		RayTraceResult result = p.getWorld().rayTraceBlocks(eye, eye.getDirection(), range);

	    if(result == null || result.getHitBlock() == null) return null;
	    return result.getHitBlock();
	}
	
	/**
	 * @param source via player's location
	 * @param radius to check for mobs
	 * @param action to accept for each entity (LivingEntity by default)
	 */
	public static void getNearbyEntities(Player source, int radius, Consumer<LivingEntity> action) 
	{
	    List<Entity> nearbyEntities = source.getNearbyEntities(radius, radius, radius);
	    
	    if (nearbyEntities.isEmpty()) 
	    {
	        PrintUtils.EolFormatError(source, "No targets found.");
	        return;
	    }

	    for (Entity target : nearbyEntities) 
	    {
	        if (target instanceof LivingEntity livingTarget) 
	        {
	            action.accept(livingTarget);
	        }
	    }
	}
	
	/**
	 * @param source via specified entity location
	 * @param radius to check for mobs
	 * @param action to accept for each entity (LivingEntity by default)
	 */
	public static void getNearbyEntities(Entity source, int radius, Consumer<LivingEntity> action) 
	{
		List<Entity> nearbyEntities = source.getNearbyEntities(radius, radius, radius);
		
		if (nearbyEntities.isEmpty()) 
	    {
	        return;
	    }

	    for (Entity target : nearbyEntities) 
	    {
	        if (target instanceof LivingEntity livingTarget) 
	        {
	        	if (target instanceof Player) continue;
	            action.accept(livingTarget);
	        }
	    }
	}

}

//Imported From Project Nexus