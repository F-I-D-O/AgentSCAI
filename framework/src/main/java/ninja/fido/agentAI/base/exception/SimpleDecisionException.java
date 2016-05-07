/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninja.fido.agentAI.base.exception;

import ninja.fido.agentAI.base.Activity;
import ninja.fido.agentAI.base.Agent;
import ninja.fido.agentAI.base.Goal;

/**
 *
 * @author F.I.D.O.
 */
public class SimpleDecisionException extends Exception{
	
	private final Class<? extends Agent> agentClass;

	private final Goal goal;
	
	private Class<? extends Agent> commandAgentClass;
	
	private Activity activity;
	
	
	
	
	public SimpleDecisionException(Class<? extends Agent> agentClass, Goal goal) {
		this.agentClass = agentClass;
		this.goal = goal;
	}

	public SimpleDecisionException(Class<? extends Agent> agentClass, Goal goal, 
			Class<? extends Agent> commandAgentClass) {
		this.agentClass = agentClass;
		this.goal = goal;
		this.commandAgentClass = commandAgentClass;
	}
	
	public SimpleDecisionException(Class<? extends Agent> agentClass, Goal goal, Activity activity) {
		this.agentClass = agentClass;
		this.goal = goal;
		this.activity = activity;
	}
	
	
	
	
	@Override
	public String getMessage() {
		if(activity == null){
			return agentClass + ": Cannot decide what to do! There is no mapping for goal: " + goal.getClass() + ", Command Agent class: "
					+ commandAgentClass;
		}
		else{
			return agentClass + ": Wrong mapping for goal: " + goal.getClass() + ", Activity: " + activity.getClass()
					+ "is incompatible with this goal";
		}
	}
	
}
