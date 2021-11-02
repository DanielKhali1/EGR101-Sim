package com.egr101sim.ui;

import java.util.ArrayList;

import com.egr101sim.arduino.component.sensors.DistanceMeasuringIRSensor;
import com.egr101sim.arduino.component.sensors.LineReadingIRSensor;
import com.egr101sim.arduino.component.sensors.UltrasonicSensor;
import com.egr101sim.arduino.components.ContinuousServoMotor;
import com.egr101sim.arduino.components.Led;
import com.egr101sim.arduino.elements.Pin;
import com.egr101sim.arduino.elements.PinType;
import com.egr101sim.arduino.elements.SpecialPin;
import com.egr101sim.core.ApplicationManager;
import com.egr101sim.wiringGUI.components.WGComonent;
import com.egr101sim.wiringGUI.components.WGComonent.CompID;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WiringGUI extends Application
{
	
	WGComonent stuckToMouse = null;
	Pane pane = new Pane();
	
	Timeline timeline;
	boolean isOverComponent = false;
	boolean wiring = false;
	double[] wiringPivot = {0 , 0};
	Pin tempPin;
	
	ArrayList<ArrayList<SLine>> lines = new ArrayList<ArrayList<SLine>>();
	ArrayList<WGComonent> wgComponent = new ArrayList<WGComonent>();
	
	ApplicationManager manager;
	public boolean pinDig;
	public int tempPinNum;
	
	Color curColor;
	
	public WiringGUI(ApplicationManager manager) {
		this.manager = manager;
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Scene scene = new Scene(pane, 950, 600);
		HBox toolBarPane = new HBox();
		toolBarPane.getChildren().addAll(
				new Pane(new WGComonent(CompID.LED)), 
				new Pane(new WGComonent(CompID.MOTOR)), 
				new Pane(new WGComonent(CompID.RESISTOR)),
				new Pane(new WGComonent(CompID.LINE_IR)),
				new Pane(new WGComonent(CompID.DIST_IR)),
				new Pane(new WGComonent(CompID.ULTRA_SONIC)));
		toolBarPane.relocate(20, 490);
		for(int i = 0; i < toolBarPane.getChildren().size(); i++) {
			final int ii = i;
			((Pane) toolBarPane.getChildren().get(i)).setPrefSize(100, 100);
			toolBarPane.getChildren().get(i).setStyle("-fx-border-style: solid; -fx-border-color: black");
			if(i != 5)
				((Pane) toolBarPane.getChildren().get(i)).getChildren().get(0).relocate(30, 20);
			else 
				((Pane) toolBarPane.getChildren().get(i)).getChildren().get(0).relocate(5, 20);
			((Pane) toolBarPane.getChildren().get(i)).setOnMouseClicked(e->{
				final WGComonent comp = new WGComonent(((WGComonent)(((Pane) toolBarPane.getChildren().get(ii)).getChildren().get(0))).compid);
				pane.getChildren().add(comp);
				stuckToMouse = (WGComonent) comp;
				
				if(comp.compid == CompID.LED) {
					wgComponent.add(comp);
					Led led = new Led();
					wgComponent.get(wgComponent.size()-1).comp = led;
					this.manager.arduino.addComponent(led);
					PinSquare pin1 = new PinSquare(4, 45, PinType.GENERAL, comp, false, false, -1);
					PinSquare pin2 = new PinSquare(17, 45, PinType.GENERAL, comp, false, false, -1);
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[0] = pin1.pin;
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[1] = pin2.pin;
					comp.getChildren().addAll(pin1, pin2);
				} else if (comp.compid == CompID.RESISTOR) {

					PinSquare pin1 = new PinSquare(8, 0, PinType.GENERAL, comp, false, false, -1);
					PinSquare pin2 = new PinSquare(8, 60, PinType.GENERAL, comp, false, false, -1);
					Pin resistorPin = new Pin(PinType.GENERAL, false);
					resistorPin.setResistance(3);
					pin1.pin = resistorPin;
					pin2.pin = resistorPin;
					
					comp.getChildren().addAll(pin1, pin2);
				}
				else if (comp.compid == CompID.MOTOR) {
					wgComponent.add(comp);
					ContinuousServoMotor csm = new ContinuousServoMotor();
					wgComponent.get(wgComponent.size()-1).comp = csm;
					this.manager.arduino.addComponent(csm);
					
					PinSquare pin1 = new PinSquare(4, 45, PinType.GENERAL, comp, false, false, -1);
					PinSquare pin2 = new PinSquare(17, 45, PinType.GENERAL, comp, false, false, -1);
					PinSquare pin3 = new PinSquare(17 + (17-4), 45, PinType.GENERAL, comp, false, false, -1);
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[0] = pin1.pin;
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[1] = pin2.pin;
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[2] = pin3.pin;
					
					comp.getChildren().addAll(pin1, pin2, pin3);
				}
				else if (comp.compid == CompID.LINE_IR) {
					wgComponent.add(comp);
					LineReadingIRSensor lrirs = new LineReadingIRSensor();
					wgComponent.get(wgComponent.size()-1).comp = lrirs;
					this.manager.arduino.addComponent(lrirs);
					
					Text txt = new Text("light reflect %");
					txt.setStyle("-fx-font-size: 10;");
					txt.relocate(-11, -50);
					TextField tf = new TextField("0");
					tf.relocate(-5, -30);
					tf.setPrefWidth(40);
					
					
					
					tf.setOnKeyReleased(a->{
						try {
							lrirs.readVal(Integer.parseInt(tf.getText()));
						} catch (NumberFormatException h) {
							lrirs.readVal(0);
						}
					});
					
					PinSquare pin1 = new PinSquare(4-5, 45+10, PinType.GENERAL, comp, false, false, -1);
					PinSquare pin2 = new PinSquare(17-5, 45+10, PinType.GENERAL, comp, false, false, -1);
					PinSquare pin3 = new PinSquare(17 + (17-4)-5, 45+10, PinType.GENERAL, comp, false, false, -1);
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[0] = pin1.pin;
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[1] = pin2.pin;
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[2] = pin3.pin;
					
					
					
					
					comp.getChildren().addAll(txt, tf, pin1, pin2, pin3);
				}
				else if (comp.compid == CompID.DIST_IR) {
					wgComponent.add(comp);
					UltrasonicSensor uss = new UltrasonicSensor();
					wgComponent.get(wgComponent.size()-1).comp = uss;
					this.manager.arduino.addComponent(uss);
					
					Text txt = new Text("distance cm");
					txt.setStyle("-fx-font-size: 10;");
					txt.relocate(30, -50);
					TextField tf = new TextField("0");
					tf.relocate(30, -30);
					tf.setPrefWidth(40);

					tf.setOnKeyReleased(a->{
						try {
							uss.readVal(Integer.parseInt(tf.getText()));
						} catch (NumberFormatException h) {
							uss.readVal(0);
						}
					});
					
					PinSquare pin1 = new PinSquare(4+10, 45+20, PinType.GENERAL, comp, false, false, -1);
					PinSquare pin2 = new PinSquare(17+10, 45+20, PinType.GENERAL, comp, false, false, -1);
					PinSquare pin3 = new PinSquare(17 + (17-4)+10, 45+20, PinType.GENERAL, comp, false, false, -1);
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[0] = pin1.pin;
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[1] = pin2.pin;
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[2] = pin3.pin;
					
					comp.getChildren().addAll(txt, tf, pin1, pin2, pin3);
				}
				else if (comp.compid == CompID.ULTRA_SONIC) {
					wgComponent.add(comp);
					LineReadingIRSensor lrirs = new LineReadingIRSensor();
					wgComponent.get(wgComponent.size()-1).comp = lrirs;
					this.manager.arduino.addComponent(lrirs);
					
					Text txt = new Text("distance cm");
					txt.setStyle("-fx-font-size: 10;");
					txt.relocate(-11, -50);
					TextField tf = new TextField("0");
					tf.relocate(0, -30);
					tf.setPrefWidth(40);
					
					
					
					tf.setOnKeyReleased(a->{
						try {
							lrirs.readVal(Integer.parseInt(tf.getText()));
						} catch (NumberFormatException h) {
							lrirs.readVal(0);
						}
					});
					
					PinSquare pin1 = new PinSquare(4-5+20, 45+10, PinType.GENERAL, comp, false, false, -1);
					PinSquare pin2 = new PinSquare(17-5+20, 45+10, PinType.GENERAL, comp, false, false, -1);
					PinSquare pin3 = new PinSquare(17 + (17-4)-5+20, 45+10, PinType.GENERAL, comp, false, false, -1);
					PinSquare pin4 = new PinSquare(17 + (17-4)*2-5+20, 45+10, PinType.GENERAL, comp, false, false, -1);
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[0] = pin1.pin;
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[1] = pin2.pin;
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[2] = pin3.pin;
					this.manager.arduino.getComponents().get(this.manager.arduino.getComponents().size()-1).getPins()[2] = pin4.pin;
					
					
					
					
					comp.getChildren().addAll(txt, tf, pin1, pin2, pin3, pin4);
				}
				
				
				comp.setOnMouseReleased(f->{
						if(!wiring) {
							System.out.println("stuck to mouse");
							if(stuckToMouse == null)
								stuckToMouse = comp;
							else if(stuckToMouse == comp){
								stuckToMouse = null;
							}
						}	
				});
			});

		}
		
		pane.setStyle("-fx-background-color: rgb(244,245,246);");
		
		WGComonent arduino = new WGComonent(CompID.ARDUINO);
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
					if(stuckToMouse.compid != CompID.LINE_IR) {
						stuckToMouse.setLayoutX(a.getX()-( stuckToMouse).getWidth()/2);
						stuckToMouse.setLayoutY(a.getY()-( stuckToMouse).getHeight()/2);
					} else {
						stuckToMouse.setLayoutX((a.getX()-( stuckToMouse).getWidth()/2)+20);
						stuckToMouse.setLayoutY((a.getY()-( stuckToMouse).getHeight()/2)+30);
					}
					
					//start at 1 because image is 0th index
					for(int i = 0; i < stuckToMouse.getChildren().size(); i++) {
						if(stuckToMouse.getChildren().get(i) instanceof PinSquare)
						{
							PinSquare temp = (PinSquare)stuckToMouse.getChildren().get(i);
							if(temp.line != null) {
								temp.line.setEndX(stuckToMouse.getLayoutX() + temp.getLayoutX()+temp.getWidth()/2);
								temp.line.setEndY(stuckToMouse.getLayoutY() + temp.getLayoutY()+temp.getHeight()/2);
							}
						}
					}
					
				}
				scene.setOnKeyPressed(e->{
					if(e.getCode() == KeyCode.ESCAPE) {
						if(stuckToMouse != null) {
							//start at 1 because image is 0th index
							for(int i = 0; i < stuckToMouse.getChildren().size(); i++) {
								if(stuckToMouse.getChildren().get(i) instanceof PinSquare)
								{
									PinSquare tempPin = (PinSquare)stuckToMouse.getChildren().get(i);
									//given line what arraylist is it apart of remove the lines if it is contained
									
									for(int j = 0; j < lines.size(); j++) {
										if(lines.get(j).contains(tempPin.line)) {
											ArrayList<SLine> tempLine = lines.get(j);
											lines.remove(j);
											for(int k = 0; k < tempLine.size(); k++) {
												pane.getChildren().remove(tempLine.get(k));
											}
										}
									}
								}
								
							}
							this.manager.arduino.getComponents().remove(stuckToMouse.comp);
							pane.getChildren().remove(stuckToMouse);
							stuckToMouse = null;
							
						} else if (wiring) {
							wiring = false;
							ArrayList<SLine> temp = lines.get(lines.size()-1);
							lines.remove(lines.size()-1);
							
							for(int i = 0; i < temp.size(); i++) {
								pane.getChildren().remove(temp.get(i));
							}
						}
					}
				});
				
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
			
			for(int i = 0; i < wgComponent.size(); i++) {
				if(wgComponent.get(i).compid == CompID.LED) {
					wgComponent.get(i).changeImage();
				}
				else if (wgComponent.get(i).compid == CompID.MOTOR) {
					wgComponent.get(i).spinMotor();
				}
			}
			
		}));
		
		Platform.runLater(new Runnable() {
		      @Override
		      public void run() {
		    	  new Thread(() -> {
		    		  
		    	  timeline.setCycleCount(Timeline.INDEFINITE);
		    	  timeline.play();
		    	  }).start();
		      }
		  });

		primaryStage.setScene(scene);
		primaryStage.setTitle("Wiring GUI");
		primaryStage.show();
	}
	
	public void removeAllWireOfThisLine(Line l) {
		//find line in arrayList
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
		Pin pin;
		
		Line line;
		boolean resistor = false;
		
		public void setLineReference(Line line) {
			this.line = line;
		}
		
		public PinSquare( double x, double y, PinType pinType, Pane p, boolean local, boolean isDigitalifIO, int ioNumber) {
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
			
			
			if((pinType == PinType.GROUND || pinType == PinType.POWER_5V || pinType == PinType.POWER_3_3V) && local) {
				pin = new SpecialPin(pinType, local);
			} else {
				pin = new Pin(pinType, local);
			}
			
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
					
					
					tempPinNum = ioNumber;
					pinDig = isDigitalifIO;
					tempPin = pin;
					
				} else {
						lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setEndX(this.p.getLayoutX() + getLayoutX()+getWidth()/2);
						lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1).setEndY(this.p.getLayoutY() + getLayoutY()+getHeight()/2);
						
						this.setLineReference(lines.get(lines.size()-1).get(lines.get(lines.size()-1).size()-1));
					wiring = false;
					wiringPivot[0] = 0;
					wiringPivot[1] = 0;
					System.out.println("DONE WIRING");
					
					
					
					if(!resistor) {
						manager.arduino.AddConnection(tempPin, pin, pinDig, isDigitalifIO, tempPinNum, ioNumber);
					} else {
						tempPin.addNext(pin);
						pin.addPrev(tempPin);
					}
					
				}
			});
		}
	}

}
