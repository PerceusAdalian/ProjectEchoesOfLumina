package com.perceus.eol.branch.mobgeneration;

import org.bukkit.NamespacedKey;
import org.bukkit.entity.Entity;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataType;

import com.perceus.eol.ProjectEchoesOfLumina;

public class ArbitraryHealthContainer
{
	
	public static NamespacedKey mobBaseHealthKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "mob_base_health_key");
	public static NamespacedKey mobBaseMinimumHealthKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "mob_base_minimum_health_key");
	public static NamespacedKey mobHealthKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "mob_health_key");
	
	public static NamespacedKey mobBaseArmorKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "mob_base_armor_key");
	public static NamespacedKey mobBaseMinimumArmorKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "mob_base_minimum_armor_key");
	public static NamespacedKey mobArmorKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "mob_armor_key");
//	public static Map<Entity, NamespacedKey[]> mapOfKeys = new HashMap<Entity, NamespacedKey[]>();
	
	public static void setBaseMaxHealth(Entity entity, double health) 
	{
		PersistentDataContainer data = entity.getPersistentDataContainer();
        data.set(mobBaseHealthKey, PersistentDataType.DOUBLE, health);
        setHealth(entity, health);
	}
	
	public static Double getBaseMaxHealth(Entity entity) 
	{
		PersistentDataContainer data = entity.getPersistentDataContainer();
		return data.get(mobBaseHealthKey, PersistentDataType.DOUBLE);
	}
	
	public static void setBaseMinHealth(Entity entity, double health) 
	{
		PersistentDataContainer data = entity.getPersistentDataContainer();
        data.set(mobBaseMinimumHealthKey, PersistentDataType.DOUBLE,  health);
	}
	
	public static Double getBaseMinHealth(Entity entity) 
	{
		PersistentDataContainer data = entity.getPersistentDataContainer();
		return data.get(mobBaseMinimumHealthKey, PersistentDataType.DOUBLE);
	}
	
	public static void setMaxHealth(Entity entity) 
	{
		Double baseHealth = getBaseMaxHealth(entity);
		if (baseHealth != null) 
		{
			setHealth(entity, baseHealth);
		}
	}
	
	public static void setHealth(Entity entity, double health) 
	{
        PersistentDataContainer data = entity.getPersistentDataContainer();
        data.set(mobHealthKey, PersistentDataType.DOUBLE, health);
    }

    public static Double getHealth(Entity entity) 
    {
        PersistentDataContainer data = entity.getPersistentDataContainer();
        return data.get(mobHealthKey, PersistentDataType.DOUBLE);
    }

    public static void damage(Entity entity, double damage) 
    {
        Double currentHealth = getHealth(entity);
        if (currentHealth != null) 
        {
            double newHealth = currentHealth - damage;
            Double minHealth = getBaseMinHealth(entity);
            if (minHealth != null && newHealth < minHealth) 
            {
            	setHealth(entity, minHealth);
            } 
            else 
            {
                setHealth(entity, Math.max(newHealth, 0));
            }
        }
    }

    public static void damagePercent(Entity entity, double percent) 
    {
    	Double maxHealth = getBaseMaxHealth(entity);
    	if (maxHealth != null) 
    	{
            double damage = (percent / 100.0) * maxHealth;
            damage(entity, damage);
        }
    }

    public static void heal(Entity entity, double healAmount) 
    {
        Double currentHealth = getHealth(entity);
        if (currentHealth != null) 
        {
            Double maxHealth = getBaseMaxHealth(entity);
            if (maxHealth != null) 
            {
                double newHealth = currentHealth + healAmount;
                setHealth(entity, Math.min(newHealth, maxHealth));
            }
        }
    }
    
    // Armor related methods

    public static void setBaseMaxArmor(Entity entity, double armor) 
	{
		PersistentDataContainer data = entity.getPersistentDataContainer();
        data.set(mobBaseArmorKey, PersistentDataType.DOUBLE, armor);
        setArmor(entity, armor);
	}
	
	public static Double getBaseMaxArmor(Entity entity) 
	{
		PersistentDataContainer data = entity.getPersistentDataContainer();
		return data.get(mobBaseArmorKey, PersistentDataType.DOUBLE);
	}
	
	public static void setBaseMinArmor(Entity entity, double armor) 
	{
		PersistentDataContainer data = entity.getPersistentDataContainer();
        data.set(mobBaseMinimumArmorKey, PersistentDataType.DOUBLE, armor);
	}
	
	public static Double getBaseMinArmor(Entity entity) 
	{
		PersistentDataContainer data = entity.getPersistentDataContainer();
		return data.get(mobBaseMinimumArmorKey, PersistentDataType.DOUBLE);
	}
	
	public static void setMaxArmor(Entity entity) 
	{
		Double baseArmor = getBaseMaxArmor(entity);
		if (baseArmor != null) 
		{
			setArmor(entity, baseArmor);
		}
	}
	
	public static void setArmor(Entity entity, double value) 
    {
        PersistentDataContainer data = entity.getPersistentDataContainer();
        data.set(mobArmorKey, PersistentDataType.DOUBLE, value);
    }

    public static Double getArmor(Entity entity) 
    {
        PersistentDataContainer data = entity.getPersistentDataContainer();
        return data.get(mobArmorKey, PersistentDataType.DOUBLE);
    }

    public static void damageArmor(Entity entity, double damage) 
    {
        Double currentArmor = getArmor(entity);
        if (currentArmor != null) 
        {
            double newArmor = currentArmor - damage;
            Double minArmor = getBaseMinArmor(entity);
            if (minArmor != null && newArmor < minArmor) 
            {
                setArmor(entity, minArmor);
            } 
            else 
            {
                setArmor(entity, Math.max(newArmor, 0));
            }
        }
    }

    public static void healArmor(Entity entity, double healAmount) 
    {
        Double currentArmor = getArmor(entity);
        if (currentArmor != null) 
        {
            Double maxArmor = getBaseMaxArmor(entity);
            if (maxArmor != null) 
            {
                double newArmor = currentArmor + healAmount;
                setArmor(entity, Math.min(newArmor, maxArmor));
            }
        }
    }
	
    //msc methods
    
    public static boolean isBroken(Entity entity) 
    {
    	Double currentArmor = getArmor(entity);
    	return currentArmor != null && currentArmor <= 0;
    }
    
    public static boolean isDead(Entity entity, double damage, double currenthealth) 
    {
		return currenthealth - damage < 0;
    }
    
}
