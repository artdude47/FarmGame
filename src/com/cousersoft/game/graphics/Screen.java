package com.cousersoft.game.graphics;

import java.util.Random;

import com.cousersoft.game.entity.mob.Player;
import com.cousersoft.game.level.tile.Tile;

public class Screen {
	public int width;
	public int height;
	public int[] pixels; //Array to hold every pixel on the screen
	public final int MAP_SIZE = 64; //Map size in tiles ( * 16 to get total number of pixels)
	public final int MAP_SIZE_MASK = MAP_SIZE -1;
	public int xOffset;
	public int yOffset;
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE]; // the array size should be map size by map size. 64 tiles along x axis and y axis
	private Random random = new Random();
	public int red;
	public int blue;
	public int green;
	
	//Initializer for screen object
	public Screen(int width, int height) { //All screens require a width and a height
		//Set the screen's width and height to the variables that were passed into the method
		this.width = width;
		this.height = height;
		pixels = new int[width * height]; //pixels array should be as big as the window
		
		//Cycle through each pixel and set them to black
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	//Method to render tiles to the screen
	public void renderTile(int xp, int yp, Tile tile) {
		//Subtract screen's offset from tiles posistion
		xp -= xOffset;
		yp -= yOffset;
		//cycle through each tile
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				//Only render tils visible on map
				if (xa  < -16 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = tile.sprite.pixels[x + y * 16];
				if (col != 0xffff00ff) {
					pixels[xa + ya * width] = col;	
				}
			}
		}
	}
	
	//Method to render player to the screen
	public void renderMob(int xp, int yp, Sprite sprite, boolean fixed) {
		//Subtract screen's offset from tiles posistion
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa  < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * sprite.SIZE];
				if (col != 0xffff00ff) {
					pixels[xa + ya * width] = col;
				}	
			}
		}
	}
	
	public void renderParticle(int xp, int yp, Sprite sprite, int color, boolean fixed) {
		int tempCol;
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				if (color == 0) tempCol = sprite.pixels[x + y * sprite.SIZE];
				else tempCol = color;
				if (tempCol != 0xffff00ff) {
					pixels[xa + ya * width] = tempCol;
				}
			}
		}
	}
	
	public void renderText(int xp, int yp, Sprite sprite, int textColor, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		
		for (int y = 0; y < 16; y++) {
			int ya = y + yp;
			for (int x = 0; x < 16; x++) {
				int xa = x + xp;
				if (xa < -16 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * 16];
				if (col != 0xffff00ff) {
					if (textColor == 0) {
						pixels[xa + ya * width] = col;
					} else {
						pixels[xa + ya * width] = textColor;
					}
				}
			}
		}
	}
	public int getXOffset() {
		return xOffset;
	}
	public int getYOffset() {
		return yOffset;
	}
	public void renderSmallText(int xp, int yp, Sprite sprite, int textColor, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < 8; y++) {
			int ya = y + yp;
			for (int x = 0; x < 8; x++) {
				int xa = x + xp;
				if (xa < -8 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[x + y * 8];
				if (col != 0xffff00ff) {
					if (textColor == 0) {
						pixels[xa + ya * width] = col;
					} else {
						pixels[xa + ya * width] = textColor;
					}
				}
			}
		}
	}
	
			
	//Method to clear the screen
	public void clear() {
		//Cycle through each pixel and set it to black
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}
	
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public int getRGB(String hex) {
		if (hex.length() > 1) {
			String temp;
			int colVal;
			temp = hex.substring(2, 4);
			red = Integer.parseInt(temp, 16);
			temp = hex.substring(4, 6);
			green = Integer.parseInt(temp, 16);
			temp = hex.substring(6, 8);
			blue = Integer.parseInt(temp, 16);
			colVal = darkenTile(red, green, blue);
			return colVal;
		} else {
			return 0;
		}
	}
	
	public int darkenTile(int r, int g, int b) {
		int col = 0;
		String red;
		String green;
		String blue;
		for (int i = 0; i < 20; i++) {
			if (r > 0) r--;
			if (g > 0) g--;
			if (b > 0) b--;
		}
		if (r == 0) red = "00";
		else red = Integer.toString(r);
		if (g == 0) green = "00";
		else green = Integer.toString(g);
		if (b == 0) blue = "00";
		else blue = Integer.toString(b);
		String hex = red + green + blue;
		col = Integer.parseInt(hex, 16);
		return col;
		
	}
	
}
