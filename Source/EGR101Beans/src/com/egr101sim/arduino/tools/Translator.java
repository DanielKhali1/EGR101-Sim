package com.egr101sim.arduino.tools;

import java.util.LinkedList;

public class Translator {
	
	private String arduinoProgram;
	
	
	public Translator(String arduinoProgram) {
		this.arduinoProgram = arduinoProgram;
	}
	
	public String translate() {
		String string =  ( "import java.util.Arrays;\r\n" + 
							"\r\n" + 
							"import com.egr101sim.arduino.BaseArduino;\r\n" + 
							"import com.egr101sim.arduino.elements.Pin;\r\n" + 
							"import com.egr101sim.arduino.elements.PinIO;\r\n" + 
							"import com.egr101sim.arduino.elements.PinState;\r\n" + 
							"import com.egr101sim.arduino.elements.PinType;\r\n" + 
							"import com.egr101sim.arduino.api.Serial;\r\n" +
							"public class ArduinoBehavior implements java.util.function.Function<BaseArduino,String>{\r\n" + 
							"   int count = 0;" + 
							"	private static final int INPUT = 0;\r\n" + 
							"	private static final int OUTPUT = 1;\r\n" + 
							"	private static final int HIGH = 1;\r\n" + 
							"	private static final int LOW = 0;\r\n" + 
							"	private static final int INPUT_PULLUP = 2;\r\n" + 
							"	private static final int LED_BUILTIN= 13;\r\n" +
							"   private static final int A0 = 0xA0;\n\r" +
							"   private static final int A1 = 0xA1;\n\r" +
							"   private static final int A2 = 0xA2;\n\r" +
							"   private static final int A3 = 0xA3;" +
							"   private static final int A4 = 0xA4;" +
							"   private static final int A5 = 0xA5;" +
							"   private static final int A6 = 0xA6;" +
							"   private static final int A7 = 0xA7;" +
							"   private static final int A8 = 0xA8;" +
							"   private static final int A9 = 0xA9;" +
							"   private static final int A10 = 0xA10;" +
							"   private static final int A11 = 0xA11;" +
							"   private static final int A12 = 0xA12;" +
							"   private static final int A13 = 0xA13;" +
							"   BaseArduino t;" +
							arduinoProgram + 
							"	public String apply(BaseArduino t) {\r\n" + 
							"		\r\n" + 
							"		if(count == 0) { count++; "+ instantiateAnyObjects(arduinoProgram)+";setup(t); this.t = t;  } else {loop(t);}\r\n" + 
							"		return \"wow\";\r\n" + 
							"	}\r\n" + 
							"	\r\n" +
							"private static boolean isAlpha(char c) {return Character.isAlphabetic(c);}\r\n" + 
							"private static boolean isAlphaNumeric(char c) {return Character.isAlphabetic(c) || Character.isDigit(c);}\r\n" + 
							"private static boolean isDigit(char c) {return Character.isDigit(c);}\r\n" + 
							"private static boolean isLowerCase(char c) {return Character.isLowerCase(c);}\r\n" + 
							"private static boolean isPunct(char c) {return c == '!' || c == '.' || c == ',';}\r\n" + 
							"private static boolean isSpace(char c) {	return c == '\\n' || c == '\\f' || c == '\\r' || c == '\\t' ;}\r\n" + 
							"private static boolean isUpperCase(char c) {	return Character.isUpperCase(c);}\r\n" + 
							"private static boolean isWhitespace(char c) {return c == '\\t' || c == ' ';}"+
							"private static int random(int val) {return (int)(Math.random()*val);}\r\n" + 
							"private static int random(int min, int max) {return (int) (Math.random() * (max - min) ) + min;}\r\n" + 
							"private static int abs(int val ) {return Math.abs(val);}\r\n" + 
							"private static int constrain(int x, int a, int b) { return Math.min(Math.max(x, a), b);} \r\n" + 
							"private static int map(int x, int in_min, int in_max, int out_min, int out_max) {return (x - in_min) * (out_max - out_min) / (in_max - in_min) + out_min;}\r\n" + 
							"private static int max(int a, int b) { return Math.max(a, b);} \r\n" + 
							"private static int min(int a, int b) { return Math.min(a, b);} \r\n" + 
							"private static double pow(double a, int b) {return Math.pow(a, b);}\r\n" + 
							"private static int sq(int val) {return val*val;}; \r\n" + 
							"private static double sqrt(int val) {return Math.sqrt(val);}\r\n" + 
							"private static double cos(int val) {return  Math.cos(val);}\r\n" + 
							"private static double sin(int val) {return  Math.sin(val);}\r\n" + 
							"private static double tan(int val) {return Math.tan(val);}\r\n" + 
							"protected void finalize()  \r\n" + 
							"{  \r\n" + 
							"System.out.println(\"Arduino Behavior has been Destroyed\");  \r\n" + 
							"}  "+
							"}\r\n")
							.replace("const", "final")
							.replace("void loop()", "void loop(BaseArduino t)")
							.replace("void setup()", "void setup(BaseArduino t)")
							.replace("unsigned", "")
							.replace("digitalWrite", "t.digitalWrite")
							.replace("digitalRead", "t.digitalRead")
							.replace("analogWrite", "t.analogWrite")
							.replace("analogRead", "t.analogRead")
							.replace("pulseIn", "t.pulseIn")
							.replace("micros(", "t.micros(")
							.replace("millis(", "t.millis(")
							.replace("delay", "t.delay")
//							.replace("Serial.print", "System.out.print")
							.replace("delayMicroSeconds", "t.delayMicroSeconds")
							.replace("pinMode", "t.pinMode");
		
		if(string.contains("#include \"Servo.h\"")) {
			string = string .replace("#include \"Servo.h\"", "");
			string = "import com.egr101sim.arduino.api.Servo;\n" + string;
		}
		
		string = "package com.egr101sim.arduino;\r\n" + string;
		//need to somehow give Servo myservo access to Base arduino reference
		// also somehow instantiate Servo
		string = instantiateAllMentions(string, "Servo ", "= new Servo()");
		
		System.out.println(string);
		
		return string;
	}
	
	private static String instantiateAnyObjects(String arduinoProgram) {
		//SERVO OBJECT
		LinkedList<String> l = new LinkedList<String>();
		
		if(arduinoProgram.indexOf("Servo ") != -1) {
			for(int index = arduinoProgram.indexOf("Servo "); index >= 0; index = arduinoProgram.indexOf("Servo ", index+1)) {
				//given index find ; index
				int semiColonIndex = arduinoProgram.substring(index).indexOf(";")+index;
				String temp = arduinoProgram.substring(index, semiColonIndex);
				l.add(temp.substring(temp.split("=")[0].indexOf(" ")+1));
			}
		}
		
		String ret = "";
		for(String varName : l) {
			ret += varName + ".b = t;\n";
		}
		
		
		
		
		return ret;
	}

	public static String instantiateAllMentions(String code, String nameOfObject, String instantiationString) {
		String fin =code;
		if(fin.indexOf(nameOfObject) != -1) {
			for(int index = fin.indexOf(nameOfObject); index >= 0; index = fin.indexOf(nameOfObject, index+1)) {
				//given index find ; index
				int semiColonIndex = fin.substring(index).indexOf(";")+index;
				System.out.println(fin.substring(index, semiColonIndex)+instantiationString);
				fin = fin.replace(fin.substring(index, semiColonIndex), fin.substring(index, semiColonIndex)+instantiationString);
			}
		}
		return fin;
	}
}
