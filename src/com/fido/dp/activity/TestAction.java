package com.fido.dp.activity;

import com.fido.dp.base.Activity;
import com.fido.dp.Log;
import com.fido.dp.base.Agent;
import com.fido.dp.base.CommandAgent;
import com.fido.dp.agent.ExplorationCommand;
import com.fido.dp.agent.unit.SCV;
import java.util.ArrayList;
import java.util.logging.Level;

public class TestAction extends Activity {

    private ExplorationCommand explorationCommand;

    public TestAction(Agent agent) {
        super(agent);
    }

    @Override
    public void performAction() {
        if (explorationCommand == null) {
            getExplorationCommand();
            ArrayList<Agent> subordinateAgents = ((CommandAgent) agent).getCommandedAgents();
            System.out.println("Number of subordinate agents: " + subordinateAgents.size());
            for (Agent subordinateAgent : subordinateAgents) {
                if (subordinateAgent instanceof SCV) {
                    Log.log(this, Level.FINE, "{0}: subordinate agent added to {1}", this.getClass(), explorationCommand.getClass());
//                    explorationCommand.addCommandedAgent(subordinateAgent);
                }
            }
        }
    }

    private void getExplorationCommand() {
        ArrayList<Agent> subordinateAgents = ((CommandAgent) agent).getCommandedAgents();
        for (Agent subordinateAgent : subordinateAgents) {
            Log.log(this, Level.FINE, "{0}: agent: {1}", this, subordinateAgent.getClass());
            if (subordinateAgent instanceof ExplorationCommand) {
                Log.log(this, Level.FINE, "{0}: INSIDE", this.getClass());
                explorationCommand = (ExplorationCommand) subordinateAgent;
                return;
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        return true;
    }

	@Override
	protected void init() {
		
	}

}