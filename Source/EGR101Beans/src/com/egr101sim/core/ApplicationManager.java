package com.egr101sim.core;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import com.egr101sim.arduino.Arduino;
import com.egr101sim.arduino.components.Component;
import com.egr101sim.arduino.components.ContinuousServoMotor;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;
import com.egr101sim.arduino.elements.SpecialPin;

public class ApplicationManager {
	
	public Arduino arduino;
	ArrayList<Component> widgets = new ArrayList<Component>();
	public SimulationManager simManager;
	private boolean isSimRunning;
	
	private ServerSocket ss;
	private Socket sock;
	private DataInputStream dis;
	private DataOutputStream dos;
	
	
	public ApplicationManager() {
		simManager = new SimulationManager();
		arduino = new Arduino(simManager);
		simManager.setArduino(arduino);
		setSimRunning(false);
	}
	
	public void initializeServerCharacteristics() {
		
		try {
			ss = new ServerSocket(666);
			sock = ss.accept();
			
			dis = new DataInputStream(sock.getInputStream());
			dos = new DataOutputStream(sock.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void sendMessage(String s) {
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
	
	/**
	 * update the code
	 */
	public void updateBehavior(String instructions) {
		System.out.println("BUILDING..");
		arduino.compileSketch(instructions);
	}
	
	/**
	 * update the wiring/widgets/design of bot
	 */
	public void updatePeripherals() {
		
	}
	
	
	private void setupInitialBoeBotMotors() {
		// right wheel: 12   left wheel: 13
		//wire initial components ie: arduino motors to boebot
		ContinuousServoMotor rightMotor = new ContinuousServoMotor();
		ContinuousServoMotor leftMotor = new ContinuousServoMotor();
		
		for(int i = 0; i < rightMotor.getPins().length; i++)
			rightMotor.getPins()[i] = new Pin(PinType.GENERAL, false);
		for(int i = 0; i < leftMotor.getPins().length; i++)
			leftMotor.getPins()[i] = new Pin(PinType.GENERAL, false);
		
		arduino.getArduino().getDigitalArray()[11] = new Pin(PinType.IO, true);
		arduino.getArduino().getGround()[0] = new Pin(PinType.GROUND, true);
		arduino.getArduino().getDigitalArray()[12] = new Pin(PinType.IO, true);
		
		System.out.println(arduino.getArduino().getDigitalArray()[11]);
		System.out.println(Arrays.toString(rightMotor.getPins()));
		arduino.getArduino().getDigitalArray()[11].addNext(rightMotor.getPins()[0]);
		rightMotor.getPins()[0].setPrev(arduino.getArduino().getDigitalArray()[11]);
		rightMotor.getPins()[2].setPrev(arduino.getArduino().getGround()[0]);
		rightMotor.getPins()[2].setPrev(arduino.getArduino().getP5V());
		
		arduino.getArduino().getDigitalArray()[12].addNext(rightMotor.getPins()[0]);
		leftMotor.getPins()[0].setPrev(arduino.getArduino().getDigitalArray()[12]);
		leftMotor.getPins()[2].setPrev(arduino.getArduino().getGround()[0]);
		leftMotor.getPins()[2].setPrev(arduino.getArduino().getP5V());

		arduino.getComponents().add(rightMotor);
		arduino.getComponents().add(leftMotor);
	}
	
	public void execute() {
		System.out.println("SETTING UP SIM..");
		new Thread(() -> initializeServerCharacteristics()).start();
		simManager.setup();
		setupInitialBoeBotMotors();
		
		System.out.println("EXECUTING..");
		
		while(isSimRunning()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			simManager.iterate();
			new Thread(() -> { sendMessage(simManager.generateMessage());}).start();
		}
		
		try {
			ss.close();
			sock.close();
			dis.close();
			dos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		simManager.shutDown();
	}

	/**
	 * @return the isSimRunning
	 */
	public boolean isSimRunning() {
		return isSimRunning;
	}

	/**
	 * @param isSimRunning the isSimRunning to set
	 */
	public void setSimRunning(boolean isSimRunning) {
		this.isSimRunning = isSimRunning;
	}

}
