package com.egr101sim.arduino;

import java.util.function.Supplier;

import com.org.joor.Reflect;

public class ArduinoBehaviorManager {
	
	private final BaseArduino arduino;
	private Supplier<String> supplier;
	private String instruction;

	
	public ArduinoBehaviorManager(BaseArduino arduino, String instruction) {
		this.instruction = instruction;
		this.arduino = arduino;
	}
	
	public void compile(String instructions) {
		this.instruction = instructions;
		setSupplier(Reflect.compile("com.egr101sim.arduino.ArduinoBehavior", this.instruction).create().get());
	}

	public Supplier<String> getSupplier() {
		return supplier;
	}

	private void setSupplier(Supplier<String> supplier) {
		this.supplier = supplier;
	}
}
