package com.perceus.eol.branch.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.perceus.eol.branch.crp.material.CrpMaterialObject;

public class MaterialInteractEvent implements Listener
{
	@EventHandler
	public void openMenu(PlayerInteractEvent event) 
	{
		ItemStack held;
		held = event.getPlayer().getInventory().getItem(EquipmentSlot.HAND);
		
		if (event.getHand() == null) 
		{
			return;
		}
		
		if (event.getHand().equals(EquipmentSlot.OFF_HAND)) 
		{
			return;
		}
		
		if (held == null || held.getType().equals(Material.AIR)) 
		{
			return;
		}

		if (!held.getItemMeta().getPersistentDataContainer().has(CrpMaterialObject.materialKey)) 
		{
			return;
		}
	}
}
