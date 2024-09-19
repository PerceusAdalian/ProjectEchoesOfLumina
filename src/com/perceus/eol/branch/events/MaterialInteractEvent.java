package com.perceus.eol.branch.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.perceus.eol.CrpMaterials;
import com.perceus.eol.branch.crp.material.CreationCatalystObject;

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

		String material = held.getItemMeta().getPersistentDataContainer().get(CreationCatalystObject.idKey, PersistentDataType.STRING);
		
		if (!CrpMaterials.materialRegistry.containsKey(material)) 
		{
			return;
		}
	}
}
