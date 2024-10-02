package com.perceus.eol.branch.combat;

import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.perceus.eol.branch.mobgeneration.ArbitraryHealthContainer;

public class CombatEffects
{
	//this checks for "BREAK" and if true, apply BREAK EFFECT
	public static void breakEffect(Entity entity) 
    {
    	if (ArbitraryHealthContainer.isBroken(entity)) 
    	{
    		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, 400, 99, true));
    		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 400, 4, true));
    		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, 400, 4, true));
    		ArbitraryHealthContainer.damagePercent(entity, 10);
    	}
    }
}
