package com.perceus.eol.branch.rel.material.creationcatalysts;

import org.bukkit.Material;

import com.perceus.eol.branch.rel.enums.Rarity;
import com.perceus.eol.utils.PrintUtils;

public class CreationCatalyst_4 extends AbstractCatalystObject
{

	public CreationCatalyst_4() 
	{
		super("Creation Catalyst IV", "creation_catalyst_4", Material.NETHER_STAR, Rarity.FOUR, true, true, 
				"An echo of the past once thought long forgotten.",
				"Now resurfaced, you hear a faint,",
				"yet familiar voice coming from within:",
				PrintUtils.ColorParser("&d'I'm coming sister!'"));
	}

}
