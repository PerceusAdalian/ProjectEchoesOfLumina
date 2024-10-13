package com.perceus.eol.branch.rel.material;

import java.util.HashMap;
import java.util.Map;

import com.perceus.eol.branch.rel.material.creationcatalysts.CreationCatalyst_1;
import com.perceus.eol.branch.rel.material.creationcatalysts.CreationCatalyst_2;
import com.perceus.eol.branch.rel.material.creationcatalysts.CreationCatalyst_3;
import com.perceus.eol.branch.rel.material.creationcatalysts.CreationCatalyst_4;
import com.perceus.eol.branch.rel.material.creationcatalysts.CreationCatalyst_5;
import com.perceus.eol.branch.rel.material.generalmaterials.WoodenBlock_1;
import com.perceus.eol.branch.rel.material.generalmaterials.WoodenBlock_2;
import com.perceus.eol.branch.rel.material.generalmaterials.WoodenBlock_3;
import com.perceus.eol.branch.rel.material.generalmaterials.WoodenBlock_4;
import com.perceus.eol.branch.rel.material.generalmaterials.WoodenBlock_5;

public class RelMaterialRegistry
{	
	public static Map<String, AbstractRelMaterial> catalystRegistry = new HashMap<>();
	public static void catalystInit() 
	{
		//Creation Catalysts
		CreationCatalyst_1 catalyst1 = new CreationCatalyst_1();
		catalystRegistry.put(catalyst1.getInternalName(), catalyst1);
		CreationCatalyst_2 catalyst2 = new CreationCatalyst_2();
		catalystRegistry.put(catalyst2.getInternalName(), catalyst2);
		CreationCatalyst_3 catalyst3 = new CreationCatalyst_3();
		catalystRegistry.put(catalyst3.getInternalName(), catalyst3);
		CreationCatalyst_4 catalyst4 = new CreationCatalyst_4();
		catalystRegistry.put(catalyst4.getInternalName(), catalyst4);
		CreationCatalyst_5 catalyst5 = new CreationCatalyst_5();
		catalystRegistry.put(catalyst5.getInternalName(), catalyst5);
	}

	public static Map<String, AbstractRelMaterial> materiaRegistry = new HashMap<>();
	public static void materiaInit() 
	{
		WoodenBlock_1 woodenBlock1 = new WoodenBlock_1();
		materiaRegistry.put(woodenBlock1.getInternalName(), woodenBlock1);
		
		WoodenBlock_2 woodenBlock2 = new WoodenBlock_2();
		materiaRegistry.put(woodenBlock2.getInternalName(), woodenBlock2);
		
		WoodenBlock_3 woodenBlock3 = new WoodenBlock_3();
		materiaRegistry.put(woodenBlock3.getInternalName(), woodenBlock3);
		
		WoodenBlock_4 woodenBlock4 = new WoodenBlock_4();
		materiaRegistry.put(woodenBlock4.getInternalName(), woodenBlock4);
		
		WoodenBlock_5 woodenBlock5 = new WoodenBlock_5();
		materiaRegistry.put(woodenBlock5.getInternalName(), woodenBlock5);
	}
}
