package it.unical.just11solver.view;

import it.unical.just11solver.model.Cell;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class CellView extends Button implements EventHandler<MouseEvent>{

	private Cell cellModel;
	
	@Override
	public void handle(MouseEvent event) {
		Matrix.getInstance().onClick(this);
		System.out.println("Clicked on " + cellModel.toString());
	}
	
	public CellView(Cell cellModel) {
		this.cellModel = cellModel;
		setText("" + cellModel.getValue());
		setOnMouseClicked(this);
		setStyle();
	}
	
	public Cell getCellModel() {
		return cellModel;
	}
	
	public void setStyle() {
		setMinWidth(50);
		setMinHeight(50);
	}
	
}
