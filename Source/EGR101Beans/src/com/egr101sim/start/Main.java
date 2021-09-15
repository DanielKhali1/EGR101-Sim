package com.egr101sim.start;

import com.egr101sim.ui.MainUI;

public class Main {
	
	public static void main(String[] args) {
			Runnable runnable = () -> { 
				try {
					new MainUI().start(new javafx.stage.Stage());
				} catch (Exception e) {
					e.printStackTrace();
				}
			};
			Thread t = new Thread(runnable);
			t.start();
	}
}
