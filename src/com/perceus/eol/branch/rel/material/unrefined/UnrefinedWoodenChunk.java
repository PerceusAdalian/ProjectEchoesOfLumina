package com.perceus.eol.branch.rel.material.unrefined;

import org.bukkit.Material;

import com.perceus.eol.branch.rel.enums.Rarity;

public class UnrefinedWoodenChunk extends AbstractUnrefinedRelMaterial
{
	public UnrefinedWoodenChunk() 
	{
		super("Unrefined Wooden Block", "unrefined_wooden_block", Material.OAK_BUTTON, Rarity.NONE, false, true);
	}
}
