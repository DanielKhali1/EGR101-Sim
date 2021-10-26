package com.egr101sim.arduino.elements;

public class Pin {
	private PinType pinType;
	private PinState pinState;
	private PinIO pinIO;
	
	// whether it is on the arduino board or not
	private boolean local;
	private Pin prev;
	private Pin next;
	
	// in ohms
	private double resistance;
	
	private double current;
	private double angle;
	
	
	/**
	 * adds previous reference
	 * 
	 * @param p
	 */
	public void addPrev(Pin p) {
		setPrev(p);
	}
	
	/**
	 * adds next (connection) reference
	 * 
	 * @param p
	 */
	public void addNext(Pin p) {
		setNext(p);
	}
	
	/**
	 * 
	 * @param pinType
	 */
	public Pin(PinType pinType, boolean local) {
		this.setPinType(pinType);
		this.setLocal(local);
		this.setResistance(1);
		
		if (this.isLocal()) {
			switch (this.pinType) {
				case POWER_5V:   setCurrent(5); System.out.println("I RAN");   break;
				case POWER_3_3V: setCurrent(3.3); break;
				case IO:         setCurrent(0);   break;
				case GENERAL:         setCurrent(0);   break;
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

	public Pin getPrev() {
		return prev;
	}

	public void setPrev(Pin prev) {
		this.prev = prev;
	}

	public Pin getNext() {
		return next;
	}

	public void setNext(Pin next) {
		this.next = next;
	}

	/**
	 * @return the nextResistance
	 */
	public double getResistance() {
		return resistance;
	}

	/**
	 * @param nextResistance the nextResistance to set
	 */
	public void setResistance(double d) {
		this.resistance = (double) d;
	}

	/**
	 * @return the angle
	 */
	public double getAngle() {
		return angle;
	}

	/**
	 * @param angle the angle to set
	 */
	public void setAngle(double angle) {
		this.angle = angle;
	}
}
