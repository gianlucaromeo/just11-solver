package it.unical.just11solver.config;

public class Settings {

	/* UI */
	public static final int SCENE_WIDTH = 400;
	public static final int SCENE_HEIGHT = 400;
	public static final boolean RESIZABLE = false;
	public static final String CSS_PATH = "/application/css/style.css";
	public static final String ICON_URL = "https://cdn6.aptoide.com/imgs/0/8/3/083e5b918a2c060f9c6a4e7bca38097c_icon.png?w=160";

	
	
	/* GAME AND MATRIX */
	public static final int MATRIX_SIZE = 5;
	public static final int MAX_NUMBER = 11;
	
	/* EMBASP */
	public static final String ENCODING_RESOURCE = "encodings/just11";
	public static final String LINUX_EXE_PATH = "lib/dlv2Linux";
	public static final String WINDOWS_EXE_PATH = "lib/dlv2_windows_64.exe";
	
	/* CONSOLE ERRORS */
	public static final String REGISTER_CLASS_ERROR = "[JUST_11_SOLVER][ERROR][registerClasses()]:";
	public static final String OPTIMAL_AS_PARSING_ERROR = "[JUST_11_SOLVER][ERROR] Error while trying to examine Atoms in Optimal Answer Set.";
	public static final String ORIENTATIONS_ERROR = "[JUST_11_SOLVER][ERROR][setOrientationStrategy]:";
	public static final String ADD_FACTS_TO_PROGRAM_ERROR = "[JUST_11_SOLVER][ERROR][addFactsToProgram()]:";
}
