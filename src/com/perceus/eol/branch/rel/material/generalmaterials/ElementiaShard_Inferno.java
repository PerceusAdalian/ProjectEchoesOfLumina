package com.perceus.eol.branch.rel.material.generalmaterials;

import org.bukkit.Material;

import com.perceus.eol.branch.rel.enums.Rarity;
import com.perceus.eol.branch.rel.material.AbstractRelMaterial;
import com.perceus.eol.utils.PrintUtils;

public class ElementiaShard_Inferno extends AbstractRelMaterial
{

	public ElementiaShard_Inferno() 
	{
		super(PrintUtils.ColorParser("&r&l&cInferno&r&f Elementia Shard&r&f"), "inferno_elementia_shard", Material.BLAZE_POWDER, Rarity.FIVE, true, "A shard of concentrated Inferno Energy.");
	}

}
