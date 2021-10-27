package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;

public class ContinuousServoMotor extends Component {
	
	double angle;
	int rotations;
	
	public double writtenAngle;
	/**
	 * 
	 */
	public ContinuousServoMotor() {
		this.setPins(new Pin[3]);
		this.voltageLimit = 6;
		this.currentDrain = 6;
		angle = 0;
		rotations = 0;
		writtenAngle = 0;
	}

	@Override
	public void checkState() throws Exception {
		
		angle = getPins()[0].getAngle();
		// is grounded?
		Pin cur = getPins()[2];
		while(cur != null) {
			if(cur.isLocal() && cur.getPinType() == PinType.GROUND ) {
				setGrounded(true);
			}
			cur = cur.getPrev();
		}
		
		// is 5V powered?
		cur = getPins()[1];
		while(cur != null) {
			if(cur.isLocal() && cur.getPinType() == PinType.POWER_5V) {
				setPowered(true);
			}
			cur = cur.getPrev();
		}
//		if(getPins()[1].getCurrent() <= 6 && getPins()[1].getCurrent() > 4.8 ) {
//			setPowered(true);
//		} else {
//			setPowered(false);
//		}
		
		//is angle being written to?
		
	}

	@Override
	public void Behavior() {
		// is grounded
		if(isPowered() && isGrounded() && angle != 90) {
			writtenAngle = (angle-90)/4;
		} else if(angle == 90) {
			writtenAngle = 0;
		}
		
	}

	public double getAngle() {
		return angle;
	}

}
