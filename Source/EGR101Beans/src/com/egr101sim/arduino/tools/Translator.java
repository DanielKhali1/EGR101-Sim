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
							"private static boolean isAlpha(char c) {return Character.isAlphabetic(c);}\r\n" + 
							"private static boolean isAlphaNumeric(char c) {return Character.isAlphabetic(c) || Character.isDigit(c);}\r\n" + 
							"private static boolean isDigit(char c) {return Character.isDigit(c);}\r\n" + 
							"private static boolean isLowerCase(char c) {return Character.isLowerCase(c);}\r\n" + 
							"private static boolean isPunct(char c) {return c == '!' || c == '.' || c == ',';}\r\n" + 
							"private static boolean isSpace(char c) {	return c == '\\n' || c == '\\f' || c == '\\r' || c == '\\t' ;}\r\n" + 
							"private static boolean isUpperCase(char c) {	return Character.isUpperCase(c);}\r\n" + 
							"private static boolean isWhitespace(char c) {return c == '\\t' || c == ' ';}"+
							"\r\n" + 
							"}\r\n"
							.replace("const", "final");
		
		return string;
	}
}
