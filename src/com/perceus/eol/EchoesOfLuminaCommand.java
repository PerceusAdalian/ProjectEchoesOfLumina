package com.perceus.eol;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.perceus.eol.branch.mobgeneration.CustomEolMob;
import com.perceus.eol.branch.rel.material.AbstractRelMaterial;
import com.perceus.eol.branch.rel.material.RelMaterialRegistry;
import com.perceus.eol.utils.EnumOfEntities;
import com.perceus.eol.utils.PrintUtils;

public class EchoesOfLuminaCommand implements CommandExecutor, TabCompleter
{
	public EchoesOfLuminaCommand() 
	{
		Bukkit.getPluginCommand("eol").setTabCompleter(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) 
	{
		Player player = (Player) sender;

		if (!(sender instanceof Player)) 
		{
			return false;
		}
		
		if (!sender.isOp()) 
		{
			player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Permission Denied"));
			return false;
		}
		
		if (args.length == 0) 
		{
			player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Invalid Argument(s)"));
			return false;
		}
		
		if (args[0].equals("adhere")) 
		{
			
			if (args[1].equals("catalyst") && RelMaterialRegistry.catalystRegistry.containsKey(args[2])) 
			{				
				AbstractRelMaterial material = RelMaterialRegistry.catalystRegistry.get(args[2]);
				ItemStack stack = material.generate();
				player.getInventory().addItem(stack);
				return true;
			}
			
			if (args[1].equals("materia") && RelMaterialRegistry.materiaRegistry.containsKey(args[2])) 
			{
				AbstractRelMaterial material = RelMaterialRegistry.materiaRegistry.get(args[2]);
				ItemStack stack = material.generate();
				player.getInventory().addItem(stack);
				return true;
			}
			
		}
		
		
		if (args[0].equals("debug"))
		{
			if (ProjectEchoesOfLumina.debug == false) 
			{
				ProjectEchoesOfLumina.debug = true;
				player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Debug console logging has been turned &a&lON"));
				return true;
			}
			ProjectEchoesOfLumina.debug = false;
			player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Debug console logging has been turned &c&lOFF"));
			return true;
		}
		
		if (args[0].equals("generate") && args.length == 3) 
		{		
			EntityType entityType;
		    try 
		    {
		        entityType = EntityType.valueOf(args[1]);
		    } 
		    catch (IllegalArgumentException e)
		    {
		        player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Invalid Argument(s). Was expecting an &o&dEntityType&r&f."));
		        return false;
		    }
			
			int levelValue;
			try 
			{
				levelValue = Integer.parseInt(args[2]);
			} 
			catch (NumberFormatException e)
			{
				player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Invalid Argument(s). Was expecting &l&6Int&r&f."));
				return false;
			}
			
			if (levelValue < 1) 
			{
				player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Invalid Argument(s). Was expecting &l&6Int&r&f (>=1)."));
				return false;
			}
			
			CustomEolMob mob = new CustomEolMob(entityType, levelValue);
			mob.generate(player);
			return true;
		}
		
		if (args[0].equals("generate") && args.length >= 6)
		{
			EntityType entityType;
		    try 
		    {
		        entityType = EntityType.valueOf(args[1]);
		    } 
		    catch (IllegalArgumentException e)
		    {
		        player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Invalid Argument(s). Was expecting &o&dEntityType&r&f."));
		        return false;
		    }
		    
			int levelValue;
			try 
			{
				levelValue = Integer.parseInt(args[2]);
			} 
			catch (NumberFormatException e)
			{
				player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Invalid Argument(s). Was expecting &l&6Int&r&f."));
				return false;
			}
			
			if (!(levelValue >= 1)) 
			{
				player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Invalid Argument(s). Was expecting &l&6Int&r&f (>=1)."));
				return false;
			}
			
			String customName = args[3]; // Assuming this is a string, no parsing needed.
			
			double healthValue;
			try 
			{
				healthValue = Double.parseDouble(args[4]);
			} 
			catch (NumberFormatException e)
			{
				player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Invalid Argument(s). Was expecting &l&6Double&r&f for &aHP&f."));
				return false;
			}
			
			int armorValue;
			try 
			{
				armorValue = Integer.parseInt(args[5]);
			} 
			catch (NumberFormatException e)
			{
				player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Invalid Argument(s). Was expecting &l&6Int&r&f for &6AP&f."));
				return false;
			}
			
			CustomEolMob mob = new CustomEolMob(entityType, levelValue, customName, healthValue, armorValue);
			mob.generate(player);
			return true;
		}
		
		else 
		{
			player.sendMessage(PrintUtils.ColorParser("&e[&c!&e] &7Invalid Argument(s)"));
		}
		
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) 
	{
		return switch(args.length) 
		{
			case 0 -> List.of("eol");
			case 1 -> List.of("adhere","debug","generate");
			case 2 -> 
			{
				yield switch(args[0])
				{
					case "adhere" -> List.of("catalyst", "materia");
					case "debug" -> List.of();
					case "generate" -> EnumOfEntities.asList();
					default -> List.of();
				};
			}
			case 3 -> 
			{
				yield switch(args[1]) 
				{
					case "catalyst" -> new ArrayList<>(RelMaterialRegistry.catalystRegistry.keySet());
					case "materia" -> new ArrayList<>(RelMaterialRegistry.materiaRegistry.keySet());
					default -> List.of();
				};
			}
			default -> List.of();
		};
	}
	
}
