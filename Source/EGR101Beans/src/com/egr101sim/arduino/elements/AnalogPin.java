package com.egr101sim.arduino.elements;

public class AnalogPin extends Pin {
	
	//5 volts / 1024 units or, 0.0049 volts (4.9 mV) per unit
	private int voltage;
	private int value;

	public AnalogPin(PinType pinType, boolean local) {
		super(pinType, local);
		value = 0;
		voltage = 0;
	}

	public int getVoltage() {
		return voltage;
	}

	public void setVoltage(int voltage) {
		this.voltage = voltage;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	

}
