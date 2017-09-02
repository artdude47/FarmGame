package com.cousersoft.game.entity.item;

import java.util.ArrayList;
import java.util.List;

import com.cousersoft.game.entity.Entity;
import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;

public class Item extends Entity{
	
	public String name = "";
	public int buyValue;
	public int sellValue;
	public Sprite sprite;
	public int quantity = 1;
	public List<Item> multiples = new ArrayList<Item>();
	public String type;
	
	public Item(int x, int y, String name, Sprite sprite, String type) {
		this.x = x;
		this.y = y;
		this.name = name;
		this.isItem = true;
		this.sprite = sprite;
		this.type = type;
	}
	
	public void render(Screen screen) {
		screen.renderMob(x, y, sprite, true);
	}
	
	

}
