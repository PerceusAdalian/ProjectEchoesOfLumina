package com.eol.branch.rel.material.unrefinedmaterials;

import org.bukkit.Material;

import com.eol.branch.rel.enums.Rarity;

public class UnrefinedWoodenChunk extends AbstractUnrefinedRelMaterial
{
	public UnrefinedWoodenChunk() 
	{
		super("Unrefined Wooden Block", "unrefined_wooden_block", Material.OAK_BUTTON, Rarity.NONE, false, true);
	}
}
