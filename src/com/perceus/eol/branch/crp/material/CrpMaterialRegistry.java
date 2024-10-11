package com.perceus.eol.branch.crp.material;

import java.util.HashMap;
import java.util.Map;

import com.perceus.eol.branch.material.creationcatalysts.CreationCatalyst_1;
import com.perceus.eol.branch.material.creationcatalysts.CreationCatalyst_2;
import com.perceus.eol.branch.material.creationcatalysts.CreationCatalyst_3;

public class CrpMaterialRegistry
{
	public static Map<String, CrpMaterialObject> materialRegistry = new HashMap<>();
	
	public static void init() 
	{
		//Creation Catalysts
		CreationCatalyst_1 catalyst1 = new CreationCatalyst_1();
		materialRegistry.put(catalyst1.getInternalName(), catalyst1);

		CreationCatalyst_2 catalyst2 = new CreationCatalyst_2();
		materialRegistry.put(catalyst2.getInternalName(), catalyst2);
		
		CreationCatalyst_3 catalyst3 = new CreationCatalyst_3();
		materialRegistry.put(catalyst3.getInternalName(), catalyst3);
	}
}
