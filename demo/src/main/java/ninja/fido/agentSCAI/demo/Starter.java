/* 
 * AgentSCAI - Demo
 */
package ninja.fido.agentSCAI.demo;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.xpath.XPathExpressionException;
import ninja.fido.agentSCAI.activity.ASAPSquadAttackMove;
import ninja.fido.agentSCAI.activity.NormalSquadAttackMove;
import ninja.fido.agentSCAI.activity.Wait;
import ninja.fido.agentSCAI.agent.BuildCommand;
import ninja.fido.agentSCAI.demo.activity.terran.BBSStrategy;
import ninja.fido.agentSCAI.agent.FullCommander;
import ninja.fido.agentSCAI.agent.ProductionCommand;
import ninja.fido.agentSCAI.agent.SquadCommander;
import ninja.fido.agentSCAI.agent.UnitCommand;
import ninja.fido.agentSCAI.base.Activity;
import ninja.fido.agentSCAI.base.GameAPI;
import ninja.fido.agentSCAI.base.Goal;
import ninja.fido.agentSCAI.base.exception.ModuleDependencyException;
import ninja.fido.agentSCAI.base.exception.MultipleCommandersException;
import ninja.fido.agentSCAI.demo.activity.protoss.DefaultProtossStrategy;
import ninja.fido.agentSCAI.demo.activity.terran.BBSAttack;
import ninja.fido.agentSCAI.demo.activity.terran.BBSBuild;
import ninja.fido.agentSCAI.demo.activity.terran.BBSProduction;
import ninja.fido.agentSCAI.demo.goal.BBSAttackGoal;
import ninja.fido.agentSCAI.demo.goal.BBSBuildGoal;
import ninja.fido.agentSCAI.demo.goal.BBSProductionGoal;
import ninja.fido.agentSCAI.demo.goal.BBSStrategyGoal;
import ninja.fido.agentSCAI.demo.goal.DefaultProtossStrategyGoal;
import ninja.fido.agentSCAI.modules.decisionStorage.DecisionStorageModule;
import ninja.fido.agentSCAI.modules.decisionMaking.DecisionModule;
import ninja.fido.agentSCAI.modules.decisionMaking.DecisionModuleActivity;
import ninja.fido.agentSCAI.modules.decisionMaking.DecisionTable;
import ninja.fido.agentSCAI.modules.decisionMaking.DecisionTablesMapKey;
import ninja.fido.agentSCAI.modules.decisionMaking.EmptyDecisionTableMapException;
import ninja.fido.agentSCAI.modules.decisionMaking.GoalParameter;
import ninja.fido.agentSCAI.modules.learning.LearningModule;
import ninja.fido.agentSCAI.modules.learning.SquadAttackScenario;
import org.xml.sax.SAXException;

/**
 * Modules demo starter.
 * @author F.I.D.O.
 */
public class Starter {
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, 
			ClassNotFoundException, TransformerException, TransformerConfigurationException, XPathExpressionException, 
			ModuleDependencyException, MultipleCommandersException, EmptyDecisionTableMapException {
		FullCommander commander = new FullCommander("Modules example", new BBSStrategyGoal(null, null));
		
		GameAPI gameAPI = new GameAPI(Level.FINE, 0, 0, commander);
		
		DecisionModule decisionModule = new DecisionModule();
		
		decisionModule.registerCommanderType(FullCommander.class, getFullCommanderDefaultDecisionTablesMap());
		decisionModule.registerAgentClass(new SquadCommander());
		
		DecisionStorageModule decisionStorageModule = new DecisionStorageModule(decisionModule);
		
		decisionStorageModule.registerActivity(new Wait());
		decisionStorageModule.registerActivity(new ASAPSquadAttackMove());
		decisionStorageModule.registerActivity(new NormalSquadAttackMove());
		
		decisionStorageModule.registerParameter(new GoalParameter(null));
		
		LearningModule learningModule = new LearningModule(gameAPI, decisionModule, decisionStorageModule);
		learningModule.setLearningScenario(new SquadAttackScenario());
		
		gameAPI.registerModule(decisionModule);
		gameAPI.registerModule(decisionStorageModule);
		gameAPI.registerModule(learningModule);
		
//		learningModule.processResults();

		setGoalActivityMaps();
		
        gameAPI.run();
    }
	
	private static void setGoalActivityMaps() throws EmptyDecisionTableMapException {
		Map<Class<? extends Goal>,Activity> defaultActivityMap = new BuildCommand().getDefaultGoalActivityMap();
		defaultActivityMap.put(BBSBuildGoal.class, new BBSBuild());
		GameAPI.addSimpleDecisionMap(BuildCommand.class, defaultActivityMap);
		
		defaultActivityMap = new ProductionCommand().getDefaultGoalActivityMap();
		defaultActivityMap.put(BBSProductionGoal.class, new BBSProduction());
		GameAPI.addSimpleDecisionMap(ProductionCommand.class, defaultActivityMap);
		
		defaultActivityMap = new UnitCommand().getDefaultGoalActivityMap();
		defaultActivityMap.put(BBSAttackGoal.class, new BBSAttack());
		GameAPI.addSimpleDecisionMap(UnitCommand.class, defaultActivityMap);
	}
	
	private static Map<DecisionTablesMapKey, DecisionTable> getFullCommanderDefaultDecisionTablesMap() {
		Map<DecisionTablesMapKey, DecisionTable> defaultDecisionTablesMap = new HashMap<>();
		
//		TreeMap<Double,Activity> actionMap = new TreeMap<>();
//		actionMap.put(1.0, new BBSStrategy(this));
//		DecisionTablesMapKey key =  new DecisionTablesMapKey();
//		key.addParameter(new RaceParameter(Race.Terran));
//		addToDecisionTablesMap(key, new DecisionTable(actionMap));
		
//		actionMap = new TreeMap<>();
//		actionMap.put(1.0, new OutbreakStrategy(this));
//		key =  new DecisionTablesMapKey();
//		key.addParameter(new RaceParameter(Race.Zerg));
//		addToDecisionTablesMap(key, new DecisionTable(actionMap));

		TreeMap<Double,DecisionModuleActivity> actionMap = new TreeMap<>();
		actionMap.put(1.0, new BBSStrategy());
		DecisionTablesMapKey key =  new DecisionTablesMapKey();
		key.addParameter(new GoalParameter(BBSStrategyGoal.class));
		defaultDecisionTablesMap.put(key, new DecisionTable(actionMap));
		
		actionMap = new TreeMap<>();
		actionMap.put(1.0, new DefaultProtossStrategy());
		key =  new DecisionTablesMapKey();
		key.addParameter(new GoalParameter(DefaultProtossStrategyGoal.class));
		defaultDecisionTablesMap.put(key, new DecisionTable(actionMap));
		
		return defaultDecisionTablesMap;
	}
}
