package com.eol.branch.menus.instances;

import java.util.Set;

import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.SoundCategory;
import org.bukkit.entity.Player;

import com.eol.branch.menus.AbstractEolGui;
import com.eol.branch.menus.EolGuiButton;
import com.eol.branch.menus.EolGuiHandler;
import com.eol.utils.EolEffects;

public class EolMainMenu extends AbstractEolGui
{

	public EolMainMenu(Player player) 
	{
		super(player, "✧}- Echoes Of Lumina -{✧", 27, Set.of(10,13,16));
	}

	@Override
	protected void build() 
	{
		EolGuiButton.button(Material.NETHER_STAR).setName("&e&lREL Protocol")
		.setLore("&r&f&o[PH] Click to begin the RELIVE process.",
				"",
				"&r&7{&c&l!&r&7}&r&f&o This feature is not yet implemented.").place(this, 13, e->
		{
			Player p = (Player) e.getWhoClicked();
			e.setCancelled(true);
			EolEffects.playSound(p, p.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, SoundCategory.MASTER, 1, 1);
		});
		
		//Exits
		EolGuiButton.button(Material.RED_STAINED_GLASS_PANE).setName("&c&lExit Menu").setLore("&r&f&oClick to exit.").place(this, 10, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_BOOK_PUT, SoundCategory.MASTER, 1, 1);
			EolGuiHandler.close(p);
		});
		
		EolGuiButton.button(Material.RED_STAINED_GLASS_PANE).setName("&c&lExit Menu").setLore("&r&f&oClick to exit.").place(this, 16, e->
		{
			Player p = (Player) e.getWhoClicked();
			p.playSound(p.getLocation(), Sound.ITEM_BOOK_PUT, SoundCategory.MASTER, 1, 1);
			EolGuiHandler.close(p);
		});
		paint();
	}

}
