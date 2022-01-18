package com.egr101sim.start;

import com.egr101sim.ui.MainUI;

import javafx.application.Platform;
import javafx.concurrent.Task;
// TODO: this doesn't work fix this later

public class Main {
	
	public static void main(String[] args) {


		new Thread(new Runnable() {
		    @Override public void run() {
		        Platform.runLater(new Runnable() {
		            @Override public void run() {
						try {
							new MainUI().start(new javafx.stage.Stage());
						} catch(Exception e) {
							System.out.println("uh oh UWU u made an oopsie");
						}
		        }});
		    }
		    }).start();
	}
}
