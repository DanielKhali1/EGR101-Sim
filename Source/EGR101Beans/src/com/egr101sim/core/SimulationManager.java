package com.egr101sim.core;

import com.egr101sim.arduino.Arduino;

public class SimulationManager {

	Arduino arduino;
	
	public SimulationManager(Arduino arduino) {
		this.arduino = arduino;
	}
	
	public void setup() {
		arduino.setup();
	}
	
	
	private void iterate() {
		
		// make pins do good things
		arduino.execute();
		
		// send power/io through pins
		
		// if widgets have power let them do things
	}

}
