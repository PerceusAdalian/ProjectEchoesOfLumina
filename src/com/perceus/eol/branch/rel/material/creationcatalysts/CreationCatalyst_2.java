package com.perceus.eol.branch.rel.material.creationcatalysts;

import org.bukkit.Material;

import com.perceus.eol.branch.rel.enums.Rarity;
import com.perceus.eol.utils.PrintUtils;

public class CreationCatalyst_2 extends AbstractCatalystObject
{

	public CreationCatalyst_2() 
	{
		super("Creation Catalyst II", "creation_catalyst_2", Material.NETHER_STAR, Rarity.TWO, true, true, 
				"An echo of the past once thought long forgotten.",
				"Now resurfaced, you hear a faint,",
				"yet familiar voice coming from within:",
				PrintUtils.ColorParser("&9'The capital is under attack! Guard the gate!'"));
	}
}
