package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;

public class ContinuousServoMotor extends Component {
	
	long angle;
	int rotations;
	/**
	 * 
	 */
	public ContinuousServoMotor() {
		this.setPins(new Pin[3]);
		this.voltageLimit = 5;
		this.currentDrain = 5;
		angle = 0;
		rotations = 0;
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
		if(isPowered() && isGrounded()) {
			
		}
	}

}
