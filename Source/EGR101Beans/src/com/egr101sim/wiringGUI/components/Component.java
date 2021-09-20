package com.egr101sim.wiringGUI.components;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Component extends ImageView{
	
	public CompID compid;
	
	public Component(CompID componentType) {
		
		
		this.compid = componentType;
		Image i = null;
		this.setPreserveRatio(true);
		if (componentType == CompID.ARDUINO) {
			i = new Image("file:Resources\\arduino.PNG");
			this.setFitHeight(350);
		} else if (componentType == CompID.RESISTOR) {
			i = new Image("file:Resources\\resistor.PNG");
		} else if (componentType == CompID.BUTTON) {
			i = new Image("file:Resources\\button.PNG");
		} else if (componentType == CompID.LED) {
			i = new Image("file:Resources\\LED.PNG");
		}
		
		this.setImage(i);
	}
	
	public enum CompID{
		ARDUINO,
		RESISTOR,
		LED,
		BUTTON
	}

}
