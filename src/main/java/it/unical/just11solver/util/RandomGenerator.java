package it.unical.just11solver.util;

import java.util.Random;

public class RandomGenerator {

	public static Random random = new Random();
	
	public static int rand(int min, int max) {
		
		if (max < 5) {			
			return random.nextInt((max - min) + 1) + min;
		} else {
			return random.nextInt((max-2 - min) + 1) + min;			
		}
	}
	
}
