package it.unical.just11solver.view;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

public class Footer extends HBox {
	
	private Label footerInfo;

	public static Footer instance = null;
	
	public static Footer getInstance() {
		if (instance == null) {
			instance = new Footer();
		}
		return instance;
	}
	
	private Footer() {
		footerInfo = new Label("Cristian D. Dramisino & Gianluca Romeo.");
		getChildren().add(footerInfo);
		setAlignment(Pos.CENTER);
		getStyleClass().add("just11-footer");
		footerInfo.getStyleClass().add("just11-footer-lbl");
	}
	
}
