package com.perceus.eol.branch.material.creationcatalysts;

import java.util.Map;
import java.util.WeakHashMap;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import com.perceus.eol.ProjectEchoesOfLumina;
import com.perceus.eol.branch.crp.enums.Rarity;
import com.perceus.eol.branch.crp.material.CrpMaterialObject;

public abstract class AbstractCreationCatalystObject extends CrpMaterialObject implements Listener
{
	static Map<Player, Object> timedPlayers = new WeakHashMap<>();
	private boolean isCatalyst;
	private static final long delay = 10;
	
	public AbstractCreationCatalystObject(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, String...description) 
	{
		super(name, internalName, material, rarity, enchantedEffect, description);
		Bukkit.getPluginManager().registerEvents(this, ProjectEchoesOfLumina.instance);
	}

	public AbstractCreationCatalystObject(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, boolean isCatalyst, String...description) 
	{
		super(name, internalName, material, rarity, enchantedEffect, description);
		this.isCatalyst = isCatalyst;
		Bukkit.getPluginManager().registerEvents(this, ProjectEchoesOfLumina.instance);
	}
	
	public boolean isCatalyst() {
		return isCatalyst;
	}
	
	public void setCatalyst(boolean isCatalyst) {
		this.isCatalyst = isCatalyst;
	}
	
	@EventHandler
	public void Cast(PlayerInteractEvent event) 
	{
		
		// Ensure the event was triggered by the main hand only
	    if (event.getHand() == null || !event.getHand().equals(EquipmentSlot.HAND)) 
	    {
	        return;
	    }
	    
	    // Ensure the action is a right-click in the air
	    if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
	    {
	        return;
	    }
	    
	    // Check if the player is holding an item in their main hand
	    ItemStack held = event.getPlayer().getInventory().getItem(EquipmentSlot.HAND);
	    
	    // Ensure the item is not null and not air
	    if (held == null || held.getType().equals(Material.AIR)) 
	    {
	        return;
	    }

	    // Check if the item has the persistent data we're looking for
	    if (!held.getItemMeta().getPersistentDataContainer().has(CrpMaterialObject.materialKey)) 
	    {
	        return;
	    }

	    // Assuming isCatalyst() is a method that checks for the catalyst condition
	    if (!isCatalyst()) 
	    {
	        return;
	    }
	    
	    if (timedPlayers.containsKey(event.getPlayer())) 
	    {
	    	return;
	    }
	    
	    //set up a timer to handle duplicate events firing..
	    timedPlayers.put(event.getPlayer(), held);
	    Bukkit.getScheduler().runTaskLater(ProjectEchoesOfLumina.instance, ()-> timedPlayers.remove(event.getPlayer(), held), delay);
	    
	    // Cancel the event and notify the player for debugging that the event is working
	    event.setCancelled(true);
	    event.getPlayer().sendMessage("This is working.");
	    return;
	}

}
