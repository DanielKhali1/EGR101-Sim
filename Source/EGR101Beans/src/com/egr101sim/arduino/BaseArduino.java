package com.egr101sim.arduino;

import com.egr101sim.arduino.elements.AnalogPin;
import com.egr101sim.arduino.elements.DigitalPin;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinIO;
import com.egr101sim.arduino.elements.PinState;
import com.egr101sim.arduino.elements.PinType;

/**
 * 
 * @author danny
 *
 *
 *	plan right now would be to use this class to house the Pins and actions 
 *  related to pins,power,ground,dig,analog Arduino Behavior Manager should be composed of this
 *  class such that we can insert behavior into the Arduino
 *  may need to add 
 */
public class BaseArduino {
	
	private final Pin p5V;
	private final Pin p3_3v;
	private final Pin ground;
	
	private final DigitalPin[] digitalArray;
	private final AnalogPin[] analogArray;
	
	
	private boolean delayed;
	private long delayedTimer;
	
	private long millis = 0;
	
	
	public BaseArduino() {
		// initialize array of digital pins from 0 - 13
		this.digitalArray = new DigitalPin[14];
		
		this.analogArray = new AnalogPin[6];

		// initialize array of analog pins from 0 - 5
		this.ground = new Pin(PinType.GROUND, true);
		this.p5V = new Pin(PinType.POWER_5V, true);
		this.p3_3v = new Pin(PinType.POWER_3_3V, true);
	}
	
	// call every time iteration so we can update millis
	public void update() {
		
		if(delayed) {
			delayedTimer -= millis;
			if(delayedTimer <= 0)
				delayed = false;			
		}
		
		if (!delayed)
			incrementMillis(1);
	}
	
	/**
	 * 
	 * Pauses the program for the amount of time (in milliseconds) 
	 * specified as parameter. (There are 1000 milliseconds in a second.)
	 * @param milliseconds
	 */
	public void delay(long milliseconds) {
		delayed = true;
		delayedTimer = milliseconds;
	}
	
	/**
	 * 
	 * Pauses the program for the amount of time (in microseconds) 
	 * specified as parameter. (There are 1000 milliseconds in a second.)
	 * @param milliseconds
	 */
	public void delayMicroseconds(long microseconds) {
		delayed = true;
		delayedTimer = microseconds/1000;
	}
	
	
	
	/**
	 * Returns the number of microseconds since the Arduino board began running
	 * the current program. This number will overflow (go back to zero), after
	 * approximately 70 minutes. On the boards from the Arduino Portenta family
	 * this function has a resolution of one microsecond on all cores. On 16 MHz
	 * Arduino boards (e.g. Duemilanove and Nano), this function has a resolution
	 * of four microseconds (i.e. the value returned is always a multiple of four).
	 * On 8 MHz Arduino boards (e.g. the LilyPad), this function has a resolution of eight
	 * microseconds.
	 * @return
	 */
	public long micros() {
		return millis*1000;
	}
	
	/**
	 * Returns the number of milliseconds passed since the 
	 * Arduino board began running the current program. This number will overflow 
	 * (go back to zero), after approximately 50 days. (25 in this program since we're using a signed long
	 * @return
	 */
	public long millis() {
		return millis;
	}
	
	/**
	 * increments milliseconds such that we can always understand how much time has passed in the arduino
	 * @param iterator
	 */
	private void incrementMillis(long iterator) {
		if(millis + iterator < Long.MAX_VALUE)
			millis += iterator;
		else
			millis = 0;
	}

	/**
	 * Reads the value from the specified analog pin.
	 * Arduino boards contain a multichannel, 10-bit analog
	 * to digital converter. This means that it will map
	 * input voltages between 0 and the operating voltage(5V or 3.3V)
	 * into integer values between 0 and 1023. On an Arduino UNO,
	 * for example, this yields a resolution between readings
	 * of: 5 volts / 1024 units or, 0.0049 volts (4.9 mV) per unit.
	 * See the table below for the usable pins, operating voltage
	 * and maximum resolution for some Arduino boards.
	 * 
	 * @param analogPin
	 * @return
	 */
	public int analogRead(int analogPin) {
		return analogArray[analogPin].getVoltage();
	}
	
	
	/**
	 * Writes an analog value (PWM wave) to a pin. Can be used to light a LED at varying
	 * brightnesses or drive a motor at various speeds. After a call to analogWrite(), the
	 * pin will generate a steady rectangular wave of the specified duty cycle until the next call
	 * to analogWrite() (or a call to digitalRead() or digitalWrite()) on the same pin.
	 * 
	 * pin: the Arduino pin to write to. Allowed data types: int.
	 * value: the duty cycle: between 0 (always off) and 255 (always on). Allowed data types: int.
	 * 
	 * @param analogPin
	 * @param val
	 */
	public void analogWrite(int analogPin, int val) {
		analogArray[analogPin].setValue(val);
	}
	
	/**
	 * Configures the specified pin to behave either as an input or an output.
	 * See the Digital Pins page for details on the functionality of the pins. As of Arduino 1.0.1,
	 * it is possible to enable the internal pullup resistors with the mode INPUT_PULLUP.
	 * Additionally, the INPUT mode explicitly disables the internal pullups.
	 * 
	 * @param pin
	 * @param pinIo
	 */
	public void pinMode(int pin, int pinIo) {
		getDigitalArray()[pin].setPinIO((pinIo == 0) ? PinIO.INPUT : PinIO.OUTPUT);
	}
	
	/**
	 * If the pin has been configured as an OUTPUT with pinMode(),
	 * its voltage will be set to the corresponding 
	 * value: 5V (or 3.3V on 3.3V boards) for HIGH,
	 * 
	 * 0V (ground) for LOW.
	 * @param pin
	 * @param pinState
	 */
	public void digitalWrite(int pin, int pinState) {
		getDigitalArray()[pin].setPinState((pinState == 0) ? PinState.LOW : PinState.HIGH);
	}
	
	/**
	 * Reads the value from a specified digital pin,
	 * returns either HIGH or LOW.
	 * 
	 * @param pin
	 * @return
	 */
	public PinState digitalRead(int pin) {
		return getDigitalArray()[pin].getPinState();
	}

	public DigitalPin[] getDigitalArray() {
		return digitalArray;
	}

	public boolean isDelayed() {
		return delayed;
	}

	public void setDelayed(boolean delayed) {
		this.delayed = delayed;
	}
	
}
