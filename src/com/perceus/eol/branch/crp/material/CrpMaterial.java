package com.perceus.eol.branch.crp.material;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.perceus.eol.ProjectEchoesOfLumina;
import com.perceus.eol.branch.crp.enums.Rarity;
import com.perceus.eol.utils.PrintUtils;

public abstract class CrpMaterial
{
	public static final NamespacedKey key = new NamespacedKey(ProjectEchoesOfLumina.instance, "crp_material");
	private String name;
	private String internalName;
	private String[] description;
	private Material material;
	private Rarity rarity;
	private boolean enchantedEffect = false;
	private boolean isCatalyst = false;
	
	public CrpMaterial(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, String...description) 
	{
		this.name = name;
		this.internalName = internalName;
		this.material = material;
		this.rarity = rarity;
		this.enchantedEffect = enchantedEffect;
		this.description = description;
	}
	
	public String getName() 
	{
		return name;
	}
	
	public void setName(String name) 
	{
		this.name = name;
	}

	public String getInternalName() 
	{
		return internalName;
	}

	public void setInternalName(String internalName) 
	{
		this.internalName = internalName;
	}

	public String[] getDescription() 
	{
		return description;
	}

	public void setDescription(String[] description) 
	{
		this.description = description;
	}

	public Material getMaterial()
	{
		return material;
	}

	public void setMaterial(Material material) 
	{
		this.material = material;
	}

	public Rarity getRarity() 
	{
		return rarity;
	}

	public void setRarity(Rarity rarity) 
	{
		this.rarity = rarity;
	}

	public boolean isEnchantedEffect() 
	{
		return enchantedEffect;
	}
	
	public boolean isCatalyst() {
		return isCatalyst;
	}

	public void setCatalyst(boolean isCatalyst) {
		this.isCatalyst = isCatalyst;
	}

	public void setEnchantedEffect(boolean enchantedEffect) 
	{
		this.enchantedEffect = enchantedEffect;
	}

	public ItemStack generate() 
	{
		String rarityStars = "♦".repeat(rarity.getRarity());
		ItemStack stack = new ItemStack(material, 1);
		ItemMeta meta = stack.getItemMeta();
		List<String> itemDescription = new ArrayList<>();
		meta.setDisplayName(PrintUtils.ColorParser("&r&f")+name);
		
		meta.getPersistentDataContainer().set(key, PersistentDataType.STRING, key.toString() + "\0" + internalName.toString());
		meta.getPersistentDataContainer().set(key, PersistentDataType.INTEGER, rarity.getRarity());
		
		char color = switch (rarity) 
		{
			case ONE -> color = '7';
			case TWO -> color = '9';
			case THREE -> color = 'b';
			case FOUR -> color = 'd';
			case FIVE -> color = 'e';
			case SIX -> color = 'c';
			case SEVEN -> color = '3';	
		};

		itemDescription.add(PrintUtils.ColorParser("&r&fCRP Material \n"));
		
		itemDescription.add(PrintUtils.ColorParser("&r&f&nRarity&r&f: «&"+color) + rarityStars + PrintUtils.ColorParser("&r&f»") + "\n");
		
		itemDescription.add(PrintUtils.ColorParser("&r&f&nUsage&r&f: \n"));
		itemDescription.add(PrintUtils.ColorParser("&r&fUsed in the CRP Process."));
		itemDescription.add(PrintUtils.ColorParser("&r&f&nRight-Click&r&f Creation Catalysts to open the CRP Menu. \n"));
		
		for (String line : description) 
		{
			PrintUtils.ColorParser("&r&f" + itemDescription.add(PrintUtils.ColorParser(line)) + "\n");
		}
		
		stack.setItemMeta(meta);
		
		return stack;
	}
}
