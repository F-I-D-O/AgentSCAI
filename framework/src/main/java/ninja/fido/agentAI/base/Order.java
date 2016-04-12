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
public abstract class Order {
	
	protected final CommandAgent commandAgent;
	
	private final Agent target;

	
	
	
	public Order(Agent target, CommandAgent commandAgent) throws ChainOfCommandViolationException {
		if(!commandAgent.getCommandedAgents().contains(target)){
			throw new ChainOfCommandViolationException(commandAgent, this, target);
		}
		this.target = target;
		this.commandAgent = commandAgent;
	}
	
	
	
	protected abstract void execute();
	
	public final void issueOrder(){
		target.addToCommandQueue(this);
		commandAgent.addUncompletedOrder(this);
	}
	
	public void reportCompleted(){
		commandAgent.reportOrderCompleted(this);
	}
	
	public <T> T getTarget(){
		return (T) target;
	}
}