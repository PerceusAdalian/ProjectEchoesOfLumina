package com.eol.branch.mobgeneration;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attributable;
import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import com.eol.ProjectEchoesOfLumina;
import com.eol.branch.events.MobGenerateEvent;
import com.eol.utils.Nullable;
import com.eol.utils.PrintUtils;

public abstract class AbstractEolMobObject 
{
	public static final NamespacedKey uniqueEolMobKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "unique_eol_mob");
	
	private EntityType eType;
	private int level;
	private String name;
	private double health;
	private int armor;
	
	public AbstractEolMobObject(EntityType eType, int level, @Nullable String name, double health, int armor) 
	{
		this.eType = eType;
		this.level = level;
		this.name = name;
		this.health = health;
		this.armor = armor;
	}
	
	public AbstractEolMobObject(EntityType eType, int level) 
	{
		this.eType = eType;
		this.level = level;
	}

	public EntityType getEntityType() 
	{
		return eType;
	}
	
	public void setEntityType(EntityType eType) 
	{
		this.eType = eType;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}
	
	public int getLevel() 
	{
		return level;
	}
	
	public void setLevel(int level) 
	{
		this.level = level;
	}
	
	public double getHealth() {
		return health;
	}
	
	public void setHealth(double health) 
	{
		this.health = health;
	}
	
	public int getArmor() 
	{
		return armor;
	}
	
	public void setArmor(int armor) 
	{
		this.armor = armor;
	}
	
	public Entity generate(Player player) 
	{
		Entity eolMob = player.getWorld().spawnEntity(player.getLocation(), getEntityType());

		if (getName() == null) 
		{
			String inherentName = getEntityType().toString().toLowerCase();
			String[] splitName = inherentName.split("_");
			inherentName = "";
			for (String s : splitName) 
			{
				char[] chars = s.toCharArray();
				chars[0] = Character.toUpperCase(chars[0]);
				inherentName += new String(chars);
				inherentName += " ";
			}
			inherentName = inherentName.substring(0, inherentName.length() - 1);
			setName(inherentName);
		}
		
		if (getHealth() == 0) 
		{
			int mobLevel = getLevel();
			double healthModifier = (0.15d * mobLevel) + 1.0;
			setHealth((((Attributable) eolMob).getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() * healthModifier));
		}
		
		if (getArmor() == 0) 
		{
			int mobLevel = getLevel();
			double healthModifier = (0.15d * mobLevel) + 1.0;
			setArmor((int) ((int) (getHealth() * healthModifier)*0.5));
		}
		
		ArbitraryHealthContainer.setBaseMaxHealth(eolMob, getHealth());
		ArbitraryHealthContainer.setBaseMinHealth(eolMob);
		ArbitraryHealthContainer.setBaseMaxArmor(eolMob, getArmor());
		ArbitraryHealthContainer.setBaseMinArmor(eolMob);
		
		eolMob.setCustomName(PrintUtils.ColorParser("&e{&f&lLvl&r&f: " + getLevel() + "&e} &f" + getName()));
		eolMob.setCustomNameVisible(true);
		
		
		eolMob.getPersistentDataContainer().set(MobGenerateEvent.mobKey, PersistentDataType.INTEGER, getLevel());
		eolMob.getPersistentDataContainer().set(uniqueEolMobKey, PersistentDataType.STRING, getName());

		HealthBar.createBossBar(eolMob, false);
		
		if (ProjectEchoesOfLumina.debug == true) 
		{
			String string = eolMob.getCustomName().toString();
			PrintUtils.Print("&e{{&cEOL_DEBUG&e}} &fEolMobObject: &f" + string + " || &aGenerated Successfully");
		}
		
		return eolMob;
		
	}
}
