package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;

public abstract class Component {
	
	private Pin[] pins;
	protected double voltageLimit;
	protected double currentDrain;
	protected String state;
	private boolean grounded;
	private boolean powered;
	protected String name;
	
	public Component() {
		this.setGrounded(false);
		this.powered = false;
	}

	
	abstract public void checkState() throws Exception;
	abstract public void Behavior();
	
	/**
	 * @return the pins
	 */
	public Pin[] getPins() {
		return pins;
	}
	
	public Pin getVCC() {
		return null;
		
	}
	
	public Pin getGND() {
		return null;
		
	}

	public Pin getOUT() {
		return null;
	
	}
	
	public Pin getIN() {
		return null;
		
	}
	
	
	/**
	 * @param pins the pins to set
	 */
	public void setPins(Pin[] pins) {
		this.pins = pins;
	}
	
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	
	/**
	 * @return whether component is powered powered
	 */
	public boolean isPowered() {
		return powered;
	}

	/**
	 * @param powered the powered to set
	 */
	public void setPowered(boolean powered) {
		this.powered = powered;
	}


	/**
	 * @return the grounded
	 */
	public boolean isGrounded() {
		return grounded;
	}


	/**
	 * @param grounded the grounded to set
	 */
	public void setGrounded(boolean grounded) {
		this.grounded = grounded;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
}