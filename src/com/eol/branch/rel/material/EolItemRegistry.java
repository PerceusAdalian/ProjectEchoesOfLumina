package com.eol.branch.rel.material;

import java.lang.reflect.Constructor;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.eol.branch.rel.material.creationcatalysts.CreationCatalyst_1;
import com.eol.branch.rel.material.creationcatalysts.CreationCatalyst_2;
import com.eol.branch.rel.material.creationcatalysts.CreationCatalyst_3;
import com.eol.branch.rel.material.creationcatalysts.CreationCatalyst_4;
import com.eol.branch.rel.material.creationcatalysts.CreationCatalyst_5;
import com.eol.branch.rel.material.generalmaterials.ElementiaShard_Inferno;
import com.eol.branch.rel.material.generalmaterials.Hilt_1;
import com.eol.branch.rel.material.generalmaterials.StringBinding_1;
import com.eol.branch.rel.material.generalmaterials.WoodenBlock_1;
import com.eol.branch.rel.material.generalmaterials.WoodenBlock_2;
import com.eol.branch.rel.material.generalmaterials.WoodenBlock_3;
import com.eol.branch.rel.material.generalmaterials.WoodenBlock_4;
import com.eol.branch.rel.material.generalmaterials.WoodenBlock_5;
import com.eol.branch.rel.material.unrefinedmaterials.ElementiaEssence_Inferno;
import com.eol.branch.rel.material.unrefinedmaterials.UnrefinedWoodenChunk;

	/**
	 * @Documented MoaItemRegistry A class to handle Nexus Objects for generation, debugging, etc.
	 * @Description 
	 *  		Get each class's internal constructor and allow for reflection to access internal methods. 
	 *  		Finally, put each object into itemRegistry via their instance and refer to the object's internal name.
	 *  		If the object could not be accessed, or doesn't exist, print a stacktrace.
	 */
	public class EolItemRegistry 
	{
		public static final Map<String, AbstractRelMaterial> catalystRegistry = new HashMap<>();
		public static Map<String, AbstractRelMaterial> materiaRegistry = new HashMap<>();
		public static Map<String, AbstractRelMaterial> unrefinedMaterialRegistry = new HashMap<>();
		
	    public static void itemInit() 
	    {
	        List<Class<? extends AbstractRelMaterial>> catalystClasses = Arrays.asList(
	            //Catalysts:
	        		CreationCatalyst_1.class,
	        		CreationCatalyst_2.class,
	        		CreationCatalyst_3.class,
	        		CreationCatalyst_4.class,
	        		CreationCatalyst_5.class);
	        
	        List<Class<? extends AbstractRelMaterial>> materiaClasses = Arrays.asList(
	        		//Generic Materials
	        		
	        		//Materials
	        		WoodenBlock_1.class,
	        		WoodenBlock_2.class,
	        		WoodenBlock_3.class,
	        		WoodenBlock_4.class,
	        		WoodenBlock_5.class,
	        		
	        		//Hilts
	        		Hilt_1.class,
	        		
	        		//Bindings
	        		StringBinding_1.class,
	        		
	        		//Elementia Shards
	        		ElementiaShard_Inferno.class
	        		);

	        List<Class<? extends AbstractRelMaterial>> unrefinedMateriaClasses = Arrays.asList(
	        		//Unrefined Objects
	        		UnrefinedWoodenChunk.class,
	        		ElementiaEssence_Inferno.class
	        		);

	        for (Class<? extends AbstractRelMaterial> clazz : catalystClasses) 
	        {
	            try 
	            {
	                Constructor<? extends AbstractRelMaterial> constructor = clazz.getDeclaredConstructor();
	                constructor.setAccessible(true);
	                AbstractRelMaterial instance = constructor.newInstance();
	                catalystRegistry.put(instance.getInternalName(), instance);
	            } 
	            catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
	        }
	        
		        
	        for (Class<? extends AbstractRelMaterial> clazz : materiaClasses) 
	        {
	            try 
	            {
	                Constructor<? extends AbstractRelMaterial> constructor = clazz.getDeclaredConstructor();
	                constructor.setAccessible(true);
	                AbstractRelMaterial instance = constructor.newInstance();
	                materiaRegistry.put(instance.getInternalName(), instance);
	            } 
	            catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
	        }
		        
			        
	        for (Class<? extends AbstractRelMaterial> clazz : unrefinedMateriaClasses) 
	        {
	            try 
	            {
	                Constructor<? extends AbstractRelMaterial> constructor = clazz.getDeclaredConstructor();
	                constructor.setAccessible(true);
	                AbstractRelMaterial instance = constructor.newInstance();
	                materiaRegistry.put(instance.getInternalName(), instance);
	            } 
	            catch (Exception e) 
	            {
	                e.printStackTrace();
	            }
	        }
	    }
	}
