package com.egr101sim.arduino.elements;

public class Connection {
	Pin pin1;
	Pin pin2;
	
	private double voltage;
	private boolean isPower;
	private boolean isGround;
	
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
}
