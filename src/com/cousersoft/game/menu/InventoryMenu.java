package com.cousersoft.game.menu;

import com.cousersoft.game.Inventory;
import com.cousersoft.game.StateHandler;
import com.cousersoft.game.entity.mob.Player;
import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;
import com.cousersoft.game.graphics.text.Font;
import com.cousersoft.game.input.Keyboard;
import com.cousersoft.game.level.Level;

public class InventoryMenu {
	
	private Font title;
	private Font itemName;
	private Font itemQuantity;
	private Inventory inventory;
	private int keyWait = 10;
	private int selected = 0;
	private int maxSel = 0;
	private int yOffset = 0;
	private int maxOnScreen = 7;
	private int yScroll = 0;
	private int numScrolled = 0;
	private String internalState = "Main";
	private int subSel = 0;
	private ActionMenu popup;
	private int pointX = 5;
	
	
	public void render(Inventory inventory, Screen screen) {
		screen.renderMob(0, 0, Sprite.background, false);
		title = new Font();
		title.initChars("Small");
		itemName = new Font();
		itemName.initChars("Small");
		itemQuantity = new Font();
		itemQuantity.initChars("Small");
		maxSel = inventory.inventory.size() - 1;
		if (maxSel == -1) maxSel = 0;
		for (int x = 0; x < inventory.inventory.size(); x++) {
			yOffset = x * 17 + 17;
			screen.renderMob(0, yOffset - yScroll, Sprite.invSlot, false);
			screen.renderMob(15, yOffset - yScroll, inventory.inventory.get(x).sprite, false);
			itemName.render(screen, 8, inventory.inventory.get(x).name, 50, yOffset + 3 - yScroll, 0, false);
			if (inventory.inventory.get(x).quantity > 1) itemQuantity.render(screen, 8, "x" + Integer.toString(inventory.inventory.get(x).quantity), 250, yOffset + 3 - yScroll, 0, false);
		}
		screen.renderMob(0, 0, Sprite.invSlot, false);
		title.render(screen, 8, "INVENTORY", 170, 7, 0, false);
		if (inventory.inventory.size() > 0) screen.renderMob(pointX, 17 * selected + 20, Sprite.select, false);
		
		
	
		if (selected > maxOnScreen) {
			selected--;
			numScrolled++;
			yScroll += 17;
		}
		
		if (selected < 0) {
			selected++;
			numScrolled--;
			yScroll -= 17;
		}
		
		if(selected == -1) selected = 0;
		if(selected > maxSel) selected = maxSel;
		
		//IF an item has been selected
		if (internalState == "Selected") {
			popup = new ActionMenu(inventory.inventory.get(selected + numScrolled), screen);
			if (selected < 6) {
				screen.renderMob(250, selected * 16 + 22, Sprite.actionMenu, false);
				popup.action.render(screen, 8, popup.actionText, 261, selected * 16 + 24, 0, false);
				popup.action1.render(screen, 8, popup.action1Text, 262, (selected + 1) * 16 + 24, 0, false);
				popup.action2.render(screen, 8, popup.action2Text, 262, (selected + 2) * 16 + 24,  0, false);
				screen.renderMob(252, (selected + subSel) * 16 + 24, Sprite.select, false);
			} else {
				screen.renderMob(250, selected * 16, Sprite.actionMenu, false);
				popup.action.render(screen, 8, popup.actionText, 261, selected * 16 + 2 , 0, false);
				popup.action1.render(screen, 8, popup.action1Text, 262, (selected + 1) * 16, 0, false);
				popup.action2.render(screen, 8, popup.action2Text, 262, (selected + 2) * 16,  0, false);
				screen.renderMob(252, (selected + subSel) * 16 + 2, Sprite.select, false);

			}
		}

	}
	
	public void update(Keyboard input, StateHandler state, Inventory inventory, Level level, Player player) {
		keyWait--;
		if (keyWait < 0) keyWait = 0;
		if (internalState == "Main") {
			if (input.inventory && keyWait == 0) {
				keyWait = 10;
				state.setState("Game");
			}
			if (input.down && keyWait == 0 && selected + numScrolled < maxSel) {
				selected++;
				keyWait = 10;
			}
			
			if (input.up && keyWait == 0 && selected + numScrolled > 0) {
				selected--;
				keyWait = 10;
			}
			if (numScrolled < 0) numScrolled = 0;
			if (selected == 0 && numScrolled == 0) yScroll = 0;
			
			if (input.action && keyWait == 0 && inventory.inventory.size() > 0) {
				keyWait = 10;
				internalState = "Selected";
			}
		}
		if (internalState == "Selected") {
			if (input.action && keyWait == 0) {
				if (subSel == 2) {
					keyWait = 10;
					subSel = 0;
					internalState = "Main";
				}
				if (subSel == 1) {
					inventory.dropItem(inventory.inventory.get(selected + numScrolled), player.x, player.y, level);
					keyWait = 10;
					subSel = 0;
					internalState = "Main";
				}
				if (subSel == 0 && keyWait == 0) {
					if (popup.actionText == "Eat") {
						inventory.eatItem(inventory.inventory.get(selected + numScrolled), level);
					}
					if (popup.actionText == "Equip") {
						inventory.equipItem(inventory.inventory.get(selected + numScrolled), level);
					}
					keyWait = 10;
					subSel = 0;
					internalState = "Main";
				}
			}
			
			if (input.down && keyWait == 0 && subSel < 2) {
				keyWait = 10;
				subSel++;
			}
			if (input.up && keyWait == 0 && subSel > 0) {
				keyWait = 10;
				subSel--;
			}
		}
	}
}
