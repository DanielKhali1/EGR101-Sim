package com.egr101sim.arduino.api;

import com.egr101sim.arduino.BaseArduino;

/**
 * This Class allows an Arduino board to control RC 
 * (hobby)servo motors. Servos have integrated gears and 
 * a shaft that can be precisely controlled. Standard servos 
 * allow the shaft to be positioned at various angles, usually 
 * between 0 and 180 degrees. Continuous rotation servos 
 * allow the rotation of the shaft to be set to various speeds.
 * 
 *
 */
public class Servo {
	
	private int min;
	private int max;
	private int pin;
	
	private int angle;
	public BaseArduino b;
	
	public Servo() {
		this.min = 544;
		this.max = 2400;
		this.pin = -1;
	}
	
	/**
	 * Attach the Servo variable to a pin.
	 * Note that in Arduino 0016 and earlier,
	 * the Servo library supports servos on only two pins: 9 and 10.
	 * 
	 * @param pin the number of the pin that the servo is attached to
	 */
	public void attach(int pin) {
		this.pin = pin;
	}
	
	/**
	 * 
	 * @param pin
	 * @param min (optional): the pulse width, in microseconds, corresponding to the minimum (0 degree) angle on the servo (defaults to 544)
	 * @param max (optional): the pulse width, in microseconds, corresponding to the maximum (180 degree) angle on the servo (defaults to 2400)
	 */
	public void attach(int pin, int min, int max) {
		this.pin = pin;
		this.min = min;
		this.max = max;
	}
	
	/**
	 * 
	 * Writes a value to the servo, controlling the shaft accordingly. 
	 * On a standard servo, this will set the angle of the shaft (in degrees), 
	 * moving the shaft to that orientation. On a continuous rotation servo, 
	 * this will set the speed of the servo (with 0 being full-speed in one direction, 
	 * 180 being full speed in the other, and a value near 90 being no movement).
	 * 
	 * @param angle the value to write to the servo, from 0 to 180
	 */
	public void write(int angle) {
		b.getDigitalArray()[pin].setAngle(angle);
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Writes a value in microseconds (us) to the servo,
	 * controlling the shaft accordingly. On a standard servo,
	 * this will set the angle of the shaft. On standard servos a parameter value of 
	 * 1000 is fully counter-clockwise, 2000 is fully clockwise, and 1500 is in the middle.
	 *
	 * Note that some manufactures do not follow this standard very closely so that servos
	 * often respond to values between 700 and 2300. Feel free to increase these endpoints
	 * until the servo no longer continues to increase its range. Note however that attempting
	 * to drive a servo past its endpoints (often indicated by a growling sound) is a high-current
	 * state, and should be avoided.
	 *
	 * Continuous-rotation servos will respond to the writeMicrosecond function in an 
	 * analogous manner to the write function.
	 * 
	 * @param us the value of the parameter in microseconds (int)
	 */
	public void writeMicroseconds(int us) {
		//TODO: figure out how to convert microseconds to angle
		b.getDigitalArray()[pin].setAngle(us/16.6666666666667);
//		System.out.println(pin + " " + b.getDigitalArray()[pin].getAngle());
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Read the current angle of the servo (the value passed to the last call to write()).
	 * 
	 * @return angle
	 */
	public int read() {
		return this.angle;
	}

	/**
	 * Check whether the Servo variable is attached to a pin.
	 * 
	 * @return isattached true if the servo is attached to pin; false otherwise
	 */
	public boolean attached() {
		return this.pin != -1;
	}
	
	/**
	 * Detach the Servo variable from its pin. If all Servo
	 * variables are detached, then pins 9 and 10 can be
	 * used for PWM output with analogWrite().
	 */
	public void detach() {
		this.pin = -1;
	}
}
