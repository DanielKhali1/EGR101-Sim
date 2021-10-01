package com.egr101sim.core;

import java.util.Date;

import com.egr101sim.arduino.Arduino;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinState;

public class SimulationManager {

	Arduino arduino;
	Date startTime;
	Date currentTime;
	
	
	public SimulationManager(Arduino arduino) {
		this.arduino = arduino;
	}
	
	public void setup() {
		arduino.setup();
		startTime = new Date();
		arduino.getArduino().setMilli(0);
	}
	
	
	public void iterate() {
		
		currentTime = new Date();
		arduino.getArduino().setMilli(currentTime.getTime() - startTime.getTime());
		arduino.execute();
		
		
		// make if a write was executed turn on digital pins
		translatePinStateToVoltage(arduino.getArduino().getDigitalArray());

		// send power through pins
		sendPowerThroughPinConnections(arduino.getArduino().getDigitalArray());
		sendPowerThroughPinConnections(arduino.getArduino().getP5V());
		sendPowerThroughPinConnections(arduino.getArduino().getP3_3v());
		
		// if components have power let them do things
		updateComponentState();
		
		executeComponentBehavior();
	}
	
	private void executeComponentBehavior() {
		for(int i = 0; i < arduino.getComponents().size(); i++) { 
			arduino.getComponents().get(i).Behavior();
		}
		
	}

	private void updateComponentState() {
		for(int i = 0; i < arduino.getComponents().size(); i++) {
			try { arduino.getComponents().get(i).checkState(); } 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void sendPowerThroughPinConnections(Pin[] pins) {
		for(int i = 0; i < pins.length; i++) {
			if(pins[i] != null) {
				Pin cur = pins[i];
				// run through the list of connections and send power through 
				while(cur != null) {
					if(cur.getPrev() != null) {
						// set the current power to the previous pins power
						cur.setCurrent(cur.getPrev().getCurrent() / cur.getPrev().getResistance());
					}
					cur = cur.getNext();
				}
			}
		}
	}

	private void translatePinStateToVoltage(Pin[] pins) {
		for(int i = 0; i < pins.length; i++) {
			if(pins[i] != null) {
				if(pins[i].getPinState() == PinState.HIGH) {
					pins[i].setCurrent(5);
				} else if(pins[i].getPinState() == PinState.LOW) {
					pins[i].setCurrent(0);
				}
			}
		}
	}

}
