package com.cousersoft.game.graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpriteSheet {
	private String path; //System path to spritesheet file
	public final int SIZE; //Size of the spritesheet
	public final int WIDTH, HEIGHT;
	public int[] pixels; //Array to hold each pixel in the spritesheet
	
	//Load in spritesheets here
	public static SpriteSheet tiles = new SpriteSheet("/textures/Outdoor.png", 256);
	public static SpriteSheet player = new SpriteSheet("/textures/player.png", 256);
	public static SpriteSheet NPC = new SpriteSheet("/textures/npc.png", 256);
	public static SpriteSheet sword = new SpriteSheet("/textures/sword.png", 16);
	public static SpriteSheet fontSheet = new SpriteSheet("/textures/fontSheet.png", 240);
	public static SpriteSheet smallFontSheet = new SpriteSheet("/textures/smallFontSheet.png", 104);
	public static SpriteSheet textBox = new SpriteSheet("/textures/textBox.png", 130);
	public static SpriteSheet background = new SpriteSheet("/textures/inventoryScreen.png", 400);
	public static SpriteSheet invSlot = new SpriteSheet("/textures/invSlot.png", 400);
	public static SpriteSheet select = new SpriteSheet("/textures/select.png", 8);
	public static SpriteSheet endSelect = new SpriteSheet("/textures/endSelect.png", 8);
	public static SpriteSheet actionMenu = new SpriteSheet("/textures/actionMenu.png", 64);
	public static SpriteSheet taco = new SpriteSheet("/textures/taco.png", 16);
	public static SpriteSheet particle = new SpriteSheet("/textures/particle.png", 4);
	public static SpriteSheet rain = new SpriteSheet("/textures/rain.png", 48);
	public static SpriteSheet tools = new SpriteSheet("/textures/tools.png", 32);
	public static SpriteSheet testPlant = new SpriteSheet("/textures/testPlant.png", 96);
	public static SpriteSheet fruit = new SpriteSheet("/textures/fruit.png", 32);
	public static SpriteSheet animHoe = new SpriteSheet("/textures/hoeAnim.png", 128);
	
	private Sprite[] sprites;
	
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) SIZE = width;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++ ) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize + (y0 + ya * spriteSize) * WIDTH)];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}
		}
	}
	
	//initialize spritesheet
	public SpriteSheet(String path, int size) {
		this.path = path; //Set the path to path passed by method
		SIZE = size; //Set size to size passed to method
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE]; //The array should hold as many pixels as the tilesheet is big
		load();
	}
	
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}
	
	public Sprite[] getSprites() {
		return sprites;
	}
	
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path)); //Read the image from the path location
			int w = image.getWidth(); //Variable to hold the width of image
			int h = image.getHeight(); //Variable to hold the heigth of image
			image.getRGB(0, 0, w, h, pixels, 0, w); //Translates image loaded into an array of individual pixels
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
