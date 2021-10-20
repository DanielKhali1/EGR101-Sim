package com.egr101sim.arduino.elements;

import java.util.ArrayList;

/**
 * 
 * SHOULD BE USED TO DESCRIBE LOCAL 5V 3.3V and GROUND PINS
 * 
 * @param local
 */
public class SpecialPin extends Pin{

	private ArrayList<Pin> nexts = new ArrayList<Pin>();
	
	public SpecialPin(PinType pinType, boolean local) {
		super(pinType, local);
	}

	/**
	 * @return the nexts
	 */
	public ArrayList<Pin> getNexts() {
		return nexts;
	}

	/**
	 * @param nexts the nexts to set
	 */
	public void setNexts(ArrayList<Pin> nexts) {
		this.nexts = nexts;
	}
}
