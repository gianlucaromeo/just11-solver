package it.unical.just11solver;

import java.lang.reflect.InvocationTargetException;

import it.unical.just11solver.model.Cell;
import it.unical.just11solver.model.Choose;
import it.unical.just11solver.model.NextCell;
import it.unical.just11solver.view.CellView;
import it.unical.just11solver.view.MainContainer;
import it.unical.just11solver.view.Matrix;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.asp.ASPInputProgram;
import it.unical.mat.embasp.languages.asp.ASPMapper;
import it.unical.mat.embasp.languages.asp.AnswerSet;
import it.unical.mat.embasp.languages.asp.AnswerSets;
import it.unical.mat.embasp.platforms.desktop.DesktopHandler;
import it.unical.mat.embasp.specializations.dlv2.desktop.DLV2DesktopService;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application {

	private static String encodingResource = "encodings/just11";
	private static Handler handler;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {

		// cambiare su windows
		handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));

		ASPMapper.getInstance().registerClass(Cell.class);
		ASPMapper.getInstance().registerClass(NextCell.class);
		ASPMapper.getInstance().registerClass(Choose.class);

		Scene scene = new Scene(MainContainer.getInstance(), 600, 600);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

		Timeline t = new Timeline(new KeyFrame(Duration.millis(500), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				
				InputProgram facts = new ASPInputProgram();
				for (CellView[] cellViews : Matrix.getInstance().getCellViews()) {
					for (CellView cell : cellViews) {
						try {
							facts.addObjectInput(cell.getCellModel());
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}

				handler.removeAll();
				handler.addProgram(facts);

				InputProgram encoding = new ASPInputProgram();
				encoding.addFilesPath(encodingResource);

				handler.addProgram(encoding);

				Output o = handler.startSync();

				AnswerSets answerSets = (AnswerSets) o;
				AnswerSet optimum = new AnswerSet(null);
				try {
					optimum = answerSets.getOptimalAnswerSets().get(0);
				} catch (Exception e) {
					System.out.println("NO MOVES!!!!");
					System.exit(1);
				}
				System.out.println("\nOttimo ----> " + optimum.toString());

				CellView[][] newCellViews = new CellView[5][5];
				try {
					for (Object obj : optimum.getAtoms()) {
						if (obj instanceof NextCell) {
							NextCell nextCell = (NextCell) obj;
							if (nextCell.getValue() == 11) {
								System.out.println("FINE!!!!");
								System.exit(1);
							}
							Cell newCell = new Cell(nextCell);
							CellView newCellView = new CellView(newCell);
							newCellViews[newCell.getRow()][newCell.getColumn()] = newCellView;
						}
					}
				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | InstantiationException e) {
					System.out.println("ERROR");
					e.printStackTrace();
				}

				Matrix.getInstance().update(newCellViews);
			}

		}));

		t.setCycleCount(Timeline.INDEFINITE);
		t.play();

	}
}
