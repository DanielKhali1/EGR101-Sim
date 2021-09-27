package com.egr101sim.arduino;



import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.tools.Translator;
import com.egr101sim.physics.Vector3d;

public class Arduino {
	
	ArduinoBehaviorManager behavior;
	BaseArduino arduino;
	
	
	public Arduino(String instructions) {
		
		arduino = new BaseArduino();
		behavior = new ArduinoBehaviorManager(arduino, null);
	}
	
	public void AddConnection(Pin pin1, Pin pin2, boolean isDigitalifIO, int ioNumber) {
		
	}
	
	public void compileSketch(String instructions) {
		String translated = new Translator(instructions).translate();
		
		System.out.println(translated);
		
		behavior.compile(translated);
	}
	
	public void execute() {
		System.out.println("execute");
		
	}
	
	private void loop() {
		behavior.getFunction().apply(arduino);
	}
	
}
