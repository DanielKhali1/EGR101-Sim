package com.egr101sim.ui;

import java.util.ArrayList;

import com.egr101sim.arduino.Arduino;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;
import com.egr101sim.wiringGUI.components.Component;
import com.egr101sim.wiringGUI.components.Component.CompID;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
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
	
	Pane stuckToMouse = null;
	Pane pane = new Pane();
	
	Timeline timeline;
	boolean isOverComponent = false;
	boolean wiring = false;
	double[] wiringPivot = {0 , 0};
	Pin tempPin;
	
	ArrayList<ArrayList<SLine>> lines = new ArrayList<ArrayList<SLine>>();
	
	Arduino arduino;
	
	public WiringGUI(Arduino arduino) {
		
		this.arduino = arduino;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(pane, 950, 600);
		
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
				Pane tempPane = new Pane();
				pane.getChildren().add(tempPane);
				stuckToMouse = tempPane;
				
				
				if(comp.compid == CompID.LED) {
					PinSquare pin1 = new PinSquare(4, 45, PinType.GROUND, tempPane, false, false, -1);
					PinSquare pin2 = new PinSquare(17, 45, PinType.IO, tempPane, false, false, -1);
					tempPane.getChildren().addAll(comp, pin1, pin2);
				} else if (comp.compid == CompID.RESISTOR) {

					PinSquare pin1 = new PinSquare(8, 0, PinType.GROUND, tempPane, false, false, -1);
					PinSquare pin2 = new PinSquare(8, 60, PinType.IO, tempPane, false, false, -1);
					tempPane.getChildren().addAll(comp, pin1, pin2);
				}
				else if (comp.compid == CompID.BUTTON) {
					PinSquare pin1 = new PinSquare(4, 45, PinType.GROUND, tempPane, false, false, -1);
					PinSquare pin2 = new PinSquare(17, 45, PinType.IO, tempPane, false, false, -1);
					tempPane.getChildren().addAll(comp, pin1, pin2);
				}
				
				
				comp.setOnMouseClicked(f->{
						if(!wiring) {
							System.out.println("stuck to mouse");
							if(stuckToMouse == null)
								stuckToMouse = tempPane;
							else if(stuckToMouse == tempPane){
								stuckToMouse = null;
							}
						}	
				});
			});
		}
		
		pane.setStyle("-fx-background-color: rgb(244,245,246);");
		
		Component arduino = new Component(CompID.ARDUINO);
		arduino.relocate(0, 100);
		
		PinSquare pin1 = new PinSquare(250, 414, PinType.POWER_3_3V, pane, true, false, -1);
		PinSquare pin2 = new PinSquare(265, 414, PinType.POWER_5V, pane, true, false , -1);
		PinSquare pin3 = new PinSquare(280, 414, PinType.GROUND, pane, true, false , -1);
		PinSquare pin4 = new PinSquare(295, 414, PinType.GROUND, pane, true, false , -1);
		PinSquare pin5 = new PinSquare(310, 414, PinType.POWER_5V, pane, true, false , -1);
		
		//digital
		
		PinSquare pin6 = new PinSquare(196, 131, PinType.GROUND, pane, true, true, -1);
		PinSquare pin7 = new PinSquare(196+15, 131, PinType.IO, pane, true, true, 13);
		PinSquare pin8 = new PinSquare(196+30, 131, PinType.IO, pane, true, true, 12);
		PinSquare pin9 = new PinSquare(196+45, 131, PinType.IO, pane, true, true, 11);
		PinSquare pin10 = new PinSquare(196+60, 131, PinType.IO, pane, true, true, 10);
		PinSquare pin11 = new PinSquare(196+75, 131, PinType.IO, pane, true, true, 9);
		PinSquare pin12 = new PinSquare(196+90, 131, PinType.IO, pane, true, true, 8);
		
		PinSquare pin13 = new PinSquare(324-15, 131, PinType.IO, pane, true, true, 7);
		PinSquare pin14 = new PinSquare(324, 131, PinType.IO, pane, true, true, 6);
		PinSquare pin15 = new PinSquare(324+15, 131, PinType.IO, pane, true, true, 5);
		PinSquare pin16 = new PinSquare(324+30, 131, PinType.IO, pane, true, true, 4);
		PinSquare pin17 = new PinSquare(324+45, 131, PinType.IO, pane, true, true, 3);
		PinSquare pin18 = new PinSquare(324+60, 131, PinType.IO, pane, true, true, 2);
		PinSquare pin19 = new PinSquare(324+75, 131, PinType.IO, pane, true, true, 1);
		PinSquare pin20 = new PinSquare(324+90, 131, PinType.IO, pane, true, true, 0);
		
		PinSquare pin21 = new PinSquare(339, 414, PinType.IO, pane, true, false, 0);
		PinSquare pin22 = new PinSquare(339+15, 414, PinType.IO, pane, true, false, 1);
		PinSquare pin23 = new PinSquare(339+30, 414, PinType.IO, pane, true, false, 2);
		PinSquare pin24 = new PinSquare(339+45, 414, PinType.IO, pane, true, false, 3);
		PinSquare pin25 = new PinSquare(339+60, 414, PinType.IO, pane, true, false, 4);
		PinSquare pin26 = new PinSquare(339+75, 414, PinType.IO, pane, true, false, 5);
		
		arduino.setOnMouseClicked(e->{
			System.out.println(e.getX() + " " + e.getY());
		});
		
		
		
		pane.getChildren().addAll(arduino, toolBarPane, 
				pin1, pin2, pin3, pin4, pin5, pin6, pin7, pin8, pin9, pin10, pin11, pin12,
				pin13, pin14, pin15, pin16, pin17, pin18, pin19, pin20, pin21, pin22, pin23, pin24, pin25, pin26);
		
		
		timeline = new Timeline(new KeyFrame(Duration.millis(20), (ActionEvent event) -> {
			
			scene.setOnMouseMoved(a->{
				if(stuckToMouse != null && !wiring) {
					stuckToMouse.setLayoutX(a.getX()-((ImageView) stuckToMouse.getChildren().get(0)).getImage().getWidth()/2);
					stuckToMouse.setLayoutY(a.getY()-((ImageView) stuckToMouse.getChildren().get(0)).getImage().getHeight()/2);
				}
				
				if(wiring) {
					
					Line l = lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1);
					if(a.getX() > l.getStartX())
						l.setEndX(a.getX() - (l.getStartX()*0.02));
					else
						l.setEndX(a.getX() + (l.getStartX()*0.02));
					
					if(a.getY() > l.getStartY())
						l.setEndY(a.getY() - (l.getStartY()*0.02));
					else
						l.setEndY(a.getY() + (l.getStartY()*0.02));
						
						
						
					
					scene.setOnMouseClicked(f->{
						if(!isOverComponent && wiring) {
							lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setEndX(a.getX());
							lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setEndY(a.getY());
							wiringPivot[0] = a.getX();
							wiringPivot[1] = a.getY();
							lines.get(lines.size()-1).add(new SLine(wiringPivot[0], wiringPivot[1],wiringPivot[0], wiringPivot[1]));
							lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setStroke(Color.GREEN);
							lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setStrokeWidth(5);
							pane.getChildren().add(lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1));
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
	
	public void removeAllWireOfThisLine(Line l) {
		//find line in arrayList
		System.out.println("I WAS CALLED");
		int arrayListIndex = 0;
		
		for(int i = 0; i < lines.size(); i++) {
			for(int j = 0; j < lines.get(i).size(); j++) {
				if(lines.get(i).get(j) == l)
					arrayListIndex = 0;
			}
		}
		// remove all instances from pane
		for(int i = 0; i < lines.get(arrayListIndex).size(); i++) {
			pane.getChildren().remove(lines.get(arrayListIndex).get(i));
		}
		
		lines.remove(arrayListIndex);
	}
	
	class SLine extends Line {
		public SLine(double startx, double starty, double endx, double endy) {
			super(startx, starty, endx, endy);
			
			setOnMouseClicked( e-> {
				if(e.isControlDown()) {
					removeAllWireOfThisLine(this);
				}
			});
		}

	}
	
	class PinSquare extends Rectangle{
		
		PinType pinType;
		Pane p;
		boolean local;
		boolean isDigitalifIO;
		int ioNumber = -1;
		
		public PinSquare(double x, double y, PinType pinType, Pane p, boolean local, boolean isDigitalifIO, int ioNumber) {
			this.ioNumber = ioNumber;
			this.local = local;
			this.isDigitalifIO = isDigitalifIO;
			this.p = p;
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
					System.out.println("DID SOMETHING");
					wiring = true;
					wiringPivot[0] = this.p.getLayoutX() + getLayoutX()+getWidth()/2;
					wiringPivot[1] = this.p.getLayoutY() + getLayoutY()+getHeight()/2;
					lines.add(new ArrayList<SLine>());
					lines.get(lines.size()-1).add(new SLine(wiringPivot[0], wiringPivot[1],wiringPivot[0], wiringPivot[1]));
					lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setStroke(Color.GREEN);
					lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setStrokeWidth(5);
					pane.getChildren().add(lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1));
					
					System.out.println(lines.size());
					
					tempPin = new Pin(this.pinType, this.local);
				} else {
						lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setEndX(this.p.getLayoutX() + getLayoutX()+getWidth()/2);
						lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setEndY(this.p.getLayoutY() + getLayoutY()+getHeight()/2);
					wiring = false;
					wiringPivot[0] = 0;
					wiringPivot[1] = 0;
					System.out.println("DONE WIRING");
					
					arduino.AddConnection(tempPin, new Pin(this.pinType, this.local), isDigitalifIO, ioNumber);
				}
			});
		}
	}

}
