/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fido.dp.action;

import com.fido.dp.Material;
import com.fido.dp.agent.Agent;
import com.fido.dp.agent.CommandAgent;
import com.fido.dp.agent.SCV;
import java.util.Objects;

/**
 *
 * @author david_000
 */
public class ManageHarvest extends CommandAction{
	
	private double mineralShare;

    public ManageHarvest(CommandAgent agent, double mineralShare) {
        super(agent);
        this.mineralShare = mineralShare;
    }

    @Override
    public void performAction() {
        for (Agent subordinateAgent : getAgent().getSubordinateAgents()) {
            if(subordinateAgent instanceof SCV){
                if(!(subordinateAgent.getCommandedAction() instanceof HarvestMineralsAction)){
                    ((SCV) subordinateAgent).commandHarvest(Material.MINERALS);
                }
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
        final ManageHarvest other = (ManageHarvest) obj;
        if (!Objects.equals(this.mineralShare, other.mineralShare)) {
            return false;
        }
        return true;
    }
    
}