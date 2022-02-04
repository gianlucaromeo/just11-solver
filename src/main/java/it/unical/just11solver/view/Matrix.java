package it.unical.just11solver.view;

import java.util.List;

import it.unical.just11solver.model.Cell;
import it.unical.just11solver.util.CalculateNewMatrix;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import javafx.scene.layout.GridPane;

public class Matrix extends GridPane {

	InputProgram facts = new ASPInputProgram();

	private int currentMax = 1;
	List<CellView> cellViews = null;

	/*
	 * Add cells to the matrix and check if there is a new max value (to generate
	 * random numbers).
	 */
	public void update(List<CellView> cellViews) {

		facts = new InputProgram();
		getChildren().clear();

		this.cellViews = cellViews;
		cellViews.forEach((cellView) -> {

			Cell cell = cellView.getCellModel();
			add(cellView, cell.getColumn(), cell.getRow());

			addCellToFacts(cell);

			int v = cell.getValue();
			if (v > currentMax) {
				currentMax = v;
			}

		});

	}

	private void addCellToFacts(Cell cell) {

		try {
			System.out.println(cell);
			//facts.addObjectInput(cell);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public InputProgram getFacts() {
		return facts;
	}

	public int getCurrentMax() {
		return currentMax;
	}

	public CellView getCellView(int row, int column) {
		return (CellView) getChildren().get(row * 5 + column);
	}

	/*
	 * Singleton
	 */
	private static Matrix instance = null;

	public static Matrix getInstance() {
		if (instance == null) {
			instance = new Matrix();
		}
		return instance;
	}

	private Matrix() {
		setStyle();
	}

	private void setStyle() {
		setMaxWidth(50 * 5 + 2 * 5);
		setMaxHeight(50 * 5 + 2 * 5);
	}

	public void onClick(CellView cellView) {
		update(CalculateNewMatrix.onClick(cellView, cellViews));
	}

}
