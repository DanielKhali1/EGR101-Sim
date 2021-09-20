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
	
	ArrayList<ArrayList<Line>> lines = new ArrayList<ArrayList<Line>>();

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
				Pane tempPane = new Pane();
				
				if(comp.compid == CompID.LED) {
					System.out.println(":LSDFJ:LSDKF");
					PinSquare pin1 = new PinSquare(4, 45, PinType.GROUND, tempPane);
					PinSquare pin2 = new PinSquare(17, 45, PinType.IO, tempPane);
					tempPane.getChildren().addAll(comp, pin1, pin2);
				} else if (comp.compid == CompID.RESISTOR) {
					System.out.println(":LSDFffffffffKF");

					PinSquare pin1 = new PinSquare(4, 45, PinType.GROUND, tempPane);
					PinSquare pin2 = new PinSquare(17, 45, PinType.IO, tempPane);
					tempPane.getChildren().addAll(comp, pin1, pin2);
				}
				
				pane.getChildren().add(tempPane);
				tempPane.relocate(250, 250);
				
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
		
		PinSquare pin1 = new PinSquare(250, 314, PinType.POWER_3_3V, pane);
		PinSquare pin2 = new PinSquare(265, 314, PinType.POWER_5V, pane);
		PinSquare pin3 = new PinSquare(280, 314, PinType.GROUND, pane);
		PinSquare pin4 = new PinSquare(295, 314, PinType.GROUND, pane);
		PinSquare pin5 = new PinSquare(310, 314, PinType.POWER_5V, pane);
		
		PinSquare pin6 = new PinSquare(196, 31, PinType.GROUND, pane);
		PinSquare pin7 = new PinSquare(196+15, 31, PinType.IO, pane);
		PinSquare pin8 = new PinSquare(196+30, 31, PinType.IO, pane);
		PinSquare pin9 = new PinSquare(196+45, 31, PinType.IO, pane);
		PinSquare pin10 = new PinSquare(196+60, 31, PinType.IO, pane);
		PinSquare pin11 = new PinSquare(196+75, 31, PinType.IO, pane);
		PinSquare pin12 = new PinSquare(196+90, 31, PinType.IO, pane);
		
		PinSquare pin13 = new PinSquare(324-15, 31, PinType.IO, pane);
		PinSquare pin14 = new PinSquare(324, 31, PinType.IO, pane);
		PinSquare pin15 = new PinSquare(324+15, 31, PinType.IO, pane);
		PinSquare pin16 = new PinSquare(324+30, 31, PinType.IO, pane);
		PinSquare pin17 = new PinSquare(324+45, 31, PinType.IO, pane);
		PinSquare pin18 = new PinSquare(324+60, 31, PinType.IO, pane);
		PinSquare pin19 = new PinSquare(324+75, 31, PinType.IO, pane);
		PinSquare pin20 = new PinSquare(324+90, 31, PinType.IO, pane);
		
		PinSquare pin21 = new PinSquare(339, 314, PinType.IO, pane);
		PinSquare pin22 = new PinSquare(339+15, 314, PinType.IO, pane);
		PinSquare pin23 = new PinSquare(339+30, 314, PinType.IO, pane);
		PinSquare pin24 = new PinSquare(339+45, 314, PinType.IO, pane);
		PinSquare pin25 = new PinSquare(339+60, 314, PinType.IO, pane);
		PinSquare pin26 = new PinSquare(339+75, 314, PinType.IO, pane);
		
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
					lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setEndX(a.getX()*0.98);
					lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setEndY(a.getY()*0.98);
					
					scene.setOnMouseClicked(f->{
						if(!isOverComponent && wiring) {
							lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setEndX(a.getX());
							lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setEndY(a.getY());
							wiringPivot[0] = a.getX();
							wiringPivot[1] = a.getY();
							pane.getChildren().add(new Circle(wiringPivot[0], wiringPivot[1], 10));
							((Shape) pane.getChildren().get(pane.getChildren().size()-1)).setFill(Color.GREEN);
							lines.get(lines.size()-1).add(new Line(wiringPivot[0], wiringPivot[1],wiringPivot[0], wiringPivot[1]));
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
	
	public static void main(String[] args) {
		launch(args);
	}
	
	class PinSquare extends Rectangle{
		
		PinType pinType;
		Pane p;
		
		public PinSquare(double x, double y, PinType pinType, Pane p) {
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
					wiringPivot[0] = getLayoutX()+getWidth()/2;
					wiringPivot[1] = getLayoutY()+getHeight()/2;
					lines.add(new ArrayList<Line>());
					lines.get(lines.size()-1).add(new Line(wiringPivot[0], wiringPivot[1],wiringPivot[0], wiringPivot[1]));
					lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setStroke(Color.GREEN);
					lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setStrokeWidth(5);
					pane.getChildren().add(lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1));
					
					System.out.println(lines.size());
				} else {
						lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setEndX(this.p.getLayoutX() + getLayoutX()+getWidth()/2);
						lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setEndY(this.p.getLayoutY() + getLayoutY()+getHeight()/2);
					wiring = false;
					wiringPivot[0] = 0;
					wiringPivot[1] = 0;
					System.out.println("DONE WIRING");
				}
			});
		}
	}

}
