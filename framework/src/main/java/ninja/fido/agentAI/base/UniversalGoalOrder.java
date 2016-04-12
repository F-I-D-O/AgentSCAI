/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninja.fido.agentAI.base;

import ninja.fido.agentAI.base.exception.ChainOfCommandViolationException;

/**
 *
 * @author F.I.D.O.
 */
public class UniversalGoalOrder extends GoalOrder{
	
	private final Goal goal;

	public UniversalGoalOrder(Agent target, CommandAgent commandAgent, Goal goal) 
			throws ChainOfCommandViolationException {
		super(target, commandAgent);
		this.goal = goal;
		goal.setOrder(this);
	}

	@Override
	protected void execute() {
		setGoal(goal);
	}
	
}