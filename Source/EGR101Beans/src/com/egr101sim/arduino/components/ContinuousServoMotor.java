package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;

public class ContinuousServoMotor extends Component {
	
	long angle;
	int rotations;
	
	int writtenAngle;
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
		
		// is grounded?
		Pin cur = getPins()[0];
		while(cur != null) {
			if(cur.isLocal()) {
				setGrounded(true);
			}
			cur = cur.getPrev();
		}
		
		// is powered?
		if(getPins()[1].getCurrent() <= 6 && getPins()[1].getCurrent() > 4.8 ) {
			setPowered(true);
		} else {
			setPowered(false);
		}
		
		//is angle being written to?
		
	}

	@Override
	public void Behavior() {
		// is grounded
		if(isPowered() && isGrounded() && angle != 90) {
			
		}
	}

	public long getAngle() {
		return angle;
	}

}
