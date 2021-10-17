package com.egr101sim.arduino;

import java.util.function.Function;

import com.org.joor.Reflect;

public class ArduinoBehaviorManager {
	
	private Function<BaseArduino,String> function;
	int index;
	
	public ArduinoBehaviorManager() {
		index = 1;
	}
	
	public void compile(String instructions) {
		//trashiest fix ever
		instructions = instructions.replace("class ArduinoBehavior implements", "class ArduinoBehavior"+ index +" implements");
		setFunction(Reflect.compile("com.egr101sim.arduino.ArduinoBehavior"+index, instructions ).create().get());
		index ++;
	}

	public void setFunction(Function<BaseArduino,String> function) {
		System.out.println("THE FUNCTION HAS CHANGED");
		if(this.function != null)
			System.out.println(this.function.equals(function));
		this.function = function;
		
	}
	
	public Function<BaseArduino,String> getFunction() {
		return function;
	}
}
