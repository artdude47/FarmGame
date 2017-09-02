package com.cousersoft.game.menu;

import com.cousersoft.game.Game;
import com.cousersoft.game.StateHandler;
import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;
import com.cousersoft.game.graphics.text.Font;
import com.cousersoft.game.graphics.text.menuText;
import com.cousersoft.game.input.Keyboard;

public class MainMenu {
	
	
	private Font title;
	private Font aboutText;
	private menuText play;
	private menuText newGame;
	private menuText loadGame;
	private menuText options;
	private menuText about;
	private menuText back;
	private String internalState = "Main";
	private int currentSel = 0;
	private int actionWait = 0;
	private String selected = "Small";
	private int numSelect = 2;
	private int numScroll;
	private int rainSelNum;
	private String rainSel = "No";
	private int gameSize;
	private String explodeSel = "No";
	private int explodeSelNum;
	private String daySel = "No";
	private int daySelNum;
	private String timeSpeed = "Fast";
	private int timeSpeedNum = 1;
	private String stressSel = "No";
	private int stressSelNum = 0;
	
	public MainMenu(Screen screen) {
	}
	public void init() {
		title = new Font();
		title.initChars("large");
		aboutText = new Font();
		aboutText.initChars("Small");
		play = new menuText("PLAY", 200, 70, true);
		newGame = new menuText("NEW GAME", 200, 90, true);
		loadGame = new menuText("LOAD GAME", 200, 110, true);
		options = new menuText("OPTIONS", 200, 130, true);
		about = new menuText("HELP", 200, 150, true);
		back = new menuText("BACK", 200, 200, true);
	}
	public void render(Screen screen) {
		screen.renderMob(0, 0, Sprite.background, false);
		if(internalState == "Main") {
			title.render(screen, 16, "\"GAME TITLE", 120, 50, 0, false);
			play.render(screen);
			newGame.render(screen);
			loadGame.render(screen);
			options.render(screen);
			about.render(screen);
		}
		
		if(internalState == "About") {
			aboutText.render(screen, 8, "Press the \rSpacebar\' key to preform actions such as \npicking up "
					+ "and using items.\nPress the \rI\' key to bring up your inventory. \nPress the \rE\' key to bring up "
					+ "player equipment \nand stats. \nPress \rescape\' at any time to bring up the main \nmenu.\n\"Report any bugs to"
					+ "your friend Art, have fun!\n"
					+ "\'Change log: added \"Hoe\',\"Watering can\', and \n\"Tomato seeds\'.\n"
					+ "added basic animation for swinging the hoe.\n"
					+ "added ability to till soil with hoe.\n"
					+ "added ability to plant seeds in tilled soil.\n"
					+ "added ability to water plants."
					+ "\nadded ability to grow polants with \rG\' key, Shrink \n     with \rF\'key and hurt with \rH\' key"
					+ "\nGraphic update for small text and tacos."
					+ "\nMap update."
					+ "\nAdded debug menu. Hit \rR\' key", 5, 5, 0, false);
			back.render(screen);
		}
		
		if(internalState == "Options") {
			aboutText.render(screen, 8, "Please select a screen size!", 18, 10, 0, false);
			aboutText.render(screen, 8, selected, 315, 10, 0, false);
			aboutText.render(screen, 8, "Rain?", 18, 20, 0, false);
			aboutText.render(screen, 8, rainSel, 315, 20, 0, false);
			aboutText.render(screen, 8, "Explosion?", 18, 30, 0, false);
			aboutText.render(screen, 8, explodeSel, 315, 30, 0, false);
			aboutText.render(screen, 8, "Day night cycle?", 18, 40, 0, false);
			aboutText.render(screen, 8, daySel, 315, 40, 0, false);
			aboutText.render(screen, 8, "Time Speed: ", 18, 50, 0, false);
			aboutText.render(screen, 8, timeSpeed, 315, 50, 0, false);
		//	aboutText.render(screen, 8, "Stress Test?", 18, 60, 0, false);
		//	aboutText.render(screen, 8, stressSel, 315, 60, 0, false);
		//	aboutText.render(screen, 8, "Note: to turn off stress test\n you will have to reset game.", 18, 70, 0, false);
			screen.renderMob(300, 10, Sprite.endSelect, false);
			screen.renderMob(375, 10, Sprite.select, false);
			screen.renderMob(300, 20, Sprite.endSelect, false);
			screen.renderMob(375, 20, Sprite.select, false);
			screen.renderMob(300, 30, Sprite.endSelect, false);
			screen.renderMob(375, 30, Sprite.select, false);
			screen.renderMob(300, 40, Sprite.endSelect, false);
			screen.renderMob(375, 40, Sprite.select, false);
			screen.renderMob(300, 50, Sprite.endSelect, false);
			screen.renderMob(375, 50, Sprite.select, false);
		//	screen.renderMob(300, 60, Sprite.endSelect, false);
		//	screen.renderMob(375, 60, Sprite.select, false);
			screen.renderMob(4, numScroll * 10 + 10, Sprite.select, false);
			back.render(screen);
		}
	}
	
