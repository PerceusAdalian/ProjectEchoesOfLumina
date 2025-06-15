package com.eol.branch.events;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

import com.eol.ProjectEchoesOfLumina;
import com.eol.branch.combat.CombatEffects;
import com.eol.branch.mobgeneration.ArbitraryHealthContainer;
import com.eol.branch.mobgeneration.HealthBar;
import com.eol.utils.AdvancedTimerRunnable;
import com.eol.utils.PrintUtils;

public class EolDamageEvent implements Listener
{
	public static Map<UUID, AdvancedTimerRunnable> mapOfTimers = new HashMap<>();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onHit(EntityDamageEvent event) 
	{
		//checks for the entity's PDC; if they are not an EOL mob, return
		if (!(event.getEntity().getPersistentDataContainer().has(MobGenerateEvent.mobKey))) 
		{
			return;
		}
		
		//runs a preliminary check for if the mob was already guard broken before this damage calculation. If so, then we deal an additional 10% damage relative to their max health.
		if (ArbitraryHealthContainer.isBroken(event.getEntity())) 
		{
			ArbitraryHealthContainer.damagePercent(event.getEntity(), 10);
		}
		
		//run damage calculations
		ArbitraryHealthContainer.damage(event.getEntity(), event.getDamage());
		ArbitraryHealthContainer.damageArmor(event.getEntity(), event.getDamage()*0.5d);
		
		//Restores mob health to original max; nullifying the minecraft damage.
		((Damageable) event.getEntity()).setHealth(((Attributable) event.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		
		//check for guard breaks
		if (ArbitraryHealthContainer.isBroken(event.getEntity()))
		{
			CombatEffects.breakEffect(event.getEntity());
			
			mapOfTimers.put(event.getEntity().getUniqueId(), new AdvancedTimerRunnable(() ->
			{
				ArbitraryHealthContainer.setArmor(event.getEntity(), ArbitraryHealthContainer.getBaseMaxArmor(event.getEntity()));
				mapOfTimers.remove(event.getEntity().getUniqueId());
			}, 600));
		}
		
		//if the damager was a player, update the hud so that it shows the health and stats of the mob(s) hit
		if (event instanceof EntityDamageByEntityEvent) 
		{
			EntityDamageByEntityEvent damageEvent = (EntityDamageByEntityEvent) event;
			
			if (damageEvent.getDamager() instanceof Player)
			{
				HealthBar.updateBossBar(event.getEntity(), true); 
				if (!mapOfTimers.containsKey(event.getEntity().getUniqueId())) 
				{
					mapOfTimers.put(event.getEntity().getUniqueId(), new AdvancedTimerRunnable(() -> 
					{
						HealthBar.hideBossBar(event.getEntity());
						mapOfTimers.remove(event.getEntity().getUniqueId());
					}, 100));			
				}
				
				mapOfTimers.get(event.getEntity().getUniqueId()).reset();
			}
			
		}
		//run debug code (if applicable)
		if (ProjectEchoesOfLumina.debug == true) 
		{
			String mobName = event.getEntity().getCustomName().toString();
			PrintUtils.EolConsoleDebug("&fEolDamageEvent: &r&f" + mobName + " was dealt &e[&c" + event.getDamage() + "&e] &fdamage. &aHP&f//&6AR &fare now: &a" + 
			ArbitraryHealthContainer.getHealth(event.getEntity()) +"&f//&6"+
			ArbitraryHealthContainer.getArmor(event.getEntity()) +
			" &f|| &eEvent Concluded");
		}
		
		//finally, we kill the mob if it's dead in the data container
		//eval entity over the health - damage conditional evaluation to 0 (hp-d<0 == true)
		if (ArbitraryHealthContainer.isDead(event.getEntity()))
		{
			//kills the entity
			((Damageable) event.getEntity()).setHealth(0);
			HealthBar.removeBossBar(event.getEntity());
		}
	}
}