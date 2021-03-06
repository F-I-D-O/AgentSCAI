/* 
 * AgentSCAI
 */
package ninja.fido.agentSCAI;

import ninja.fido.agentSCAI.base.Agent;

/**
 *
 * @author F.I.D.O.
 */
public class ResourceDeficiencyException extends Exception{
	
	private final Agent spender;
	
	private final ResourceType resourceType;
	
	private final int amount;
	
	private final int currentAmount;

	
	
	
	public Agent getSpender() {
		return spender;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public int getAmount() {
		return amount;
	}

	public int getCurrentAmount() {
		return currentAmount;
	}

	
	
	
	
	public ResourceDeficiencyException(Agent spender, ResourceType resourceType, int amount,  int currentAmount) {
		this.spender = spender;
		this.resourceType = resourceType;
		this.amount = amount;
		this.currentAmount = currentAmount;
	}
	
	
	
	
	@Override
	public String getMessage() {
		return spender.getClass() + ": Don't have enought " + resourceType + ": " + amount + ". Current amount: " + currentAmount;
	}
}
