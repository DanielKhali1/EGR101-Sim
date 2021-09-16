package com.egr101sim.core;

import com.egr101sim.arduino.Arduino;
import com.egr101sim.physics.Vector3d;

public class ApplicationManager {
	
	Arduino arduino;
	
	public ApplicationManager() {
		arduino = new Arduino(null , new Vector3d());
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
	
	public void execute() {
		
	}

}
