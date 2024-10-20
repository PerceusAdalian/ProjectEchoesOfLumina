package com.perceus.eol.branch.rel.enums;

public enum ElementiumSlotType
{
	NO_SLOT("No Slot", true),
	GLACIO("Glacio", true),
	INFERNO("Inferno", true),
	GEO("Geo", true),
	AERO("Aero", true),
	MODULO("Modulo", true),
	CELESTIO("Celestio", true),
	MORTIO("Mortio", true),
	COSMO("Cosmo", true);

	private final String element;
	private final boolean isElement;
	
	private ElementiumSlotType(String element, boolean isElement) 
	{
		this.element = element;
		this.isElement = isElement;
	}

	public String getElement() {
		return element;
	}

	public boolean isElement() 
	{
		return isElement;
	}
}
