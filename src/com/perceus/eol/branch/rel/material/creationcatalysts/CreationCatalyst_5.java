package com.perceus.eol.branch.rel.material.creationcatalysts;

import org.bukkit.Material;

import com.perceus.eol.branch.rel.enums.Rarity;
import com.perceus.eol.utils.PrintUtils;

public class CreationCatalyst_5 extends AbstractCatalystObject
{

	public CreationCatalyst_5() 
	{
		super("Creation Catalyst V", "creation_catalyst_5", Material.NETHER_STAR, Rarity.FIVE, true, true, 
				"An echo of the past once thought long forgotten.",
				"Now resurfaced, you hear a faint,",
				"yet familiar voice coming from within:",
				PrintUtils.ColorParser("&e'The curtain is rising.. on the final act.'"));
	}

}
