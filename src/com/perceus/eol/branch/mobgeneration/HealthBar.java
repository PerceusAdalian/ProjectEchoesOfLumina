package com.perceus.eol.branch.mobgeneration;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.NamespacedKey;
import org.bukkit.boss.BarColor;
import org.bukkit.boss.BarStyle;
import org.bukkit.boss.BossBar;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.persistence.PersistentDataType;

import com.perceus.eol.ProjectEchoesOfLumina;
import com.perceus.eol.utils.PrintUtils;

public class HealthBar
{
	public static final NamespacedKey healthBarTitle = new NamespacedKey(ProjectEchoesOfLumina.instance, "boss_bar_title");
    public static final NamespacedKey healthBarProgress = new NamespacedKey(ProjectEchoesOfLumina.instance, "boss_bar_progress");
    public static final Map<UUID, BossBar> bossBars = new HashMap<>();
    public static String bossBarTitle;
	public static void createBossBar(Entity entity, Boolean b) 
	{
		
		if (ArbitraryHealthContainer.isBroken(entity)) 
		{
			bossBarTitle = PrintUtils.ColorParser("&f" + entity.getCustomName() + " &6AP&f: " + "&f&k0&r&e{&c&lBreak!&r&e}&f&k0");
		}
		else 
		{
			bossBarTitle = PrintUtils.ColorParser("&f" + entity.getCustomName() + " &6AP&f: &7(&f" + ArbitraryHealthContainer.getArmor(entity) + "&7/&f" + ArbitraryHealthContainer.getBaseMaxArmor(entity) + "&7)");
		}
		BossBar bar = Bukkit.createBossBar(bossBarTitle, BarColor.RED, BarStyle.SOLID);
		
		double maxHealth = ArbitraryHealthContainer.getBaseMaxHealth(entity);
        double currentHealth = ArbitraryHealthContainer.getHealth(entity);
        double progress = currentHealth / maxHealth;
//        PrintUtils.PrintError("Entity Being Created: " + entity.getCustomName() + "PROGRESS IN CREATE BOSS BAR IS: " + progress);
        if (progress > 1d) 
        {
        	progress = 1d;
        }
        if (progress < 0d) 
        {
        	progress = 0d;
        }
//        PrintUtils.PrintError("Entity Being Created: " + entity.getCustomName() + "PROGRESS IN CREATE BOSS BAR BELOW IF STATEMENT IS: " + progress);
		bar.setProgress(progress);
		bar.setVisible(b);
		
		entity.getPersistentDataContainer().set(healthBarTitle, PersistentDataType.STRING, bossBarTitle);
		entity.getPersistentDataContainer().set(healthBarProgress, PersistentDataType.DOUBLE, 1.0);
		
		for (Entity entityTarget : entity.getWorld().getEntities()) 
		{
            if (entityTarget instanceof Player) 
            {
                Player player = (Player) entityTarget;
                bar.addPlayer(player);
            }
        }
		
		bossBars.put(entity.getUniqueId(), bar);
	}
	
	public static void updateBossBar(Entity entity, Boolean b)
	{
        BossBar bar = bossBars.get(entity.getUniqueId());
        
        if (bar != null) 
        {
        	if (ArbitraryHealthContainer.isBroken(entity)) 
    		{
    			bossBarTitle = PrintUtils.ColorParser("&f" + entity.getCustomName() + " &6AP&f: &7(&f" + "&k0&r&e{&c&lBreak!&r&e}&f&k0&r"+ "&7)");
    		}
    		else 
    		{
    			bossBarTitle = PrintUtils.ColorParser("&f" + entity.getCustomName() + " &6AP&f: &7(&f" + ArbitraryHealthContainer.getArmor(entity) + "&7/&f" + ArbitraryHealthContainer.getBaseMaxArmor(entity) + "&7)");
    		}
            double maxHealth = ArbitraryHealthContainer.getBaseMaxHealth(entity);
            double currentHealth = ArbitraryHealthContainer.getHealth(entity);
            double progress = currentHealth / maxHealth;
            if (progress > 1d)
            {
            	progress = 1d;
            }
            if (progress < 0d) 
            {
            	progress = 0d;
            }
            	
            bar.setProgress(progress);
            bar.setTitle(bossBarTitle);
//            PrintUtils.Print("Entity Being DAMAGED: " + entity.getCustomName() + "PROGRESS IN UPDATE BOSS BAR IS: " + progress);
            bar.setVisible(b);
            entity.getPersistentDataContainer().set(healthBarProgress, PersistentDataType.DOUBLE, progress);
            return;
        }
        createBossBar(entity, true);
    }
	
	public static void hideBossBar(Entity entity) 
	{
		BossBar bar = bossBars.get(entity.getUniqueId());
		if (bar != null) bar.setVisible(false);
	}
	
	public static void addPlayer(Player player) 
	{
		for (Map.Entry<UUID, BossBar> entry : HealthBar.bossBars.entrySet()) 
		{
            BossBar bar = entry.getValue();
            bar.addPlayer(player.getPlayer());
        }
	}
	
	public static void removePlayer(Player player) 
	{
		for (Map.Entry<UUID, BossBar> entry : HealthBar.bossBars.entrySet()) 
		{
            BossBar bar = entry.getValue();
            bar.removePlayer(player.getPlayer());
        }
	}
	
	public static void removeBossBar(Entity entity) 
	{
        BossBar bar = bossBars.remove(entity.getUniqueId());

        if (bar != null) 
        {
            bar.setVisible(false);
            bar.removeAll();
        }
    }
	
	public static void removeBossBars() 
	{
		HealthBar.bossBars.forEach((key, bar) -> bar.removeAll());
        HealthBar.bossBars.clear();
	}
}

//double maxHealth = ArbitraryHealthContainer.getHealth(entity);