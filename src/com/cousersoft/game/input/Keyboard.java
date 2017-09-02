package com.cousersoft.game.input;

import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class Keyboard implements KeyListener{
	//Create an array of booleans to represent each key on the keyboard
	private boolean[] keys = new boolean[120];
	public boolean up; //Keeps track of the up and w key...IF true one is pressed
	public boolean down; //Keeps track of the down and s key...
	public boolean left; //keeps track of the left and a key...
	public boolean right; //keeps track of the right and d key...
	public boolean action;
	public boolean playerScreen;
	public boolean mainMenu;
	public boolean inventory;
	public boolean grow;
	public boolean shrink;
	public boolean debugModeToggle;
	public boolean sleep;
	
	//Method to update if keys are pressed
	public void update() {
		//Variable for up is equal to both up key and w...either can be used to walk
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S]; //Down can use down and s
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A]; //Left can use left and a
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D]; //Right can use Right and d
		action = keys[KeyEvent.VK_SPACE];
		mainMenu = keys[KeyEvent.VK_ESCAPE];
		inventory = keys[KeyEvent.VK_I];
		playerScreen = keys[KeyEvent.VK_E];
		grow = keys[KeyEvent.VK_G];
		shrink = keys[KeyEvent.VK_F];
		sleep = keys[KeyEvent.VK_H];
		debugModeToggle = keys[KeyEvent.VK_R];
	}


	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true; //If a key is pressed then that key's portion of the array is set to true
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false; //Once the key is released it's value in the array is false
	}


	public void keyTyped(KeyEvent e) {		
	}
	

}
