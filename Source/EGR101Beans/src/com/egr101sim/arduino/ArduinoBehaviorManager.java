package com.egr101sim.arduino;

import java.util.function.Function;

import com.org.joor.Reflect;

public class ArduinoBehaviorManager {
	
	private Function<BaseArduino,String> function;
	
	private String instruction;

	
	public void compile(String instructions) {
		this.instruction = instructions;
		setFunction(Reflect.compile("com.egr101sim.arduino.ArduinoBehavior", this.instruction).create().get());
	}

	public void setFunction(Function<BaseArduino,String> function) {
		this.function = function;
	}
	
	public Function<BaseArduino,String> getFunction() {
		return function;
	}
}
