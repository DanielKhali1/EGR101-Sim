package com.egr101sim.arduino.elements;

public class Pin {
	double currentVoltage;
	PinType pinType;
	
	// whether it is on the arduino board or not
	boolean local;
	
	/**
	 * 
	 * @param pinType
	 */
	public Pin(PinType pinType) {
		this.pinType = pinType;
		this.local = false;
	}
	
	/**
	 * 
	 * @param pinType
	 * @param local
	 */
	public Pin(PinType pinType, boolean local) {
		this.pinType = pinType;
		this.local = local;
	}
}
