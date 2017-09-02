package com.cousersoft.game.entity.mob;

import com.cousersoft.game.Inventory;
import com.cousersoft.game.StateHandler;
import com.cousersoft.game.entity.item.Item;
import com.cousersoft.game.entity.soil.Plant;
import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;
import com.cousersoft.game.graphics.SpriteSheet;
import com.cousersoft.game.input.Keyboard;
import com.cousersoft.game.level.Level;

public class Player extends Mob{
	
	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;
	public int keyTimer = 0;
	private StateHandler state;
	private Item temp;
	private Plant tempPlant;
	private static int xPlaceHolder;
	private static int yPlaceHolder;
	public int stamina = 100;
	public int maxStamina = 100;
	private boolean animNow = false;
	private boolean swing;
	private boolean planting;
	private int animTime = 0;
	private boolean watering;
	public boolean debugMode = false;
	public String direction;
	public boolean sleeping;
	private String plantType;
	
	//Create player at a default location
	public Player(Keyboard input) {
		this.input = input;
	}
	
	//Used for creating a player at a specific location
	public Player(int x, int y, Keyboard input, StateHandler state) {	
		this.x = x;
		this.y = y;
		this.input = input;
		this.state = state;
	}
	
	//Used for logic and controls
	public void update(Inventory inventory, Screen screen) {
		if (dir == 0) direction = "Up";
		if (dir == 1) direction = "Right";
		if (dir == 2) direction = "Down";
		if (dir == 3) direction = "Left";
		if (input.mainMenu) state.setState("Menu");
		if (input.playerScreen && keyTimer == 0) {
			keyTimer = 10;
			state.setState("Player");
		}
		if (input.inventory && keyTimer == 0) {
			keyTimer = 10;
			state.setState("Inventory");
		}
		if (keyTimer > 0) keyTimer--;
		if (keyTimer < 0) keyTimer = 0;
		if (anim < 7500) {
			anim++;
		} else {
			anim = 0;
		}
		int xa = 0;
		int ya = 0;
		if(!swing && !planting && !watering) {
			if (input.up) ya--;
			if (input.down) ya++;
			if (input.left) xa--;
			if (input.right) xa++;
		}
		if (input.sleep && keyTimer == 0) {
			sleeping = true;
			keyTimer = 10;
		}
		if (input.action) {
			if (keyTimer == 0) {
				temp = level.itemCollision(this.x, this.y);
				if (temp != null) inventory.addItem(temp, level);
				else if (level.getCrop(this.x, this.y, dir) != null) {
					tempPlant = (level.getCrop(this.x, this.y, dir));
					level.getCropItem(tempPlant, inventory);
				}else if (inventory.equipped != null && !swing) {
					if (inventory.equipped.name == "Heavy Hoe") {
						animNow = true;
						playToolAnim(inventory, screen);
					} else if (inventory.equipped.name == "Tomato Seeds") {
						plantType = "Tomato";
						planting = true;
					}else if (inventory.equipped.name == "Corn Seeds") {
						plantType = "Corn";
						planting = true;
					} else if (inventory.equipped.name == "Heavy Watering Can") {
						watering = true;
					}
				}
				keyTimer = 10;
			}
		}
		
		if(input.debugModeToggle && keyTimer == 0) {
			if (debugMode == false) {
				debugMode = true;
				keyTimer = 10;
			}
			if (debugMode == true && keyTimer == 0) {
				debugMode = false;
				keyTimer = 10;
			}
		}
		if (swing) {
			level.soilTill(x, y, dir);
		}
		
		if (watering) {
			level.soilWater(x, y, dir);
		}
		
		if (planting) {
			boolean planting;
			boolean taken = false;
			planting = level.plantSeed(x, y, dir, plantType);
			if (inventory.equipped != null && planting == true) {
				for (int x = 0; x < inventory.inventory.size(); x++) {
					if (inventory.inventory.get(x).name == inventory.equipped.name) {
						inventory.equipped = null;
						inventory.equipItem(inventory.inventory.get(x), level);
						planting = false;
						taken = true;
						break;
					}
				}
				if(!taken) {
					inventory.equipped = null;
				}
			}
		}

		if (xa != 0 || ya != 0) { 
			move(xa, ya); 
			walking = true;
		} else {
			walking = false;
		}
	}
	
	//Used for rendering player image and animation
	public void render(Screen screen, Inventory inventory) {
		if (swing) {
			if(animTime < 100) {
				animTime++;
				if (dir == 0) {
					if (animTime < 50) {
						screen.renderMob(this.x, this.y, inventory.equipped.sprite, true);
					} else {
						screen.renderMob(this.x, this.y - 16, inventory.equipped.sprite, true);
					}
				}
				if (dir == 1) {
					if (animTime < 50) {
						screen.renderMob(this.x, this.y - 32, Sprite.hoeAnimRight1, true);
						this.sprite = Sprite.playerRight1;
					} else {
						screen.renderMob(this.x + 16 , this.y - 16, Sprite.hoeAnimRight2, true);
						this.sprite = Sprite.playerRight2;
					}
				}
				if (dir == 2) {
					if (animTime < 50) {
						screen.renderMob(this.x, this.y - 32, Sprite.hoeAnimDown1, true);
						this.sprite = Sprite.playerDown1;
					} else {
						screen.renderMob(this.x, this.y, Sprite.hoeAnimDown2, true);
						this.sprite = Sprite.playerDown2;
					}
				}
				if (dir == 3) {
					if (animTime < 50) {
						screen.renderMob(this.x - 16, this.y - 32, Sprite.hoeAnimLeft1, true);
						this.sprite = Sprite.playerLeft1;
					} else {
						screen.renderMob(this.x - 32, this.y - 16, Sprite.hoeAnimLeft2, true);
						this.sprite = Sprite.playerLeft2;
					}
				}
			}
			if (animTime == 100) {
				swing = false;
				animTime = 0;
			}
		}
		
		if(planting) {
			animTime++;
			if (animTime == 100) {
				planting = false;
				animTime = 0;
			}
		}
		if(watering) {
			animTime++;
			if(animTime == 100) {
				watering = false;
				animTime = 0;
			}
		}
		if (dir == 0) {
			sprite = Sprite.player_up;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_up_1;
				} else {
					sprite = Sprite.player_up_2;
				}
			}
		}
		if (dir == 2 && !swing) {
			sprite = Sprite.player_down;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_down_1;
				} else {
					sprite = Sprite.player_down_2;
				}
			}
		}
		if (dir == 1 && !swing) {
			sprite = Sprite.player_right;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_right_1;
				} else
					sprite = Sprite.player_right;
			}
		}
		if (dir == 3 && !swing)  {
			sprite = Sprite.player_left;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.player_left_1;
				} else {
					sprite = Sprite.player_left;
				}
			}
		}
		screen.renderMob(x, y, sprite, true);
	}
	
	
	private void playToolAnim(Inventory inventory, Screen screen) {
		swing = true;	
	}
	
	

}
