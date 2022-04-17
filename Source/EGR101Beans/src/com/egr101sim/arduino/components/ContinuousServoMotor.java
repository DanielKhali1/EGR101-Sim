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
		state = "writtenAngle: 0";
	}

	@Override
	public void checkState() throws Exception {
		
		angle = getPins()[0].getAngle();
//		System.out.println("angle from checkState = " + getPins()[0].getPrev().getAngle());
		// is grounded?
		boolean grounded = false;
		Pin cur = getPins()[2];
		while(cur != null) {
			if(cur.isLocal()) {
				grounded = true;
			}
			cur = cur.getPrev();
		}
		setGrounded(grounded);
		
		boolean powered = false;
		// is 5V powered?
		cur = getPins()[1];
		while(cur != null) {
			if(cur.isLocal() && cur.getPinType() == PinType.POWER_5V) {
				powered = true;
			}
			cur = cur.getPrev();
		}
		
//		System.out.println(isPowered() + " " + isGrounded() + " " + angle);
		setPowered(powered);
	}

	@Override
	public void Behavior() {
		// is grounded
		if(isPowered() && isGrounded() && angle != 90) {
			writtenAngle = (angle-90)/4;
		} else if(angle == 90) {
			writtenAngle = 0;
		}
		state = "" + writtenAngle;
	}

	public double getAngle() {
		return angle;
	}

}
