package com.egr101sim.physics;

public class Vector2d {
	
	protected double x, y;
	
	public Vector2d(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2d() {
		this.x = 0;
		this.y = 0;
	}
	
	public double magnitude() {
		return Math.sqrt(x*x + y*y);
	}
	
	public void add(Vector2d v) {
		this.x += v.x;
		this.y += v.y;
	}
	
	public void mult(double constant) {
		this.x *= constant;
		this.y *= constant;
	}
	
	public void limit() {
		this.x /= magnitude();
		this.y /= magnitude();
	}
	

}
