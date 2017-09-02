package com.cousersoft.game.level;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.cousersoft.game.entity.mob.Dummy;
import com.cousersoft.game.level.tile.Tile;

public class SpawnLevel extends Level {
	
	
	
	public SpawnLevel(String path, String path2) {
		super(path, path2);
	}
	
	
	protected void generateLevel() {
	}
	
	protected void loadLevel(String path, String path2) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file");
		}
		
		//Get second tile layer;
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path2));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			layer2 = new int[w * h];
			image.getRGB(0, 0, w, h, layer2, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("COULDN'T LOAD SECOND LAYER");
		}
	}
	
}

