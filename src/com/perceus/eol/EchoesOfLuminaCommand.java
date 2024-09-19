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
		Bukkit.getPluginCommand("adhere").setTabCompleter(this);
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
			player.sendMessage(PrintUtils.ColorParser("&c[!] You do not have permission to use this command."));
			return false;
		}
		
		if (args.length == 0) 
		{
			player.sendMessage(PrintUtils.ColorParser("&c[!] This branch is either not implemented, or more arguments are required."));
			return false;
		}
		
		if (CrpMaterials.materialRegistry.containsKey(args[0])) 
		{
			CrpMaterialObject material = CrpMaterials.materialRegistry.get(args[0]);
			ItemStack stack = material.generate();
			player.getInventory().addItem(stack);
			return true;
		}
		
		else 
		{
			player.sendMessage(PrintUtils.ColorParser("&c[!] Invalid Item Name"));
		}
		
		return false;
	}

	@Override
	public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args) 
	{
		return new ArrayList<>(CrpMaterials.materialRegistry.keySet());
	}
}
