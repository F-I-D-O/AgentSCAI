/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninja.fido.agentai.order;

import bwapi.Position;
import bwapi.UnitType;
import ninja.fido.agentai.agent.unit.Worker;
import ninja.fido.agentai.base.CommandAgent;
import ninja.fido.agentai.base.GoalOrder;
import ninja.fido.agentai.base.exception.ChainOfCommandViolationException;
import ninja.fido.agentai.goal.StartExpansionGoal;

/**
 *
 * @author F.I.D.O.
 */
public class StartExpansionOrder extends GoalOrder{
	
	private final UnitType expansionBuildingType;

//	private final TilePosition buildigPosition;
	
	private final Position expansionPosition;

//	public StartExpansionOrder(Worker target, CommandAgent commandAgent, UnitType expansionBuilding, 
//			TilePosition buildigPosition) {
//		super(target, commandAgent);
//		this.buildigPosition = buildigPosition;
//		this.expansionBuildingType = expansionBuilding;
//	}
	
	public StartExpansionOrder(Worker target, CommandAgent commandAgent, UnitType expansionBuilding, 
			Position expansionPosition) throws ChainOfCommandViolationException {
		super(target, commandAgent);
		this.expansionPosition = expansionPosition;
		this.expansionBuildingType = expansionBuilding;
	}

	@Override
	protected void execute() {
//		setGoal(new StartExpansionGoal(getTarget(), this, expansionBuildingType, buildigPosition));
		setGoal(new StartExpansionGoal(getTarget(), this, expansionBuildingType, expansionPosition));
	}
	
}
