package com.cousersoft.game.entity.soil;

import com.cousersoft.game.entity.Entity;
import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;

public class Soil extends Entity {
	
	public boolean isPlanted = false;
	public boolean isTilled = false;
	public boolean isWatered = false;
	private Sprite sprite;
	
	public Soil(int x, int y) {
		this.x = x;
		this.y = y;
		this.isSoil = true;
	}
	
	public void render(Screen screen) {
		if (this.isTilled) sprite = Sprite.soilTilled;
		else if (this.isWatered) sprite = Sprite.soilWatered;
		else sprite = Sprite.soil;
		screen.renderMob(x, y, sprite, true);
		
	}

}
