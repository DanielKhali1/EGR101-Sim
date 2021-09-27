package com.egr101sim.arduino.elements;

import java.util.ArrayList;

public class Pin {
	private PinType pinType;
	private PinState pinState;
	private PinIO pinIO;
	
	// whether it is on the arduino board or not
	private boolean local;
	Pin prev;
	Pin next;
	
	private double current;
	
	
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
		this.setLocal(local);
		
		if (this.isLocal()) {
			switch (this.pinType) {
				case POWER_5V:   setCurrent(5);   break;
				case POWER_3_3V: setCurrent(3.3); break;
				case IO:         setCurrent(0);   break;
				case GROUND:     setCurrent(-1);  break;
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
		this.setLocal(local);
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

	public boolean isLocal() {
		return local;
	}

	public void setLocal(boolean local) {
		this.local = local;
	}

	public void setCurrent(double current) {
		this.current = current;
	}
}
