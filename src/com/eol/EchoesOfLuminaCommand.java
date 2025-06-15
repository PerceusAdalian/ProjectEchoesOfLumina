package com.eol;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.eol.branch.menus.EolGuiHandler;
import com.eol.branch.menus.instances.EolMainMenu;
import com.eol.branch.mobgeneration.CustomEolMob;
import com.eol.branch.rel.material.AbstractRelMaterial;
import com.eol.branch.rel.material.EolItemRegistry;
import com.eol.utils.EnumOfEntities;
import com.eol.utils.EolEffects;
import com.eol.utils.PrintUtils;

public class EchoesOfLuminaCommand implements CommandExecutor, TabCompleter
{
	public EchoesOfLuminaCommand() 
	{
		Bukkit.getPluginCommand("eol").setTabCompleter(this);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String s, String[] args) 
	{

		if (!(sender instanceof Player)) 
		{
			return false;
		}
		Player p = (Player) sender;
		
		if (!sender.isOp()) 
		{
			PrintUtils.EolFormatError(p, "&7Permission Denied");
			return false;
		}
		
		if (args.length == 0) 
		{
			PrintUtils.EolFormatError(p, "&7Invalid Argument(s)");
			return false;
		}
		
		if (args[0].equals("adhere")) 
		{
			
			if (args[1].equals("catalyst") && EolItemRegistry.catalystRegistry.containsKey(args[2])) 
			{				
				AbstractRelMaterial material = EolItemRegistry.catalystRegistry.get(args[2]);
				ItemStack stack = material.generate();
				p.getInventory().addItem(stack);
				return true;
			}
			
			if (args[1].equals("materia") && EolItemRegistry.materiaRegistry.containsKey(args[2])) 
			{
				AbstractRelMaterial material = EolItemRegistry.materiaRegistry.get(args[2]);
				ItemStack stack = material.generate();
				p.getInventory().addItem(stack);
				return true;
			}
			
			if (args[1].equals("unrefinedmaterial") && EolItemRegistry.unrefinedMaterialRegistry.containsKey(args[2])) 
			{
				AbstractRelMaterial material = EolItemRegistry.unrefinedMaterialRegistry.get(args[2]);
				ItemStack stack = material.generate();
				p.getInventory().addItem(stack);
				return true;
			}
			
		}
		
		if (args[0].equals("menu")) 
		{
			EolEffects.playSound(p, p.getLocation(), Sound.BLOCK_CHISELED_BOOKSHELF_PICKUP_ENCHANTED, SoundCategory.MASTER, 1, 1);
			EolGuiHandler.open(p, new EolMainMenu(p));
		}
		
		if (args[0].equals("debug"))
		{
			if (ProjectEchoesOfLumina.debug == false) 
			{
				ProjectEchoesOfLumina.debug = true;
				PrintUtils.EolFormatDebug(p, "&7Debug console logging has been turned &a&lON");
				return true;
			}
			ProjectEchoesOfLumina.debug = false;
			PrintUtils.EolFormatDebug(p, "&7Debug console logging has been turned &c&lOFF\"");
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
		    	PrintUtils.EolFormatError(p, "&7Invalid Argument(s). Was expecting an &o&dEntityType&r&f.");
		        return false;
		    }
			
			int levelValue;
			try 
			{
				levelValue = Integer.parseInt(args[2]);
			} 
			catch (NumberFormatException e)
			{
				PrintUtils.EolFormatError(p, "&7Invalid Argument(s). Was expecting &l&6Int&r&f.");
				return false;
			}
			
			if (levelValue < 1) 
			{
				PrintUtils.EolFormatError(p, "&7Invalid Argument(s). Was expecting &l&6Int&r&f (>=1).");
				return false;
			}
			
			CustomEolMob mob = new CustomEolMob(entityType, levelValue);
			mob.generate(p);
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
		    	PrintUtils.EolFormatError(p, "&7Invalid Argument(s). Was expecting &o&dEntityType&r&f.");
		        return false;
		    }
		    
			int levelValue;
			try 
			{
				levelValue = Integer.parseInt(args[2]);
			} 
			catch (NumberFormatException e)
			{
				PrintUtils.EolFormatError(p, "&7Invalid Argument(s). Was expecting &l&6Int&r&f.");
				return false;
			}
			
			if (!(levelValue >= 1)) 
			{
				PrintUtils.EolFormatError(p, "&7Invalid Argument(s). Was expecting &l&6Int&r&f (>=1).");
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
				PrintUtils.EolFormatError(p, "&7Invalid Argument(s). Was expecting &l&6Double&r&f for &aHP&f.");
				return false;
			}
			
			int armorValue;
			try 
			{
				armorValue = Integer.parseInt(args[5]);
			} 
			catch (NumberFormatException e)
			{
				PrintUtils.EolFormatError(p, "&7Invalid Argument(s). Was expecting &l&6Int&r&f for &6AP&f.");
				return false;
			}
			
			CustomEolMob mob = new CustomEolMob(entityType, levelValue, customName, healthValue, armorValue);
			mob.generate(p);
			return true;
		}
		
		else 
		{
			PrintUtils.EolFormatError(p, "Expecting valid arguments..");
		}
		
		return false;
	}
	
	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) 
	{
		return switch(args.length) 
		{
			case 0 -> List.of("eol");
			case 1 -> List.of("adhere","debug","generate","menu");
			case 2 -> 
			{
				yield switch(args[0])
				{
					case "adhere" -> List.of("catalyst", "materia", "unrefinedmaterial");
					case "debug" -> List.of();
					case "generate" -> EnumOfEntities.asList();
					default -> List.of();
				};
			}
			case 3 -> 
			{
				yield switch(args[1]) 
				{
					case "catalyst" -> new ArrayList<>(EolItemRegistry.catalystRegistry.keySet());
					case "materia" -> new ArrayList<>(EolItemRegistry.materiaRegistry.keySet());
					case "unrefinedmaterial" -> new ArrayList<>(EolItemRegistry.unrefinedMaterialRegistry.keySet());
					default -> List.of();
				};
			}
			default -> List.of();
		};
	}
	
}
