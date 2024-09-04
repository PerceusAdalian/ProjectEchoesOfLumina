package com.perceus.eol.branch.crp;

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
    	if (ArbitraryHealthContainer.isBroken(entity) == true) 
    	{
    		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.SLOWNESS, PotionEffect.INFINITE_DURATION, 2, true));
    		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, PotionEffect.INFINITE_DURATION, 2, true));
    		((LivingEntity) entity).addPotionEffect(new PotionEffect(PotionEffectType.MINING_FATIGUE, PotionEffect.INFINITE_DURATION, 2, true));
    		ArbitraryHealthContainer.damagePercent(entity, 10);
    	}
    	
    }
}
