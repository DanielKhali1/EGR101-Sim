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
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.geometry.*;

public class MainUI extends Application{
	
	Pane pane;
	Scene scene;
	ApplicationManager manager;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		manager = new ApplicationManager();
		pane = new Pane();
		scene = new Scene(pane, 600, 500);
		
		TextArea codeSpace = new TextArea("void setup() {\r\n" + 
										  "  // put your setup code here, to run once:\r\n" + 
										  "\r\n" + 
										  "}\r\n" + 
										  "\r\n" + 
										  "void loop() {\r\n" + 
										  "  // put your main code here, to run repeatedly:\r\n" + 
										  "\r\n" + 
										  "}");
		
		new WiringGUI(manager.arduino).start(new Stage());
		
		codeSpace.relocate(50, 80);
		codeSpace.setPrefSize(550, 500);
		
		Rectangle rectangle = new Rectangle();
		rectangle.setFill(Color.DARKGREEN);
		rectangle.setX(0);
		rectangle.setY(0);
		rectangle.setWidth(600);
		rectangle.setHeight(80); 
		
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
		
		pane.getChildren().addAll(rectangle, codeSpace,run, build, newFile,open,save, ToolBar(primaryStage));
		/*
		build.setOnAction(e->{
			manager.updateBehavior(codeSpace.getText());
		});*/

		
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
