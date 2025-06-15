package com.eol.branch.rel.material.generalmaterials;

import org.bukkit.Material;

import com.eol.branch.rel.enums.Rarity;
import com.eol.branch.rel.material.AbstractRelMaterial;

public class WoodenBlock_1 extends AbstractRelMaterial
{

	public WoodenBlock_1() 
	{
		super("Wooden Block", "wooden_block_1", Material.OAK_BUTTON, Rarity.ONE, true, 
				"There once was a boy.",
				"He had a family with parents who'd loved him.",
				"His father gave him a sword in hopes that one day,",
				"he would be ready for the war to come.");
	}

}
