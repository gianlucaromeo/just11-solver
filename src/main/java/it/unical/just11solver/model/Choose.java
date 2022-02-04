package it.unical.just11solver.model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("choose")
public class Choose {

	@Param(0)
	private int row;
	
	@Param(1)
	private int column;

	public Choose(int r, int c) {
		this.row = r;
		this.column = c;
	}

	public Choose() {}

	public int getRow() {
		return row;
	}

	public void setRow(int row) {
		this.row = row;
	}

	public int getColumn() {
		return column;
	}

	public void setColumn(int column) {
		this.column = column;
	}
	
	@Override
	public String toString() {
		return "(" + row + ", " + column + ")";
	}
	
}
