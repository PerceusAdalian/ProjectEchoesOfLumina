package com.perceus.eol;

import java.util.HashMap;
import java.util.Map;

import com.perceus.eol.branch.crp.material.CreationCatalyst_1;
import com.perceus.eol.branch.crp.material.CrpMaterialObject;

public class CrpMaterials
{
	public static Map<String, CrpMaterialObject> materialRegistry = new HashMap<>();
	
	public static void init() 
	{
		//Creation Catalysts
		CreationCatalyst_1 catalyst1 = new CreationCatalyst_1();
		materialRegistry.put(catalyst1.getInternalName(), catalyst1);
		
		//PH
	}
}
