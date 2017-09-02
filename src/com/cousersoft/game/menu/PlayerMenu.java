package com.cousersoft.game.menu;

import com.cousersoft.game.Inventory;
import com.cousersoft.game.StateHandler;
import com.cousersoft.game.entity.mob.Player;
import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;
import com.cousersoft.game.graphics.text.Font;
import com.cousersoft.game.input.Keyboard;
import com.cousersoft.game.level.Level;

public class PlayerMenu {
	
	private Font font;
	private int keyWait = 10;
	
	public void update(Player player, Inventory inventory, Screen screen, Keyboard key, StateHandler state, Level level) {
		if (keyWait > 0) keyWait--;
		if (key.action && keyWait == 0 && inventory.equipped != null) { 
			keyWait = 10;
			inventory.addItem(inventory.equipped, level);
			inventory.equipped = null;
		}
		
		if (key.playerScreen && keyWait == 0) {
			keyWait = 10;
			state.setState("Game");
		}
	}
	
	public void render(Player player, Inventory inventory, Screen screen) {
		font = new Font();
		font.initChars("Small");
		screen.renderMob(0, 0, Sprite.background, false);
		font.render(screen, 8, "Stamina: " + player.stamina + " Max: " + player.maxStamina, 10, 10, 0, false);
		font.render(screen, 8, "Currently Equipped: ", 10, 30, 0, false);
		if (inventory.equipped != null) {
			font.render(screen, 8, inventory.equipped.name, 180, 30, 0, false);
			screen.renderMob(330, 25, inventory.equipped.sprite, false);
		}
	}

}
