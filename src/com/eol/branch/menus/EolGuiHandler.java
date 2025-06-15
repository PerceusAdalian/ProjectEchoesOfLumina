package com.eol.branch.menus;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.plugin.java.JavaPlugin;

import com.eol.ProjectEchoesOfLumina;

public class EolGuiHandler implements Listener
{
	private static final Map<Player, AbstractEolGui> openGuis = new HashMap<>();

    public static void open(Player player, AbstractEolGui gui) 
    {
        openGuis.put(player, gui);
        gui.open();
    }

    public static AbstractEolGui getOpenGui(Player player) 
    {
        return openGuis.get(player);
    }

    public static void close(Player player) 
    {
        openGuis.remove(player);
        player.closeInventory();
    }

    public static void changeMenu(Player player, AbstractEolGui gui) 
    {
    	close(player);
    	Bukkit.getScheduler().runTaskLater(ProjectEchoesOfLumina.instance, ()->open(player, gui), 1);
    }
    
    public static void registerEventListener(JavaPlugin plugin) 
    {
        Bukkit.getPluginManager().registerEvents(new Listener() 
        {
            @EventHandler
            public void onClick(InventoryClickEvent e) 
            {
                if (!(e.getWhoClicked() instanceof Player p)) return;
                AbstractEolGui gui = openGuis.get(p);
                if (gui == null || !e.getView().getTitle().equals(gui.getGuiTitle())) return;

                gui.handleClick(e);
            }

            @EventHandler
            public void onClose(InventoryCloseEvent e) 
            {
            	AbstractEolGui gui = openGuis.remove((Player) e.getPlayer());
                if (gui != null) gui.handleClose(e);
            }
        }, plugin);
    }
}
