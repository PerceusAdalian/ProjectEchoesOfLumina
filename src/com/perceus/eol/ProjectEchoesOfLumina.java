package com.perceus.eol;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.perceus.eol.branch.events.CatalystInteractEvent;
import com.perceus.eol.branch.events.EolDamageEvent;
import com.perceus.eol.branch.events.EolMobDeathEvent;
import com.perceus.eol.branch.events.EolPlayerJoinEvent;
import com.perceus.eol.branch.events.EolPlayerQuitEvent;
import com.perceus.eol.branch.events.MobGenerateEvent;
import com.perceus.eol.branch.mobgeneration.HealthBar;
import com.perceus.eol.branch.rel.material.RelMaterialRegistry;
import com.perceus.eol.utils.PrintUtils;

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
		Bukkit.getPluginManager().registerEvents(new CatalystInteractEvent(), this);
		
		RelMaterialRegistry.catalystInit();
		RelMaterialRegistry.materiaInit();
		PrintUtils.Print("&c[!] &e&lProject: Echoes of Lumina Enabled Successfully");
//		Bukkit.getPluginManager().registerEvents(new NaturalDamageToEntityEvent(), this);
//		this.getCommand("test").setExecutor(new EolCommand());
	}
	
	@Override
	public void onDisable() 
	{
		HealthBar.removeBossBars();
	}
}

/*
 * Project Notes:
 * 		CRP (Creation Recollection Precept) -> REL (Resonance Echoic Liberation) | RELIVE (Resonance Echoic Liberation Integral Vivification Experience)
 *		-Current Tasks:
 *			1) Work on CRP materials; you p much have all the other shit ironed out! :D
 *			2) Make a command that can build an EOL mob; hailey's hint:
 *		-So, mob spawning is something that the `World` does. You can get the overworld with `Bukkit.getWorld("world")`. 
 *		-If you remember how to spawn projectiles, it's pretty similar. Poke around when you get a world instance.
 *			3) 
 *			4) 
 *			5) 
 *			6) Write a map by which the materials are dropped via LootGenerateEvent and EntityDeathEvent
 *
 * if (ProjectEchoesOfLumina.debug == true){}
 *
 *
 */


