package com.perceus.eol.branch.rel.material.creationcatalysts;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import com.perceus.eol.ProjectEchoesOfLumina;
import com.perceus.eol.branch.rel.enums.Rarity;
import com.perceus.eol.branch.rel.material.AbstractRelMaterial;

public abstract class AbstractCatalystObject extends AbstractRelMaterial implements Listener
{
	
	private boolean isCatalyst;
	
	public AbstractCatalystObject(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, String...description) 
	{
		super(name, internalName, material, rarity, enchantedEffect, description);
		Bukkit.getPluginManager().registerEvents(this, ProjectEchoesOfLumina.instance);
	}

	public AbstractCatalystObject(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, boolean isCatalyst, String...description) 
	{
		super(name, internalName, material, rarity, enchantedEffect, description);
		this.isCatalyst = isCatalyst;
		Bukkit.getPluginManager().registerEvents(this, ProjectEchoesOfLumina.instance);
	}
	
	public boolean isCatalyst() 
	{
		return isCatalyst;
	}

}
