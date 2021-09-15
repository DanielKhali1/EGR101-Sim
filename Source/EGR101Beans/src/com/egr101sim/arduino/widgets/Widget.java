package com.egr101sim.arduino.widgets;

import com.egr101sim.arduino.elements.Pin;

public abstract class Widget {
	
	protected Pin digitalPin;
	protected Pin analogPin;
	protected Pin p5vPin;
	protected Pin p3_3vPin;
	protected Pin groundPin;
	
	
	abstract public void Behavior();
	
}