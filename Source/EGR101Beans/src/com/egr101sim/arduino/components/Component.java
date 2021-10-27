package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;

public abstract class Component {
	
	private Pin[] pins;
	double voltageLimit;
	double currentDrain;
	private boolean grounded;
	private boolean powered;
	
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
	/**
	 * @param pins the pins to set
	 */
	public void setPins(Pin[] pins) {
		this.pins = pins;
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
}