package com.perceus.eol.branch.material.creationcatalysts;

import org.bukkit.Material;

import com.perceus.eol.branch.crp.enums.Rarity;
import com.perceus.eol.utils.PrintUtils;

public class CreationCatalyst_2 extends AbstractCreationCatalystObject
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
