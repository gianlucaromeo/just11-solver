package it.unical.just11solver.view;

import it.unical.just11solver.model.Cell;
import it.unical.just11solver.util.RandomGenerator;
import javafx.scene.layout.BorderPane;

public class MainContainer extends BorderPane {	
	
	/*
	 * Singleton
	 * */
	private static MainContainer instance = null;
	
	public static MainContainer getInstance() {
		if (instance == null) {
			instance = new MainContainer();
		}
		return instance;
	}
	
	private MainContainer() {
		System.out.println("Initial Matrix:");
		UICell[][] newCellViews = new UICell[5][5];
		setCenter(Matrix.getInstance());
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				Cell cell = new Cell(r, c, RandomGenerator.rand(1, 3)); // TODO Move values to constants
				UICell cellView = new UICell(cell);
				newCellViews[r][c] = cellView;
				System.out.print("[" + cell.getValue() + "]");
			}
			System.out.println();
		}
		System.out.println("_______________");
		Matrix.getInstance().setCurrentMax(3);
		Matrix.getInstance().update(newCellViews);
		setBottom(Footer.getInstance());
		setTop(PlayStopButton.getInstance());
		setStyle();
	}
	
	public void setStyle() {
		getStyleClass().add("just11-main-container");
	}

	public void reset() {
		instance = null;
		instance = new MainContainer();
	}
	
}
