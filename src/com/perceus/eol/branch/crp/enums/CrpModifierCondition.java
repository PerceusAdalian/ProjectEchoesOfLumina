package com.perceus.eol.branch.crp.enums;

public enum CrpModifierCondition
{
	//generic conditions
	PVE,
	PVP,
	PASSIVE,
	
	//world restrictions
	OVERWORLD,
	DURING_DAY,
	DURING_NIGHT,
	CLEAR_WEATHER,
	RAINY_WEATHER,
	STORMY_WEATHER,
	NETHER,
	END,

	//other restrictions
	UNDEAD,
	LIVING,
	FLYING,
	AQUATIC,
	BUGS,
	RAID,
	COLDBIOMES,
	HOTBIOMES,
	GENERICBIOMES;
}
