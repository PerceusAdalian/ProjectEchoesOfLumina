package com.perceus.eol.branch.rel.relprotocol;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;

import com.perceus.eol.ProjectEchoesOfLumina;
import com.perceus.eol.branch.rel.enums.DamageType;
import com.perceus.eol.branch.rel.enums.ElementiumSlotType;
import com.perceus.eol.branch.rel.enums.Rarity;
import com.perceus.eol.branch.rel.enums.ReprocessCondition;

public class AbstractRelObject 
{
	public static final NamespacedKey relObjectKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "rel_object");
	public static final NamespacedKey damageTypeKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "damage_type");
	public static final NamespacedKey abilityKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "ability_type");
	public static final NamespacedKey reprocessKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "reprocess_key");
	public static final NamespacedKey elementTypeKey = new NamespacedKey(ProjectEchoesOfLumina.instance, "element_type");
	
	
	private String name;
	private String internalName;
	private String[] description;
	
	private Material material;
	private Rarity rarity;
	private DamageType damageType;
	private ElementiumSlotType slotType;
	private ReprocessCondition reprocessCondition;
	
	private boolean enchantedEffect = false;

	public AbstractRelObject(String name, String internalName, Material material, Rarity rarity, DamageType damageType, ElementiumSlotType slotType, ReprocessCondition reprocessCondition, boolean enchantEffect, String...description) 
	{
		this.name = name;
		this.internalName = name;
		this.description = description;
		this.material = material;
		this.rarity = rarity;
		this.damageType = damageType;
		this.slotType = slotType;
		this.reprocessCondition = reprocessCondition;
		this.enchantedEffect = enchantEffect;
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
		return slotType;
	}

	public void setElementiumSlotType(ElementiumSlotType slotType) 
	{
		this.slotType = slotType;
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
	
}

//	private double baseAttack;
//	private long attackSpeed;
//	private double critRate;
//	private double critModifier;