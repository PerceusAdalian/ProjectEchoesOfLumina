package com.perceus.eol;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.perceus.eol.branch.events.EolDamageEvent;
import com.perceus.eol.branch.events.EolMobDeathEvent;
import com.perceus.eol.branch.events.EolPlayerJoinEvent;
import com.perceus.eol.branch.events.EolPlayerQuitEvent;
import com.perceus.eol.branch.events.MaterialInteractEvent;
import com.perceus.eol.branch.events.MobGenerateEvent;
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
		Bukkit.getPluginManager().registerEvents(new MaterialInteractEvent(), this);
		
		CrpMaterials.init();
		PrintUtils.Print("&c[!] &e&lProject: Echoes of Lumina Enabled Successfully");
//		Bukkit.getPluginManager().registerEvents(new NaturalDamageToEntityEvent(), this);
//		this.getCommand("test").setExecutor(new EolCommand());
	}
	
	@Override
	public void onDisable() 
	{
		
	}
}

/*
 * Project Notes:
 * 		
 *		-Current Tasks:
 *			1) Work on CRP materials; you p much have all the other shit ironed out! :D
 *			2) 
 *			3) 
 *			4) 
 *			5) 
 *			6) Write a map by which the materials are dropped via LootGenerateEvent and EntityDeathEvent
 *
 * if (ProjectEchoesOfLumina.debug == true){}
 *
 *
 */


