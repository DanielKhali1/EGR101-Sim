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
		double current = voltageDistanceFunction(distance);
//		System.out.println(current);
		
		while(cur != null) {
			if(cur.isLocal() && cur.getPinType() == PinType.IO && cur.getPinIO() == PinIO.INPUT) {
				cur.setCurrent(current);
			}
			cur = cur.getPrev();
		}
		
	}
	
	private double voltageDistanceFunction(double distance) {
		double volt = distance;
		
		if(distance >= 15 ) {
			//distance = -29.053 + 9.35 * Math.pow(volt/22, (1/(-1.32) ));
		    volt = 22 * Math.pow((distance+29.053)/9.35, -1.32);
		}
		else if(distance >= 10) {
			volt = 1.35 + .1*distance;
		}
		else if(distance >= 0) {
			volt = .235*distance;
		} 
		
		return volt;
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
