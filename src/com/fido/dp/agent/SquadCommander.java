/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fido.dp.agent;

import com.fido.dp.action.SquadAttackMove;
import com.fido.dp.base.Action;
import com.fido.dp.base.CommandAgent;
import com.fido.dp.goal.SquadAttackMoveGoal;

/**
 *
 * @author F.I.D.O.
 */
public class SquadCommander extends CommandAgent {

	@Override
	protected Action chooseAction() {
		if(getGoal() instanceof SquadAttackMoveGoal){
			SquadAttackMoveGoal goal = getGoal();
			return new SquadAttackMove(this, goal.getAttackTarget());
		}
		return null;
	}
	
}