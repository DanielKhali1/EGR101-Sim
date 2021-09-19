package com.egr101sim.ui;

import java.util.ArrayList;

import com.egr101sim.arduino.elements.PinType;
import com.egr101sim.wiringGUI.components.Component;
import com.egr101sim.wiringGUI.components.Component.CompID;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WiringGUI extends Application
{
	
	Component stuckToMouse = null;
	Pane pane = new Pane();
	

	boolean isOverComponent = false;
	boolean wiring = false;
	double[] wiringPivot = {0 , 0};
	
	ArrayList<Line> lines = new ArrayList<Line>();

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(pane, 750, 600);
		
		HBox toolBarPane = new HBox();
		toolBarPane.getChildren().addAll(new Pane(new Component(CompID.LED)), new Pane(new Component(CompID.BUTTON)), new Pane(new Component(CompID.RESISTOR)));
		toolBarPane.relocate(20, 490);
		for(int i = 0; i < toolBarPane.getChildren().size(); i++) {
			final int ii = i;
			((Pane) toolBarPane.getChildren().get(i)).setPrefSize(100, 100);
			toolBarPane.getChildren().get(i).setStyle("-fx-border-style: solid; -fx-border-color: black");
			((Pane) toolBarPane.getChildren().get(i)).getChildren().get(0).relocate(30, 20);
			
			((Pane) toolBarPane.getChildren().get(i)).setOnMouseClicked(e->{
				final Component comp = new Component(((Component)(((Pane) toolBarPane.getChildren().get(ii)).getChildren().get(0))).compid);
				
				pane.getChildren().add(comp);
				comp.relocate(250, 250);
				
				comp.setOnMouseClicked(f->{
					if(stuckToMouse == null)
						stuckToMouse = comp;
					else if(stuckToMouse == comp){
						stuckToMouse = null;
					}
				});
			});
		}
		
		
		
		
		pane.setStyle("-fx-background-color: rgb(244,245,246);");
		
		Component arduino = new Component(CompID.ARDUINO);
		
		PinSquare pin1 = new PinSquare(250, 314, PinType.POWER_3_3V);
		PinSquare pin2 = new PinSquare(265, 314, PinType.POWER_5V);
		PinSquare pin3 = new PinSquare(280, 314, PinType.GROUND);
		PinSquare pin4 = new PinSquare(295, 314, PinType.GROUND);
		PinSquare pin5 = new PinSquare(310, 314, PinType.POWER_5V);
		
		PinSquare pin6 = new PinSquare(196, 31, PinType.GROUND);
		PinSquare pin7 = new PinSquare(196+15, 31, PinType.IO);
		PinSquare pin8 = new PinSquare(196+30, 31, PinType.IO);
		PinSquare pin9 = new PinSquare(196+45, 31, PinType.IO);
		PinSquare pin10 = new PinSquare(196+60, 31, PinType.IO);
		PinSquare pin11 = new PinSquare(196+75, 31, PinType.IO);
		PinSquare pin12 = new PinSquare(196+90, 31, PinType.IO);
		
		PinSquare pin13 = new PinSquare(324-15, 31, PinType.IO);
		PinSquare pin14 = new PinSquare(324, 31, PinType.IO);
		PinSquare pin15 = new PinSquare(324+15, 31, PinType.IO);
		PinSquare pin16 = new PinSquare(324+30, 31, PinType.IO);
		PinSquare pin17 = new PinSquare(324+45, 31, PinType.IO);
		PinSquare pin18 = new PinSquare(324+60, 31, PinType.IO);
		PinSquare pin19 = new PinSquare(324+75, 31, PinType.IO);
		PinSquare pin20 = new PinSquare(324+90, 31, PinType.IO);
		
		PinSquare pin21 = new PinSquare(339, 314, PinType.IO);
		PinSquare pin22 = new PinSquare(339+15, 314, PinType.IO);
		PinSquare pin23 = new PinSquare(339+30, 314, PinType.IO);
		PinSquare pin24 = new PinSquare(339+45, 314, PinType.IO);
		PinSquare pin25 = new PinSquare(339+60, 314, PinType.IO);
		PinSquare pin26 = new PinSquare(339+75, 314, PinType.IO);
		
		arduino.setOnMouseClicked(e->{
			System.out.println(e.getX() + " " + e.getY());
		});
		
		
		
		pane.getChildren().addAll(arduino, toolBarPane, 
				pin1, pin2, pin3, pin4, pin5, pin6, pin7, pin8, pin9, pin10, pin11, pin12,
				pin13, pin14, pin15, pin16, pin17, pin18, pin19, pin20, pin21, pin22, pin23, pin24, pin25, pin26);
		
		
		Timeline timeline = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
			
			System.out.println(isOverComponent);
			
			scene.setOnMouseMoved(a->{
				if(stuckToMouse != null) {
					stuckToMouse.setLayoutX(a.getX()-stuckToMouse.getImage().getWidth()/2);
					stuckToMouse.setLayoutY(a.getY()-stuckToMouse.getImage().getHeight()/2);
				}
				
				if(wiring) {
					lines.get(lines.size()-1).setEndX(a.getX()*0.98);
					lines.get(lines.size()-1).setEndY(a.getY()*0.98);
					
					pane.setOnMouseClicked(f->{
						if(!isOverComponent) {
							lines.get(lines.size()-1).setEndX(a.getX());
							lines.get(lines.size()-1).setEndY(a.getY());
							wiringPivot[0] = a.getX();
							wiringPivot[1] = a.getY();
							pane.getChildren().add(new Circle(wiringPivot[0], wiringPivot[1], 10));
							((Shape) pane.getChildren().get(pane.getChildren().size()-1)).setFill(Color.GREEN);
							lines.add(new Line(wiringPivot[0], wiringPivot[1],wiringPivot[0], wiringPivot[1]));
							lines.get(lines.size()-1).setStroke(Color.GREEN);
							lines.get(lines.size()-1).setStrokeWidth(5);
							pane.getChildren().add(lines.get(lines.size()-1));
						} else {
							lines.get(lines.size()-1).setEndX(a.getX());
							lines.get(lines.size()-1).setEndY(a.getY());
							wiring = false;
							wiringPivot[0] = 0;
							wiringPivot[1] = 0;
						}
					});
				}
			});
			
		}));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		primaryStage.setScene(scene);
		primaryStage.setTitle("Wiring GUI");
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	class PinSquare extends Rectangle{
		
		PinType pinType;
		
		public PinSquare(double x, double y, PinType pinType) {
			this.pinType = pinType;
			this.setWidth(8);
			this.setHeight(8);
			setLayoutX(x);
			setLayoutY(y);
			
			this.setFill(Color.BLACK);
			this.setStroke(Color.BLACK);
			
			this.setOnMouseEntered(e-> {
				this.setFill(Color.GREEN);
				this.setStroke(Color.GREEN);
				isOverComponent = true;
			});
			this.setOnMouseExited(e-> {
				this.setFill(Color.BLACK);
				this.setStroke(Color.BLACK);
				isOverComponent = false;
			});
			
			
			this.setOnMouseClicked(e-> {
				if(!wiring) {
					wiring = true;
					wiringPivot[0] = getLayoutX()+getWidth()/2;
					wiringPivot[1] = getLayoutY()+getHeight()/2;
					lines.add(new Line(wiringPivot[0], wiringPivot[1],wiringPivot[0], wiringPivot[1]));
					lines.get(lines.size()-1).setStroke(Color.GREEN);
					lines.get(lines.size()-1).setStrokeWidth(5);
					pane.getChildren().add(lines.get(lines.size()-1));
				}
			});
		}
	}

}
