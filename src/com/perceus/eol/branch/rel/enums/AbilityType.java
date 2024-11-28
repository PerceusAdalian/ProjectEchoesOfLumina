package com.perceus.eol.branch.rel.enums;

public enum AbilityType 
{
	ACTIVE(true),
	PASSIVE(false);

	private final boolean isActive;
	
	AbilityType(boolean isActive) 
	{
		this.isActive = isActive;
	}
	public boolean isActive() 
	{
		return isActive;
	}
}
