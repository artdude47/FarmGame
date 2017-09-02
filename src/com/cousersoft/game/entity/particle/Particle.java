package com.cousersoft.game.entity.particle;

import com.cousersoft.game.entity.Entity;
import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;
import com.cousersoft.game.level.Level;

public class Particle extends Entity{
	
	private int life;
	private double xMove;
	private double yMove;
	private int color;
	private int xDir;
	private int yDir;
	private int speed;
	private int spriteNum;
	private Sprite sprite;
	private boolean fixed = false;
	private int tempCol;
	
	public Particle(int x, int y, int color, Level level, String type) {
		this.x = x;
		this.y = y;
		if (type == "Explode") {
			this.life = random.nextInt(60);
			this.color = color;
			tempCol = random.nextInt(5);
			if (tempCol == 0) this.color = color;
			if (tempCol == 1) this.color = 0xffff7700;
			if (tempCol == 2) this.color = 0xff953300;
			if (tempCol == 3) this.color = 0xffcc8800;
			if (tempCol == 4) this.color = 0xffff8248;
			speed = random.nextInt(9) + 1;
			xMove = random.nextDouble();
			yMove = random.nextDouble();
			xDir = random.nextInt(2);
			yDir = random.nextInt(2);
			if (xDir == 1) xMove = xMove * -1;
			if (yDir == 1) yMove = yMove * -1;
			xMove *= speed;
			yMove *= speed;
			sprite = Sprite.particle;
			fixed = false;
		}
		if (type == "Rain") {
			life = 1000;
			spriteNum = random.nextInt(3) + 1;
			if (spriteNum == 1) sprite = Sprite.rain1;
			if (spriteNum == 2) sprite = Sprite.rain2;
			if (spriteNum == 2) sprite = Sprite.rain3;
			else sprite = Sprite.rain1;
			xMove = 1;
			yMove = 1;
			fixed = false;
		}
	}
	
	public void update() {
		if(life > 0) life--;
		if(life == 0) this.remove();
		x += xMove;
		y += yMove;
	}
	
	public void render(Screen screen) {
		screen.renderParticle(x, y, sprite, color, fixed);
	}

}
