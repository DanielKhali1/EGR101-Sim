package com.egr101sim.arduino;

import com.egr101sim.arduino.tools.Translator;
import com.egr101sim.physics.Vector3d;

public class Arduino {
	
	ArduinoBehaviorManager behavior;
	BaseArduino arduino;
	TransformManager object;
	
	
	public Arduino(String instructions, Vector3d position) {
		
		arduino = new BaseArduino();
		behavior = new ArduinoBehaviorManager(arduino, null);
		object = new TransformManager(position);
		
	}
	
	public void compileSketch(String instructions) {
		System.out.println(instructions);
		String translated = new Translator(instructions).translate();
		System.out.println(translated);
		//behavior.compile(translated);
		
	}
	
	private void loop() {
		behavior.getSupplier().get();
	}
	
}
