package com.perceus.eol.branch.material.creationcatalysts;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;

import com.perceus.eol.ProjectEchoesOfLumina;
import com.perceus.eol.branch.crp.enums.Rarity;
import com.perceus.eol.branch.crp.material.AbstractCrpMaterialObject;

public abstract class AbstractCreationCatalystObject extends AbstractCrpMaterialObject implements Listener
{
	
	private boolean isCatalyst;
	
	public AbstractCreationCatalystObject(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, String...description) 
	{
		super(name, internalName, material, rarity, enchantedEffect, description);
		Bukkit.getPluginManager().registerEvents(this, ProjectEchoesOfLumina.instance);
	}

	public AbstractCreationCatalystObject(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, boolean isCatalyst, String...description) 
	{
		super(name, internalName, material, rarity, enchantedEffect, description);
		this.isCatalyst = isCatalyst;
		Bukkit.getPluginManager().registerEvents(this, ProjectEchoesOfLumina.instance);
	}
	
	public boolean isCatalyst() {
		return isCatalyst;
	}
	
	public void setCatalyst(boolean isCatalyst) {
		this.isCatalyst = isCatalyst;
	}

}
