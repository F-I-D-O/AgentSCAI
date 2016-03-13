package com.fido.dp;

import bwapi.DefaultBWListener;
import bwapi.Game;
import bwapi.Mirror;
import bwapi.Unit;
import bwapi.UnitType;
import com.fido.dp.base.Agent;
import com.fido.dp.agent.BuildCommand;
import com.fido.dp.agent.Commander;
import com.fido.dp.agent.ExplorationCommand;
import com.fido.dp.base.LeafAgent;
import com.fido.dp.agent.ResourceCommand;
import com.fido.dp.agent.SCV;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameAPI extends DefaultBWListener implements EventEngineListener{
	
	private static Commander commanderStatic;
	
	private static MapTools mapTools;
	
	private static BuildingPlacer buildingPlacer;
	
	private static Mirror mirror;

    private static Game game;
	
	
	
	public static Commander getCommander(){
		return commanderStatic;
	}

	public static MapTools getMapTools() {
		return mapTools;
	}

	public static BuildingPlacer getBuildingPlacer() {
		return buildingPlacer;
	}

    public static Mirror getMirror() {
        return mirror;
    }

    public static Game getGame() {
        if (game == null) {
            game = mirror.getGame();
        }
        return game;
    }
	
	

	public static void main(String[] args) {
        new GameAPI().run();
    }
	
	
	
	
	private EventEngine eventEngine;
	
	private Commander commander;
	
	private ArrayList<Agent> agents;
	
	private HashMap<Unit,LeafAgent> leafAgentsMappedByUnit;
	
	private int frameCount;
	
	private Level logLevel;

	
	
	public GameAPI() {
		this.frameCount = 0;
		agents = new ArrayList();
		leafAgentsMappedByUnit = new HashMap<>();
		eventEngine =  new EventEngine();
		eventEngine.addListener(this);
		logLevel = Level.INFO;
	}

    public void run() {
        mirror = new Mirror();
        mirror.getModule().setEventListener(this);
        mirror.startGame();
    }

    @Override
    public void onUnitCreate(Unit unit) {
		try{
			UnitType type = unit.getType();
			LeafAgent agent = null;
			
			// Construction finished event
			if(unit.getType() == UnitType.Buildings){
				Unit builderUnit = unit.getBuildUnit();
				SCV builderAgent = (SCV) leafAgentsMappedByUnit.get(builderUnit);
				builderAgent.onConstructionFinished();
			}
			
			if (type.equals(UnitType.Terran_SCV)) {
				agent = new SCV(unit);
				commander.addsubordinateAgent(agent);
				agents.add(agent);
			}
			
			// check beacause we not handle all units creation
			if(agent != null){
				leafAgentsMappedByUnit.put(unit, agent);
			}
		}
		catch (Exception exception) {
            Log.log(this, Level.SEVERE, "EXCEPTION!");
            exception.printStackTrace();
        } 
		catch (Error error) {
            Log.log(this, Level.SEVERE, "ERROR!");
            error.printStackTrace();
        }
    }

    @Override
    public void onFrame() {
        try {
			frameCount++;
            Log.log(this, Level.INFO, "OnFrame start - frame {0}", frameCount);
			
			eventEngine.run();
			
            Log.log(this, Level.FINE, "Number of agents: {0}", agents.size());
            for (Agent agent : agents) {
                agent.run();
            }
            Log.log(this, Level.INFO, "OnFrame end");
        } 
		catch (Exception exception) {
            Log.log(this, Level.SEVERE, "EXCEPTION!");
            exception.printStackTrace();
        } 
		catch (Error error) {
            Log.log(this, Level.SEVERE, "ERROR!");
            error.printStackTrace();
        }
    }

    @Override
    public void onStart() {
		try{
			Log.log(this, Level.FINE, "OnStart START");
			Logger rootLog = Logger.getLogger("");
			bwta.BWTA.readMap();
			bwta.BWTA.analyze();
			rootLog.setLevel(logLevel);
			rootLog.getHandlers()[0].setLevel(logLevel);
			rootLog.getHandlers()[0].setFormatter(new LogFormater());
			
			getGame().setLocalSpeed(10);
			getGame().setFrameSkip(0);
			getGame().enableFlag(1);

			commander = new Commander();

			commanderStatic = commander;
			mapTools = new UAlbertaMapTools();
			buildingPlacer = new UAlbertaBuildingPlacer();

			agents.add(commander);
			addAgent(new ExplorationCommand());
			addAgent(new ResourceCommand());
			addAgent(new BuildCommand());
			
//			buildingPlacer.getBuildingLocation(new Building(GameAPI.getGame().self().getStartLocation().toPosition(),
//					UnitType.Terran_Barracks, null, false));
			Log.log(this, Level.FINE, "OnStart END");
		}
		catch (Exception exception) {
            Log.log(this, Level.SEVERE, "EXCEPTION!");
            exception.printStackTrace();
        } 
		catch (Error error) {
            Log.log(this, Level.SEVERE, "ERROR!");
            error.printStackTrace();
        }
    }

    public void addAgent(Agent agent) {
        commander.addsubordinateAgent(agent);
        agents.add(agent);
    }

	@Override
	public void onBuildingConstructionStart(Unit building) {
		Unit builderUnit = building.getBuildUnit();
		SCV builderAgent = (SCV) leafAgentsMappedByUnit.get(builderUnit);
		builderAgent.onConstructionStarted();
	}
	
	
}
