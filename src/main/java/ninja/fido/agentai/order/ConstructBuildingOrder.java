/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninja.fido.agentai.order;

import bwapi.TilePosition;
import bwapi.UnitType;
import ninja.fido.agentai.base.CommandAgent;
import ninja.fido.agentai.agent.unit.Worker;
import ninja.fido.agentai.base.GoalOrder;
import ninja.fido.agentai.base.exception.ChainOfCommandViolationException;
import ninja.fido.agentai.goal.ConstructBuildingGoal;

/**
 *
 * @author F.I.D.O.
 */
public class ConstructBuildingOrder extends GoalOrder{
	
	private final UnitType buildingType;
	
	private final TilePosition placeToBuildOn;

	public ConstructBuildingOrder(Worker target, CommandAgent commandAgent, UnitType buildingType, 
			TilePosition placeToBuildOn) throws ChainOfCommandViolationException {
		super(target, commandAgent);
		this.buildingType = buildingType;
		this.placeToBuildOn = placeToBuildOn;
	}

	@Override
	protected void execute() {
		setGoal(new ConstructBuildingGoal(getTarget(), this, buildingType, placeToBuildOn));
	}
	
}
