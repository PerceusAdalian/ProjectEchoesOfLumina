package com.eol.branch.menus;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.eol.utils.PrintUtils;

public class EolGuiButton 
{
	private final ItemStack item;
    private final ItemMeta meta;
    private final List<String> lore = new ArrayList<>();
    
    public EolGuiButton(Material material) 
    {
        item = new ItemStack(material);
        meta = item.getItemMeta();
    }
    
    public EolGuiButton(ItemStack obj) 
    {
		this.item = new ItemStack(obj);
		this.meta = obj.getItemMeta();
    }
    
    public static EolGuiButton button(Material material) 
    {
        return new EolGuiButton(material);
    }
    
    public static EolGuiButton button(ItemStack stack) 
    {
    	return new EolGuiButton(stack);
    }
    
    public EolGuiButton setName(String name) 
    {
        meta.setDisplayName(PrintUtils.ColorParser(name));
        return this;
    }

    public EolGuiButton setLore(String... lines) 
    {
        for (String line : lines) 
        {
            lore.add(PrintUtils.ColorParser("&r&f" + line));
        }
        return this;
    }
    
    public void place(AbstractEolGui gui, int slot, Consumer<InventoryClickEvent> action) 
    {
        meta.setLore(lore);
        item.setItemMeta(meta);
        gui.getInventory().setItem(slot, item);
        gui.clickActions.put(slot, action);
    }
    
}
