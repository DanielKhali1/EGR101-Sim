package com.egr101sim.arduino;

import java.util.Arrays;

import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;

public class BaseArduino {
	
	private final Pin p5V;
	private final Pin p3_3v;
	private final Pin ground;
	
	final Pin[] digitalArray;
	private final Pin[] analogArray;
	
	public BaseArduino() {
		// initialize array of digital pins from 0 - 13
		this.digitalArray = new Pin[14];
		fillPinArray(PinType.DIGITAL, this.digitalArray);
		
		this.analogArray = new Pin[6];
		fillPinArray(PinType.ANALOG, this.analogArray);

		// initialize array of analog pins from 0 - 5
		this.ground = new Pin(PinType.GROUND);
		this.p5V = new Pin(PinType.POWER_5V);
		this.p3_3v = new Pin(PinType.POWER_3_3V);
	}
	
	private static void fillPinArray(PinType type, Pin[] array) {
		for(int i = 0; i < array.length; i++)
			array[i] = new Pin(type);
	}
}
