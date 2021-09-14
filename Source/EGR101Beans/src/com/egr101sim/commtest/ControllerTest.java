package com.egr101sim.commtest;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
public class ControllerTest extends Application{

	ServerSocket ss;
	Socket sock;
	DataInputStream dis;
	DataOutputStream dos;
	
	@Override
	public void start(Stage stage) throws Exception {
		
		new Thread(() -> initializeServerCharacteristics()).start();
		Pane pane = new Pane(); 
		Scene scene = new Scene(pane, 300, 300);
		
		Button up = new Button("up");
		Button down = new Button("down");
		Button left = new Button("left");
		Button right = new Button("right");
		
		up.relocate(75, 25);
		down.relocate(75, 75);
		left.relocate(25, 75);
		right.relocate(125, 75);
		
		up.setPrefSize(50, 50);
		down.setPrefSize(50, 50);
		left.setPrefSize(50, 50);
		right.setPrefSize(50, 50);
		pane.getChildren().addAll(up, down, left, right);
		
		up.setOnAction(e->{ new Thread(() -> { sendMessage("u") ;}).start();});
		down.setOnAction(e->{ new Thread(() -> { sendMessage("d") ;}).start();});
		left.setOnAction(e->{ new Thread(() -> { sendMessage("l") ;}).start();});
		right.setOnAction(e->{ new Thread(() -> { sendMessage("r") ;}).start();});
		
		stage.setScene(scene);
		stage.setTitle("x");
		stage.show();

	}
	
	public void initializeServerCharacteristics() {
		
		try {
			ss = new ServerSocket(666);
			sock = ss.accept();
			
			dis = new DataInputStream(sock.getInputStream());
			dos = new DataOutputStream(sock.getOutputStream());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(String s){
		try {
	        byte[] toSendBytes = s.getBytes();
	        int toSendLen = toSendBytes.length;
	        byte[] toSendLenBytes = new byte[4];
	        toSendLenBytes[0] = (byte)(toSendLen & 0xff);
	        toSendLenBytes[1] = (byte)((toSendLen >> 8) & 0xff);
	        toSendLenBytes[2] = (byte)((toSendLen >> 16) & 0xff);
	        toSendLenBytes[3] = (byte)((toSendLen >> 24) & 0xff);
	        dos.write(toSendLenBytes);
	        dos.write(toSendBytes);
			dos.flush();
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
