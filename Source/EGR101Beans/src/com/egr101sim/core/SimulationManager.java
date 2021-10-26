package com.egr101sim.core;

import java.util.Date;

import com.egr101sim.arduino.Arduino;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinState;
import com.egr101sim.arduino.elements.SpecialPin;

public class SimulationManager {

	private Arduino arduino;
	Date startTime;
	Date currentTime;
	
	
	public SimulationManager() {
	}
	
	public void setup() {
		getArduino().setup();
		startTime = new Date();
		getArduino().getArduino().setMilli(0);
	}
	
	
	public void iterate() {
		
		getArduino().execute();
		
		basicIterate();
	}
	
	public void basicIterate() {
		getArduino().getArduino().setMilli(new Date().getTime() - startTime.getTime());
		getArduino().getArduino().update();
		
		// make if a write was executed turn on digital pins
		translatePinStateToVoltage(getArduino().getArduino().getDigitalArray());

		// send power through pins
		sendPowerThroughPinConnections(getArduino().getArduino().getDigitalArray());
		sendPowerThroughPinConnections((SpecialPin)getArduino().getArduino().getP5V());
		sendPowerThroughPinConnections((SpecialPin)getArduino().getArduino().getP3_3v());
		
		// if components have power let them do things
		updateComponentState();
		
		executeComponentBehavior();
	}
	
	private void executeComponentBehavior() {
		for(int i = 0; i < getArduino().getComponents().size(); i++) { 
			getArduino().getComponents().get(i).Behavior();
		}
		
	}

	private void updateComponentState() {
		for(int i = 0; i < getArduino().getComponents().size(); i++) {
			try { getArduino().getComponents().get(i).checkState(); } 
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
	
	private void sendPowerThroughPinConnections(SpecialPin pin) {
			
			for(int i = 0; i < pin.getNexts().size(); i++) {
					Pin cur = pin.getNexts().get(i);
					// run through the list of connections and send power through 
					while(cur != null) {
						if(cur.getPrev() != null) {
							// set the current power to the previous pins power
							cur.setCurrent(cur.getPrev().getCurrent() / cur.getPrev().getResistance());
							cur.setAngle(cur.getPrev().getAngle());
						}
						cur = cur.getNext();
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

	/**
	 * @return the arduino
	 */
	public Arduino getArduino() {
		return arduino;
	}

	/**
	 * @param arduino the arduino to set
	 */
	public void setArduino(Arduino arduino) {
		this.arduino = arduino;
	}

	/**
	 * shuts down Arduino
	 */
	public void shutDown() {
		killAllPower(getArduino().getArduino().getDigitalArray());
		killAllPower(getArduino().getArduino().getAnalogArray());
		killAllPower((SpecialPin)getArduino().getArduino().getP5V());
		killAllPower((SpecialPin)getArduino().getArduino().getP3_3v());
		System.out.println("Killing Power");
	}
	
	private void killAllPower(Pin[] pins) {
		for(int i = 0; i < pins.length; i++) {
			if(pins[i] != null) {
				Pin cur = pins[i];
				// run through the list of connections and send power through 
				while(cur != null) {
					if(cur.getPrev() != null) {
						// set the current power to the previous pins power
						cur.setCurrent(0);
						cur.setAngle(0);
					}
					cur = cur.getNext();
				}
			}
		}
	}
	
	private void killAllPower(SpecialPin pin) {
		
		for(int i = 0; i < pin.getNexts().size(); i++) {
				Pin cur = pin.getNexts().get(i);
				// run through the list of connections and send power through 
				while(cur != null) {
					if(cur.getPrev() != null) {
						// set the current power to the previous pins power
						cur.setCurrent(0);
						cur.setAngle(0);
					}
					cur = cur.getNext();
				}
		}
	}

}
