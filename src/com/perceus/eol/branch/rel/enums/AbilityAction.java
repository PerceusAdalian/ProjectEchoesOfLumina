package com.perceus.eol.branch.rel.enums;

import org.bukkit.event.block.Action;

public enum AbilityAction 
{
	RIGHT_CLICK(Action.RIGHT_CLICK_AIR),
	RIGHT_CLICK_BLOCK(Action.RIGHT_CLICK_BLOCK),
	SHIFT_RIGHT_CLICK(Action.RIGHT_CLICK_AIR),
	SHIFT_RIGHT_CLICK_BLOCK(Action.RIGHT_CLICK_BLOCK);

	
	private Action actionType;
	
	AbilityAction(Action actionType) 
	{
		this.actionType = actionType;
	}

	public Action getActionType() 
	{
		return actionType;
	}
}
