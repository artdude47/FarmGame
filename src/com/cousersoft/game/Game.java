package com.cousersoft.game;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import javax.swing.*;

import com.cousersoft.game.entity.item.Item;
import com.cousersoft.game.entity.mob.Dummy;
import com.cousersoft.game.entity.mob.Player;
import com.cousersoft.game.entity.particle.Emitter;
import com.cousersoft.game.entity.particle.Particle;
import com.cousersoft.game.entity.soil.Soil;
import com.cousersoft.game.graphics.text.Font;
import com.cousersoft.game.graphics.text.menuText;
import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;
import com.cousersoft.game.input.Keyboard;
import com.cousersoft.game.level.Level;
import com.cousersoft.game.level.RandomLevel;
import com.cousersoft.game.level.SpawnLevel;
import com.cousersoft.game.level.TileCoordinate;
import com.cousersoft.game.menu.InventoryMenu;
import com.cousersoft.game.menu.MainMenu;
import com.cousersoft.game.menu.PlayerMenu;

import java.awt.Graphics;
import java.awt.Rectangle;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;
	
	//Window width and height variables
	public static int width = 400;
	public static int height = width / 16 * 9;  //16:9 aspect ratio
	private int scale = 4;
	
	private Thread thread;
	private JFrame frame;
	//Variable determines if game is running, used for main loop
	private boolean running = false;
	
	//Screen instance
	private Screen screen;
	public MainMenu mainMenu;
	public StateHandler handler;
	//Keyboard instance
	private Keyboard key;
	
	public Inventory inventory;
	public Item item;
	//Create buffered image to show rendered view
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	//Array of writeable pixels
	private int[] pixels = ((DataBufferInt)image.getRaster().getDataBuffer()).getData(); 
	
	//Constructor for the level
	private Level level;
	
	//Constructor to create player
	private Player player;
	private Item test;
	
	public int numUpdates = 0;
	public int numFrames = 0;
	
	private int speed = 8;
	private boolean dayNight = false;
	private int timeSpeedSel = 0;
	//Variables for game time
	private int gameMin = 0;
	private int gameHour = 0;
	private int day = 0;
	private int year;
	private int count;
	private String gameMinStr = Integer.toString(gameMin);
	private String gameHourStr = Integer.toString(gameHour);
	private String dayName = "Monday";
	private Font timeFont;
	private int dayAlpha = 0;
	private InventoryMenu invtMenu;
	private PlayerMenu playerMenu;
	private Emitter rain;
	private Emitter explode;
	private Soil soil;
	private Font debugText;
	private boolean newDay = false;
	
	//Runs when game is started
	public Game() {
		//Define window size
		Dimension size = new Dimension(width * scale, height * scale); //Multiply by scale so that window is scaled up
		this.setPreferredSize(size);
		frame = new JFrame(); //Create the window
		screen = new Screen(width, height); //Create the screen object the same size as the window
		handler = new StateHandler();
		key = new Keyboard(); //Create a keyboard object to keep track of key event
		level = new SpawnLevel("/textures/level2.png", "/textures/level2layer2.png");
		TileCoordinate PlayerSpawn = new TileCoordinate(2, 4);
		player = new Player(PlayerSpawn.x(), PlayerSpawn.y(), key, handler);
		player.init(level);
		debugText = new Font();
		debugText.initChars("Small");
		for (int q = 0; q < 5; q++) {
			for (int g = 0; g < 5; g++) {
				soil = new Soil(64 + g * 16, 32 + q * 16);
				soil.init(level);
				level.add(soil);
			}
		}
		
		for (int q = 0; q < 5; q++) {
			for (int g = 0; g < 8; g++) {
				soil = new Soil((38 * 16) + g * 16, 32 + q * 16);
				soil.init(level);
				level.add(soil);
			}
		}
		test = new Item(16,16,"Taco", Sprite.taco, "Food");
		test.init(level);
		level.add(test);
		test = new Item(32, 16,"Heavy Hoe",Sprite.hoe, "Tool");
		test.init(level);
		level.add(test);
		test = new Item(48, 16,"Heavy Watering Can", Sprite.wateringCan, "Tool");
		test.init(level);
		level.add(test);
		for (int i = 0; i < 20; i++) {
			for (int g = 0; g < 5; g++) {
				if (i < 10) {
					test = new Item(i * 16 + (16 * 16),g * 16 + (7 * 16),"Tomato Seeds", Sprite.tomatoSeedBag, "Tool");
					test.init(level);
					level.add(test);
				} else {
					test = new Item(i * 16 + (16 * 16), g * 16 + (7 * 16), "Corn Seeds", Sprite.cornSeedBag, "Tool");
					test.init(level);
					level.add(test);
				}
			}
		}
		test = new Item(64 ,16,"LASER SWORD", Sprite.sword, "Tool");
		test.init(level);
		level.add(test);
		rain = new Emitter(0, 0, "Rain", 0);
		rain.init(level);
		level.add(rain);
		explode = new Emitter(150, 60, "Explode", 0);
		explode.init(level);
		level.add(explode);
		inventory = new Inventory();
		mainMenu = new MainMenu(screen);
		invtMenu = new InventoryMenu();
		playerMenu = new PlayerMenu();
		timeFont = new Font();
		timeFont.initChars("Small");
		mainMenu.init();
		level.add(new Dummy(1,10));
		level.add(new Dummy(40,10));
		addKeyListener(key); //Create key listener for the keyboard
	}
	

	public synchronized void start() {	
		running = true; //Game is now started
		thread = new Thread(this, "Display");
		thread.start();
	}
	
	//Method to stop the thread
	public synchronized void stop() {
		running = false; //Game has ended
		try {
			thread.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Once the thread is started the run method will be run
	public void run() {
		//Create game timer for keeping track of framerate and limiting updates to 60FPS
		long lastTime = System.nanoTime(); //Returns current time in nano seconds
		long timer = System.currentTimeMillis(); //Returns current time in mili seconds
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0; //Frame per second
		int updates = 0; //Updates per second
		requestFocus(); //Make game window the active window
		
		
		//Main loop
		while(running) {
			long now = System.nanoTime(); //updates what current time is
			delta += (now - lastTime) / ns; //Figure out how long it's been since program started
			lastTime = now; //Update the last time for next cycle
			while(delta >= 1) {
					if (handler.getState() == "Game") {
						count++;
					}
					update(); //Only call the update method at 60fps
					updates++; //Increment updates per second
					delta--; //Put delta back below 1
			}

			//Update game Time
			if (count > speed) {
				if (gameHour > 23) {
					day++;
					newDay = true;
					if (newDay == true) {
						level.newDay();
						newDay = false;
					}
					gameHour = 0;
					gameHourStr = Integer.toString(gameHour);
				}
				if (gameMin > 58) {
					gameHour++;
					gameMin = 0;
					gameHourStr = Integer.toString(gameHour);
				}
				gameMin++;
				gameMinStr = Integer.toString(gameMin);
				count = 0;
			}
			if (day > 6) day = 0;
			if (day == 0) dayName = "Monday";
			if (day == 1) dayName = "Tuesday";
			if (day == 2) dayName = "Wednesday";
			if (day == 3) dayName = "Thursday";
			if (day == 4) dayName = "Friday";
			if (day == 5) dayName = "Saturday";
			if (day == 6) dayName = "Sunday";

				render(); //Rendering doesn't need to be limited to a certain fps, needs to happen as fast as possible
				frames++; //increment frames per second		
			if (System.currentTimeMillis() - timer > 1000) { //if its been more than a second reset fps and ups clocks
				timer += 1000;
				numFrames = frames;
				numUpdates = updates;
				updates = 0;
				frames = 0;
			}			
		}
	}
	
	
	public void update() {
		key.update();
		if (handler.getState() == "Game") {
			player.update(inventory, screen);
			level.update();
		}
		if (handler.getState() == "Menu") {
			mainMenu.update(key, handler, this);
		}
		
		if (handler.getState() == "Inventory") {
			invtMenu.update(key, handler, inventory, level, player);
		}
		
		if (handler.getState() == "Player") {
			playerMenu.update(player, inventory, screen, key, handler, level);
		}
	}
	
	public void render() {
		//RENDER GRAPHICS HERE
		BufferStrategy bs = getBufferStrategy();
		//IF no buffer strategy has been created yet
		if (bs == null) {
			createBufferStrategy(3); //Triple buffering
			return;
		}
		
		screen.clear();
		if (handler.getState() == "Game") {
			int xScroll = player.x - screen.width / 2;
			int yScroll = player.y - screen.height / 2;
			level.render(xScroll, yScroll, screen);
			player.render(screen, inventory);
			level.layer2Render(xScroll, yScroll, screen);
			screen.renderMob(0, 0, Sprite.textBox, false);
			if (gameMin < 10 && gameHour < 10) timeFont.render(screen, 8, "0" + gameHourStr + ":" + "0" + gameMinStr + " " + dayName, 2, 2, 0, false);
			else if (gameMin >= 10 && gameHour < 10) timeFont.render(screen, 8, "0" + gameHourStr + ":"  + gameMinStr + " " + dayName, 2, 2, 0, false);
			else if (gameMin < 10 && gameHour >= 10) timeFont.render(screen, 8, gameHourStr + ":" + "0" + gameMinStr + " " + dayName, 2, 2, 0, false);
			else timeFont.render(screen, 8, gameHourStr + ":" + gameMinStr + " " + dayName, 2, 2, 0, false);
			
			if (gameHour == 18) dayAlpha = 50;
			else if (gameHour == 19) dayAlpha = 100;
			else if (gameHour > 19 || gameHour < 6) dayAlpha = 150;
			else if (gameHour == 6) dayAlpha = 100;
			else if (gameHour == 7) dayAlpha = 50;
			else dayAlpha = 0; 
			
			if (player.sleeping == true) {
				sleepFunc();
				player.sleeping = false;
			}
			if (player.debugMode == true) {
				debugText.render(screen, 8, "X: " + Integer.toString(player.x) + " , Y: " + Integer.toString(player.y)
				+ "\nX Coord: " + Integer.toString(player.x / 16) + " , Y Coord: " + Integer.toString(player.y / 16)
				+ "\nUPS: " + numUpdates + " FPS: " + numFrames
				+ "\nPlayer Direction: " + player.direction
				+ "\nEntity Count " + level.getCount(), 5, 30, 0, false);
			}
		}
		
		if (handler.getState() == "Menu") {
			mainMenu.render(screen);
		}
		
		if (handler.getState() == "Inventory") {
			invtMenu.render(inventory, screen);
		}
		
		if (handler.getState() == "Player") {
			playerMenu.render(player, inventory, screen);
		}
		
			//Set game's pixel array equal to the screen's
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}
		
		
		
		//Create link between graphics and buffer
		Graphics g = bs.getDrawGraphics();
		//Draw graphics to the screen
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		Color myCol = new Color(0,0,0,dayAlpha);
		g.setColor(myCol);
		if (dayNight) {
			if (dayAlpha > 0) g.fillRect((player.x - screen.getXOffset()) - 200, (player.y - screen.getYOffset()) - 110, getWidth(), getHeight());
		}
		g.dispose(); //Dispose of current graphcis
		bs.show(); //FLips buffer
	}
	
	public static void main(String args[]) {
		//MAIN METHOD PROGRAM STARTS HERE
		Game game = new Game(); //Create an instance of game object
		game.frame.setResizable(false); //window cannot be resized
		game.frame.add(game); //Add the game canvas to the frame
		game.frame.setTitle("Art's Game Version: 00.00.04");
		game.frame.pack(); //Size to the preferred size
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);//Centers window
		game.frame.setVisible(true);
		
		//Start the game
		game.start();
	}
	
	public void Resize(int size, int rain, int explode, int dayNightSel, int timeSpeed, int stressSel) {
		this.scale = size;
		Dimension newSize = new Dimension(width * this.scale, height * this.scale);
		this.setPreferredSize(newSize);
		this.frame.setSize(newSize);
		this.frame.pack();
		this.frame.setLocationRelativeTo(null);
		int numEnt = 0;
		
		if (rain == 0) level.rain = false;
		if (rain == 1) level.rain = true;
		if (explode == 0) level.explode = false;
		if (explode == 1) level.explode = true;
		if (dayNightSel == 0) this.dayNight = false;
		if (dayNightSel == 1) this.dayNight = true;
		if (timeSpeed == 0) speed = 0;
		if (timeSpeed == 1) speed = 8;
		if (stressSel == 1) numEnt = 1000;
		if (stressSel == 2) numEnt = 10000;
		if (stressSel == 3) numEnt = 100000;
		if (stressSel != 0) level.stressTest(numEnt);
	}
	
	private void sleepFunc() {
		 day++;
		 level.newDay();
	}
	

}
