package it.unical.just11solver.model;

import it.unical.mat.embasp.languages.Id;
import it.unical.mat.embasp.languages.Param;

@Id("cell")
public class Cell {

	@Param(0)
	private int row;
	
	@Param(1)
	private int column;
	
	@Param(2)
	private int value;

	public Cell(int r, int c, int v) {
		this.row = r;
		this.column = c;
		this.value = v;
	}

	public Cell() {}

	public Cell(NewCell newCell) {
		this.row = newCell.getRow();
		this.column = newCell.getColumn();
		this.value = newCell.getValue();
	}

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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return "(" + row + ", " + column + ", val: " + value + ")";
	}

}
