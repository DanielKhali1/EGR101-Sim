package com.egr101sim.ui;


import java.io.File;
import com.egr101sim.core.ApplicationManager;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Separator;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.text.*;

public class MainUI extends Application {
	
	Pane pane;
	Scene scene;
	ApplicationManager manager;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		manager = new ApplicationManager();
		pane = new Pane();
		scene = new Scene(pane, 1000, 760);
		
		TextArea codeSpace = new TextArea("void setup() {\r\n" + 
										  "  // put your setup code here, to run once:\r\n" + 
										  "\r\n" + 
										  "}\r\n" + 
										  "\r\n" + 
										  "void loop() {\r\n" + 
										  "  // put your main code here, to run repeatedly:\r\n" + 
										  "\r\n" + 
										  "}");
		
		new WiringGUI(manager).start(new Stage());
		
		codeSpace.relocate(0, 100);
		codeSpace.setPrefSize(1000, 500);
		
		// colors for background 
		Color lightGreen = Color.web("#17a1a5");
		Color darkGreen = Color.web("#006468");
		Color textGreen = Color.web("#0f6464");
//		Color textBlue = Color.web("#1c9ea4");
//		Color textYellow = Color.web("#68731a");
		
		Rectangle rectangle = new Rectangle();
		rectangle.setFill(lightGreen);
		rectangle.setX(0);
		rectangle.setY(60);
		rectangle.setWidth(1000);
		rectangle.setHeight(40); 
		
		Rectangle rectangle2 = new Rectangle();
		rectangle2.setFill(darkGreen);
		rectangle2.setX(0);
		rectangle2.setY(30);
		rectangle2.setWidth(1000);
		rectangle2.setHeight(40); 
		
		Rectangle rectangle4 = new Rectangle();
		rectangle4.setFill(lightGreen);
		rectangle4.setX(0);
		rectangle4.setY(600);
		rectangle4.setWidth(1000);
		rectangle4.setHeight(30); 
		
		Rectangle rectangle5 = new Rectangle();
		rectangle5.setFill(Color.BLACK);
		rectangle5.setX(0);
		rectangle5.setY(620);
		rectangle5.setWidth(1000);
		rectangle5.setHeight(100); 
		
		Rectangle rectangle6 = new Rectangle();
		rectangle6.setFill(darkGreen);
		rectangle6.setX(0);
		rectangle6.setY(720);
		rectangle6.setWidth(1000);
		rectangle6.setHeight(50); 
		
		Rectangle rectangle3 = new Rectangle();
		rectangle3.setFill(Color.WHITE);
		rectangle3.setX(20);
		rectangle3.setY(70);
		rectangle3.setWidth(80);
		rectangle3.setHeight(60); 
		rectangle3.setArcHeight(10);
		rectangle3.setArcWidth(10);
		
		Text t = new Text();
		t.setX(30);
		t.setY(90);
		t.setText("sketch_");
		t.setFill(textGreen);
		
		Text t2 = new Text();
		t2.setX(940);
		t2.setY(750);
		t2.setText("Arduino");
		t2.setFill(Color.WHITE);
		
		Button run = new Button("Run");
		run.relocate(0, 35);
		run.setPrefSize(40, 30);
		
		Button build = new Button("Build");
		build.relocate(45, 35);
		build.setPrefSize(45, 30);
		
		Button newFile = new Button("New File");
		newFile.relocate(95, 35);
		newFile.setPrefSize(65, 30);

		Button open = new Button("Open");
		open.relocate(165, 35);
		open.setPrefSize(50, 30);
		
		Button save = new Button("Save");
		save.relocate(220,35);
		save.setPrefSize(50, 30);
		
		pane.getChildren().addAll(rectangle6, rectangle5, rectangle4, rectangle, rectangle2, rectangle3, t, t2, codeSpace,run, build, newFile,open,save, ToolBar(primaryStage));
		
		build.setOnAction(e->{
			manager.updateBehavior(codeSpace.getText());
		});
		
		run.setOnAction(e->{
			
			Platform.runLater(new Runnable() {
			      @Override
			      public void run() {
			    	  new Thread(() -> {
			    		  manager.execute();
			    	  }).start();
			      }
			  });
		});

		File f = new File("Styles.css");
		scene.getStylesheets().add("File:///"+f.getAbsolutePath().replace("\\","/"));
		
		primaryStage.setScene(scene);
		primaryStage.setTitle("EGR101 Simulation Software");
		primaryStage.show();
		
	}
	
	public ToolBar ToolBar(Stage stage)
	{
		FileChooser fileChooser = new FileChooser();
		ToolBar toolBar = new ToolBar();
		
		MenuItem btnfile = new MenuItem("New");
		
		MenuItem btnsketch = new MenuItem("Open");
		
		btnsketch.setOnAction(e ->{
			fileChooser.setTitle("Open Resource File");
			fileChooser.showOpenDialog(stage);
		});
		
		MenuItem btnSave = new MenuItem("Save");
		
		MenuItem btnSaveAs = new MenuItem("Save As");
		
		MenuItem btnSaveConfig = new MenuItem("Save Config File");
		
		MenuButton file = new MenuButton("File");
		file.setPrefSize(70,20);
		
		file.getItems().addAll(btnfile, btnsketch , btnSave, btnSaveAs, btnSaveConfig);
		
		MenuItem btnVerifyCompile = new MenuItem("Verify/Compile");
		
		MenuItem btnUpload = new MenuItem("Upload");
		
		MenuButton sketch = new MenuButton("Sketch");
		sketch.setPrefSize(70,20);
		
		sketch.getItems().addAll(btnVerifyCompile, btnUpload);
		
		MenuItem btnSerialMon = new MenuItem("Serial Monitor");
		
		MenuButton tools = new MenuButton("Tools");
		tools.setPrefSize(70, 20);
		
		tools.getItems().addAll(btnSerialMon);
		
		/*
		execute.setOnAction(e->{
			manager.execute();
		});*/
		toolBar.getItems().addAll(file, new Separator(), sketch, new Separator(), tools);
		
		return toolBar;
	}
	
	
	public static void main(String[] args) {
		launch(args);
	}

}
