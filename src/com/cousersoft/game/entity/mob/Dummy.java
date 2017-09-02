package com.cousersoft.game.entity.mob;

import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;
import com.cousersoft.game.level.Level;

public class Dummy extends Mob{
	private boolean walking = false;
	private int anim = 0;
	private int dir = 2;
	private int time = 0;
	private int xa = 1, ya = 0;
		
	public Dummy(int x, int y) {
		this.x = x * 16;
		this.y = y * 16;
		sprite = Sprite.npc_down;
		this.isNPC = true;
	}
	
	
	public void update() {
		time++;
		if (time % 60 == 0) {
			if (ya == 0) {
				xa = random.nextInt(6) - 1;
				if (xa <= 2) xa = 0;
				if (xa == 3) xa = -1;
				if (xa == 4) xa = 1;
			}
			if (xa == 0) {
				ya = random.nextInt(6) - 1;
				if (ya <= 2) ya = 0;
				if (ya == 3) ya = -1;
				if (ya == 4) ya = 1;
			}
		}
		if (xa > 0) dir = 1; // right
		if (xa < 0) dir = 3; // left
		if (ya > 0) dir = 2; // down
		if (ya < 0) dir = 0; // up
		if (anim < 7500) {
			anim++;
		} else {
			anim = 0;
		}
		if (xa != 0 || ya != 0) { 
			if (!level.npcCol(x, y, dir)) {
				move(xa, ya); 
				walking = true;
			}
		} else {
			walking = false;
		} 
	}
	
	
	public void render(Screen screen){
		if (dir == 0) {
			sprite = Sprite.npc_up;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.npc_up_1;
				} else {
					sprite = Sprite.npc_up_2;
				}
			}
		}
		if (dir == 2) {
			sprite = Sprite.npc_down;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.npc_down_1;
				} else {
					sprite = Sprite.npc_down_2;
				}
			}
		}
		if (dir == 1) {
			sprite = Sprite.npc_right;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.npc_right_1;
				} else
					sprite = Sprite.npc_right;
			}
		}
		if (dir == 3)  {
			sprite = Sprite.npc_left;
			if (walking) {
				if (anim % 20 > 10) {
					sprite = Sprite.npc_left_1;
				} else {
					sprite = Sprite.npc_left;
				}
			}
		}
		screen.renderMob(x, y, sprite, true);
	}

}
