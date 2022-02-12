package it.unical.just11solver.view;

import it.unical.just11solver.App;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class PlayStopButton extends Button implements EventHandler<MouseEvent> {

	private static PlayStopButton instance = null;
	
	public static PlayStopButton getInstance() {
		if (instance == null) {
			instance = new PlayStopButton();
		}
		return instance;
	}
	
	@Override
	public void handle(MouseEvent event) {
		//App.next();
	}
	
	private PlayStopButton() {
		super("Next Move");
		setOnMouseClicked(this);
		setStyle();
	}
	
	private void setStyle() {
		getStyleClass().add("play-stop-btn");
		setDisable(true);
	}
	
}
