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
		
		this.analogArray = new Pin[6];

		// initialize array of analog pins from 0 - 5
		this.ground = new Pin(PinType.GROUND);
		this.p5V = new Pin(PinType.POWER_5V);
		this.p3_3v = new Pin(PinType.POWER_3_3V);
	}
	
	

	// behavior from each motor/sensor/other can be utilized in update
	// behavior must somehow be affected by coding, the question is how that is effected
	// 
	public void update() {
		
		//blink
		//digital write 100 to pin 1 
		//sleep(100)
		//digital write 0 to pin 1
		
	}
	
	private static void fillPinArray(PinType type, Pin[] array) {
		for(int i = 0; i < array.length; i++)
			array[i] = new Pin(type);
	}
}
