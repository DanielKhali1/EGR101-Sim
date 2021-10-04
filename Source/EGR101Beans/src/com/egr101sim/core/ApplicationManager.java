package com.egr101sim.core;

import java.util.ArrayList;

import com.egr101sim.arduino.Arduino;
import com.egr101sim.arduino.components.Component;
import com.egr101sim.physics.Vector3d;

public class ApplicationManager {
	
	public Arduino arduino;
	ArrayList<Component> widgets = new ArrayList<Component>();
	public SimulationManager simManager;
	
	
	
	public ApplicationManager() {
		simManager = new SimulationManager();
		arduino = new Arduino(simManager);
		simManager.setArduino(arduino);
	}
	
	/**
	 * update the code
	 */
	public void updateBehavior(String instructions) {
		System.out.println("BUILDING..");
		arduino.compileSketch(instructions);
	}
	
	/**
	 * update the wiring/widgets/design of bot
	 */
	public void updatePeripherals() {
		
	}
	
	public void execute() {
		System.out.println("SETTING UP SIM..");
		simManager.setup();
		System.out.println("EXECUTING..");
		
		while(true) {
			simManager.iterate();
		}
	}

}
