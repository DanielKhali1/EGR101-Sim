package com.egr101sim.arduino;



import java.util.ArrayList;

import com.egr101sim.arduino.components.Component;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;
import com.egr101sim.arduino.elements.SpecialPin;
import com.egr101sim.arduino.tools.Translator;
import com.egr101sim.core.SimulationManager;

import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

/**
 * 
 * @author danny
 *
 * Arduino Class: maintains:
 * - base arduino (arduino pins & voltages)
 * - components
 * - arduino compiled behavior
 * 
 *
 */
public class Arduino {
	
	public ArduinoBehaviorManager behavior;
	private BaseArduino arduino;
	
	private ArrayList<Component> components = new ArrayList<Component>();
	
	/**
	 * Base Arduino requires the parameter SimulationManager so this constructor
	 * mainly is used to instantiate Base Arduino and pass its params
	 * 
	 * @param Simulation Manager
	 */
	public Arduino(SimulationManager m) {
		
		setArduino(new BaseArduino(m));
		behavior = new ArduinoBehaviorManager();
	}
	
	public String stackPrint() 
	{
		return behavior.stackPrint(); 
	}

	
	/**
	 * adds component object to the component list
	 * 
	 * @param Component
	 */
	public void addComponent(Component c) {
		getComponents().add(c);
	}
	
	
	/**
	 * connects pins together given 2 pins.
	 * pin1 -> pin2
	 * 
	 * pin1.next -> pin2
	 * pin2.prev -> pin1
	 * 
	 * @param pin1
	 * @param pin2
	 * @param isDigitalifIO1
	 * @param isDigitalifIO2
	 * @param ioNumber1
	 * @param ioNumber2
	 */
	public void AddConnection(Pin pin1, Pin pin2,
							  boolean isDigitalifIO1, boolean isDigitalifIO2,
							  int ioNumber1, int ioNumber2) {
		
		
		// if IO and Local map pin1 to the digital Array or analog array based off of preference
		if(pin1.isLocal() && pin1.getPinType() == PinType.IO) {
			if(isDigitalifIO1)
				getArduino().getDigitalArray()[ioNumber1] = pin1;
			else
				getArduino().getAnalogArray()[ioNumber1] = pin1;
		} 
		
		// if IO and Local map pin2 to the digital Array or analog array based off of preference
		if(pin2.isLocal() && pin1.getPinType() == PinType.IO) {
			if(isDigitalifIO2)
				getArduino().getDigitalArray()[ioNumber2] = pin2;
			else
				getArduino().getAnalogArray()[ioNumber2] = pin2;
		}
		
		
		if(pin1.isLocal() && (pin1.getPinType() == PinType.POWER_5V || pin1.getPinType() == PinType.POWER_3_3V)) {
			if(pin1.getPinType() == PinType.POWER_5V ) {
				((SpecialPin)arduino.getP5V()).getNexts().add(pin2);
			} else if(pin1.getPinType() == PinType.POWER_3_3V) {
				((SpecialPin)arduino.getP3_3v()).getNexts().add(pin2);
			}
			pin2.setPrev(arduino.getP5V());
		}
		else {
			pin1.addNext(pin2);
			pin2.addPrev(pin1);
		}
		
	}
	
	/**
	 * translates Arduino string to java string then passes 
	 * it through the behavior compile call which compiles the
	 * java code at runtime
	 * 
	 * @param instructions
	 */
	public void compileSketch(String instructions, Text console) {
		
		String translated = new Translator(instructions).translate();
		behavior.compile(translated, console);
	}

	/**
	 * runs executes the behavior function taken from a run-time compiled
	 * Arduino instructions.
	 * 
	 * MUST RUN FIRST BEFORE execute();
	 */
	public void setup() {
		loop();
	}
	
	/**
	 * calls the behavior function taken from the run-time compiled
	 * Arduino instructinos. should be used to iterate in Simulation Manager
	 * 
	 */
	public void execute() {
		if(!arduino.isDelayed())
			loop();
	}
	
	/**
	 * ONLY USE AFTER ARDUINO BEHAVIOR COMPILE CALL
	 * this will interface with the compiled arduino code and call the apply
	 * function (passing the base arduino)
	 * 
	 */
	private void loop() {
		behavior.getFunction().apply(getArduino());
	}

	/**
	 * returns base arduino
	 * 
	 * @return base Arduino
	 */
	public BaseArduino getArduino() {
		return arduino;
	}

	/**
	 * sets base arduino
	 * 
	 * @param base arduino
	 */
	public void setArduino(BaseArduino arduino) {
		this.arduino = arduino;
	}

	/**
	 * @return the components
	 */
	public ArrayList<Component> getComponents() {
		return components;
	}

	/**
	 * @param components the components to set
	 */
	public void setComponents(ArrayList<Component> components) {
		this.components = components;
	}
	
	
	/**
	 * re-instantiates behavior for next compile
	 */
	public void reloadBehavior() {
		behavior = new ArduinoBehaviorManager();
	}

}
