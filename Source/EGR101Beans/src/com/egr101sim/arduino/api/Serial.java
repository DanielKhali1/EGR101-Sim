package com.egr101sim.arduino.api;

public class Serial {

	public static long dataRate;
	public static String serialLog = "";
	
	/**
	 * Sets the data rate in bits per second (baud) for serial data transmission.
	 * For communicating with Serial Monitor, make sure to use one of the baud rates
	 * listed in the menu at the bottom right corner of its screen. You can, however,
	 * specify other rates - for example, to communicate over pins 0 and 1 with a
	 * component that requires a particular baud rate.
	 *  
	 * An optional second argument configures the data, parity, and stop bits.
	 * The default is 8 data bits, no parity, one stop bit.
	 * 
	 * @param baudRate in bits per second (baud). Allowed data types: long.
	 */
	public static void begin(long speed) {
		dataRate = speed;
	}
	
	/**
	 * Prints data to the serial port as human-readable ASCII text followed by a carriage
	 * return character (ASCII 13, or '\r') and a newline character (ASCII 10, or '\n').
	 * This command takes the same forms as Serial.print().
	 * 
	 * @param val the value to print. Allowed data types: any data type.
	 */
	public static void println(String val) {
		if(serialLog.length() > 1000) {
			serialLog = serialLog.substring(500);
		}
		serialLog += val + "\n";
	}
	
	/**
	 * Prints data to the serial port as human-readable ASCII text followed by a carriage
	 * return character (ASCII 13, or '\r') and a newline character (ASCII 10, or '\n').
	 * This command takes the same forms as Serial.print().
	 * 
	 */
	public static void print(String val) {
		if(serialLog.length() > 1000) {
			serialLog = serialLog.substring(500);
		}
		serialLog += val + "\n";
	}
	
	/**
	 * Waits for the transmission of outgoing serial data to complete. (Prior to 
	 * Arduino 1.0, this instead removed any buffered incoming serial data.)
	 * 
	 */
	public static void flush() {
		//TODO: figure out what the fuck this means
	}
}
