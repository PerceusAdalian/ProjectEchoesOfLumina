package com.eol.branch.rel.material.unrefinedmaterials;

import org.bukkit.Material;

import com.eol.branch.rel.enums.Rarity;
import com.eol.utils.PrintUtils;

public class ElementiaEssence_Inferno extends AbstractUnrefinedRelMaterial
{

	public ElementiaEssence_Inferno() 
	{
		super(PrintUtils.ColorParser("&r&fEssence of &l&cInferno&r&f"), "elementia_essence_inferno", Material.BLAZE_POWDER, Rarity.NONE, false, true, "The essence of Inferno.",
				"This radiates the primordial energy of Hel unrefined,",
				"it needs to be concentrated for further use.");
	}

}
