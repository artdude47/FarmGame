package com.cousersoft.game.entity.soil.plants;

import com.cousersoft.game.entity.item.Item;
import com.cousersoft.game.entity.soil.Plant;
import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;

public class CornPlant extends Plant {

	public CornPlant(int x, int y, Sprite s) {
		super(x, y, s);
		this.pickFruit = new Item(0, 0, "Corn", Sprite.corn, "Food");
	}
	
	public void render(Screen screen) {
		if (age == 0) this.sprite = Sprite.cornSeed;
		if (age == 1) this.sprite = Sprite.cornSprout;
		if (age == 2) this.sprite = Sprite.corn1;
		if (age == 3) this.sprite = Sprite.corn2;
		if (age == 4) this.sprite = Sprite.corn3;
		if (age == 5) this.sprite = Sprite.cornFull;
		if (health == 0) {
			if (age == 0 || age == 1) this.sprite = Sprite.tomato1Dead;
			if (age == 2) this.sprite = Sprite.tomato2Dead;
			if (age == 3 || age == 4 || age == 5) this.sprite = Sprite.tomato3Dead;
		}
		screen.renderMob(x, y, sprite, true);
	}

}
