package com.egr101sim.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

import com.egr101sim.arduino.Arduino;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinState;
import com.egr101sim.arduino.elements.SpecialPin;

import javafx.scene.text.Text;

public class SimulationManager {

	private Arduino arduino;
	Date startTime;
	Date currentTime;
	private ServerSocket ss;
	private Socket sock;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	
	public SimulationManager() {
		
	}
	
	public void setup() {
		getArduino().setup();
		startTime = new Date();
		getArduino().getArduino().setMilli(0);
	}
	
	
	public void iterate() {
		
		getArduino().execute();
		
		basicIterate();
		
		//should send messages here
	}
	
	public void basicIterate() {
		getArduino().getArduino().setMilli(new Date().getTime() - startTime.getTime());
		getArduino().getArduino().update();
		
		// make if a write was executed turn on digital pins
		translatePinStateToVoltage(getArduino().getArduino().getDigitalArray());

		// send power through pins
		sendPowerThroughPinConnections(getArduino().getArduino().getDigitalArray());
		sendPowerThroughPinConnections((SpecialPin)getArduino().getArduino().getP5V());
		sendPowerThroughPinConnections((SpecialPin)getArduino().getArduino().getP3_3v());
		
		// if components have power let them do things
		updateComponentState();
		
		executeComponentBehavior();
		
		//send message to unity
		
		try {
			new Thread(()-> sendMessage(generateMessage())).start();
			Thread.sleep(50);
		} catch (Exception e) {
			System.out.println("Issue Sending message");
		}
	}
	
	private void executeComponentBehavior() {
		for(int i = 0; i < getArduino().getComponents().size(); i++) { 
			getArduino().getComponents().get(i).Behavior();
		}
		
	}

	private void updateComponentState() {
		for(int i = 0; i < getArduino().getComponents().size(); i++) {
			try { getArduino().getComponents().get(i).checkState(); } 
			catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private void sendPowerThroughPinConnections(Pin[] pins) {
		
		for(int i = 0; i < pins.length; i++) {
			if(pins[i] != null) {
				Pin cur = pins[i];
				// run through the list of connections and send power through 
				while(cur != null) {
					if(cur.getPrev() != null) {
						// set the current power to the previous pins power
						cur.setCurrent(cur.getPrev().getCurrent() / cur.getPrev().getResistance());
						cur.setAngle(cur.getPrev().getAngle());

					}
					cur = cur.getNext();
				}
			}
		}
	}
	
	private void sendPowerThroughPinConnections(SpecialPin pin) {
			
			for(int i = 0; i < pin.getNexts().size(); i++) {
					Pin cur = pin.getNexts().get(i);
					// run through the list of connections and send power through 
					while(cur != null) {
						if(cur.getPrev() != null) {
							// set the current power to the previous pins power
							cur.setCurrent(cur.getPrev().getCurrent() / cur.getPrev().getResistance());
							cur.setAngle(cur.getPrev().getAngle());
						}
						cur = cur.getNext();
					}
			}
		}

	private void translatePinStateToVoltage(Pin[] pins) {
		for(int i = 0; i < pins.length; i++) {
			if(pins[i] != null) {
				if(pins[i].getPinState() == PinState.HIGH) {
					pins[i].setCurrent(5);
				} else if(pins[i].getPinState() == PinState.LOW) {
					pins[i].setCurrent(0);
				} 
			}
		}
	}

	/**
	 * @return the arduino
	 */
	public Arduino getArduino() {
		return arduino;
	}

	/**
	 * @param arduino the arduino to set
	 */
	public void setArduino(Arduino arduino) {
		this.arduino = arduino;
	}

	/**
	 * shuts down Arduino
	 * @param console 
	 */
	public void shutDown(Text console) {
		killAllPower(getArduino().getArduino().getDigitalArray());
		killAllPower(getArduino().getArduino().getAnalogArray());
		killAllPower((SpecialPin)getArduino().getArduino().getP5V());
		killAllPower((SpecialPin)getArduino().getArduino().getP3_3v());
		System.out.println("Killing Power");
		console.setText(console.getText() + "\nKilling Power..");

		
		if(ss != null) {
			try {
				ss.close();
				sock.close();
				dis.close();
				dos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		console.setText(console.getText() + "\nEnding Server Processes..");
		System.out.println("Closing Socket Streams");
		
		
	}
	
	public void sendMessage(String s) {
		System.out.println(s);
		try {
	        byte[] toSendBytes = s.getBytes();
	        int toSendLen = toSendBytes.length;
	        byte[] toSendLenBytes = new byte[4];
	        toSendLenBytes[0] = (byte)(toSendLen & 0xff);
	        toSendLenBytes[1] = (byte)((toSendLen >> 8) & 0xff);
	        toSendLenBytes[2] = (byte)((toSendLen >> 16) & 0xff);
	        toSendLenBytes[3] = (byte)((toSendLen >> 24) & 0xff);
	        dos.write(toSendLenBytes);
	        dos.write(toSendBytes);
			dos.flush();
		} catch(Exception e) {
			//System.out.println("no connection");
		}
		
	}
	
	private void killAllPower(Pin[] pins) {
		for(int i = 0; i < pins.length; i++) {
			if(pins[i] != null) {
				Pin cur = pins[i];
				// run through the list of connections and send power through 
				while(cur != null) {
					if(cur.getPrev() != null) {
						// set the current power to the previous pins power
						cur.setCurrent(0);
						cur.setAngle(90);
					}
					cur = cur.getNext();
				}
			}
		}
	}
	
	private void killAllPower(SpecialPin pin) {
		
		for(int i = 0; i < pin.getNexts().size(); i++) {
				Pin cur = pin.getNexts().get(i);
				// run through the list of connections and send power through 
				while(cur != null) {
					if(cur.getPrev() != null) {
						// set the current power to the previous pins power
						cur.setCurrent(0);
						cur.setAngle(90);
					}
					cur = cur.getNext();
				}
		}
	}

	public String generateMessage() {
		//run through list of components and send behaviors per frame
		String message = "";
		for(int i = 0; i < arduino.getComponents().size(); i++) {
			message+= i + "," + arduino.getComponents().get(i).getName() + "," + arduino.getComponents().get(i).getState() + "\n";
		}
		
//		System.out.println(message);
		
		return message;
	}

	public void setupComms(ServerSocket ss, Socket sock, DataInputStream dis, DataOutputStream dos) {
		this.ss = ss;
		this.sock = sock;
		this.dis = dis;
		this.dos = dos;
		
		
	}

}
