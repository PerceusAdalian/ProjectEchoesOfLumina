package com.perceus.eol.branch.crp.material;

import org.bukkit.Material;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.eol.branch.crp.enums.Rarity;

public abstract class CrpMaterial_CreationCatalyst extends CrpMaterial
{
	private boolean isCatalyst;
	
	public CrpMaterial_CreationCatalyst(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, String...description) 
	{
		super(name, internalName, material, rarity, enchantedEffect, description);
	}

	public CrpMaterial_CreationCatalyst(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, boolean isCatalyst, String...description) 
	{
		super(name, internalName, material, rarity, enchantedEffect, description);
		this.isCatalyst = isCatalyst;
	}
	
	public boolean isCatalyst() {
		return isCatalyst;
	}
	
	public void setCatalyst(boolean isCatalyst) {
		this.isCatalyst = isCatalyst;
	}
	
	public abstract boolean openCrpMenu(PlayerInteractEvent event);
	
}
