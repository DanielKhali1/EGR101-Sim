package com.egr101sim.arduino;

import com.egr101sim.arduino.tools.Translator;
import com.egr101sim.physics.Vector3d;

public class Arduino {
	
	ArduinoBehaviorManager behavior;
	BaseArduino arduino;
	TransformManager object;
	
	
	public Arduino(String instructions, Vector3d position) {
		
		arduino = new BaseArduino();
		behavior = new ArduinoBehaviorManager(arduino, null);
		object = new TransformManager(position);
		
	}
	
	public void compileSketch(String instructions) {
		System.out.println(instructions);
//		String translated = new Translator(instructions).translate();
//		System.out.println(translated);
		
		String translated = "package com.example;\n" +
				    	    "class HelloWorld implements java.util.function.Supplier<String> {\n" +
				    	    "    public String get() {\n" +
				    	    "        return \"Hello World!\";\n" +
				    	    "    }\n" +
				    	    "}\n";
		
		behavior.compile(translated);
		
	}
	
	public void execute() {
		System.out.println("execute");
	}
	
	private void loop() {
		behavior.getSupplier().get();
	}
	
}
