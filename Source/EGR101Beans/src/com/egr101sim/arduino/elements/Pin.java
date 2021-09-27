package com.egr101sim.arduino.elements;

import java.util.ArrayList;

public class Pin {
	private PinType pinType;
	private PinState pinState;
	private PinIO pinIO;
	
	// whether it is on the arduino board or not
	boolean local;
	Pin prev;
	Pin next;
	
	double current;
	
	
	/**
	 * adds previous reference
	 * 
	 * @param p
	 */
	public void addPrev(Pin p) {
		prev = p;
	}
	
	/**
	 * adds next (connection) reference
	 * 
	 * @param p
	 */
	public void addNext(Pin p) {
		next = p;
	}
	
	/**
	 * 
	 * @param pinType
	 */
	public Pin(PinType pinType, boolean local) {
		this.setPinType(pinType);
		this.local = local;
		
		if (this.local) {
			switch (this.pinType) {
				case POWER_5V:   current = 5;   break;
				case POWER_3_3V: current = 3.3; break;
				case IO:         current = 0;   break;
				case GROUND:     current = -1;  break;
			}
		}
		
	}
	
	/**
	 * 
	 * @param pinType
	 * @param local
	 */
	public Pin(boolean local) {
		this.setPinType(PinType.IO);
		this.local = local;
	}

	public PinType getPinType() {
		return pinType;
	}

	public void setPinType(PinType pinType) {
		this.pinType = pinType;
	}
	
	public String toString() {
		return "PIN TYPE: " + pinType.ordinal();
	}

	public PinState getPinState() {
		return pinState;
	}

	public void setPinState(PinState pinState) {
		this.pinState = pinState;
	}

	public PinIO getPinIO() {
		return pinIO;
	}

	public void setPinIO(PinIO pinIO) {
		this.pinIO = pinIO;
	}

	public double getCurrent() {
		return current;
	}
}
