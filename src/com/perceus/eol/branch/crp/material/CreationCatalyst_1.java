package com.perceus.eol.branch.crp.material;

import org.bukkit.Material;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import com.perceus.eol.branch.crp.enums.Rarity;

public class CreationCatalyst_1 extends CrpMaterial_CreationCatalyst
{

	public CreationCatalyst_1() 
	{
		super("Creation Catalyst", "creation_catalyst_1", Material.NETHER_STAR, Rarity.ONE, true, true, "");
	}

	@Override
	public boolean openCrpMenu(PlayerInteractEvent event) 
	{
		
		boolean catalyst = this.isCatalyst();
		
		if (this.isCatalyst()) 
		{
			if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
			{
				return false;
			}
			//open le gui
		}
		return catalyst;
	}

}
