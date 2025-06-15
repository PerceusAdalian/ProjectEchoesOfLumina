package com.eol.utils;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;

public class PrintUtils
{
	public static String ColorParser(String msg) 
	{
		return ChatColor.translateAlternateColorCodes('&', msg);
	}
	
	public static void Print(String msg) 
	{
		Bukkit.getServer().getConsoleSender().sendMessage(ColorParser(msg));
	}
	
	public static void Print(Player player, String msg) 
	{
		player.getPlayer().sendMessage(ColorParser(msg));
	}
	
	public static void PrintToActionBar(Player player, String msg) 
	{
		player.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ColorParser(msg)));
	}
	
	public static void EolFormatPrint(Player player, String msg) 
	{
		Print(player, "&f<&eψ&r&f> "+msg+" &r&f/&e$&f//");
	}
	
	public static void EolFormatError(Player player, String msg) 
	{
		Print(player, "&f<&cψ&r&f> "+msg+" &r&f/&c!&f//");
	}
	
	public static void EolFormatDebug(Player player, String msg) 
	{
		Print(player.getPlayer(), "&f<&bψ&r&f> "+msg+ " &r&f/&b?&f//");
	}
	
	public static void EolConsolePrint(String msg) 
	{
		Print("&f<&eψ&r&f> "+msg+" &r&f/&e$&f//");
	}
	
	public static void EolConsoleError(String msg) 
	{
		Print("&f<&cψ&r&f> "+msg+" &r&f/&c!&f//");
	}
	
	public static void EolConsoleDebug(String msg) 
	{
		Print("&f<&bψ&r&f> "+msg+ " &r&f/&b?&f//");
	}
}
