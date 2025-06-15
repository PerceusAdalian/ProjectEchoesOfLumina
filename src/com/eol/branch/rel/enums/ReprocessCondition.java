package com.eol.branch.rel.enums;

public enum ReprocessCondition 
{
	ENCHANTABLE(true),
	SCRAPABLE(true),
	FUSABLE(true);

	private boolean canReprocess;
	
	ReprocessCondition(boolean canReprocess) 
	{
		this.canReprocess = canReprocess;
	}

	public boolean canReprocess() 
	{
		return canReprocess;
	}
}
