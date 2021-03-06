/* 
 * AgentSCAI
 */
package ninja.fido.agentSCAI.base;

/**
 * Represents information sended from one agent to another
 * @author F.I.D.O.
 */
public abstract class Info {
	
	/**
	 * Recipient of the info.
	 */
	private final Agent recipient;
	
	/**
	 * Sender of the info.
	 */
	private final Agent sender;
	
	/**
	 * Sender getter.
	 * @return Returns info sender.
	 */
	public Agent getSender() {
		return sender;
	}
	
	
	/**
	 * Constructor.
	 * @param recipient Recipient of the info.
	 * @param sender Sender of the info.
	 */
	public Info(Agent recipient, Agent sender) {
		this.recipient = recipient;
		this.sender = sender;
	}
	
	
	
	
	/**
	 * Sends the info to the target.
	 */
	public final void send(){
		recipient.queInfo(this);
	}
}
