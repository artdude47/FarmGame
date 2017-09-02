package com.cousersoft.game.level;

import java.util.ArrayList;
import java.util.List;

import com.cousersoft.game.Inventory;
import com.cousersoft.game.entity.Entity;
import com.cousersoft.game.entity.item.Item;
import com.cousersoft.game.entity.mob.Dummy;
import com.cousersoft.game.entity.soil.Plant;
import com.cousersoft.game.entity.soil.Soil;
import com.cousersoft.game.entity.soil.plants.CornPlant;
import com.cousersoft.game.entity.soil.plants.TomatoPlant;
import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;
import com.cousersoft.game.level.tile.Tile;
import com.cousersoft.game.level.tile.TreeTile;

public class Level {
	protected int width; //Width of level by number of tiles
	protected int height; //Height of level by number of tiles
	protected int[] tileInt; //Array to hold each tile
	protected int[] tiles;
	protected int[] layer2;
	public boolean rain = false;
	public boolean explode = false;
	
	private List<Entity> entities = new ArrayList<Entity>();
	
	
	//Level constructor for random level
	public Level(int width, int height) {
		this.width = width; //level width is passed
		this.height = height; 
		tileInt = new int[width * height]; //tiles array is the width and height
		generateLevel(); //Generate random level
	}
	
	//For loading level load from the path file
	public Level(String path, String path2) {
		loadLevel(path, path2);
		generateLevel();
	}
	
	protected void generateLevel() {
	}
	
	
	
	

	
	protected void loadLevel(String path, String path2) {
		//Load from level path
	}
	
	
	public void update() {
		for(int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}
	}
	
	private void time() {
	}
	
