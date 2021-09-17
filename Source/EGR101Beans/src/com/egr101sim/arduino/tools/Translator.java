package com.egr101sim.arduino.tools;

public class Translator {
	
	private String arduinoProgram;
	
	public Translator(String arduinoProgram) {
		this.arduinoProgram = arduinoProgram;
	}
	
	public String translate() {
		String string =  "package com.egr101sim.arduino;\r\n" + 
							"\r\n" + 
							"import java.util.Arrays;\r\n" + 
							"\r\n" + 
							"import com.egr101sim.arduino.BaseArduino;\r\n" + 
							"\r\n" + 
							"public class ArduinoBehavior implements java.util.function.Function<BaseArduino,String>{\r\n" + 
							"\r\n" + arduinoProgram + 
							"\n\npublic ArduinoBehavior() {\n"
							+ "      setup();\n"
							+ "}\n"
							+"	public String apply(BaseArduino t) {\r\n" + 
							"		\r\n" + 
							"		loop();\r\n" + 
							"		\r\n" + 
							"		return \"wow\";\r\n" + 
							"	}\r\n" + 
							"	\r\n" + 
							"\r\n" + 
							"}\r\n"
							.replace("const", "final");
		
		return string;
	}
}
