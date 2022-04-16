package com.egr101sim.arduino.component.sensors;

import com.egr101sim.arduino.components.Component;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinIO;
import com.egr101sim.arduino.elements.PinType;

public class LineReadingIRSensor extends Component{

	
	private int whiteness;
	int[] randomNoiseBound;
	
	boolean works;
	
	public LineReadingIRSensor() {
		this.setPins(new Pin[3]);
		this.voltageLimit = 5;
		this.currentDrain = 5;
		randomNoiseBound = new int[2];
		randomNoiseBound[0] = 0;
		randomNoiseBound[1] = 0;
		works = false;
	}
	
	@Override
	public void checkState() throws Exception {
		
		// is grounded?
		boolean grounded = false;
		Pin cur = getPins()[2];
		while(cur != null) {
			if(cur.isLocal() && cur.getPinType() == PinType.GROUND ) {
				grounded = true;
			}
			cur = cur.getPrev();
		}
		setGrounded(grounded);
		
		boolean powered = false;
		// is 5V powered?
		cur = getPins()[0];
		while(cur != null) {
			if(cur.isLocal() && cur.getPinType() == PinType.POWER_5V) {
				powered = true;
			}
			cur = cur.getPrev();
		}
		setPowered(powered);
		
		//this is the output pin
		cur = getPins()[1];
		double current = 5.0 * ((double)getWhiteness()/(double)100) + (Math.random()*(randomNoiseBound[1] - randomNoiseBound[0]) + randomNoiseBound[0]);
		
		while(cur != null) {
			if(cur.isLocal() && cur.getPinType() == PinType.IO && cur.getPinIO() == PinIO.INPUT) {
				cur.setCurrent(current);
			}
			cur = cur.getPrev();
		}
		
	}
	
	public void readVal(int whiteness) {
		if(works)
			this.setWhiteness(whiteness);
	}

	@Override
	public void Behavior() {
		if(isPowered() && isGrounded()) {
			works = true;
		}
		System.out.println(super.name+" "+whiteness + " " + works);
	}

	public int getWhiteness() {
		return whiteness;
	}

	public void setWhiteness(int whiteness) {
		this.whiteness = whiteness;
	}

}
