package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;

public abstract class Component {
	
	Pin[] pins;
	double voltageLimit;
	double currentDrain;
	boolean grounded = false;
	
	abstract public void Behavior();
}