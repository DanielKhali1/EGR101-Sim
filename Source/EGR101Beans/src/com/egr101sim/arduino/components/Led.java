package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;

public class Led extends Component{

	
	
	public Led() {
		this.pins = new Pin[2];
		//2 volts
		this.voltageLimit = 2;
		// has an IO pin not native to the Arduino board
		this.pins[0] = new Pin(PinType.IO, false);
		// has a ground pin not native to the Arduino board
		this.pins[1] = new Pin(PinType.GROUND, false);
	}
	
	@Override
	public void Behavior() {
		
		
	}

}
