package com.perceus.eol.branch.events;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Damageable;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

import com.perceus.eol.branch.mobgeneration.ArbitraryHealthContainer;
import com.perceus.eol.branch.mobgeneration.HealthBar;
import com.perceus.eol.branch.mobgeneration.MobGenerator;
import com.perceus.eol.utils.AdvancedTimerRunnable;

public class PluginDamageToEntityEvent implements Listener
{
	public static Map<UUID, AdvancedTimerRunnable> mapOfTimers = new HashMap<>();
	
	@EventHandler(priority = EventPriority.HIGHEST)
	public void onHit(EntityDamageByEntityEvent event) 
	{
		//so anyways, can you explain to me why removing this one fucking condition actually fixes shit? :D get fucked.
//		if (!(event.getDamager() instanceof Player)) 
//		{
//			return;
//		}	
		
		//NOTE: AYO DO NOT FUCKING USE 'event.getFinalDamage()' IT DOES NOT WORK!! :3
		
		if (!(event.getEntity().getPersistentDataContainer().has(MobGenerator.mobKey))) 
		{
			return;
		}
		
		//So anyways why tf did we use an arbitrary cancellation instead of just a nullification and maximization of their health? :D
		//cancel natural health damaging while still allowing for arb health dmg by creating a native offset
		((Damageable) event.getEntity()).setHealth(((Attributable) event.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());			
		
		ArbitraryHealthContainer.damage(event.getEntity(), event.getDamage());
		ArbitraryHealthContainer.damageArmor(event.getEntity(), (int) event.getDamage()*0.5d); // Yo wtf this actually works as intended now
		
//		if (ArbitraryHealthContainer.isBroken(event.getEntity()))
//		{
//			CombatEffects.breakEffect(event.getEntity());
//		}
		
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
		
		//eval entity over the health - damage conditional evaluation to 0 (hp-d<0 == true)
		if (ArbitraryHealthContainer.isDead(event.getEntity(), event.getDamage(), ArbitraryHealthContainer.getHealth(event.getEntity()))) 
		{
			//kills the entity
			((Damageable) event.getEntity()).setHealth(0);
			HealthBar.removeBossBar(event.getEntity());
		}
	}
}
