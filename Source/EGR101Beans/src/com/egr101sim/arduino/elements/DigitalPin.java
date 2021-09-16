package com.egr101sim.arduino.elements;

public class DigitalPin extends Pin {

	private PinState pinState;
	private PinIO pinIO;

	
	public DigitalPin(PinType pinType, boolean local) {
		super(pinType, local);
		this.pinIO = PinIO.INPUT;
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

}
