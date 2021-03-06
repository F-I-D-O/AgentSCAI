/* 
 * AgentSCAI
 */
package ninja.fido.agentSCAI.activity;

import ninja.fido.agentSCAI.base.UnitActivity;
import ninja.fido.agentSCAI.base.GameAPI;
import bwapi.Unit;
import ninja.fido.agentSCAI.agent.unit.Worker;
import ninja.fido.agentSCAI.base.Goal;
import ninja.fido.agentSCAI.modules.decisionMaking.DecisionModuleActivity;

public class HarvestMinerals<A extends Worker,G extends Goal> extends UnitActivity<A,G,HarvestMinerals> 
		implements DecisionModuleActivity<A, G, HarvestMinerals>{

	public HarvestMinerals() {
	}

    public HarvestMinerals(A unitAgent) {
        super(unitAgent);
    }
	
	
	

    @Override
    public void performAction() {
        if (agent.getUnit().isIdle()) {
            Unit closestMineral = null;
            Unit unit = agent.getUnit();
            for (Unit neutralUnit : GameAPI.getGame().neutral().getUnits()) {
                if (neutralUnit.getType().isMineralField()) {
                    if (closestMineral == null || unit.getDistance(neutralUnit) < unit.getDistance(closestMineral)) {
                        closestMineral = neutralUnit;
                    }
                }
            }
            if (closestMineral != null) {
                unit.gather(closestMineral, false);
            }
        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HarvestMinerals other = (HarvestMinerals) obj;
        return true;
    }
	
	

	@Override
	protected void init() {
		
	}

	@Override
	public HarvestMinerals create(A agent, G goal) {
		return new HarvestMinerals(agent);
	}

}
