package com.egr101sim.arduino.test;

import java.util.Arrays;

import com.egr101sim.arduino.BaseArduino;

public class Behave implements java.util.function.Function<BaseArduino,String>{

	@Override
	public String apply(BaseArduino t) {
		
		System.out.println(Arrays.toString(t.getDigitalArray()));
		
		return "wow";
	}
	

}
