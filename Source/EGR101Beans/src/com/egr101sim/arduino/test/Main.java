package com.egr101sim.arduino.test;

import java.util.function.Function;
import java.util.function.Supplier;

import com.egr101sim.arduino.BaseArduino;
import com.org.joor.Reflect;

public class Main {
	
	

	public static void main(String[] args) {
		Main m = new Main();
		
//		m.test1();
		m.test2();
		

	}
	
	void test1() {
		Supplier<String> s = Reflect.compile("com.egr101sim.arduino.ArduinoBehavior", "package com.egr101sim.arduino;\n" +
		"import java.util.Arrays;\n" +
	    "class ArduinoBehavior implements java.util.function.Supplier<String> {\n" +
	    "    public String get() {\n" +
	    "        return \"Hello World!\";\n" +
	    "    }\n" +
	    "}\n").create().get();
System.out.println(s.get());
	}
	
	void test2() {
//		BaseArduino b = new BaseArduino();
//		System.out.println("basearduino: " + b);
		
		Function<BaseArduino,String> f = Reflect.compile("com.egr101sim.arduino.ArduinoBehavior", 
				"package com.egr101sim.arduino;\r\n" + 
				"\r\n" + 
				"import java.util.Arrays;\r\n" + 
				"\r\n" + 
				"import com.egr101sim.arduino.BaseArduino;\r\n" + 
				"\r\n" + 
				"public class ArduinoBehavior implements java.util.function.Function<BaseArduino,String>{\r\n" + 
				"\r\n" + 
				"	public String apply(BaseArduino t) {\r\n" + 
				"		\r\n" + 
				"		System.out.println(Arrays.toString(t.getDigitalArray()));\r\n" + 
				"		\r\n" + 
				"		return \"wow\";\r\n" + 
				"	}\r\n" + 
				"	\r\n" + 
				"\r\n" + 
				"}\r\n" + 
				"").create().get();
//		f.apply(b);
	}
}
