/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninja.fido.agentAI.base.exception;

import ninja.fido.agentAI.base.Agent;
import ninja.fido.agentAI.base.Goal;

/**
 *
 * @author F.I.D.O.
 */
public class SimpleDecisionException extends Exception{
	
	private final Class<? extends Agent> agentClass;

	private final Goal goal;
	
	private final Class<? extends Agent> commandAgentClass;

	public SimpleDecisionException(Class<? extends Agent> agentClass, Goal goal, Class<? extends Agent> commandAgentClass) {
		this.agentClass = agentClass;
		this.goal = goal;
		this.commandAgentClass = commandAgentClass;
	}
	
	
	
	
	@Override
	public String getMessage() {
		return agentClass + ": Cannot decide what to do! There is no mapping for goal: " + goal + ", Command Agent class: "
				+ commandAgentClass;
	}
	
}
