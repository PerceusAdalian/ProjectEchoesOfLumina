package com.eol.branch.events;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

import com.eol.ProjectEchoesOfLumina;
import com.eol.branch.menus.EolGuiHandler;
import com.eol.branch.menus.instances.EolMainMenu;
import com.eol.branch.rel.material.AbstractRelMaterial;
import com.eol.branch.rel.material.EolItemRegistry;
import com.eol.utils.EolPlayerActions;
import com.eol.utils.PrintUtils;

public class MaterialCastHandler implements Listener
{
	@EventHandler
	public boolean onCast(PlayerInteractEvent e) 
	{
		ItemStack held = e.getPlayer().getInventory().getItem(EquipmentSlot.HAND);
		
		if (e.getHand() == null) return false;
		if (e.getHand().equals(EquipmentSlot.OFF_HAND) || !e.getHand().equals(EquipmentSlot.HAND)) return false;
		if (held == null || held.getType().equals(Material.AIR)) return false;		
		if (held.getItemMeta() == null || !held.getItemMeta().getPersistentDataContainer().has(AbstractRelMaterial.catalystKey, PersistentDataType.STRING)) return false;

		if (EolItemRegistry.catalystRegistry.get(held.getItemMeta().getPersistentDataContainer().get(AbstractRelMaterial.catalystKey, PersistentDataType.STRING)) != null && EolPlayerActions.rightClickAir(e)) 
	    {
	    	e.setCancelled(true);
			EolGuiHandler.open(e.getPlayer(), new EolMainMenu(e.getPlayer()));
			
			if (ProjectEchoesOfLumina.debug) 
			{

				PrintUtils.EolFormatDebug(e.getPlayer(), e.getAction().toString());
				PrintUtils.EolFormatDebug(e.getPlayer(), e.getHand().toString());
				String internalName = null;
				if (held.getItemMeta() != null) 
				{
					internalName = held.getItemMeta().getPersistentDataContainer().get(AbstractRelMaterial.materialKey, PersistentDataType.STRING);
				}
				
				if (internalName == null) 
				{
					PrintUtils.EolConsoleError("From: MoaCastHandler.java | Could not retrieve internal name from baked item.");
	                return true;
				}
				
				AbstractRelMaterial relObject = EolItemRegistry.catalystRegistry.get(internalName);
				if (relObject == null) 
				{
					PrintUtils.EolConsoleError("From: MoaCastHandler.java | Internal name exists, but item is not in the registry.");
	                return true;
				}
				PrintUtils.EolConsoleDebug(e.getPlayer().getName() + " has used item: " + relObject.getName());
			}
			return true;
	    }
		return false;
	}
}
