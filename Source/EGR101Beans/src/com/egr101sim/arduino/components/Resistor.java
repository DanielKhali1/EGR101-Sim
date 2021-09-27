package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;

public class Resistor extends Component {
	
	double resistance;
	
	public Resistor() {
		this.pins = new Pin[2];
		//2 volts
		this.voltageLimit = 500;
		// has an IO pin not native to the Arduino board
		this.pins[0] = new Pin(PinType.GENERAL, false);
		// has a ground pin not native to the Arduino board
		this.pins[1] = new Pin(PinType.GENERAL, false);
	}
	
	public void directCurrent(Pin pin, Pin pin2) {
		pin.setCurrent(pin2.getCurrent()/resistance);
	}
	
	
	@Override
	public void Behavior() {
		
	}
}
