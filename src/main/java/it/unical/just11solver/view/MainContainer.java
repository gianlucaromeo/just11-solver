package it.unical.just11solver.view;

import java.util.ArrayList;
import java.util.List;

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
		List<CellView> allCells = new ArrayList();
		setCenter(Matrix.getInstance());
		for (int r = 0; r < 5; r++) {
			for (int c = 0; c < 5; c++) {
				Cell cell = new Cell(r, c, RandomGenerator.rand(1, 3)); // TODO Move values to constants
				CellView cellView = new CellView(cell);
				allCells.add(cellView);
			}
		}
		Matrix.getInstance().update(allCells);
		setStyle();
	}
	
	public void setStyle() {
		
	}
	
}
