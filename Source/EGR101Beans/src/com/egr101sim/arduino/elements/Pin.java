package com.egr101sim.arduino.elements;

public class Pin {
	private PinType pinType;
	
	// whether it is on the arduino board or not
	boolean local;
	
	/**
	 * 
	 * @param pinType
	 */
	public Pin(PinType pinType, boolean local) {
		this.setPinType(pinType);
		this.local = local;
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

}
