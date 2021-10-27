package com.egr101sim.wiringGUI.components;

import com.egr101sim.arduino.components.Led;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class WGComonent extends ImageView{
	
	public CompID compid;
	public Led led;
	
	public WGComonent(CompID componentType) {
		
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
	
	
	
	public void changeImage() {
		if(led.isPowered()) {
			this.setImage(new Image("file:Resources\\LED_ON.PNG"));
		} else {
			this.setImage(new Image("file:Resources\\LED.png"));
		}
	}
	
	
	
	public enum CompID{
		ARDUINO,
		RESISTOR,
		LED,
		BUTTON
	}

}
