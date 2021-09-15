package com.egr101sim.ui;


import com.egr101sim.core.ApplicationManager;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
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
		
		Button execute = new Button("GO");
		execute.relocate(5, 5);
		execute.setPrefSize(40, 40);
		codeSpace.relocate(0, 50);
		codeSpace.setPrefSize(600, 300);
		
		execute.setOnAction(e->{
			manager.execute(codeSpace.getText());
		});
		
		pane.getChildren().addAll(execute, codeSpace);
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("EGR101 Simulation Software");
		primaryStage.show();
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
