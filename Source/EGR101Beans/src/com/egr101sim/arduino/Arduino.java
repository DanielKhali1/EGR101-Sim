package com.egr101sim.arduino;



import java.util.ArrayList;

import com.egr101sim.arduino.components.Component;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;
import com.egr101sim.arduino.tools.Translator;
import com.egr101sim.physics.Vector3d;

public class Arduino {
	
	ArduinoBehaviorManager behavior;
	BaseArduino arduino;
	
	ArrayList<Component> components = new ArrayList<Component>();
	
	public Arduino() {
		
		arduino = new BaseArduino();
		behavior = new ArduinoBehaviorManager(arduino, null);
	}
	
	public void addComponent(Component c) {
		components.add(c);
	}
	
	public void AddConnection(Pin pin1, Pin pin2,
							  boolean isDigitalifIO1, boolean isDigitalifIO2,
							  int ioNumber1, int ioNumber2) {
		
		if(pin1.isLocal() && pin1.getPinType() == PinType.IO) {
			if(isDigitalifIO1)
				arduino.getDigitalArray()[ioNumber1] = pin1;
			else
				arduino.getAnalogArray()[ioNumber1] = pin1;
		} 
		
		if(pin2.isLocal() && pin1.getPinType() == PinType.IO) {
			if(isDigitalifIO2)
				arduino.getDigitalArray()[ioNumber2] = pin2;
			else
				arduino.getAnalogArray()[ioNumber2] = pin2;
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
		for(int i = 0; i < components.size(); i++) {
			
		}
	}

	public void setup() {
		loop();
	}
	
	public void execute() {
		loop();
	}
	
	private void loop() {
		behavior.getFunction().apply(arduino);
	}
	
}
