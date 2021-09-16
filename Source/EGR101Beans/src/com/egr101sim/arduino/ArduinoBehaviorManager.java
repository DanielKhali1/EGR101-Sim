package com.egr101sim.arduino;

import java.util.function.Supplier;

import com.org.joor.Reflect;

public class ArduinoBehaviorManager {
	
	private final BaseArduino arduino;
	private final Supplier<String> supplier;
	private String instruction;

	
	public ArduinoBehaviorManager(BaseArduino arduino, String instruction) {
		this.instruction = instruction;
		supplier = Reflect.compile("com.egr101sim.arduino.ArduinoBehavior", this.instruction).create().get();
		this.arduino = arduino;
	}
	

}
