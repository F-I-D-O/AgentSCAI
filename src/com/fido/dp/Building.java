/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fido.dp;

import bwapi.Position;
import bwapi.Unit;
import bwapi.UnitType;

/**
 *
 * @author F.I.D.O.
 */
public class Building {
	
	private Position desiredPosition;
	
	private UnitType type;
	
	private Unit builderUnit;
	
	private boolean gasSteal;

	
	
	public Position getDesiredPosition() {
		return desiredPosition;
	}

	public UnitType getType() {
		return type;
	}

	public Unit getBuilderUnit() {
		return builderUnit;
	}

	public boolean isGasSteal() {
		return gasSteal;
	}

	
	
	public Building(Position desiredPosition, UnitType type, Unit builderUnit, boolean gasSteal) {
		this.desiredPosition = desiredPosition;
		this.type = type;
		this.builderUnit = builderUnit;
		this.gasSteal = gasSteal;
	}
	
	
	
	
}