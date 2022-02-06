package it.unical.just11solver.view;

import it.unical.just11solver.model.Cell;
import it.unical.just11solver.util.Colors;
import it.unical.just11solver.util.Colors.Pair;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Paint;

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
		if (cellModel.getValue() > 0) {			
			Pair colors = Colors.colors.get(cellModel.getValue());
			String backgroundColor = colors.getBackgroundColor();
			String fontColor = colors.getFontColor();
			setTextFill(Paint.valueOf(fontColor));
			setStyle("-fx-background-color: " + backgroundColor + ";");
		}
		getStyleClass().add("just11-cell");
		
	}

	public void update(int currentValue) {
		cellModel.setValue(currentValue);
		setStyle();
		setText(""+currentValue);
	}
	
}
