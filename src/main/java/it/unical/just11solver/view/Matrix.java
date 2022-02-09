package it.unical.just11solver.view;

import it.unical.just11solver.model.Cell;
import it.unical.just11solver.util.RandomGenerator;
import javafx.scene.layout.GridPane;

public class Matrix extends GridPane {

	private int currentMax = 3;
	CellView[][] m = new CellView[5][5];

	/*
	 * Add cells to the matrix and check if there is a new max value (to generate
	 * random numbers).
	 */
	public void update(CellView[][] newMatrix) {

		m = newMatrix;
		getChildren().clear();
		//moveToBottom();
		
		System.out.println("\n\nNew (After moveToBottom())");
		
		for (int r = 0; r < 5; ++r) {
			
			for (int c = 0; c < 5; ++c) {
				
				Cell cell = m[r][c].getCellModel();
				
				if (cell.getValue() == 0) {
					m[r][c].update(0); //RandomGenerator.rand(1, currentMax)
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
	
	private void moveToBottom() {
		for (int r = 3; r >= 0; --r) {
			for (int c = 0; c < 5; ++c) {
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
	 * Ritorna la nuova riga in cui spostare una cella.
	 * Se la cella non pu� essere spostata,
	 * la nuova riga rimane uguale alla precedente.
	 * */
	private int getNewRow(Cell cell) {
		
		int currentRow = cell.getRow();
		int col = cell.getColumn();
		
		boolean hasSpots = true;
		while (hasSpots && currentRow < 4) {
			
			if (m[currentRow + 1][col].getCellModel().getValue() == 0) {
				++currentRow;
			} else {
				hasSpots = false;
			}
			
		}
		
		return currentRow;
		
	}
	

	public CellView[][] getCellViews() {
		return m;
	}

	public int getCurrentMax() {
		return currentMax;
	}
	
	public void setCurrentMax(int currentMax) {
		this.currentMax = currentMax;
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
		//setMaxWidth(50 * 5 + 2 * 5);
		//setMaxHeight(50 * 5 + 2 * 5);
		getStyleClass().add("just11-cells-container");
	}

	public void onClick(CellView cellView) {
		System.out.println("Clicked on: " + cellView.getCellModel().toString());
	}

}
