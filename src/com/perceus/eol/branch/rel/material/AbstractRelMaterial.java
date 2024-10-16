package com.perceus.eol.branch.rel.material;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import com.perceus.eol.ProjectEchoesOfLumina;
import com.perceus.eol.branch.rel.enums.Rarity;
import com.perceus.eol.utils.PrintUtils;

public abstract class AbstractRelMaterial
{
	public static final NamespacedKey materialKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "rel_material");
	public static final NamespacedKey idKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "material_id");
	public static final NamespacedKey rarityKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "rarity");
	public static final NamespacedKey catalystKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "catalyst");
	public static final NamespacedKey unrefinedMaterialKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "unrefined_rel_material");
	
	private String name;
	private String internalName;
	private String[] description;
	private Material material;
	private Rarity rarity;
	private boolean enchantedEffect = false;
	private boolean isCatalyst = false;
	private boolean isUnrefined = false;
	
	public AbstractRelMaterial(String name, String internalName, Material material, Rarity rarity, boolean enchantedEffect, String...description) 
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
	
	public boolean isCatalyst() 
	{
		return isCatalyst;
	}

	public void setCatalyst(boolean isCatalyst) 
	{
		this.isCatalyst = isCatalyst;
	}

	public boolean isUnrefined() 
	{
		return isUnrefined;
	}
	
	public void setUnrefined(boolean isUnrefined) 
	{
		this.isUnrefined = isUnrefined;
	}
	
	public void setEnchantedEffect(boolean enchantedEffect) 
	{
		this.enchantedEffect = enchantedEffect;
	}

	private String rarityStars;
	
	public ItemStack generate() 
	{
		if (rarity.getRarity() == 0) 
		{
			rarityStars = "?";
		}
		else 
		{
			
			rarityStars = "♦".repeat(rarity.getRarity());
		}
		
		ItemStack stack = new ItemStack(material, 1);
		ItemMeta meta = stack.getItemMeta();
		List<String> itemDescription = new ArrayList<>();
		
		char color = switch (rarity) 
		{
			case NONE -> color = 'k';
			case ONE -> color = '7';
			case TWO -> color = '6';
			case THREE -> color = 'b';
			case FOUR -> color = 'd';
			case FIVE -> color = 'e';
			case SIX -> color = 'c';
			case SEVEN -> color = '3';	
		};

		String tier = switch(rarity) 
		{
			case NONE -> tier = "?";
			case ONE -> tier = "I";
			case TWO -> tier = "II";
			case THREE -> tier = "III";
			case FOUR -> tier = "IV";
			case FIVE -> tier = "V";
			case SIX -> tier = "VI";
			case SEVEN -> tier = "VII";	
		};
		
		if (this.isUnrefined() == true)
		{
			meta.setDisplayName(PrintUtils.ColorParser("&r&f" + name));
		}
		else 
		{	
			meta.setDisplayName(PrintUtils.ColorParser("&r&f" + name + " &" + color + tier));			
		}
		
		meta.getPersistentDataContainer().set(materialKey, PersistentDataType.STRING, materialKey.toString());
		meta.getPersistentDataContainer().set(idKey, PersistentDataType.STRING, internalName.toString());
		meta.getPersistentDataContainer().set(rarityKey, PersistentDataType.INTEGER, rarity.getRarity());
		
		
		if (this.isEnchantedEffect() == true) 
		{
			meta.setEnchantmentGlintOverride(true);
		}
		if (this.isUnrefined() == true) 
		{
			itemDescription.add(PrintUtils.ColorParser("&r&f&o&lUnrefined REL Material&r&f\n"));
		}
		else
		{	
			itemDescription.add(PrintUtils.ColorParser("&r&f-+-{ &e&o&lREL Object &r&f}-+-\n"));			
		}
		
		itemDescription.add("\n");
		itemDescription.add(PrintUtils.ColorParser("&r&f&nRarity&r&f: «&" + color) + rarityStars + PrintUtils.ColorParser("&r&f»") + "\n");
		itemDescription.add("\n");
		itemDescription.add(PrintUtils.ColorParser("&r&f&nUsage&r&f: \n"));
		
		if (this.isCatalyst()) 
		{
			meta.getPersistentDataContainer().set(catalystKey, PersistentDataType.STRING, getInternalName());
			itemDescription.add("\n");
			itemDescription.add(PrintUtils.ColorParser("&r&f&lRight-Click&r&f Catalysts to access the REL Protocol.\n"));	
		}
		if (this.isUnrefined() == true) 
		{
			meta.getPersistentDataContainer().set(unrefinedMaterialKey, PersistentDataType.STRING, getInternalName());
			itemDescription.add("\n");
			itemDescription.add(PrintUtils.ColorParser("&r&fThis material can be &nRefined&r&f for further use."));	
		}
		if (!this.isCatalyst() && !this.isUnrefined()) 
		{
			itemDescription.add("\n");
			itemDescription.add(PrintUtils.ColorParser("&r&fA component used in the REL Protocol.\n"));	
		}

		itemDescription.add("\n");
		
		for (String line : description) 
		{
			itemDescription.add(PrintUtils.ColorParser("&r&7&o" + line) +"\n");			
		}
		
		meta.setLore(itemDescription);
		
		stack.setItemMeta(meta);
		 
		return stack;
	}

}
