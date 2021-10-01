package com.egr101sim.arduino;



import java.util.ArrayList;

import com.egr101sim.arduino.components.Component;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;
import com.egr101sim.arduino.tools.Translator;
import com.egr101sim.physics.Vector3d;

public class Arduino {
	
	ArduinoBehaviorManager behavior;
	private BaseArduino arduino;
	
	private ArrayList<Component> components = new ArrayList<Component>();
	
	public Arduino() {
		
		setArduino(new BaseArduino());
		behavior = new ArduinoBehaviorManager(getArduino(), null);
	}
	
	public void addComponent(Component c) {
		getComponents().add(c);
	}
	
	public void AddConnection(Pin pin1, Pin pin2,
							  boolean isDigitalifIO1, boolean isDigitalifIO2,
							  int ioNumber1, int ioNumber2) {
		
		if(pin1.isLocal() && pin1.getPinType() == PinType.IO) {
			if(isDigitalifIO1)
				getArduino().getDigitalArray()[ioNumber1] = pin1;
			else
				getArduino().getAnalogArray()[ioNumber1] = pin1;
		} 
		
		if(pin2.isLocal() && pin1.getPinType() == PinType.IO) {
			if(isDigitalifIO2)
				getArduino().getDigitalArray()[ioNumber2] = pin2;
			else
				getArduino().getAnalogArray()[ioNumber2] = pin2;
		}
			
		pin1.addNext(pin2);
		pin2.addPrev(pin1);
	}
	
	public void compileSketch(String instructions) {
		String translated = new Translator(instructions).translate();
		
		System.out.println(translated);
		
		behavior.compile(translated);
		
		verifyComponentConnections();
	}
	
	
	private void verifyComponentConnections() {
		for(int i = 0; i < getComponents().size(); i++) {
			
		}
	}

	public void setup() {
		loop();
	}
	
	public void execute() {
		loop();
	}
	
	private void loop() {
		behavior.getFunction().apply(getArduino());
	}

	public BaseArduino getArduino() {
		return arduino;
	}

	public void setArduino(BaseArduino arduino) {
		this.arduino = arduino;
	}

	/**
	 * @return the components
	 */
	public ArrayList<Component> getComponents() {
		return components;
	}

	/**
	 * @param components the components to set
	 */
	public void setComponents(ArrayList<Component> components) {
		this.components = components;
	}
	
}
