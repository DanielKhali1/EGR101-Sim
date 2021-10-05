package com.egr101sim.arduino.components;

import com.egr101sim.arduino.elements.Pin;

public class Led extends Component{

	
	boolean grounded = false;
	private boolean powered = false;
	
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
	
	
	/**
	 * checks whether powered or grounded and updates LED state
	 */
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
		if(getPins()[1].getCurrent() <= 2.2 && getPins()[1].getCurrent() > 1 ) {
//			System.out.println("LED POWER ON voltage rx: " + getPins()[1].getCurrent());
			setPowered(true);
		} else if(getPins()[1].getPrev().getCurrent() > 2.2) {
//			System.out.println("LED MELTED voltage in: " + getPins()[1].getCurrent());
			setPowered(false);
		} else {
//			System.out.println("LED POWER OFF voltage rx: " + getPins()[1].getCurrent());
			setPowered(false);
		}
	}
	
	@Override
	public void Behavior() {
		if(isPowered() && grounded) {
			//if anything do it here
		}
	}

	/**
	 * @return the powered
	 */
	public boolean isPowered() {
		return powered;
	}

	/**
	 * @param powered the powered to set
	 */
	public void setPowered(boolean powered) {
		this.powered = powered;
	}

}
