package com.perceus.eol;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.perceus.eol.branch.events.EolMobDeathEvent;
import com.perceus.eol.branch.events.MaterialInteractEvent;
import com.perceus.eol.branch.events.MobGenerateEvent;
import com.perceus.eol.branch.events.PlayerJoinEventHandler;
import com.perceus.eol.branch.events.PlayerQuitEventHandler;
import com.perceus.eol.branch.events.EolDamageEvent;
import com.perceus.eol.branch.mobgeneration.HealthBar;
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
		Bukkit.getPluginManager().registerEvents(new PlayerJoinEventHandler(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitEventHandler(), this);
		Bukkit.getPluginManager().registerEvents(new MaterialInteractEvent(), this);
		
		CrpMaterials.init();
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
 * 		
 *		-Current Tasks:
 *			1) Refactor the whole project such that using interfaces allows/disallows methods to inherit via CrpMaterial super's constructor. 
 *			2) Dynamically Create Creation Catalysts depending on the Enum: ONE->SEVEN such that the enum dictates all of the assets generated in relation to the mockups 
 *			3) Delete CrpMaterial_CreationCatalyst and subsume using an interface to dictate which CrpMaterial inherits the openMenu method
 *			4) Maybe create an enum that dictates whether something is or isn't a creation catalyst and integrate using the interface. 
 *			5) Reformat the generate() method in CrpMaterial's abstract methods.
 *			6) Write a map by which the materials are dropped via LootGenerateEvent and EntityDeathEvent
 *
 * if (ProjectEchoesOfLumina.debug == true){}
 *
 *
 */


