/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninja.fido.agentAI.demo.activity.protoss;

import ninja.fido.agentAI.agent.FullCommander;
import ninja.fido.agentAI.agent.SquadCommander;
import ninja.fido.agentAI.agent.unit.UnitAgent;
import ninja.fido.agentAI.base.CommandActivity;
import ninja.fido.agentAI.base.GameAPI;
import ninja.fido.agentAI.base.Goal;
import ninja.fido.agentAI.base.UniversalGoalOrder;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ninja.fido.agentAI.base.exception.ChainOfCommandViolationException;
import ninja.fido.agentAI.demo.goal.FormationTestSquadFormationGoal;
import ninja.fido.agentAI.modules.decisionMaking.EmptyDecisionTableMapException;

/**
 *
 * @author F.I.D.O.
 */
public class FormationTestStrategy extends CommandActivity<FullCommander,Goal,FormationTestStrategy>{
	
	private SquadCommander squadCommander;

	
	public FormationTestStrategy() {
	}

	public FormationTestStrategy(FullCommander agent) {
		super(agent);
	}

	@Override
	public boolean equals(Object obj) {
		return obj != null && obj instanceof FormationTestStrategy;
	}

	@Override
	protected void performAction() {
		List<UnitAgent> agents = agent.getCommandedAgents(UnitAgent.class);
		agent.detachCommandedAgents(agents, squadCommander);
	}

	@Override
	protected void init() throws ChainOfCommandViolationException {
		try {
			squadCommander = new SquadCommander();
			GameAPI.addAgent(squadCommander, agent);
			new UniversalGoalOrder(squadCommander, agent,
					new FormationTestSquadFormationGoal(squadCommander, null)).issueOrder();
//		new UniversalGoalOrder(squadCommander, agent,
//				new FormationTestSquadFormationIndividualGoal(squadCommander, null)).issueOrder();
		} catch (EmptyDecisionTableMapException ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public FormationTestStrategy create(FullCommander agent, Goal goal) {
		return new FormationTestStrategy(agent);
	}
	
}