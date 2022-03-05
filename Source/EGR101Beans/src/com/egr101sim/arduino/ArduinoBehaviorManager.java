package com.egr101sim.arduino;

import java.util.function.Function;

import com.org.joor.Reflect;

import javafx.scene.control.TextArea;
import javafx.scene.text.Text;

public class ArduinoBehaviorManager {
	
	private Function<BaseArduino,String> function;
	private String stackTrace; 
	int index;
	
	public ArduinoBehaviorManager() {
		index = 1;
	}
	
	public void compile(String instructions, Text console) {
		//trashiest fix ever
		instructions = instructions.replace("class ArduinoBehavior implements", "class ArduinoBehavior"+ index +" implements");
		Object f = Reflect.compile("com.egr101sim.arduino.ArduinoBehavior"+index, instructions, this); 
		//System.out.println((String) f);
		
		try
		{
			setFunction(((Reflect) f).create().get(), console);
			index ++;
		}
		catch(NullPointerException e)
		{
			System.out.println("Build errors");
			console.setText(console.getText()+"\nFatal Error: building");
		}
		
	}

	public void setFunction(Function<BaseArduino,String> function,Text console) {
		System.out.println("THE FUNCTION HAS CHANGED");
		console.setText(console.getText()+"\nBuild Successful: Behavior has changed");
		if(this.function != null)
			System.out.println(this.function.equals(function));
		this.function = function;
		
	}
	
	public String stackPrint() 
	{
		return stackTrace; 
	}
	
	public void setStackTrace(String s)
	{
		stackTrace = s; 
	}

	
	public Function<BaseArduino,String> getFunction() {
		return function;
	}
}
