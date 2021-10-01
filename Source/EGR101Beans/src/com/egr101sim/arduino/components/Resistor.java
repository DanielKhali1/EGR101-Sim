package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;


/**
 * 
 * NO LONGER NEEDED WE WILL TREAT RESISTORS AS A TYPE OF PIN
 *
 */

public class Resistor extends Component {
	
	double resistance;
	Pin inputPin;
	Pin outputPin;
	
	
	public Resistor(int ohms) {
		this.setPins(new Pin[2]);
		//2 volts
		this.voltageLimit = 500;
		// has an input pin
		this.inputPin = new Pin(PinType.GENERAL, false);
		// has an output pin
		this.outputPin = new Pin(PinType.GENERAL, false);

		this.inputPin.setResistance(ohms);
	}
	
	
	@Override
	public void Behavior() {
		
	}

	@Override
	public void checkState() {
		Pin cur = inputPin;
		
		// is grounded?
		while(cur != null) {
			cur = cur.getPrev();
			if(cur.isLocal()) {
				grounded = true;
			}
		}
	}
}
