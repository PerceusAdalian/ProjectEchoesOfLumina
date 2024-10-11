package com.perceus.eol.branch.events;

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
import com.perceus.eol.branch.crp.material.AbstractCrpMaterialObject;

public class CreationCatalystInteractEvent implements Listener
{

	static Map<Player, Object> timedPlayers = new WeakHashMap<>();
	private static final long delay = 10;

	@EventHandler
	public void materialCast(PlayerInteractEvent event) 
	{
		ItemStack held = event.getPlayer().getInventory().getItem(EquipmentSlot.HAND);
		
		if (event.getHand() == null || !event.getHand().equals(EquipmentSlot.HAND)) 
		{
			return;
		}
		
		if (!event.getAction().equals(Action.RIGHT_CLICK_AIR)) 
		{
			return;
		}
		
		if (held == null || held.getType().equals(Material.AIR)) 
		{
			return;
		}
		
		if (!held.getItemMeta().getPersistentDataContainer().has(AbstractCrpMaterialObject.catalystKey)) 
		{
			return;
		}
		
		if (timedPlayers.containsKey(event.getPlayer())) 
		{
			return;
		}
		
		timedPlayers.put(event.getPlayer(), held);
		Bukkit.getScheduler().runTaskLater(ProjectEchoesOfLumina.instance, ()-> timedPlayers.remove(event.getPlayer(), held), delay);
		
		event.setCancelled(true);
		event.getPlayer().sendMessage("This is working.");
		return;
		
	}
	
}
