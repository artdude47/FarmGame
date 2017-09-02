package com.cousersoft.game.entity.particle;

import com.cousersoft.game.entity.Entity;

public class Emitter extends Entity{
	
	
	private String type;
	private int nextSpawn = 20;
	private int explodeTimer = 100;
	
	public Emitter(int x, int y, String type, int color) {
		this.x = x;
		this.y = y;
		this.type = type;
	}
	
	public void update() {
		if (this.isRemoved() == false) {
			if (type == "Rain" && level.rain == true) {
				if (nextSpawn > 0) nextSpawn--;
				if(nextSpawn == 0) {
					int partX = random.nextInt(1000) - 400;
					int partYOff = random.nextInt(50) - 25;
					Particle part = new Particle(partX, y - 40 + partYOff, 0, this.level, type);
					part.init(level);
					level.add(part);
					nextSpawn = 0;
				}
			}
			if (type == "Explode") {
				if (explodeTimer > 0) explodeTimer--;
				if (level.explode == true && explodeTimer == 0) {
					for(int i = 0; i < 75; i++) {
						Particle part = new Particle(this.x, this.y, 0xffff0000, this.level, type);
						part.init(level);
						level.add(part);
					}
					explodeTimer = 100;
				}
			}
		}
	}
}
