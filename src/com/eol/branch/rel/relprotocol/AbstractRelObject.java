package com.eol.branch.rel.relprotocol;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.eol.ProjectEchoesOfLumina;
import com.eol.branch.rel.enums.AbilityType;
import com.eol.branch.rel.enums.DamageType;
import com.eol.branch.rel.enums.ElementiumSlotType;
import com.eol.branch.rel.enums.Rarity;
import com.eol.branch.rel.enums.ReprocessCondition;
import com.eol.utils.PrintUtils;

public abstract class AbstractRelObject 
{
	public static final NamespacedKey relObjectKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "rel_object");
	public static final NamespacedKey damageTypeKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "damage_type");
	public static final NamespacedKey baseAttackDamageKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "base_attack");
	public static final NamespacedKey abilityKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "ability_id");
	public static final NamespacedKey reprocessKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "reprocess_key");
	public static final NamespacedKey elementTypeKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "element_type");

	private String name;
	private String internalName;
	private String[] description;
	
	private Material material;
	private Rarity rarity;
	private DamageType damageType;
	private ElementiumSlotType elementType;
	private ReprocessCondition reprocessCondition;
	private AbilityType abilityType;
	
	private boolean enchantedEffect = false;

	private final int maxModSlots = 5;
	private int currentModSlots = 0;
	
	public AbstractRelObject(
			String name, 
			String internalName, 
			Material material, 
			Rarity rarity, 
			DamageType damageType, 
			ElementiumSlotType elementType, 
			ReprocessCondition reprocessCondition, 
			AbilityType abilityType,
			boolean enchantEffect, 
			String...description) 
	{
		this.name = name;
		this.internalName = name;
		this.description = description;
		this.material = material;
		this.rarity = rarity;
		this.damageType = damageType;
		this.elementType = elementType;
		this.reprocessCondition = reprocessCondition;
		this.abilityType = abilityType;
		this.enchantedEffect = enchantEffect;
	}
	
	public AbilityType getAbilityType() 
	{
		return abilityType;
	}
	public void setAbilityType(AbilityType abilityType) 
	{
		this.abilityType = abilityType;
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
	public Material getMaterial() {
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
	public DamageType getDamageType() 
	{
		return damageType;
	}
	public void setDamageType(DamageType damageType) 
	{
		this.damageType = damageType;
	}
	public ElementiumSlotType getElementiumSlotType() 
	{
		return elementType;
	}
	public void setElementiumSlotType(ElementiumSlotType elementType) 
	{
		this.elementType = elementType;
	}
	public ReprocessCondition getReprocessCondition() 
	{
		return reprocessCondition;
	}
	public void setReprocessCondition(ReprocessCondition reprocessCondition) 
	{
		this.reprocessCondition = reprocessCondition;
	}
	public boolean isEnchantedEffect() 
	{
		return enchantedEffect;
	}
	public void setEnchantedEffect(boolean enchantedEffect) 
	{
		this.enchantedEffect = enchantedEffect;
	}
	public int getMaxModSlots() 
	{
		return maxModSlots;
	}
	public int getCurrentModSlots() 
	{
		return currentModSlots;
	}
	public void setCurrentModSlots(int currentModSlots) 
	{
		this.currentModSlots = currentModSlots;
	}
	
	private String rarityStars;
	
	public ItemStack generate() 
	{
		rarityStars = "♦".repeat(rarity.getRarity());
		
		ItemStack stack = new ItemStack(material, 1);
		ItemMeta meta = stack.getItemMeta();
		List<String> itemDescription = new ArrayList<>();
		
		char color = switch (rarity) 
		{
			case ONE -> color = '7';
			case TWO -> color = '6';
			case THREE -> color = 'b';
			case FOUR -> color = 'd';
			case FIVE -> color = 'e';
			case SIX -> color = 'c';
			case SEVEN -> color = '3';
			case NONE -> throw new UnsupportedOperationException("Unimplemented case: " + rarity);
			default -> throw new IllegalArgumentException("Unexpected value: " + rarity);	
		};

		String tier = switch(rarity) 
		{
			case ONE -> tier = "I";
			case TWO -> tier = "II";
			case THREE -> tier = "III";
			case FOUR -> tier = "IV";
			case FIVE -> tier = "V";
			case SIX -> tier = "VI";
			case SEVEN -> tier = "VII";
			case NONE -> throw new UnsupportedOperationException("Unimplemented case: " + rarity);
			default -> throw new IllegalArgumentException("Unexpected value: " + rarity);	
		};
		
		meta.setDisplayName(PrintUtils.ColorParser("&r&f" + name + " &" + color + tier));			
		
		if (this.isEnchantedEffect() == true) 
		{
			meta.setEnchantmentGlintOverride(true);
		}
		
		itemDescription.add(PrintUtils.ColorParser("&r&f-+-{ &e&o&lREL Object &r&f}-+-\n"));	
		
		itemDescription.add("\n");
		itemDescription.add(PrintUtils.ColorParser("&r&f&nRarity&r&f: «&" + color) + rarityStars + PrintUtils.ColorParser("&r&f»") + "\n");
		itemDescription.add("\n");
		itemDescription.add(PrintUtils.ColorParser("&r&f&nUsage&r&f: \n"));
		
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

//	private double baseAttack;
//	private long attackSpeed;
//	private double critRate;
//	private double critModifier;