	public void layer2Render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll / 16;
		int x1 = (xScroll + screen.width + 16) / 16;
		int y0 = yScroll / 16;
		int y1 = (yScroll + screen.height + 16) / 16;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				if (getLayer2(x, y) != Tile.clearTile) getLayer2(x, y).render(x, y, screen);
			}
		}
	}
	//Method to rnder the level
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll / 16;
		int x1 = (xScroll + screen.width + 16) / 16;
		int y0 = yScroll / 16;
		int y1 = (yScroll + screen.height + 16) / 16;
		
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x , y).render(x, y, screen);
			}
		}
		for (int i = 0; i < entities.size(); i++) {
			if (!entities.get(i).isRemoved()) {
				entities.get(i).render(screen);
			}
		}
	}
	
	
	public void stressTest(int testNum) {
		Dummy bob;
		for (int i = 0; i < testNum; i++) {
			bob = new Dummy(i + 2, 10);
			bob.init(this);
			this.add(bob);
		}
	}
	
	public boolean npcCol(int x, int y, int dir) {
		if (dir == 0) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).isNPC) {
					if (entities.get(i).x == x) {
						if (entities.get(i).y == y -1) {
							return true;
						}
					}
				}
			}
		}
		if (dir == 1) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).isNPC) {
					if (entities.get(i).x == x + 1) {
						if ((entities.get(i).y + 8)  == y) {
							return true;
						}
					}
				}
			}
		}
		if (dir == 2) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).isNPC) {
					if (entities.get(i).x == x) {
						if (entities.get(i).y == y + 1) {
							return true;
						}
					}
				}
			}
		}
		if (dir == 3) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).isNPC) {
					if (entities.get(i).x == x - 1) {
						if (entities.get(i).y == y ) {
							return true;
						}
					}
				}
			}
		}
		
		return false;
	}
	public Item itemCollision(int x, int y) {
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).x / 16 == x / 16 || entities.get(i).x / 16 == x / 16 + 1) {
				if (entities.get(i).y / 16 == y / 16 || entities.get(i).y / 16 == y / 16 + 1) {
					if (entities.get(i).isItem) return (Item) entities.get(i);
				}
			}
		}
		return null;
	}
	
	public void newDay() {
		Plant plant;
		Soil soil;
		for (int i = 0; i < entities.size(); i++) {
			if (entities.get(i).isPlant) {
				plant = (Plant) entities.get(i);
				if (plant.isWatered) {
					if (plant.age < plant.maxAge) plant.age++;
					plant.isWatered = false;
				} else {
					if(plant.health > 0) plant.health--;
				}
			}
			if (entities.get(i).isSoil) {
				soil = (Soil) entities.get(i);
				if(soil.isWatered) {
					soil.isWatered = false;
					soil.isTilled = true;
				}
			}
		}
	}
	
	public Plant getCrop(int x, int y, int dir) {
		Plant tempPlant;
		if (dir == 0) {
			for (int i = 0; i < entities.size(); i++) {
				if(entities.get(i).x / 16 == (x + 8) / 16) {
					if (entities.get(i).y / 16 == (y + 8) / 16 - 1) {
						if (entities.get(i).isPlant) {
							tempPlant = (Plant) entities.get(i);
							if (tempPlant.age == tempPlant.maxAge) {
								return tempPlant;
							} else {
								return null;
							}
						}
					}
				}
			}
		}
		if (dir == 1) {
			for (int i = 0; i < entities.size(); i++) {
				if(entities.get(i).x / 16 == (x + 8) / 16 + 1) {
					if (entities.get(i).y / 16 == (y + 8) / 16) {
						if (entities.get(i).isPlant) {
							tempPlant = (Plant) entities.get(i);
							if (tempPlant.age == tempPlant.maxAge) {
								return tempPlant;
							} else {
								return null;
							}
						}
					}
				}
			}
		}
		if (dir == 2) {
			for (int i = 0; i < entities.size(); i++) {
				if(entities.get(i).x / 16 == (x + 8) / 16) {
					if (entities.get(i).y / 16 == (y + 8) / 16 + 1) {
						if (entities.get(i).isPlant) {
							tempPlant = (Plant) entities.get(i);
							if (tempPlant.age == tempPlant.maxAge) {
								return tempPlant;
							} else {
								return null;
							}
						}
					}
				}
			}
		}
		if (dir == 3) {
			for (int i = 0; i < entities.size(); i++) {
				if(entities.get(i).x / 16 == (x + 8) / 16 - 1) {
					if (entities.get(i).y / 16 == (y + 8) / 16) {
						if (entities.get(i).isPlant) {
							tempPlant = (Plant) entities.get(i);
							if (tempPlant.age == tempPlant.maxAge) {
								return tempPlant;
							} else {
								return null;
							}
						}
					}
				}
			}
		}
		return null;
	}
	
	public String getCount() {
		return Integer.toString(entities.size());
	}
	
	public void removeItem(Item item) {
		if (entities.contains(item)) entities.remove(item);
	}
	
	public void removePlant(Plant plant) {
		if (entities.contains(plant)) entities.remove(plant);
	}
	public void getCropItem(Plant plant, Inventory inventory) {
		Item tempItem;
		plant.age = 3;
		tempItem = plant.pickFruit;
		this.init(tempItem);
		this.add(tempItem);
		inventory.addItem(tempItem, this);
	}
	
	public void soilTill(int x, int y, int dir) {
		Soil tempSoil;
		Plant tempPlant;
		if (dir == 0) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).x / 16 == (x + 10) / 16) {
					if (entities.get(i).y / 16 == (y + 8) / 16 - 1) {
						if (entities.get(i).isSoil) {
							tempSoil = (Soil) entities.get(i);
							if(!tempSoil.isPlanted && !tempSoil.isWatered) tempSoil.isTilled = true;
							if(tempSoil.isPlanted) {
								for (int z = 0; z < entities.size(); z++) {
									if (entities.get(z).x == tempSoil.x && entities.get(z).y == tempSoil.y && entities.get(z).isPlant) {
										tempPlant = (Plant) entities.get(z);
										tempPlant.remove();
										this.removePlant(tempPlant);
									}
								}
							}
						}
					}
				}
			}
		}
		if (dir == 1) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).x / 16 == (x + 8) / 16 + 1) {
					if (entities.get(i).y / 16 == (y + 8) / 16) {
						if (entities.get(i).isSoil) {
							tempSoil = (Soil) entities.get(i);
							if(!tempSoil.isPlanted && !tempSoil.isWatered) tempSoil.isTilled = true;
							if(tempSoil.isPlanted) {
								for (int z = 0; z < entities.size(); z++) {
									if (entities.get(z).x == tempSoil.x && entities.get(z).y == tempSoil.y && entities.get(z).isPlant) {
										tempPlant = (Plant) entities.get(z);
										tempPlant.remove();
										this.removePlant(tempPlant);
									}
								}
							}
						}
					}
				}
			}
		}
		if (dir == 2) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).x / 16 == (x + 8) / 16) {
					if (entities.get(i).y / 16 == (y + 8) / 16 + 1) {
						if (entities.get(i).isSoil) {
							tempSoil = (Soil) entities.get(i);
							if(!tempSoil.isPlanted && !tempSoil.isWatered) tempSoil.isTilled = true;
							if(tempSoil.isPlanted) {
								for (int z = 0; z < entities.size(); z++) {
									if (entities.get(z).x == tempSoil.x && entities.get(z).y == tempSoil.y && entities.get(z).isPlant) {
										tempPlant = (Plant) entities.get(z);
										tempPlant.remove();
										this.removePlant(tempPlant);
									}
								}
							}
						}
					}
				}
			}
		}
		if (dir == 3) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).x / 16 == (x + 8) / 16 - 1) {
					if (entities.get(i).y / 16 == (y + 8) / 16) {
						if (entities.get(i).isSoil) {
							tempSoil = (Soil) entities.get(i);
							if(!tempSoil.isPlanted && !tempSoil.isWatered) tempSoil.isTilled = true;
							if(tempSoil.isPlanted) {
								for (int z = 0; z < entities.size(); z++) {
									if (entities.get(z).x == tempSoil.x && entities.get(z).y == tempSoil.y && entities.get(z).isPlant) {
										tempPlant = (Plant) entities.get(z);
										tempPlant.remove();
										this.removePlant(tempPlant);
									}
								}
							}
						}
					}
				}
			}
		}
	}
	
	public void soilWater(int x, int y, int dir) {
		Soil tempSoil;
		Plant plant;
		if (dir == 0) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).x / 16 == (x + 8) / 16) {
					if (entities.get(i).y / 16 == (y + 8) / 16 - 1) {
						if (entities.get(i).isSoil) {
							tempSoil = (Soil) entities.get(i);
							if(!tempSoil.isWatered && tempSoil.isTilled) {
								tempSoil.isTilled = false;
								tempSoil.isWatered = true;
							}
						}
						if (entities.get(i).isPlant) {
							plant = (Plant) entities.get(i);
							plant.isWatered = true;
						}
					}
				}
			}
		}
		if (dir == 1) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).x / 16 == (x + 8)  / 16 + 1) {
					if (entities.get(i).y / 16 == (y + 8) / 16) {
						if (entities.get(i).isSoil) {
							tempSoil = (Soil) entities.get(i);
							if(tempSoil.isTilled) {
								tempSoil.isTilled = false;
								tempSoil.isWatered = true;
							}
						}
						if (entities.get(i).isPlant) {
							plant = (Plant) entities.get(i);
							plant.isWatered = true;
						}
					}
				}
			}
		} else if (dir == 2) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).x / 16 == (x + 8) / 16) {
					if (entities.get(i).y / 16 == (y + 8) / 16 + 1) {
						if (entities.get(i).isSoil) {
							tempSoil = (Soil) entities.get(i);
							if(!tempSoil.isWatered && tempSoil.isTilled) {
								tempSoil.isTilled = false;
								tempSoil.isWatered = true;
							}
						}
						if (entities.get(i).isPlant) {
							plant = (Plant) entities.get(i);
							plant.isWatered = true;
						}
					}
				}
			}
		} else if (dir == 3) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).x / 16 == (x + 8) / 16 - 1) {
					if (entities.get(i).y / 16 == (y + 8) / 16) {
						if (entities.get(i).isSoil) {
							tempSoil = (Soil) entities.get(i);
							if(!tempSoil.isWatered && tempSoil.isTilled) {
								tempSoil.isTilled = false;
								tempSoil.isWatered = true;
							}
						}
						if (entities.get(i).isPlant) {
							plant = (Plant) entities.get(i);
							plant.isWatered = true;
						}
					}
				}
			}
		}
	}
	public Boolean plantSeed(int x, int y, int dir, String type) {
		Soil tempSoil;
		Plant plant;
		if (dir == 0) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).x / 16 == (x + 8) / 16) {
					if (entities.get(i).y / 16 == (y + 8) / 16 - 1) {
						if (entities.get(i).isSoil) {
							tempSoil = (Soil) entities.get(i);
							if(!tempSoil.isPlanted && tempSoil.isTilled) {
								tempSoil.isPlanted = true;
								if (type == "Tomato") {
									plant = new TomatoPlant(((x + 8) / 16) * 16, (((y + 8) / 16) - 1) * 16, Sprite.tomatoSeed);
									plant.init(this);
									this.add(plant);
								}
								if (type == "Corn") {
									plant = new CornPlant(((x + 8) / 16) * 16, (((y + 8) / 16) -1) * 16, Sprite.cornSeed);
									plant.init(this);
									this.add(plant);
								}
								return true;
							}
						}
					}
				}
			}
		}else if (dir == 1) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).x / 16 == (x + 8)  / 16 + 1) {
					if (entities.get(i).y / 16 == (y + 8) / 16) {
						if (entities.get(i).isSoil) {
							tempSoil = (Soil) entities.get(i);
							if(!tempSoil.isPlanted && tempSoil.isTilled) {
								tempSoil.isPlanted = true;
								if (type == "Tomato") {
									plant = new TomatoPlant((((x + 8) / 16) + 1) * 16, ((y + 8) / 16) * 16, Sprite.tomatoSeed);
									plant.init(this);
									this.add(plant);
								}
								if (type == "Corn") {
									plant = new CornPlant((((x + 8) / 16) + 1) * 16, ((y + 8) / 16) * 16, Sprite.cornSeed);
									plant.init(this);
									this.add(plant);
								}
								return true;
							}
						}
					}
				}
			}
		} else if (dir == 2) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).x / 16 == (x + 8) / 16) {
					if (entities.get(i).y / 16 == (y + 8) / 16 + 1) {
						if (entities.get(i).isSoil) {
							tempSoil = (Soil) entities.get(i);
							if(!tempSoil.isPlanted && tempSoil.isTilled) {
								tempSoil.isPlanted = true;
								if (type == "Tomato") {
									plant = new TomatoPlant(((x + 8) / 16) * 16, (((y + 8) / 16) + 1) * 16, Sprite.tomatoSeed);
									plant.init(this);
									this.add(plant);
								}
								if (type == "Corn") {
									plant = new CornPlant(((x + 8) / 16) * 16, (((y + 8) / 16)  + 1) * 16, Sprite.cornSeed);
									plant.init(this);
									this.add(plant);
								}
								return true;
							}
						}
					}
				}
			}
		} else if (dir == 3) {
			for (int i = 0; i < entities.size(); i++) {
				if (entities.get(i).x / 16 == (x + 8) / 16 - 1) {
					if (entities.get(i).y / 16 == (y + 8) / 16) {
						if (entities.get(i).isSoil) {
							tempSoil = (Soil) entities.get(i);
							if(!tempSoil.isPlanted && tempSoil.isTilled) {
								tempSoil.isPlanted = true;
								if (type == "Tomato") {
									plant = new TomatoPlant((((x + 8) / 16) - 1) * 16, ((y + 8) / 16) * 16, Sprite.tomatoSeed);
									plant.init(this);
									this.add(plant);
								}
								if (type == "Corn") {
									plant = new CornPlant((((x + 8) / 16) - 1) * 16, ((y + 8) / 16) * 16, Sprite.cornSeed);
									plant.init(this);
									this.add(plant);
								}
								return true;
							}
						}
					}
				}
			}
		}
		return false;
	}
	
	public void add(Entity e) {
		entities.add(e);
		init(e);
	}
	
	public void init(Entity e) {
		e.level = this;
	}
	
	//Get tile method returns tile that's needed
	public Tile getTile(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == 0xff0f5609) return Tile.grass;
		if (tiles[x + y * width] == 0xff2f9e25) return Tile.grass1;
		if (tiles[x + y * width] == 0xff97ce31) return Tile.grass2;
		if (tiles[x + y * width] == 0xff4ace31) return Tile.grass3;
		if (tiles[x + y * width] == 0xffffff00) return Tile.yellowFlower;
		if (tiles[x + y * width] == 0xffff00ff) return Tile.purpleFlower;
		if (tiles[x + y * width] == 0xffffff9b) return Tile.bigFlower;
		if (tiles[x + y * width] == 0xff933700) return Tile.dirt;
		if (tiles[x + y * width] == 0xffc44700) return Tile.dirtDown1;
		if (tiles[x + y * width] == 0xfff95b00) return Tile.dirtDown2;
		if (tiles[x + y * width] == 0xfff9ad81) return Tile.dirtUp1;
		if (tiles[x + y * width] == 0xfff98c4d) return Tile.dirtUp2;
		if (tiles[x + y * width] == 0xffbe3700) return Tile.dirtTop;
		if (tiles[x + y * width] == 0xffe13700) return Tile.dirtBottom;
		if (tiles[x + y * width] == 0xfff95b5d) return Tile.dirtTLCorner;
		if (tiles[x + y * width] == 0xff602100) return Tile.dirtBLCorner;
		if (tiles[x + y * width] == 0xfff76e25) return Tile.dirtTRCorner;
		if (tiles[x + y * width] == 0xfff9ad33) return Tile.dirtBRCorner;
		if (tiles[x + y * width] == 0xff1e0b00) return Tile.dirtLeft;
		if (tiles[x + y * width] == 0xff93373e) return Tile.dirtRight;
		if (tiles[x + y * width] == 0xff606060) return Tile.rock;
		if (tiles[x + y * width] == 0xff3D3703) return Tile.houseBot1;
		if (tiles[x + y * width] == 0xff665C05) return Tile.houseBot2;
		if (tiles[x + y * width] == 0xff666033) return Tile.houseBot3;
		if (tiles[x + y * width] == 0xffEFE377) return Tile.houseBot4;
		if (tiles[x + y * width] == 0xffEFDDEC) return Tile.houseBot5;
		if (tiles[x + y * width] == 0xffEFDDAE) return Tile.houseBot6;
		if (tiles[x + y * width] == 0xffED71DE) return Tile.houseBot7;
		return Tile.voidTile;
	}
	
	public Tile getLayer2(int x, int y) {
		if (x < 0 || x >= width || y < 0 || y >= height) return Tile.clearTile;
		if (layer2[x + y * width] == 0xffff6a00) return Tile.blTrunk;
		if (layer2[x + y * width] == 0xff7f3300) return Tile.Trunk;
		if (layer2[x + y * width] == 0xffc44e00) return Tile.brTrunk;
		if (layer2[x + y * width] == 0xff765de4) return Tile.tlLeave;
		if (layer2[x + y * width] == 0xff768fe4) return Tile.tmLeave;
		if (layer2[x + y * width] == 0xff76b6e4) return Tile.trLeave;
		if (layer2[x + y * width] == 0xff32ea00) return Tile.mlLeave;
		if (layer2[x + y * width] == 0xff76ea56) return Tile.mmlLeave;
		if (layer2[x + y * width] == 0xff76ea93) return Tile.mmLeave;
		if (layer2[x + y * width] == 0xff76eac2) return Tile.mmrLeave;
		if (layer2[x + y * width] == 0xff76eae4) return Tile.mrLeave;
		if (layer2[x + y * width] == 0xff197800) return Tile.blLeave;
		if (layer2[x + y * width] == 0xff195900) return Tile.blmLeave;
		if (layer2[x + y * width] == 0xff194200) return Tile.bmLeave;
		if (layer2[x + y * width] == 0xff192b00) return Tile.brmLeave;
		if (layer2[x + y * width] == 0xff191800) return Tile.brLeave;
		if (layer2[x + y * width] == 0xff19c900) return Tile.mlTrunk;
		if (layer2[x + y * width] == 0xff19f800) return Tile.mmlTrunk;
		if (layer2[x + y * width] == 0xff190a00) return Tile.mmTrunk;
		if (layer2[x + y * width] == 0xff19b200) return Tile.mmrTrunk;
		if (layer2[x + y * width] == 0xff199300) return Tile.mrTrunk;
		if (layer2[x + y * width] == 0xff000721) return Tile.houseRoof1;
		if (layer2[x + y * width] == 0xff520721) return Tile.houseRoof2;
		if (layer2[x + y * width] == 0xff900721) return Tile.houseRoof3;
		if (layer2[x + y * width] == 0xffC90721) return Tile.houseRoof4;
		if (layer2[x + y * width] == 0xffFF0721) return Tile.houseRoof5;
		if (layer2[x + y * width] == 0xffFF99A4) return Tile.houseRoof6;
		if (layer2[x + y * width] == 0xffFFCCD1) return Tile.houseRoof7;
		if (layer2[x + y * width] == 0xffFFCCA1) return Tile.houseBotRoof1;
		if (layer2[x + y * width] == 0xffFFCC72) return Tile.houseBotRoof2;
		if (layer2[x + y * width] == 0xffFFCC46) return Tile.houseBotRoof3;
		if (layer2[x + y * width] == 0xffFFCC17) return Tile.houseBotRoof4;
		if (layer2[x + y * width] == 0xffFF9F17) return Tile.houseBotRoof5;
		if (layer2[x + y * width] == 0xffFF9FDF) return Tile.houseBotRoof6;
		if (layer2[x + y * width] == 0xffD183B7) return Tile.houseBotRoof7;
		if (layer2[x + y * width] == 0xffD170B1) return Tile.houseTop1;
		if (layer2[x + y * width] == 0xffD14DA5) return Tile.houseTop2;
		if (layer2[x + y * width] == 0xffAD4088) return Tile.houseTop3;
		if (layer2[x + y * width] == 0xffAD40C7) return Tile.houseTop4;
		if (layer2[x + y * width] == 0xffDA40FF) return Tile.houseTop5;
		if (layer2[x + y * width] == 0xffDAA4FF) return Tile.houseTop6;
		if (layer2[x + y * width] == 0xff795C8E) return Tile.houseTop7;
		if (layer2[x + y * width] == 0xff574266) return Tile.houseMid1;
		if (layer2[x + y * width] == 0xff3F3049) return Tile.houseMid2;
		if (layer2[x + y * width] == 0xff381F49) return Tile.houseMid3;
		if (layer2[x + y * width] == 0xff2B0049) return Tile.houseMid4;
		if (layer2[x + y * width] == 0xff7100C1) return Tile.houseMid5;
		if (layer2[x + y * width] == 0xffD8C484) return Tile.houseMid6;
		if (layer2[x + y * width] == 0xff6D6305) return Tile.houseMid7;
		if (layer2[x + y * width] == 0xff3D3703) return Tile.houseBot1;
		if (layer2[x + y * width] == 0xff665C05) return Tile.houseBot2;
		if (layer2[x + y * width] == 0xff666033) return Tile.houseBot3;
		if (layer2[x + y * width] == 0xffEFE377) return Tile.houseBot4;
		if (layer2[x + y * width] == 0xffEFDDEC) return Tile.houseBot5;
		if (layer2[x + y * width] == 0xffEFDDAE) return Tile.houseBot6;
		if (layer2[x + y * width] == 0xffED71DE) return Tile.houseBot7; 
		return Tile.clearTile;
	}
	

}
