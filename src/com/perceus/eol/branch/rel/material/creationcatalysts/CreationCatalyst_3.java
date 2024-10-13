package com.perceus.eol.branch.rel.material.creationcatalysts;

import org.bukkit.Material;

import com.perceus.eol.branch.rel.enums.Rarity;
import com.perceus.eol.utils.PrintUtils;

public class CreationCatalyst_3 extends AbstractCatalystObject
{

	public CreationCatalyst_3() 
	{
		super("Creation Catalyst III", "creation_catalyst_3", Material.NETHER_STAR, Rarity.THREE, true, true, 
				"An echo of the past once thought long forgotten.",
				"Now resurfaced, you hear a faint,",
				"yet familiar voice coming from within:",
				PrintUtils.ColorParser("&b'Find me!'"));
	}
}
