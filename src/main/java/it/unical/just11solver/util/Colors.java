package it.unical.just11solver.util;

import java.util.HashMap;
import java.util.Map;

public class Colors {
	
	public static final Map<Integer, Pair> colors = new HashMap<Integer, Pair>();
	
	/* Background and Font color for value 1 */
	private static String BG1 = "#c5f4fe"; // LIGHT BLUE
	private static String FC1 = "#007d8b"; // TEAL

	/* Background and Font color for value 2 */
	private static String BG2 = "#fad8cf"; // LIGHT PINK (?)
	private static String FC2 = "#ff8775"; // PINK/ORANGE
	
	/* Background and Font color for value 3 */
	private static String BG3 = "#c9fbbc"; // LIGHT GREEN
	private static String FC3 = "#2fc71d"; // GREEN
	
	/* Background and Font color for value 4 */
	private static String BG4 = "#eebbfd"; // LIGHT PURBLE
	private static String FC4 = "#bf65d7"; // PURPLE
	
	/* Background and Font color for value 5 */
	private static String BG5 = "#fef9bf"; // LIGHT YELLOW
	private static String FC5 = "#d4c40c"; // YELLOW/GOLD

	/* Background and Font color for value 6 */
	private static String BG6 = "#99fee0"; // LIGHT TEAL 
	private static String FC6 = "#2bbd90"; // TEAL

	/* Background and Font color for value 7 */
	private static String BG7 = "#ffc796"; // LIGHT ORANGE
	private static String FC7 = "#e48430"; // ORANGE
	
	/* Background and Font color for value 8 */
	private static String BG8 = "#d6fd6e"; // LIME
	private static String FC8 = "#92ba25"; // OLIVE

	/* Background and Font color for value 9 */
	private static String BG9 = "#fda2c5"; // PINK
	private static String FC9 = "#cb3771"; // PINK MORE PINK

	/* Background and Font color for value 10 */
	private static String BG10 = "#fdf76d"; // YELLOW
	private static String FC10 = "#b4471e"; // BORDEAUX

	/* Background and Font color for value 11 */
	private static String BG11 = "#047bfd"; // BLUE
	private static String FC11 = "#fff"; // WHITE
	
	
	static {
		colors.put(1, new Pair(BG1, FC1));
		colors.put(2, new Pair(BG2, FC2));
		colors.put(3, new Pair(BG3, FC3));
		colors.put(4, new Pair(BG4, FC4));
		colors.put(5, new Pair(BG5, FC5));
		colors.put(6, new Pair(BG6, FC6));
		colors.put(7, new Pair(BG7, FC7));
		colors.put(8, new Pair(BG8, FC8));
		colors.put(9, new Pair(BG9, FC9));
		colors.put(10, new Pair(BG10, FC10));
		colors.put(11, new Pair(BG11, FC11));
	}
	
	public static class Pair {
		
		String backgroundColor;
		String fontColor;

		public Pair(String backgroundColor, String fontColor) {
			this.backgroundColor = backgroundColor;
			this.fontColor = fontColor;
		}

		public String getBackgroundColor() {
			return backgroundColor;
		}

		public String getFontColor() {
			return fontColor;
		}
		
	}

}
