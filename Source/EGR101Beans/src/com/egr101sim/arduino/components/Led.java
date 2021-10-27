package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;

public class Led extends Component{

	/**
	 * short ground 1st pin
	 * long weird one power 2nd pin
	 */
	public Led() {
		this.setPins(new Pin[2]);
		this.voltageLimit = 2.2;
		this.currentDrain = 2.2;
	}
	
	
	/**
	 * checks whether powered or grounded and updates LED state
	 */
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
		if(getPins()[1].getCurrent() <= 2.2 && getPins()[1].getCurrent() > 1 ) {
			setPowered(true);
		} else if(getPins()[1].getPrev().getCurrent() > 2.2) {
			setPowered(false);
		} else {
			setPowered(false);
		}
	}
	
	@Override
	public void Behavior() {
		if(isPowered() && isGrounded()) {
			//if anything do it here
			//send comm to Unity saying led on
		}
	}

}
