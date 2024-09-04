package com.perceus.eol;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import com.perceus.eol.branch.events.PlayerJoinEventHandler;
import com.perceus.eol.branch.events.PlayerQuitEventHandler;
import com.perceus.eol.branch.events.PluginDamageToEntityEvent;
import com.perceus.eol.branch.mobgeneration.HealthBar;
import com.perceus.eol.branch.mobgeneration.MobGenerator;

public class ProjectEchoesOfLumina extends JavaPlugin
{
	public static ProjectEchoesOfLumina instance; 
	
	@Override
	public void onEnable() 
	{		
		instance = this;
		
		System.out.println("Echoes of Lumina Enabled");
		Bukkit.getPluginManager().registerEvents(new MobGenerator(), this);
		Bukkit.getPluginManager().registerEvents(new PluginDamageToEntityEvent(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerJoinEventHandler(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerQuitEventHandler(), this);
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
 */