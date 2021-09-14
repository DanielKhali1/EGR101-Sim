package com.egr101sim.arduino.elements;

public class Pin {
	double currentVoltage;
	PinType pinType;

	public Pin(PinType pinType) {
		this.pinType = pinType;
		
	}
}
