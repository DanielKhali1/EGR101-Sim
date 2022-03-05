package com.egr101sim.wiringGUI.components;

import com.egr101sim.arduino.components.Component;
import com.egr101sim.arduino.components.ContinuousServoMotor;
import com.egr101sim.arduino.components.Led;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;

public class WGComonent extends Pane{
	
	public CompID compid;
	ImageView imageView;
	
	public Component comp;
	
	public Line motorTick;
	
	
	public WGComonent(CompID componentType) {
		
		this.compid = componentType;
		Image i = null;
		imageView = new ImageView();
		imageView.setPreserveRatio(true);
		this.getChildren().add(imageView);
		if (componentType == CompID.ARDUINO) {
			i = new Image("file:Resources\\arduino.PNG");
			imageView.setFitHeight(350);
		} else if (componentType == CompID.RESISTOR) {
			i = new Image("file:Resources\\resistor.PNG");
		} else if (componentType == CompID.MOTOR) {
			Rectangle rectangle = new Rectangle(50, 40);
			rectangle.setFill(Color.BLUE);
			motorTick = new Line(50/2, 40/2, 50*1.5, 20*1.5);
			this.getChildren().addAll(rectangle, motorTick);
		} else if (componentType == CompID.LED) {
			i = new Image("file:Resources\\LED.PNG");
		} else if (componentType == CompID.LINE_IR) {
			i = new Image("file:Resources\\lineReadingIR.PNG");
			imageView.setFitHeight(50);
		} else if (componentType == CompID.DIST_IR) {
			i = new Image("file:Resources\\IRSensor.PNG");
		} else if (componentType == CompID.ULTRA_SONIC) {
			i = new Image("file:Resources\\UltraSonic.PNG");
			imageView.setFitHeight(50);
		}
		imageView.setImage(i);
	}
	
	public void spinMotor() {
		motorTick.getTransforms().add(new Rotate(((ContinuousServoMotor)this.comp).writtenAngle, motorTick.getStartX(), motorTick.getStartY(), 0, Rotate.Z_AXIS));
	}
	
	
	
	public void changeImage() {
		if(((Led)comp).isPowered()) {
			imageView.setImage(new Image("file:Resources\\LED_ON.PNG"));
		} else {
			imageView.setImage(new Image("file:Resources\\LED.png"));
		}
	}
	
	
	
	public enum CompID{
		ARDUINO,
		RESISTOR,
		LED,
		BUTTON,
		MOTOR, 
		LINE_IR, 
		DIST_IR, 
		ULTRA_SONIC
	}

}
