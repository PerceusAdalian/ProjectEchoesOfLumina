package com.perceus.eol.branch.events;

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

import com.perceus.eol.ProjectEchoesOfLumina;
import com.perceus.eol.branch.mobgeneration.ArbitraryHealthContainer;
import com.perceus.eol.branch.mobgeneration.HealthBar;
import com.perceus.eol.utils.AdvancedTimerRunnable;
import com.perceus.eol.utils.PrintUtils;

public class EolDamageEvent implements Listener
{
	public static Map<UUID, AdvancedTimerRunnable> mapOfTimers = new HashMap<>();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onHit(EntityDamageByEntityEvent event) 
	{
		//NOTE: AYO DO NOT USE 'event.getFinalDamage()' IT DOES NOT WORK!! :3
		
		if (!(event.getEntity().getPersistentDataContainer().has(MobGenerateEvent.mobKey))) 
		{
			return;
		}
		
		if (event.getEntity() instanceof Damageable) 
		{			
			((Damageable) event.getEntity()).setHealth(((Attributable) event.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());			
		}

		ArbitraryHealthContainer.damage(event.getEntity(), event.getDamage());
		ArbitraryHealthContainer.damageArmor(event.getEntity(), event.getDamage()*0.5d); // Yo wtf this actually works as intended now
		
		
		if (event.getDamager() instanceof Player)
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
		
		if (ProjectEchoesOfLumina.debug == true) 
		{
			String string = event.getEntity().getCustomName().toString();
			PrintUtils.Print("&e{{&cEOL_DEBUG&e}} &fEolDamageEvent: &r&f" + string + " was dealt &e[&c" + event.getDamage() + "&e] &fdamage. &aHP&f//&6AR &fare now: &a" + 
			ArbitraryHealthContainer.getHealth(event.getEntity()) +"&f//&6"+
			ArbitraryHealthContainer.getArmor(event.getEntity()) +
			" &f|| &eEvent Concluded");
		}
		//eval entity over the health - damage conditional evaluation to 0 (hp-d<0 == true)
		if (ArbitraryHealthContainer.isDead(event.getEntity(),ArbitraryHealthContainer.getHealth(event.getEntity()) , event.getDamage())); 
		{
			//kills the entity
			((Damageable) event.getEntity()).setHealth(0);
			HealthBar.removeBossBar(event.getEntity());
		}
	}
}

//if (ArbitraryHealthContainer.isBroken(event.getEntity()))
//{
//	CombatEffects.breakEffect(event.getEntity());
//}