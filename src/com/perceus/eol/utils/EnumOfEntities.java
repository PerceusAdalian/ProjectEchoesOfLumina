package com.perceus.eol.utils;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.entity.EntityType;

public class EnumOfEntities
{
	
	public static List<String> asList() 
	{
		List<String> entityNames = new ArrayList<>(); // Start with an empty list
		for (EntityType type : EntityType.values()) // Iterate over all the enum variants
		{
		  entityNames.add(type.name()); // Get the variant's name and then add it to the list
		}
		return entityNames;	
	}
	
}
