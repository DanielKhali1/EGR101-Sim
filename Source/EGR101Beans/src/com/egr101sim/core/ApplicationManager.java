package com.egr101sim.core;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

import com.egr101sim.arduino.Arduino;
import com.egr101sim.arduino.component.sensors.DistanceMeasuringIRSensor;
import com.egr101sim.arduino.component.sensors.LineReadingIRSensor;
import com.egr101sim.arduino.component.sensors.UltrasonicSensor;
import com.egr101sim.arduino.components.Component;
import com.egr101sim.arduino.components.ContinuousServoMotor;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;
import com.egr101sim.arduino.elements.SpecialPin;

import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

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
		setupInitialBoeBotMotors();

	}

	public void initializeServerCharacteristics() {

		try {
			System.out.println("Starting Socket Streams");
			ss = new ServerSocket(667);
			sock = ss.accept();

			dis = new DataInputStream(sock.getInputStream());
			dos = new DataOutputStream(sock.getOutputStream());

			simManager.setupComms(ss, sock, dis, dos);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * update the code
	 */
	public void updateBehavior(String instructions, Text console) {
		System.out.println("BUILDING..");
		console.setText(console.getText() + "\nBuilding..");
		arduino.compileSketch(instructions, console);
	}

	private void setupInitialBoeBotMotors() {
		// right wheel: 12 left wheel: 13
		// wire initial components ie: arduino motors to boebot
		ContinuousServoMotor rightMotor = new ContinuousServoMotor();
		ContinuousServoMotor leftMotor = new ContinuousServoMotor();

		for (int i = 0; i < rightMotor.getPins().length; i++)
			rightMotor.getPins()[i] = new Pin(PinType.GENERAL, false);
		for (int i = 0; i < leftMotor.getPins().length; i++)
			leftMotor.getPins()[i] = new Pin(PinType.GENERAL, false);

		arduino.getArduino().getDigitalArray()[11] = new Pin(PinType.IO, true);
		arduino.getArduino().getGround()[0] = new Pin(PinType.GROUND, true);
		arduino.getArduino().getDigitalArray()[12] = new Pin(PinType.IO, true);

//		System.out.println(arduino.getArduino().getDigitalArray()[11]);
//		System.out.println(Arrays.toString(rightMotor.getPins()));
		arduino.getArduino().getDigitalArray()[11].addNext(rightMotor.getPins()[0]);
		rightMotor.getPins()[0].setPrev(arduino.getArduino().getDigitalArray()[11]);
		rightMotor.getPins()[1].setPrev(arduino.getArduino().getP5V());
		rightMotor.getPins()[2].setPrev(arduino.getArduino().getGround()[0]);

		arduino.getArduino().getDigitalArray()[12].addNext(leftMotor.getPins()[0]);
		leftMotor.getPins()[0].setPrev(arduino.getArduino().getDigitalArray()[12]);
		leftMotor.getPins()[1].setPrev(arduino.getArduino().getP5V());
		leftMotor.getPins()[2].setPrev(arduino.getArduino().getGround()[0]);

		
		leftMotor.setName("leftMotor");
		rightMotor.setName("rightMotor");
		arduino.getComponents().add(rightMotor);
		arduino.getComponents().add(leftMotor);
	}

	public void execute(Process process, Text console) {
		console.setText(console.getText() + "\nSimulation setting up..");
		System.out.println("SETTING UP SIM..");
		new Thread(() -> initializeServerCharacteristics()).start();
		simManager.setup();

		console.setText(console.getText() + "\nSimulation executing..");
		System.out.println("EXECUTING..");
		
		while (isSimRunning()) {

			simManager.iterate();
			// new Thread(()->simManager.sendMessage(simManager.generateMessage()));

//			if (!process.isAlive()) {
//				setSimRunning(false);
//			}
		}
		
		simManager.shutDown(console);
		for(int i = 2;i  < arduino.getComponents().size(); i++) {
			System.out.println("removing");
			arduino.getComponents().remove(i);
			
		}
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

	public String stackPrint() {
		String temp = arduino.stackPrint();
		String stackPrint = temp.replace("/com/egr101sim/arduino/ArduinoBehavior1.java:", "");
		return stackPrint;
	}

	public void addComponentsAndConnections() throws FileNotFoundException {
		File componentData = new File("../../Data/Component_Data.dat");
//		File wiringData = new File("../../Data/Wiring_Data.dat");
		BufferedReader bf = new BufferedReader(new FileReader(componentData));

		try {

			String line = "";
			while ((line = bf.readLine()) != null) {
				System.out.println(line);
				if(line.contains("UltraSonic")) {
					addComponent(line, new UltrasonicSensor());
				} else if (line.contains("lineReadingIR")) {
					addComponent(line, new LineReadingIRSensor());
				} else if (line.contains("distancemeasuringirsensor")) {
					addComponent(line, new DistanceMeasuringIRSensor());
				}
			}
			bf.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void addComponent(String name, Component c) {
		Component component = c;
		arduino.getComponents().add(component);
	}

}
