package com.eol.branch.rel.enums;

public enum DamageType 
{
	//Base Types
	GENERIC(1.0,1.0,1.0,1.0),
	
	BLUNT(1.0,0.5,1.5,0.0),
	SLICE(1.0,0.5,1.5,0.0),
	PIERCE(1.0,0.5,1.5,0.0),
	
	//True Damage Types (Implements Themes from BLUNT, SLICE, and PIERCE)
	CRUSH(1.0,1.0,1.0,1.0),
	SEVER(1.0,1.0,1.0,1.0),
	IMPALE(1.0,1.0,1.0,1.0),
	
	//Wands
	ARCANO(1.0,0.5,1.5,0.0),
	
	//Bows or Other Ranged Damage Dealers
	RANGED(1.0,0.5,1.5,0.0),
	
	//Elemental
	INFERNO(1.0,0.5,1.5,0.0),
	GLACIO(1.0,0.5,1.5,0.0),
	GEO(1.0,0.5,1.5,0.0),
	AERO(1.0,0.5,1.5,0.0),
	CELESTIO(1.0,0.5,1.5,0.0),
	MORTIO(1.0,0.5,1.5,0.0),
	COSMO(1.0,0.5,1.5,0.0);
	
	private final double baseMultiplier;
	private final double resistanceMultiplier;
	private final double weaknessMultiplier;
	private final double immuneMultiplier;
	
	private DamageType(double baseMultiplier, double resistanceMultiplier, double weaknessMultiplier, double immuneMultiplier)
	{
		this.baseMultiplier = baseMultiplier;
		this.resistanceMultiplier = resistanceMultiplier;
		this.weaknessMultiplier = weaknessMultiplier;
		this.immuneMultiplier = immuneMultiplier;
	}
	
//	public DamageType getDamageType() 
//	{
//		return this;
//	}
	
	public double getBaseMultiplier() 
	{
		return baseMultiplier;
	}

	public double getResistanceMultiplier() 
	{
		return resistanceMultiplier;
	}

	public double getWeaknessMultiplier() 
	{
		return weaknessMultiplier;
	}

	public double getImmuneMultiplier() 
	{
		return immuneMultiplier;
	}
}
