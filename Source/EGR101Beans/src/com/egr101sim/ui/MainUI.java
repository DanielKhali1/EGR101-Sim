package com.egr101sim.ui;


import com.egr101sim.core.ApplicationManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class MainUI extends Application{
	
	Pane pane;
	Scene scene;
	ApplicationManager manager;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		manager = new ApplicationManager();
		pane = new Pane();
		scene = new Scene(pane, 600, 400);
		
		TextArea codeSpace = new TextArea("void setup() {\r\n" + 
										  "  // put your setup code here, to run once:\r\n" + 
										  "\r\n" + 
										  "}\r\n" + 
										  "\r\n" + 
										  "void loop() {\r\n" + 
										  "  // put your main code here, to run repeatedly:\r\n" + 
										  "\r\n" + 
										  "}");
		
		
		codeSpace.relocate(0, 50);
		codeSpace.setPrefSize(600, 300);
		
		
	
		Rectangle rectangle = new Rectangle();
		rectangle.setFill(Color.DARKGREEN);
		rectangle.setX(0);
		rectangle.setY(0);
		rectangle.setWidth(600);
		rectangle.setHeight(32.5);
		
		Button execute = new Button("Run");
		execute.relocate(0, 35);
		execute.setPrefSize(40, 30);
		codeSpace.relocate(0, 100);
		codeSpace.setPrefSize(600, 300);
		
		Button build = new Button("Build");
		build.relocate(45, 35);
		build.setPrefSize(45, 30);
		
		build.setOnAction(e->{
			manager.updateBehavior(codeSpace.getText());
		});
		
		Button file = new Button("File");
		file.relocate(0, 0);
		file.setPrefSize(40,30);
		
		Button sketch = new Button("Sketch");
		sketch.relocate(45, 0);
		sketch.setPrefSize(60, 30);
		
		execute.setOnAction(e->{
			manager.execute();
		});
		
		pane.getChildren().addAll(rectangle, build, execute, file, sketch, codeSpace);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("EGR101 Simulation Software");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
