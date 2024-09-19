package com.perceus.eol.branch.crp.material;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.eol.ProjectEchoesOfLumina;
import com.perceus.eol.branch.crp.enums.Rarity;

public abstract class CreationCatalystObject extends CrpMaterialObject implements Listener
{
	private boolean isCatalyst;
	
	public CreationCatalystObject(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, String...description) 
	{
		super(name, internalName, material, rarity, enchantedEffect, description);
		Bukkit.getPluginManager().registerEvents(this, ProjectEchoesOfLumina.instance);
	}

	public CreationCatalystObject(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, boolean isCatalyst, String...description) 
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
	
	public abstract void Cast(PlayerInteractEvent event);
}
