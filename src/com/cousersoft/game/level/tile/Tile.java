package com.cousersoft.game.level.tile;

import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;
import com.cousersoft.game.graphics.SpriteSheet;

public class Tile {
	//x and y coords of tile
	public int x;
	public int y;
	public Sprite sprite; //sprite that contains tile
	
	
	//Initialize tiles here
	//GRASS TILES////////////////////////////////////////////////////////////////////////////////////////
	public static Tile grass = new GrassTile(Sprite.grass);
	public static Tile grass1 = new GrassTile(Sprite.grass1);
	public static Tile grass2 = new GrassTile(Sprite.grass2);
	public static Tile grass3 = new GrassTile(Sprite.grass3);
	public static Tile yellowFlower = new GrassTile(Sprite.yellowFlower);
	public static Tile bigFlower = new GrassTile(Sprite.bigFlower);
	public static Tile purpleFlower = new GrassTile(Sprite.purpleFlower);
	public static Tile rock = new RockTile(Sprite.rock);
	//DIRT TILES////////////////////////////////////////////////////////////////////////////////////////////////
	public static Tile dirt = new DirtTile(Sprite.dirt);
	public static Tile dirtUp1 = new DirtTile(Sprite.dirtUp1);
	public static Tile dirtUp2 = new DirtTile(Sprite.dirtUp2);
	public static Tile dirtDown1 = new DirtTile(Sprite.dirtDown1);
	public static Tile dirtDown2 = new DirtTile(Sprite.dirtDown2);
	public static Tile dirtRight = new DirtTile(Sprite.dirtRight);
	public static Tile dirtLeft = new DirtTile(Sprite.dirtLeft);
	public static Tile dirtTop = new DirtTile(Sprite.dirtTop);
	public static Tile dirtBottom = new DirtTile(Sprite.dirtBottom);
	public static Tile dirtTLCorner = new DirtTile(Sprite.dirtTLCorner);
	public static Tile dirtTRCorner = new DirtTile(Sprite.dirtTRCorner);
	public static Tile dirtBLCorner = new DirtTile(Sprite.dirtBLCorner);
	public static Tile dirtBRCorner = new DirtTile(Sprite.dirtBRCorner);
	//TREE TILES/////////////////////////////////////////////////////////////
	public static Tile tlLeave = new TreeTile(Sprite.tlLeave);
	public static Tile tmLeave = new TreeTile(Sprite.tmLeave);
	public static Tile trLeave = new TreeTile(Sprite.trLeave);
	public static Tile mlLeave = new TreeTile(Sprite.mlLeave);
	public static Tile mmlLeave = new TreeTile(Sprite.mmlLeave);
	public static Tile mmLeave = new TreeTile(Sprite.mmLeave);
	public static Tile mmrLeave = new TreeTile(Sprite.mmrLeave);
	public static Tile mrLeave = new TreeTile(Sprite.mrLeave);
	public static Tile blLeave = new TreeTile(Sprite.blLeave);
	public static Tile blmLeave = new TreeTile(Sprite.blmLeave);
	public static Tile bmLeave = new TreeTile(Sprite.bmLeave);
	public static Tile brmLeave = new TreeTile(Sprite.brmLeave);
	public static Tile brLeave = new TreeTile(Sprite.brLeave);
	public static Tile mlTrunk = new TreeTile(Sprite.mlTrunk);
	public static Tile mmlTrunk = new TreeTile(Sprite.mmlTrunk);
	public static Tile mmTrunk = new TreeTile(Sprite.mmTrunk);
	public static Tile mmrTrunk = new TreeTile(Sprite.mmrTrunk);
	public static Tile mrTrunk = new TreeTile(Sprite.mrTrunk);
	public static Tile blTrunk = new TrunkTile(Sprite.blTrunk);
	public static Tile Trunk = new TrunkTile(Sprite.Trunk);
	public static Tile brTrunk = new TrunkTile(Sprite.brTrunk);
	//VOID TILE//////////////////////////////////////////////////////////////
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);
	public static Tile clearTile = new ClearTile(Sprite.clearSprite);
	
	//HOUSE TILES/////////////////////////////////////////////////////////////////
	public static Tile houseRoof1 = new HouseTile(Sprite.houseRoof1);
	public static Tile houseRoof2 = new HouseTile(Sprite.houseRoof2);
	public static Tile houseRoof3 = new HouseTile(Sprite.houseRoof3);
	public static Tile houseRoof4 = new HouseTile(Sprite.houseRoof4);
	public static Tile houseRoof5 = new HouseTile(Sprite.houseRoof5);
	public static Tile houseRoof6 = new HouseTile(Sprite.houseRoof6);
	public static Tile houseRoof7 = new HouseTile(Sprite.houseRoof7);
	public static Tile houseBotRoof1 = new HouseTile(Sprite.houseBotRoof1);
	public static Tile houseBotRoof2 = new HouseTile(Sprite.houseBotRoof2);
	public static Tile houseBotRoof3 = new HouseTile(Sprite.houseBotRoof3);
	public static Tile houseBotRoof4 = new HouseTile(Sprite.houseBotRoof4);
	public static Tile houseBotRoof5 = new HouseTile(Sprite.houseBotRoof5);
	public static Tile houseBotRoof6 = new HouseTile(Sprite.houseBotRoof6);
	public static Tile houseBotRoof7 = new HouseTile(Sprite.houseBotRoof7);
	public static Tile houseTop1 = new HouseTile(Sprite.houseTop1);
	public static Tile houseTop2 = new HouseTile(Sprite.houseTop2);
	public static Tile houseTop3 = new HouseTile(Sprite.houseTop3);
	public static Tile houseTop4 = new HouseTile(Sprite.houseTop4);
	public static Tile houseTop5 = new HouseTile(Sprite.houseTop5);
	public static Tile houseTop6 = new HouseTile(Sprite.houseTop6);
	public static Tile houseTop7 = new HouseTile(Sprite.houseTop7);
	public static Tile houseMid1 = new HouseTile(Sprite.houseMid1);
	public static Tile houseMid2 = new HouseTile(Sprite.houseMid2);
	public static Tile houseMid3 = new HouseTile(Sprite.houseMid3);
	public static Tile houseMid4 = new HouseTile(Sprite.houseMid4);
	public static Tile houseMid5 = new HouseTile(Sprite.houseMid5);
	public static Tile houseMid6 = new HouseTile(Sprite.houseMid6);
	public static Tile houseMid7 = new HouseTile(Sprite.houseMid7);
	public static Tile houseBot1 = new HouseTile(Sprite.houseBot1);
	public static Tile houseBot2 = new HouseTile(Sprite.houseBot2);
	public static Tile houseBot3 = new HouseTile(Sprite.houseBot3);
	public static Tile houseBot4 = new HouseTile(Sprite.houseBot4);
	public static Tile houseBot5 = new HouseTile(Sprite.houseBot5);
	public static Tile houseBot6 = new HouseTile(Sprite.houseBot6);
	public static Tile houseBot7 = new HouseTile(Sprite.houseBot7);
	
	
	//New tile creation requires a sprite
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}
	
	
	public void render(int x, int y, Screen screen){
		screen.renderTile(x * 16, y * 16, this);
	}
	
	public boolean solid() {
		return false; //by default tile is not solid
	}
	
	public boolean isSoil() {
		return false;
	}
}
