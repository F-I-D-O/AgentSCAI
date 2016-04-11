/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ninja.fido.agentai.modules.decisionMaking;

import bwapi.Race;
import ninja.fido.agentai.BWAPITools;
import ninja.fido.agentai.base.Agent;
import ninja.fido.agentai.base.GameAPI;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 *
 * @author F.I.D.O.
 */
public class RaceParameter extends DecisionTablesMapParameter<Agent, Race, RaceParameter>{

	public RaceParameter(Race value) {
		super(value);
	}

	@Override
	public RaceParameter getCurrentParameter(Agent agent) {
		return new RaceParameter(GameAPI.getGame().self().getRace());
	}

	@Override
	public Element getXml(Document document) {
		Element parameter = document.createElement("raceParameter");
		parameter.setAttribute("value", getValue().toString());
		
		return parameter;
	}

	@Override
	public RaceParameter createFromXml(Element element) {
		return new RaceParameter(BWAPITools.raceFromString(element.getAttribute("value")));
	}

	@Override
	public String getId() {
		return "RaceParameter";
	}
	
	
}