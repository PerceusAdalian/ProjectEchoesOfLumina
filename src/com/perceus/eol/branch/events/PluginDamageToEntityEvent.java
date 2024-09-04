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
		if (!(event.getDamager() instanceof Player)) 
		{
			return;
		}	
		
		if (!(event.getEntity().getPersistentDataContainer().has(MobGenerator.mobKey))) 
		{
			return;
		}

		
		//cancel natural health damaging while still allowing for arb health dmg
		double offSetHealth = event.getFinalDamage();
		try
		{
			((Damageable) event.getEntity()).setHealth(((Damageable) event.getEntity()).getHealth() + offSetHealth);	
		} 
		catch(IllegalArgumentException e) 
		{
			
			((Damageable) event.getEntity()).setHealth(((Attributable) event.getEntity()).getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());			
		}
		
		ArbitraryHealthContainer.damage(event.getEntity(), event.getFinalDamage());
		ArbitraryHealthContainer.damageArmor(event.getEntity(), Math.ceil(event.getFinalDamage()*0.1d));
		
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
		if (ArbitraryHealthContainer.isDead(event.getEntity(), event.getFinalDamage(), ArbitraryHealthContainer.getHealth(event.getEntity()))) 
		{
			//kills the entity
			((Damageable) event.getEntity()).setHealth(0);
			HealthBar.removeBossBar(event.getEntity());
		}
	}
}
