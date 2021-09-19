package com.egr101sim.core;

import java.util.ArrayList;

import com.egr101sim.arduino.Arduino;
import com.egr101sim.arduino.widgets.Widget;
import com.egr101sim.physics.Vector3d;

public class ApplicationManager {
	
	Arduino arduino;
	ArrayList<Widget> widgets = new ArrayList<Widget>();
	
	
	
	public ApplicationManager() {
		arduino = new Arduino(null);
	}
	
	/**
	 * update the code
	 */
	public void updateBehavior(String instructions) {
		arduino.compileSketch(instructions);
	}
	
	/**
	 * update the wiring/widgets/design of bot
	 */
	public void updatePeripherals() {
		
	}
	
	public void updateConnections() {
		
	}
	
	public void execute() {
		
	}

}
