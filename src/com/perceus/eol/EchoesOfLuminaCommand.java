package com.perceus.eol;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabCompleter;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import com.perceus.eol.branch.crp.material.CrpMaterialObject;
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
		
		if (args[0].equals("adhere") && CrpMaterials.materialRegistry.containsKey(args[1])) 
		{
			CrpMaterialObject material = CrpMaterials.materialRegistry.get(args[1]);
			ItemStack stack = material.generate();
			player.getInventory().addItem(stack);
			return true;
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
			case 1 -> List.of("adhere","debug");
			case 2 -> 
			{
				yield switch(args[0])
				{
					case "adhere" -> new ArrayList<>(CrpMaterials.materialRegistry.keySet());
					case "debug" -> List.of();
					default -> List.of();
				};
			}
			
			default -> List.of();
		};

				
				//new ArrayList<>(CrpMaterials.materialRegistry.keySet());
	}
}
