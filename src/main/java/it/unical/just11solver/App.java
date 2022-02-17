package it.unical.just11solver;

import java.lang.reflect.InvocationTargetException;

import it.unical.just11solver.config.Settings;
import it.unical.just11solver.model.Cell;
import it.unical.just11solver.model.Choose;
import it.unical.just11solver.model.NewCell;
import it.unical.just11solver.model.NewCellBeforeMove;
import it.unical.just11solver.model.Orientation;
import it.unical.just11solver.view.UICell;
import it.unical.just11solver.view.MainContainer;
import it.unical.just11solver.view.Matrix;
import it.unical.mat.embasp.base.Handler;
import it.unical.mat.embasp.base.InputProgram;
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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.util.Duration;

public class App extends Application implements EventHandler<ActionEvent> {

	private enum HandlerType {
		WINDOWS, LINUX,
	};

	private static Scene scene = null;

	private static boolean stop = false;
	private static String currentOrientation = "dx";
	private static Handler handler;

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage stage) {

		initScene(stage);

		createHandler(HandlerType.LINUX);
		registerClasses();

		Timeline t = new Timeline(new KeyFrame(Duration.millis(550), this));
		t.setCycleCount(Timeline.INDEFINITE);
		t.play();

	}

	@Override
	public void handle(ActionEvent event) {

		if (stop) {
			resetGame();
		} else {

			InputProgram facts = new ASPInputProgram();

			addFactsToProgram(facts);
			setOrientationStrategy(facts);

			resetHandler(facts);

			AnswerSets answerSets = (AnswerSets) handler.startSync();
			AnswerSet optimal = new AnswerSet(null);

			try {

				optimal = answerSets.getOptimalAnswerSets().get(0);

				System.out.println("Found Optimal : " + optimal.toString());

				UICell[][] newCellViews = new UICell[Settings.MATRIX_SIZE][Settings.MATRIX_SIZE];

				@SuppressWarnings("unused")
				Choose choose = null;

				try {
					for (Object obj : optimal.getAtoms()) {
						if (obj instanceof Choose) {
							choose = (Choose) obj;
						} else if (obj instanceof NewCell) {
							NewCell newCell = (NewCell) obj;
							if (newCell.getValue() == Settings.MAX_NUMBER) {
								System.out.println("End of the game. AI won!");
								stop = true;
							}
							Cell newCellModel = new Cell(newCell);
							UICell uiCell = new UICell(newCellModel);
							newCellViews[newCellModel.getRow()][newCellModel.getColumn()] = uiCell;
						}
					}

					Matrix.getInstance().update(newCellViews);

				} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException
						| NoSuchMethodException | SecurityException | InstantiationException e) {
					System.err.println(Settings.OPTIMAL_AS_PARSING_ERROR);
					e.printStackTrace();
				}
			} catch (Exception e) {
				System.out.println("No moves left. AI loose!");
				//e.printStackTrace();
				stop = true;
			}

		}

	} // end handle

	private void resetHandler(InputProgram facts) {

		handler.removeAll();
		handler.addProgram(facts);

		InputProgram encoding = new ASPInputProgram();
		encoding.addFilesPath(Settings.ENCODING_RESOURCE);

		handler.addProgram(encoding);

	}

	@Deprecated
	private void setOrientationStrategy(InputProgram facts) {
		currentOrientation = currentOrientation.equals("dx") ? "sx" : "dx";
		try {
			facts.addObjectInput(new Orientation(currentOrientation));
		} catch (Exception e1) {
			System.err.println(Settings.ORIENTATIONS_ERROR);
			e1.printStackTrace();
		}
	}

	private void addFactsToProgram(InputProgram facts) {
		for (UICell[] cellViews : Matrix.getInstance().getCellViews()) {
			for (UICell cell : cellViews) {
				try {
					facts.addObjectInput(cell.getCellModel());
				} catch (Exception e) {
					System.err.println(Settings.ADD_FACTS_TO_PROGRAM_ERROR);
					e.printStackTrace();
				}
			}
		}
	}

	private void resetGame() {
		MainContainer.getInstance().reset();
		scene.setRoot(MainContainer.getInstance());
		stop = false;
	}

	private void initScene(Stage stage) {
		scene = new Scene(MainContainer.getInstance(), Settings.SCENE_WIDTH, Settings.SCENE_HEIGHT);
		scene.getStylesheets().add(getClass().getResource(Settings.CSS_PATH).toExternalForm());
		stage.setScene(scene);
		stage.setResizable(Settings.RESIZABLE);

		stage.getIcons().add(new Image(Settings.ICON_URL));
		
		stage.show();
	}

	private void registerClasses() {
		try {
			ASPMapper.getInstance().registerClass(NewCellBeforeMove.class); // Currently not necessary
			ASPMapper.getInstance().registerClass(Orientation.class); // Currently not necessary
			ASPMapper.getInstance().registerClass(Cell.class);
			ASPMapper.getInstance().registerClass(Choose.class);
			ASPMapper.getInstance().registerClass(NewCell.class);
		} catch (ObjectNotValidException | IllegalAnnotationException e) {
			System.err.println(Settings.REGISTER_CLASS_ERROR);
			e.printStackTrace();
		}
	}

	private static void createHandler(HandlerType handlerType) {
		String exe_path = handlerType == HandlerType.WINDOWS ? Settings.WINDOWS_EXE_PATH : Settings.LINUX_EXE_PATH;
		handler = new DesktopHandler(new DLV2DesktopService(exe_path));
	}

} // end class
