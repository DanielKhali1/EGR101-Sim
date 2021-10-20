package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;

public abstract class Component {
	
	private Pin[] pins;
	double voltageLimit;
	double currentDrain;
	boolean grounded = false;
	
	abstract public void checkState() throws Exception;
	abstract public void Behavior();
	
	/**
	 * @return the pins
	 */
	public Pin[] getPins() {
		return pins;
	}
	/**
	 * @param pins the pins to set
	 */
	public void setPins(Pin[] pins) {
		this.pins = pins;
	}
}