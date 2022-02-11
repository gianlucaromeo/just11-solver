package it.unical.just11solver.view;

import it.unical.just11solver.config.Settings;
import it.unical.just11solver.model.Cell;
import it.unical.just11solver.util.RandomGenerator;
import javafx.scene.layout.GridPane;

public class Matrix extends GridPane {

	private int currentMax = 3;
	private final int ROWS = Settings.MATRIX_SIZE;
	private final int COLUMNS = Settings.MATRIX_SIZE;
	UICell[][] m = new UICell[ROWS][COLUMNS];

	/*
	 * Add cells to the matrix and check if there is a new max value (to generate
	 * random numbers).
	 */
	public void update(UICell[][] newMatrix) {

		m = newMatrix;
		getChildren().clear();
		// moveToBottom();

		System.out.println("\n\nNew (After moveToBottom())");

		for (int r = 0; r < ROWS; ++r) {
			for (int c = 0; c < COLUMNS; ++c) {

				Cell cell = m[r][c].getCellModel();

				if (cell.getValue() == 0) {
					m[r][c].update(RandomGenerator.rand(1, currentMax));
				}

				add(m[r][c], cell.getColumn(), cell.getRow());
				if (cell.getValue() > currentMax) {
					currentMax = cell.getValue();
				}

				System.out.print("[" + m[r][c].getCellModel().getValue() + "]");

			}

			System.out.println();

		}

	}

	@SuppressWarnings("unused")
	@Deprecated
	private void moveToBottom() {
		for (int r = ROWS - 2; r >= 0; --r) {
			for (int c = 0; c < COLUMNS; ++c) {
				Cell current = m[r][c].getCellModel();
				if (current.getValue() != 0) {
					move(current, getNewRow(current));
				}
			}
		}
	}

	private void move(Cell current, int newRow) {

		if (current.getRow() == newRow) {
			return;
		}

		int currentValue = current.getValue();
		m[newRow][current.getColumn()].update(currentValue);
		m[current.getRow()][current.getColumn()].update(0);

	}

	/*
	 * Ritorna la nuova riga in cui spostare una cella. Se la cella non pu� essere
	 * spostata, la nuova riga rimane uguale alla precedente.
	 */
	private int getNewRow(Cell cell) {

		int currentRow = cell.getRow();
		int col = cell.getColumn();

		boolean hasSpots = true;
		while (hasSpots && currentRow < ROWS - 1) {

			if (m[currentRow + 1][col].getCellModel().getValue() == 0) {
				++currentRow;
			} else {
				hasSpots = false;
			}

		}

		return currentRow;

	}

	public UICell[][] getCellViews() {
		return m;
	}

	public int getCurrentMax() {
		return currentMax;
	}

	public void setCurrentMax(int currentMax) {
		this.currentMax = currentMax;
	}

	public UICell getCellView(int row, int column) {
		return (UICell) getChildren().get(row * ROWS + column);
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
		getStyleClass().add("just11-cells-container");
	}

	public void onClick(UICell cellView) {
		System.out.println("Clicked on: " + cellView.getCellModel().toString());
	}

}
