package com.cousersoft.game.level;
import java.util.Random;

public class RandomLevel extends Level {
	private static final Random random = new Random();
	
	
	//Generate random level and set the width and height to the super's value
	public RandomLevel(int width, int height) {
		super(width, height);
	}
	
	//Method to generate random level
	public void generateLevel() {
		//cycle through each tile and set to a random value between 0 and 3
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tileInt[x + y * width] = random.nextInt(21);
			}
		}
	}
}
