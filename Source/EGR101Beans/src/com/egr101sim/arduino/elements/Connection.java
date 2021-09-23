package com.egr101sim.arduino.elements;

public class Connection {
	private Pin pin1;
	private Pin pin2;
	
	private double voltage;
	private boolean isPower;
	private boolean isGround;
	
	public Connection(Pin pin1, Pin pin2) {
		this.pin1 = pin1;
		this.pin2 = pin2;
	}
	
	public boolean isGround() {
		return isGround;
	}
	
	public void setGround(boolean isGround) {
		this.isGround = isGround;
	}
	
	public boolean isPower() {
		return isPower;
	}
	
	public void setPower(boolean isPower) {
		this.isPower = isPower;
	}
	
	public double getVoltage() {
		return voltage;
	}
	
	public void setVoltage(double voltage) {
		this.voltage = voltage;
	}

	public Pin getPin1() {
		return pin1;
	}

	public void setPin1(Pin pin1) {
		this.pin1 = pin1;
	}

	public Pin getPin2() {
		return pin2;
	}

	public void setPin2(Pin pin2) {
		this.pin2 = pin2;
	}
}
