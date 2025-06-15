package com.eol;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.eol.branch.events.EolDamageEvent;
import com.eol.branch.events.EolMobDeathEvent;
import com.eol.branch.events.EolPlayerJoinEvent;
import com.eol.branch.events.EolPlayerQuitEvent;
import com.eol.branch.events.MaterialCastHandler;
import com.eol.branch.events.MobGenerateEvent;
import com.eol.branch.menus.EolGuiHandler;
import com.eol.branch.mobgeneration.HealthBar;
import com.eol.branch.rel.material.EolItemRegistry;
import com.eol.utils.PrintUtils;

public class ProjectEchoesOfLumina extends JavaPlugin
{
	public static ProjectEchoesOfLumina instance; 
	public static boolean debug;
	
	@Override
	public void onEnable() 
	{		
		instance = this;
		debug = false;
		
		this.getCommand("eol").setExecutor(new EchoesOfLuminaCommand());;
		
		Bukkit.getPluginManager().registerEvents(new MobGenerateEvent(), this);
		Bukkit.getPluginManager().registerEvents(new EolDamageEvent(), this);
		Bukkit.getPluginManager().registerEvents(new EolMobDeathEvent(), this);
		Bukkit.getPluginManager().registerEvents(new EolPlayerJoinEvent(), this);
		Bukkit.getPluginManager().registerEvents(new EolPlayerQuitEvent(), this);
		Bukkit.getPluginManager().registerEvents(new MaterialCastHandler(), this);
		EolGuiHandler.registerEventListener(instance);
		
		EolItemRegistry.itemInit();
		PrintUtils.EolConsolePrint("Echoes of Lumina -- &aOK");
		PrintUtils.EolConsolePrint("&e" + ((int) (EolItemRegistry.catalystRegistry.size() + EolItemRegistry.materiaRegistry.size() + EolItemRegistry.unrefinedMaterialRegistry.size())) + " &r&fEOL Items Loaded &aSuccessfully");
//		Bukkit.getPluginManager().registerEvents(new NaturalDamageToEntityEvent(), this);
//		this.getCommand("test").setExecutor(new EolCommand());
	}
	
	@Override
	public void onDisable() 
	{
		HealthBar.removeBossBars();
		PrintUtils.EolConsolePrint("Echoes of Lumina -- &3Disabling...");
	}
}

/*
 * Project Notes:
 * 		CRP (Creation Recollection Precept) -> REL (Resonance Echoic Liberation) | RELIVE (Resonance Echoic Liberation Integral Vivification Experience)
 *		-Current Tasks:
 *			1) Work on CRP materials; you p much have all the other shit ironed out! :D
 *			2) Write a map by which the materials are dropped via LootGenerateEvent and EntityDeathEvent
 *			3) 
 *
 * if (ProjectEchoesOfLumina.debug == true){}
 *
 *
 */

/*
 * Commonly Used Commands:
 * /kill @e[type=! player]
 */
