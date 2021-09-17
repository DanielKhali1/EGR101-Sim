package com.egr101sim.ui;


import com.egr101sim.core.ApplicationManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.geometry.*;

public class MainUI extends Application{
	
	Pane pane;
	Scene scene;
	//ApplicationManager manager;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		//manager = new ApplicationManager();
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
		
		ToolBar toolBar = new ToolBar();
	
		Rectangle rectangle = new Rectangle();
		rectangle.setFill(Color.DARKGREEN);
		rectangle.setX(0);
		rectangle.setY(0);
		rectangle.setWidth(600);
		rectangle.setHeight(80);
		
		Button execute = new Button("Run");
		execute.relocate(0, 35);
		execute.setPrefSize(40, 30);
		codeSpace.relocate(0, 100);
		codeSpace.setPrefSize(600, 300);
		
		Button build = new Button("Build");
		build.relocate(45, 35);
		build.setPrefSize(45, 30);
		
		/*
		build.setOnAction(e->{
			manager.updateBehavior(codeSpace.getText());
		});*/
		
		MenuItem btnfile = new MenuItem("New");
		
		MenuItem btnsketch = new MenuItem("Open");
		
		MenuItem btnSave = new MenuItem("Save");
		
		MenuItem btnSaveAs = new MenuItem("Save As");
		
		MenuButton file = new MenuButton("File");
		file.setPrefSize(70,20);
		
		file.getItems().addAll(btnfile, btnsketch , btnSave, btnSaveAs);
		
		MenuItem btnVerifyCompile = new MenuItem("Verify/Compile");
		
		MenuItem btnUpload = new MenuItem("Upload");
		
		MenuItem btnSerialMon = new MenuItem("Serial Monitor");
		
		MenuButton sketch = new MenuButton("Sketch");
		sketch.setPrefSize(70,20);
		
		sketch.getItems().addAll(btnVerifyCompile, btnUpload, btnSerialMon);
		
		/*
		execute.setOnAction(e->{
			manager.execute();
		});*/
		toolBar.getItems().addAll(file, new Separator(), sketch);
		pane.getChildren().addAll(rectangle, build, execute, codeSpace, file, toolBar);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("EGR101 Simulation Software");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
