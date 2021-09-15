package com.egr101sim.core;

import com.egr101sim.arduino.Arduino;
import com.egr101sim.physics.Vector3d;

public class ApplicationManager {
	
	Arduino arduino;
	
	public ApplicationManager() {
		arduino = new Arduino(null , new Vector3d());
	}
	
	public void execute(String programCode) {
		
	}

}
