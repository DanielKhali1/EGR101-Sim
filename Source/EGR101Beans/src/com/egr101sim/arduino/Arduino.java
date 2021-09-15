package com.egr101sim.arduino;

import com.egr101sim.arduino.tools.Translator;
import com.egr101sim.physics.Vector3d;

public class Arduino {
	
	ArduinoBehaviorManager behavior;
	BaseArduino arduino;
	TransformManager object;
	
	
	public Arduino(String instructions, Vector3d position) {
		
		arduino = new BaseArduino(new Translator(instructions).translate());
		behavior = new ArduinoBehaviorManager(arduino);
		object = new TransformManager(position);
		
	}
	
}
