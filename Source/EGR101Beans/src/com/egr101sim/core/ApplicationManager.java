package com.egr101sim.core;

import java.util.ArrayList;

import com.egr101sim.arduino.Arduino;
import com.egr101sim.arduino.components.Component;

public class ApplicationManager {
	
	public Arduino arduino;
	ArrayList<Component> widgets = new ArrayList<Component>();
	public SimulationManager simManager;
	private boolean isSimRunning;
	
	
	
	public ApplicationManager() {
		simManager = new SimulationManager();
		arduino = new Arduino(simManager);
		simManager.setArduino(arduino);
		setSimRunning(false);
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
		
		while(isSimRunning()) {
			simManager.iterate();
		}
		
		simManager.shutDown();
	}

	/**
	 * @return the isSimRunning
	 */
	public boolean isSimRunning() {
		return isSimRunning;
	}

	/**
	 * @param isSimRunning the isSimRunning to set
	 */
	public void setSimRunning(boolean isSimRunning) {
		this.isSimRunning = isSimRunning;
	}
	
	public String stackPrint() 
	{
		String temp = arduino.stackPrint();
		String stackPrint = temp.replace("/com/egr101sim/arduino/ArduinoBehavior1.java:19: ","");
		return stackPrint; 
	}

}
