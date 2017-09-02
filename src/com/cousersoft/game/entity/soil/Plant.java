package com.cousersoft.game.entity.soil;

import com.cousersoft.game.entity.Entity;
import com.cousersoft.game.entity.item.Item;
import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;

public class Plant extends Entity{

	protected Sprite sprite;
	public int age = 0;
	public int maxAge = 5;
	public int pickAge = 3;
	public int health = 2;
	public Item pickFruit;
	public boolean isWatered = false;
	public boolean isDead = false;
	
	
	
	public Plant(int x, int y, Sprite s) {
		this.x = x;
		this.y = y;
		sprite = s;
		this.isPlant = true;
	}
	
	
	public void render(Screen screen) {
		if (age == 0) this.sprite = Sprite.tomatoSeed;
		if (age == 1) this.sprite = Sprite.tomatoSprout;
		if (age == 2) this.sprite = Sprite.tomato1;
		if (age == 3) this.sprite = Sprite.tomato2;
		if (age == 4) this.sprite = Sprite.tomato3;
		if (age == 5) this.sprite = Sprite.tomatoFull;
		if (health == 0) {
			if (age == 0 || age == 1) this.sprite = Sprite.tomato1Dead;
			if (age == 2) this.sprite = Sprite.tomato2Dead;
			if (age == 3 || age == 4 || age == 5) this.sprite = Sprite.tomato3Dead;
		}
		screen.renderMob(x, y, sprite, true);
	}
}
