package com.perceus.eol.branch.mobgeneration;

import org.bukkit.entity.EntityType;

public class CustomEolMob extends AbstractEolMobObject
{
	public CustomEolMob(EntityType eType, int level, String name, double health, int armor) 
	{
		super(eType, level, name, health, armor);
	}
	
	public CustomEolMob(EntityType eType, int level) 
	{
		this(eType, level,null,0,0);
	}
}
