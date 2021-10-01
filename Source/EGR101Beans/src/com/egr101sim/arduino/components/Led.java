package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;

public class Led extends Component{

	
	boolean grounded = false;
	boolean powered = false;
	
	/**
	 * short ground 1st pin
	 * longweird one power 2nd pin
	 */
	public Led() {
		this.setPins(new Pin[2]);
		//2 volts
		this.voltageLimit = 2.2;
		this.currentDrain = 2.2;
	}
	
	public void checkState() throws Exception {
		
		
		// is grounded?
		Pin cur = getPins()[0];
		while(cur != null) {
			if(cur.isLocal()) {
				grounded = true;
			}
			cur = cur.getPrev();
		}
		
		// is powered?
		if(getPins()[1].getPrev().getCurrent() <= 2.2 && getPins()[1].getPrev().getCurrent() > 1 ) {
			powered = true;
		} else if(getPins()[1].getPrev().getCurrent() > 2.2) {
			System.out.println("LED MELTED voltage in: " + getPins()[1].getPrev().getCurrent());
			//throw new Exception();
		} else {
			powered = false;
		}
			
		
	}
	
	@Override
	public void Behavior() {
		if(powered && grounded) {
			System.out.println("LED ON");
		}
		
	}

}
