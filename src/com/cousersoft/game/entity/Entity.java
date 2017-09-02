package com.cousersoft.game.entity;

import java.util.Random;

import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.level.Level;

public abstract class Entity {
	
	public int x;
	public int y;
	private boolean removed = false; //Has the entity been removed from the level
	public boolean isItem = false;
	public boolean isSoil = false;
	public boolean isPlant = false;
	public boolean isNPC = false;
	public Level level;
	protected final Random random = new Random();
	
	
	//Entity logic (movement ai)
	public void update() {
	}
	
	//Render entity to screen
	public void render(Screen screen) {
	}
	
	
	//Method to remove entity from the game
	public void remove() {
		removed = true;
	}
	
	public void add() {
		removed = false;
	}
	
	//Call to find if entity has been correctly removed
	public boolean isRemoved() {
		return removed;
	}
	
	public void init(Level level) {
		this.level = level;
	}
	
	

}
