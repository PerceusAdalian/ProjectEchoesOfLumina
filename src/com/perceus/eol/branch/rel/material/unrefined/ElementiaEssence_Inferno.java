package com.perceus.eol.branch.rel.material.unrefined;

import org.bukkit.Material;

import com.perceus.eol.branch.rel.enums.Rarity;
import com.perceus.eol.utils.PrintUtils;

public class ElementiaEssence_Inferno extends AbstractUnrefinedRelMaterial
{

	public ElementiaEssence_Inferno() 
	{
		super(PrintUtils.ColorParser("&r&fEssence of &l&cInferno&r&f"), "elementia_essence_inferno", Material.BLAZE_POWDER, Rarity.NONE, false, true, "The essence of Inferno.",
				"This radiates the primordial energy of Hel unrefined,",
				"it needs to be concentrated for further use.");
	}

}
