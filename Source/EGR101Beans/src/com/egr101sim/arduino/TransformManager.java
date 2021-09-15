package com.egr101sim.arduino;

import com.egr101sim.physics.Vector3d;

public class TransformManager {
	
	Vector3d position;
	Vector3d velocity;
	Vector3d acceleration;
	
	public TransformManager(Vector3d position) {
		this.position = position;
	}

}
