package it.unical.just11solver.view;

import javafx.scene.layout.BorderPane;

public class MainContainer extends BorderPane {

	private static MainContainer instance = null;
	
	public static MainContainer getInstance() {
		if (instance == null) {
			instance = new MainContainer();
		}
		return instance;
	}
	
	private MainContainer() {
		
	}
	
}
