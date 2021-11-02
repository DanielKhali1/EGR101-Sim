package com.egr101sim.arduino;

import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinIO;
import com.egr101sim.arduino.elements.PinState;
import com.egr101sim.arduino.elements.PinType;
import com.egr101sim.arduino.elements.SpecialPin;
import com.egr101sim.core.SimulationManager;

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
	
	private final SpecialPin p5V;
	private final SpecialPin p3_3v;
	private final Pin[] ground;
	
	private final Pin[] digitalArray;
	private final Pin[] analogArray;
	
	
	private boolean delayed;
	private long delayedTimer;
	
	private long millis = 0;
	SimulationManager simulationManager;
	
	public BaseArduino(SimulationManager s) {
		// initialize array of digital pins from 0 - 13
		this.digitalArray = new Pin[14];
		
		// initialize array of analog pins from 0 - 5
		this.analogArray = new Pin[14];

		this.ground = new Pin[3];
		
		this.p5V = new SpecialPin(PinType.POWER_5V, true);
		this.p3_3v = new SpecialPin(PinType.POWER_3_3V, true);
		
		simulationManager =s;

	}
	
	// call every time iteration so we can update millis
	public void update() {
		
		if(delayed) {
			if(delayedTimer < millis())
				delayed = false;			
		}
	}
	
	/**
	 * 
	 * Pauses the program for the amount of time (in milliseconds) 
	 * specified as parameter. (There are 1000 milliseconds in a second.)
	 * @param milliseconds
	 */
	public void delay(long milliseconds) {
		
		delayedTimer = milliseconds + millis();
		while(delayedTimer > millis()) {
			simulationManager.basicIterate();
		}
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
	public double analogRead(int pin) {
		
		if(pin >= 160 && pin < 2576) {
			return getAnalogArray()[pin-160].getCurrent()/0.0049;
		} else if(pin >= 2576) {
			return getDigitalArray()[pin-2566].getCurrent()/0.0049;
		}
		return getDigitalArray()[pin].getCurrent()/0.0049;
		
		
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
		// TODO: initiate PWM using Time to send waves to the pin or something
		//analogArray[analogPin].setCurrent(val);
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
		if(pin >= 160 && pin < 2576) {
			getAnalogArray()[pin-160].setPinIO((pinIo == 0) ? PinIO.INPUT : PinIO.OUTPUT);
		} else if(pin >= 2576) {
			getAnalogArray()[pin-2566].setPinIO((pinIo == 0) ? PinIO.INPUT : PinIO.OUTPUT);
		}
		else if(getDigitalArray()[pin] != null)
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
		if(pin >= 160 && pin < 2576) {
			getAnalogArray()[pin-160].setPinState((pinState == 0) ? PinState.LOW : PinState.HIGH);
		} else if(pin >= 2576) {
			getAnalogArray()[pin-2566].setPinState((pinState == 0) ? PinState.LOW : PinState.HIGH);
		}
		else if(getDigitalArray()[pin] != null)
			getDigitalArray()[pin].setPinState((pinState == 0) ? PinState.LOW : PinState.HIGH);
	}
	
	public double pulseIn(int pin, int pinState) {
		if(pin >= 160 && pin < 2576) {
			if (getAnalogArray()[pin-160].getPinIO()!= PinIO.INPUT)
				return 0;
			
			return getAnalogArray()[pin-160].getMicro();
		} else if(pin >= 2576) {
			if (getAnalogArray()[pin-2566].getPinIO()!= PinIO.INPUT)
				return 0;
			
			return  getAnalogArray()[pin-2566].getMicro();
		}
		else if(getDigitalArray()[pin] != null)
		{
			if (getDigitalArray()[pin].getPinIO()!= PinIO.INPUT)
				return 0;
			return getDigitalArray()[pin].getMicro();
		}
		return 0;
	}
	
	/**
	 * Reads the value from a specified digital pin,
	 * returns either HIGH or LOW.
	 * 
	 * @param pin
	 * @return
	 */
	public int digitalRead(int pin) {
		if(pin >= 160 && pin < 2576) {
			return (getAnalogArray()[pin-160].getCurrent() > 3.0) ? 1 : 0;
		} else if(pin >= 2576) {
			return (getAnalogArray()[pin-2566].getCurrent() > 3.0) ? 1 : 0;
		}
		
		return (digitalArray[pin].getCurrent() > 3.0) ? 1 : 0;
	}

	/**
	 * Returns the digital Array of Pin references
	 * @return digitalArray
	 */
	public Pin[] getDigitalArray() {
		return digitalArray;
	}

	public boolean isDelayed() {
		return delayed;
	}

	public void setDelayed(boolean delayed) {
		this.delayed = delayed;
	}

	public Pin[] getAnalogArray() {
		return analogArray;
	}

	public void setMilli(long time) {
		millis = time;
	}

	/**
	 * @return the p5V
	 */
	public Pin getP5V() {
		return p5V;
	}

	/**
	 * @return the p3_3v
	 */
	public Pin getP3_3v() {
		return p3_3v;
	}

	/**
	 * @return the ground
	 */
	public Pin[] getGround() {
		return ground;
	}
	
}
