package com.cousersoft.game.entity.mob;

import com.cousersoft.game.entity.Entity;
import com.cousersoft.game.graphics.Sprite;
import com.cousersoft.game.level.Level;
import com.cousersoft.game.level.tile.Tile;

public abstract class Mob extends Entity{
	
	//Mob's sprite
	protected Sprite sprite;
	//Mob's direction  0 = north 1 = east 2 = south and 3 = west
	protected int dir = 0;
	//Is mob moving (for animation)
	protected boolean moving = false;
	
	//Used to move the entity on the screen
	public void move(int xa, int ya) { 
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa > 0) dir = 1; // right
		if (xa < 0) dir = 3; // left
		if (ya > 0) dir = 2; // down
		if (ya < 0) dir = 0; // up
		//Check if mob is colliding with solid before moving
		if (!collision(xa, ya)) {
			x += xa;
			y += ya;
		}
	}
	
	//Logic updates
	public void update() {
	}
	
	public void render() {
	}
	
	//Checks if the mob is colliding with solid object if yes return true
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + xa) + c % 2 * 10 + 4) / 16;
			int yt = ((y + ya) + c / 2 * 10 + 4) / 16;
			if (level == null) getLevel();
			if (level.getTile(xt, yt).solid()) solid = true;
			if (level.getLayer2(xt, yt).solid()) solid = true;
		}
		return solid;
	} 
	
	private void getLevel() {
		
	}

}
