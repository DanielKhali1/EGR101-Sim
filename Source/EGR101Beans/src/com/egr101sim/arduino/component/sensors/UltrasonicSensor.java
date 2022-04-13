package com.egr101sim.arduino.component.sensors;

import com.egr101sim.arduino.components.Component;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinIO;
import com.egr101sim.arduino.elements.PinType;

public class UltrasonicSensor extends Component{

	
	int distance;
	int[] randomNoiseBound;
	
	boolean works;
	
	public UltrasonicSensor() {
		this.setPins(new Pin[4]);
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
		Pin cur = getPins()[3];
		boolean grounded = false;
		if(getPins()[2] != null)
		{
//			Pin cur = getPins()[3];
			while(cur != null) {
				if(cur.isLocal() && cur.getPinType() == PinType.GROUND ) {
					grounded = true;
				}
				cur = cur.getPrev();
			}
		}
		setGrounded(grounded);
		
		boolean powered = false;
		// is 5V powered?
		if(getPins()[0] != null) {
			
		cur = getPins()[0];
		while(cur != null) {
			if(cur.isLocal() && cur.getPinType() == PinType.POWER_5V) {
				powered = true;
			}
			cur = cur.getPrev();
		}
		}
		setPowered(powered);
		
		
		if(getPins()[1] != null && getPins()[1].getCurrent() > 3.0 ) {
			works = true;
		} else {
			works = false;
		}
		
		
		//this is the echo pin (INPUT PIN)
		if(getPins()[2] != null)
			getPins()[2].setMicro(distance*29*2);
	}
	
	public void readVal(int distance) {
		//if(works)
			this.distance = distance;
	}

	@Override
	public void Behavior() {
		if(isPowered() && isGrounded()) {
			works = true;
		}
	}

}
