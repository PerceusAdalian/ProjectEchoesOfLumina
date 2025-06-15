package com.eol.branch.rel.material.unrefinedmaterials;

import org.bukkit.Material;

import com.eol.branch.rel.enums.Rarity;
import com.eol.branch.rel.material.AbstractRelMaterial;

public class AbstractUnrefinedRelMaterial extends AbstractRelMaterial
{
	private boolean isUnrefined;
	
	public AbstractUnrefinedRelMaterial(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, String[] description) 
	{
		super(name, internalName, material, rarity, enchantedEffect, description);
	}
	
	public AbstractUnrefinedRelMaterial(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, boolean isUnrefined, String...description) 
	{
		super(name, internalName, material, rarity, enchantedEffect, description);
		this.setUnrefined(isUnrefined);
	}

	public boolean isUnrefined() 
	{
		return isUnrefined;
	}

	public void setUnrefined(boolean isUnrefined) 
	{
		this.isUnrefined = isUnrefined;
	}
	
}