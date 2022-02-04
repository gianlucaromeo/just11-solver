package it.unical.just11solver;

import java.util.ArrayList;
import java.util.List;

import it.unical.just11solver.model.Cell;
import it.unical.just11solver.model.Choose;
import it.unical.just11solver.view.CellView;
import it.unical.just11solver.view.MainContainer;
import it.unical.just11solver.view.Matrix;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
import it.unical.mat.embasp.base.Output;
import it.unical.mat.embasp.languages.IllegalAnnotationException;
import it.unical.mat.embasp.languages.ObjectNotValidException;
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

		handler = new DesktopHandler(new DLV2DesktopService("lib/dlv2.exe"));

		ASPMapper.getInstance().registerClass(Cell.class);
		ASPMapper.getInstance().registerClass(Choose.class);

		Scene scene = new Scene(MainContainer.getInstance(), 600, 600);

		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();

		handler.addProgram(Matrix.getInstance().getFacts());

		InputProgram encoding = new ASPInputProgram();
		encoding.addFilesPath(encodingResource);

		handler.addProgram(encoding);
		
		InputProgram facts = new ASPInputProgram();
		facts.addObjectInput(new Cell(2,2,2));
		
		/*
		 * Output o = handler.startSync();
		 * 
		 * AnswerSets answerSets = (AnswerSets) o; AnswerSet optimum =
		 * answerSets.getOptimalAnswerSets().get(0);
		 * 
		 * //List<CellView> newCellViews = new ArrayList(); for (Object obj :
		 * optimum.getAtoms()) { if (obj instanceof Choose) { Choose choose = (Choose)
		 * obj; System.out.println("Choose: " + choose.toString()); CellView cellView =
		 * Matrix.getInstance().getCellView(choose.getRow(), choose.getColumn());
		 * Matrix.getInstance().onClick(cellView); } }
		 */

	}
}
