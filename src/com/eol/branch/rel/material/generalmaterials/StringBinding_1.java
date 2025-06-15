package com.eol.branch.rel.material.generalmaterials;

import org.bukkit.Material;

import com.eol.branch.rel.enums.Rarity;
import com.eol.branch.rel.material.AbstractRelMaterial;

public class StringBinding_1 extends AbstractRelMaterial
{

	public StringBinding_1() 
	{
		super("String Binding", "string_binding_1", Material.STRING, Rarity.ONE, false, 
				"A simple spool of string used as binding.",
				"It looks like it would snap immediately under stress.");	
	}

}