	public void update(Keyboard input, StateHandler handler, Game game) {
		if (actionWait > 0) actionWait--;
		if (internalState == "Main") {
			if (currentSel < 0) currentSel = 4;
			if (currentSel > 4) currentSel = 0;
			if (input.down && actionWait == 0) {
				currentSel++;
				actionWait = 10;
			}
			if (input.up && actionWait == 0) {
				currentSel--;
				actionWait = 10;
			}
			if (currentSel == 0) play.isSelected = true;
			else play.isSelected = false;
			if (currentSel == 1) newGame.isSelected = true;
			else newGame.isSelected = false;
			if (currentSel == 2) loadGame.isSelected = true;
			else loadGame.isSelected = false;
			if (currentSel == 3) options.isSelected = true;
			else options.isSelected = false;
			if (currentSel == 4) about.isSelected = true;
			else about.isSelected = false;
			
			if (input.action && actionWait == 0) {
				actionWait = 10;
				if (currentSel == 0) handler.setState("Game");
				if (currentSel == 3) internalState = "Options";
				if (currentSel == 4) internalState = "About";
			}
		}
		
		if (internalState == "Options" || internalState == "About") {
			back.isSelected = true;
			if (input.action && actionWait ==0) {
				actionWait = 10;
				if (internalState == "Options") game.Resize(gameSize, rainSelNum, explodeSelNum, daySelNum, timeSpeedNum, stressSelNum);
				internalState = "Main";
			}
			if (input.right && actionWait == 0) {
				if(numScroll == 0) {
					numSelect++;
					actionWait = 10;
				}
				if (numScroll ==1 ) {
					rainSelNum++;
					actionWait = 10;
				}
				if (numScroll == 2) {
					explodeSelNum++;
					actionWait = 10;
				}
				if (numScroll == 3) {
					daySelNum++;
					actionWait = 10;
				}
				if (numScroll == 4) {
					timeSpeedNum++;
					actionWait = 10;
				}
				if (numScroll == 5) {
					stressSelNum++;
					actionWait = 10;
				}
				if (explodeSelNum > 1) explodeSelNum = 0;
				if (rainSelNum > 1) rainSelNum = 0;
				if (numSelect > 2) numSelect = 0;
				if (daySelNum > 1) daySelNum = 0;
				if (timeSpeedNum > 1) timeSpeedNum = 0;
				if (stressSelNum > 3) stressSelNum =0;
			}
			if (input.left && actionWait == 0) {
				if(numScroll == 0) {
					numSelect--;
					actionWait = 10;
				}
				if(numScroll == 1) {
					rainSelNum--;
					actionWait = 10;
				}
				if (numScroll ==2) {
					explodeSelNum--;
					actionWait = 10;
				}
				if (numScroll == 3) {
					daySelNum--;
					actionWait = 10;
				}
				if (numScroll == 4) {
					timeSpeedNum--;
					actionWait = 10;
				}
				if (numScroll == 5) {
					stressSelNum--;
					actionWait = 10;
				}
				if (explodeSelNum < 0) explodeSelNum = 1;
				if (rainSelNum < 0) rainSelNum = 1;
				if (numSelect < 0) numSelect = 2;
				if (daySelNum < 0) daySelNum = 1;
				if (timeSpeedNum < 0) timeSpeedNum = 1;
				if (stressSelNum < 0) stressSelNum = 3;
			}
			if (input.up && actionWait == 0) {
				numScroll--;
				if (numScroll < 0) numScroll = 04;
				actionWait = 10;
			}
			if (input.down && actionWait == 0) {
				numScroll++;
				if (numScroll > 4) numScroll = 0;
				actionWait = 10;
			}
			if (rainSelNum == 0)  rainSel = "No";
			if (rainSelNum == 1) rainSel = "Yes";
			if (explodeSelNum == 0) explodeSel = "No";
			if (explodeSelNum == 1) explodeSel = "Yes";
			if (daySelNum == 0) daySel = "No";
			if (daySelNum == 1) daySel = "Yes";
			if (timeSpeedNum == 0) timeSpeed = "Fast";
			if (timeSpeedNum == 1) timeSpeed = "Normal";
			if (stressSelNum == 0) stressSel = "No";
			if (stressSelNum == 1) stressSel = "1k";
			if (stressSelNum == 2) stressSel = "10k";
			if (stressSelNum == 3) stressSel = "100k";
			if (numSelect == 0) {
				gameSize = 2;
				selected = "Small";
			}
			if (numSelect == 1) {
				gameSize = 3;
				selected = "Medium";
			}
			if (numSelect == 2) {
				gameSize = 4;
				selected = "Large";
			}
		}
		
	}
	

}
