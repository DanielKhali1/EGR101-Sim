package com.egr101sim.physics;

public class Vector3d extends Vector2d {

	double z;
	
	public Vector3d(double x, double y, double z) {
		super(x, y);
		
	}
	
	public Vector3d() {
		this.x = 0;
		this.y = 0;
		this.z = 0;
	}
	
	@Override
	public double magnitude() {
		return Math.sqrt(x*x + y*y + z*z);
	}
	
	public void add(Vector3d v) {
		this.x += v.x;
		this.y += v.y;
		this.z += v.z;
	}
	
	@Override
	public void mult(double constant) {
		this.x *= constant;
		this.y *= constant;
		this.z *= constant;
	}
	
	@Override
	public void limit() {
		this.x /= magnitude();
		this.y /= magnitude();
		this.z /= magnitude();
	}
}
