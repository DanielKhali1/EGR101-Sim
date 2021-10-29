package com.egr101sim.arduino.component.sensors;

import com.egr101sim.arduino.components.Component;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinIO;
import com.egr101sim.arduino.elements.PinType;

public class DistanceMeasuringIRSensor extends Component{
	
	int distance;
	int[] randomNoiseBound;
	
	boolean works;
	
	public DistanceMeasuringIRSensor() {
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
		Pin cur = getPins()[1];
		while(cur != null) {
			if(cur.isLocal() && cur.getPinType() == PinType.GROUND ) {
				grounded = true;
			}
			cur = cur.getPrev();
		}
		setGrounded(grounded);
		
		boolean powered = false;
		// is 5V powered?
		cur = getPins()[2];
		while(cur != null) {
			if(cur.isLocal() && cur.getPinType() == PinType.POWER_5V) {
				powered = true;
			}
			cur = cur.getPrev();
		}
		setPowered(powered);
		
		//this is the output pin
		cur = getPins()[0];
		double current = Math.pow(Math.pow(29.988, 1000)/Math.pow(distance, 1000), 1/1173);
		System.out.println(current);
		
		while(cur != null) {
			if(cur.isLocal() && cur.getPinType() == PinType.IO && cur.getPinIO() == PinIO.INPUT) {
				cur.setCurrent(current);
			}
			cur = cur.getPrev();
		}
		
	}
	
	public void readVal(int distance) {
		if(works)
			this.distance = distance;
	}

	@Override
	public void Behavior() {
		if(isPowered() && isGrounded()) {
			works = true;
		}
	}

}
