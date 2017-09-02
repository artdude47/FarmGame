package com.cousersoft.game.graphics;

public class Sprite {
	
	public final int SIZE; //Size for the individual sprite
	private final static int TSIZE = 16; //Size of tiles in the game
	private int x; //x location of the sprite
	private int y; //y location of the sprite
	private int width, height;
	public int[] pixels; //array to hold each pixel in the sprite
	protected SpriteSheet sheet; //What spritesheet hold the sprite
	
	//Declare sprites here
	//ALL GRASS TILES///////////////////////////////////////////////////////////////////////////////////////
	public static Sprite grass = new Sprite(TSIZE, 0, 0, SpriteSheet.tiles);
	public static Sprite grass1 = new Sprite(TSIZE,1, 0, SpriteSheet.tiles);
	public static Sprite grass2 = new Sprite(TSIZE, 0, 1, SpriteSheet.tiles);
	public static Sprite grass3 = new Sprite(TSIZE, 1, 1, SpriteSheet.tiles);
	public static Sprite yellowFlower = new Sprite(TSIZE, 2, 0, SpriteSheet.tiles);
	public static Sprite bigFlower = new Sprite(TSIZE, 3, 0, SpriteSheet.tiles);
	public static Sprite purpleFlower = new Sprite(TSIZE, 2, 1, SpriteSheet.tiles);
	public static Sprite rock = new Sprite(TSIZE, 10, 0, SpriteSheet.tiles);
	//ALL DIRT TILES///////////////////////////////////////////////////////////////////////////////////////////
	public static Sprite dirt = new Sprite(TSIZE, 3, 1, SpriteSheet.tiles);
	public static Sprite dirtUp1 = new Sprite(TSIZE, 4, 1, SpriteSheet.tiles);
	public static Sprite dirtUp2 = new Sprite(TSIZE, 4, 0, SpriteSheet.tiles);
	public static Sprite dirtDown1 = new Sprite(TSIZE, 5, 1, SpriteSheet.tiles);
	public static Sprite dirtDown2 = new Sprite(TSIZE, 5, 0, SpriteSheet.tiles);
	public static Sprite dirtRight = new Sprite(TSIZE, 6, 0, SpriteSheet.tiles);
	public static Sprite dirtBottom = new Sprite(TSIZE, 6, 1, SpriteSheet.tiles);
	public static Sprite dirtLeft = new Sprite(TSIZE, 7, 0, SpriteSheet.tiles);
	public static Sprite dirtTop = new Sprite(TSIZE, 7, 1, SpriteSheet.tiles);
	public static Sprite dirtTLCorner = new Sprite(TSIZE, 8, 0, SpriteSheet.tiles);
	public static Sprite dirtTRCorner = new Sprite(TSIZE, 9, 0, SpriteSheet.tiles);
	public static Sprite dirtBLCorner = new Sprite(TSIZE, 8, 1, SpriteSheet.tiles);
	public static Sprite dirtBRCorner = new Sprite(TSIZE, 9, 1, SpriteSheet.tiles);
	public static Sprite soil = new Sprite(TSIZE, 11, 0, SpriteSheet.tiles);
	public static Sprite soilTilled = new Sprite(TSIZE, 12, 0, SpriteSheet.tiles);
	public static Sprite soilWatered = new Sprite(TSIZE, 13, 0, SpriteSheet.tiles);
	//TREE TILES////////////////////////////////////////////////////////////////////////////////////////////////
	public static Sprite tlLeave = new Sprite(TSIZE, 1, 2, SpriteSheet.tiles);
	public static Sprite tmLeave = new Sprite(TSIZE, 2, 2, SpriteSheet.tiles);
	public static Sprite trLeave = new Sprite(TSIZE, 3, 2, SpriteSheet.tiles);
	public static Sprite mlLeave = new Sprite(TSIZE, 0, 3, SpriteSheet.tiles);
	public static Sprite mmlLeave = new Sprite(TSIZE, 1, 3, SpriteSheet.tiles);
	public static Sprite mmLeave = new Sprite(TSIZE, 2, 3, SpriteSheet.tiles);
	public static Sprite mmrLeave = new Sprite(TSIZE, 3, 3, SpriteSheet.tiles);
	public static Sprite mrLeave = new Sprite(TSIZE, 4, 3, SpriteSheet.tiles);
	public static Sprite blLeave = new Sprite(TSIZE, 0, 4, SpriteSheet.tiles);
	public static Sprite blmLeave = new Sprite(TSIZE, 1, 4, SpriteSheet.tiles);
	public static Sprite bmLeave = new Sprite(TSIZE, 2, 4, SpriteSheet.tiles);
	public static Sprite brmLeave = new Sprite(TSIZE, 3, 4, SpriteSheet.tiles);
	public static Sprite brLeave = new Sprite(TSIZE, 4, 4, SpriteSheet.tiles);
	public static Sprite mlTrunk = new Sprite(TSIZE, 0, 5, SpriteSheet.tiles);
	public static Sprite mmlTrunk = new Sprite(TSIZE, 1, 5, SpriteSheet.tiles);
	public static Sprite mmTrunk = new Sprite(TSIZE, 2, 5, SpriteSheet.tiles);
	public static Sprite mmrTrunk = new Sprite(TSIZE,3, 5, SpriteSheet.tiles);
	public static Sprite mrTrunk = new Sprite(TSIZE, 4, 5, SpriteSheet.tiles);
	public static Sprite blTrunk = new Sprite(TSIZE, 1, 6, SpriteSheet.tiles);
	public static Sprite Trunk = new Sprite(TSIZE, 2, 6, SpriteSheet.tiles);
	public static Sprite brTrunk = new Sprite(TSIZE, 3, 6, SpriteSheet.tiles);
	//VOID TILE/////////////////////////////////////////////////////////////
	public static Sprite voidSprite = new Sprite(TSIZE, 0);
	public static Sprite clearSprite = new Sprite(TSIZE, 0x21000000);
	
	//PLAYER SPRITES///////////////////////////////////////////////////////////
	public static Sprite player_down = new Sprite(16, 1, 0, SpriteSheet.player);
		public static Sprite player_down_1 = new Sprite(16, 0, 0, SpriteSheet.player);
		public static Sprite player_down_2 = new Sprite(16, 2, 0, SpriteSheet.player);
	public static Sprite player_up = new Sprite(16, 1, 1, SpriteSheet.player);
		public static Sprite player_up_1 = new Sprite(16, 0, 1, SpriteSheet.player);
		public static Sprite player_up_2 = new Sprite(16, 2, 1, SpriteSheet.player);
	public static Sprite player_left = new Sprite(16, 1, 3, SpriteSheet.player);
		public static Sprite player_left_1 = new Sprite(16, 0, 3, SpriteSheet.player);
		public static Sprite player_left_2 = new Sprite(16, 2, 3, SpriteSheet.player);
	public static Sprite player_right = new Sprite(16, 1, 2, SpriteSheet.player);
		public static Sprite player_right_1 = new Sprite(16, 0, 2, SpriteSheet.player);
		public static Sprite player_right_2 = new Sprite(16, 2, 2, SpriteSheet.player);
	public static Sprite playerRight1 = new Sprite(16, 3, 0, SpriteSheet.player);
	public static Sprite playerRight2 = new Sprite(16, 3, 1, SpriteSheet.player);
	public static Sprite playerLeft1 = new Sprite(16,3,2,SpriteSheet.player);
	public static Sprite playerLeft2 = new Sprite(16,3,3,SpriteSheet.player);
	public static Sprite playerDown1 = new Sprite(16,4,0,SpriteSheet.player);
	public static Sprite playerDown2 = new Sprite(16,4,1,SpriteSheet.player);
	//NPC SPRITES////////////////////////////////////////////////////////////////////////////
	public static Sprite npc_down = new Sprite(16, 1, 0, SpriteSheet.NPC);	
		public static Sprite npc_down_1 = new Sprite(16, 0, 0, SpriteSheet.NPC);
		public static Sprite npc_down_2 = new Sprite(16, 2, 0, SpriteSheet.NPC);
	public static Sprite npc_up = new Sprite(16, 1, 1, SpriteSheet.NPC);
		public static Sprite npc_up_1 = new Sprite(16, 0, 1, SpriteSheet.NPC);
		public static Sprite npc_up_2 = new Sprite(16, 2, 1, SpriteSheet.NPC);
	public static Sprite npc_left = new Sprite(16, 1, 3, SpriteSheet.NPC);
		public static Sprite npc_left_1 = new Sprite(16, 0, 3, SpriteSheet.NPC);
		public static Sprite npc_left_2 = new Sprite(16, 2, 3, SpriteSheet.NPC);
	public static Sprite npc_right = new Sprite(16, 1, 2, SpriteSheet.NPC);
		public static Sprite npc_right_1 = new Sprite(16, 0, 2, SpriteSheet.NPC);
		public static Sprite npc_right_2 = new Sprite(16, 2, 2, SpriteSheet.NPC);
	
	public static Sprite sword = new Sprite(16, 0, 0, SpriteSheet.sword);
	
	////////////FONT SPRITES///////////////////////////////////////////////////////////////////////////////////////
	public static Sprite capA = new Sprite(16, 0, 0, SpriteSheet.fontSheet);
	public static Sprite capB = new Sprite(16, 1, 0, SpriteSheet.fontSheet);
	public static Sprite capC = new Sprite(16, 2, 0, SpriteSheet.fontSheet);
	public static Sprite capD = new Sprite(16, 3, 0, SpriteSheet.fontSheet);
	public static Sprite capE = new Sprite(16, 4, 0, SpriteSheet.fontSheet);
	public static Sprite capF = new Sprite(16, 5, 0, SpriteSheet.fontSheet);
	public static Sprite capG = new Sprite(16, 6, 0, SpriteSheet.fontSheet);
	public static Sprite capH = new Sprite(16, 7, 0, SpriteSheet.fontSheet);
	public static Sprite capI = new Sprite(16, 8, 0, SpriteSheet.fontSheet);
	public static Sprite capJ = new Sprite(16, 9, 0, SpriteSheet.fontSheet);
	public static Sprite capK = new Sprite(16, 10, 0, SpriteSheet.fontSheet);
	public static Sprite capL = new Sprite(16, 11, 0, SpriteSheet.fontSheet);
	public static Sprite capM = new Sprite(16, 12, 0, SpriteSheet.fontSheet);
	public static Sprite capN = new Sprite(16, 13, 0, SpriteSheet.fontSheet);
	public static Sprite capO = new Sprite(16, 14, 0, SpriteSheet.fontSheet);
	public static Sprite capP = new Sprite(16, 0, 1, SpriteSheet.fontSheet);
	public static Sprite capQ = new Sprite(16, 1, 1, SpriteSheet.fontSheet);
	public static Sprite capR = new Sprite(16, 2, 1, SpriteSheet.fontSheet);
	public static Sprite capS = new Sprite(16, 3, 1, SpriteSheet.fontSheet);
	public static Sprite capT = new Sprite(16, 4, 1, SpriteSheet.fontSheet);
	public static Sprite capU = new Sprite(16, 5, 1, SpriteSheet.fontSheet);
	public static Sprite capV = new Sprite(16, 6, 1, SpriteSheet.fontSheet);
	public static Sprite capW = new Sprite(16, 7, 1, SpriteSheet.fontSheet);
	public static Sprite capX = new Sprite(16, 8, 1, SpriteSheet.fontSheet);
	public static Sprite capY = new Sprite(16, 9, 1, SpriteSheet.fontSheet);
	public static Sprite capZ = new Sprite(16, 10, 1, SpriteSheet.fontSheet);
	///LOWER CASE LETTERS////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static Sprite lowA = new Sprite(16, 11, 1, SpriteSheet.fontSheet);
	public static Sprite lowB = new Sprite(16, 12, 1, SpriteSheet.fontSheet);
	public static Sprite lowC = new Sprite(16, 13, 1, SpriteSheet.fontSheet);
	public static Sprite lowD = new Sprite(16, 14, 1, SpriteSheet.fontSheet);
	public static Sprite lowE = new Sprite(16, 0, 2, SpriteSheet.fontSheet);
	public static Sprite lowF = new Sprite(16, 1, 2, SpriteSheet.fontSheet);
	public static Sprite lowG = new Sprite(16, 2, 2, SpriteSheet.fontSheet);
	public static Sprite lowH = new Sprite(16, 3, 2, SpriteSheet.fontSheet);
	public static Sprite lowI = new Sprite(16, 4, 2, SpriteSheet.fontSheet);
	public static Sprite lowJ = new Sprite(16, 5, 2, SpriteSheet.fontSheet);
	public static Sprite lowK = new Sprite(16, 6, 2, SpriteSheet.fontSheet);
	public static Sprite lowL = new Sprite(16, 7, 2, SpriteSheet.fontSheet);
	public static Sprite lowM = new Sprite(16, 8, 2, SpriteSheet.fontSheet);
	public static Sprite lowN = new Sprite(16, 9, 2, SpriteSheet.fontSheet);
	public static Sprite lowO = new Sprite(16, 10, 2, SpriteSheet.fontSheet);
	public static Sprite lowP = new Sprite(16, 11, 2, SpriteSheet.fontSheet);
	public static Sprite lowQ = new Sprite(16, 12, 2, SpriteSheet.fontSheet);
	public static Sprite lowR = new Sprite(16, 13, 2, SpriteSheet.fontSheet);
	public static Sprite lowS = new Sprite(16, 14, 2, SpriteSheet.fontSheet);
	public static Sprite lowT = new Sprite(16, 0, 3, SpriteSheet.fontSheet);
	public static Sprite lowU = new Sprite(16, 1, 3, SpriteSheet.fontSheet);
	public static Sprite lowV = new Sprite(16, 2, 3, SpriteSheet.fontSheet);
	public static Sprite lowW = new Sprite(16, 3, 3, SpriteSheet.fontSheet);
	public static Sprite lowX = new Sprite(16, 4, 3, SpriteSheet.fontSheet);
	public static Sprite lowY = new Sprite(16, 5, 3, SpriteSheet.fontSheet);
	public static Sprite lowZ = new Sprite(16, 6, 3, SpriteSheet.fontSheet);
	public static Sprite period = new Sprite(16, 7, 3, SpriteSheet.fontSheet);
	public static Sprite comma = new Sprite(16, 8, 3, SpriteSheet.fontSheet);
	public static Sprite expPt = new Sprite(16, 9, 3, SpriteSheet.fontSheet);
	public static Sprite question = new Sprite(16, 10, 3, SpriteSheet.fontSheet);
	/////////////////////////////SMALL FONT SPRITES///////////////////////////////////////////////////////////////////////////////////
	public static Sprite smcapA = new Sprite(8, 0, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapB = new Sprite(8, 1, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapC = new Sprite(8, 2, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapD = new Sprite(8, 3, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapE = new Sprite(8, 4, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapF = new Sprite(8, 5, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapG = new Sprite(8, 6, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapH = new Sprite(8, 7, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapI = new Sprite(8, 8, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapJ = new Sprite(8, 9, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapK = new Sprite(8, 10, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapL = new Sprite(8, 11, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapM = new Sprite(8, 12, 0, SpriteSheet.smallFontSheet);
	public static Sprite smcapN = new Sprite(8, 0, 1, SpriteSheet.smallFontSheet);
	public static Sprite smcapO = new Sprite(8, 1, 1, SpriteSheet.smallFontSheet);
	public static Sprite smcapP = new Sprite(8, 2, 1, SpriteSheet.smallFontSheet);
	public static Sprite smcapQ = new Sprite(8, 3, 1, SpriteSheet.smallFontSheet);
	public static Sprite smcapR = new Sprite(8, 4, 1, SpriteSheet.smallFontSheet);
	public static Sprite smcapS = new Sprite(8, 5, 1, SpriteSheet.smallFontSheet);
	public static Sprite smcapT = new Sprite(8, 6, 1, SpriteSheet.smallFontSheet);
	public static Sprite smcapU = new Sprite(8, 7, 1, SpriteSheet.smallFontSheet);
	public static Sprite smcapV = new Sprite(8, 8, 1, SpriteSheet.smallFontSheet);
	public static Sprite smcapW = new Sprite(8, 9, 1, SpriteSheet.smallFontSheet);
	public static Sprite smcapX = new Sprite(8, 10, 1, SpriteSheet.smallFontSheet);
	public static Sprite smcapY = new Sprite(8, 11, 1, SpriteSheet.smallFontSheet);
	public static Sprite smcapZ = new Sprite(8, 12, 1, SpriteSheet.smallFontSheet);
	///LOWER CASE LETTERS////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static Sprite smlowA = new Sprite(8, 0, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowB = new Sprite(8, 1, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowC = new Sprite(8, 2, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowD = new Sprite(8, 3, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowE = new Sprite(8, 4, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowF = new Sprite(8, 5, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowG = new Sprite(8, 6, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowH = new Sprite(8, 7, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowI = new Sprite(8, 8, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowJ = new Sprite(8, 9, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowK = new Sprite(8, 10, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowL = new Sprite(8, 11, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowM = new Sprite(8, 12, 2, SpriteSheet.smallFontSheet);
	public static Sprite smlowN = new Sprite(8, 0, 3, SpriteSheet.smallFontSheet);
	public static Sprite smlowO = new Sprite(8, 1, 3, SpriteSheet.smallFontSheet);
	public static Sprite smlowP = new Sprite(8, 2, 3, SpriteSheet.smallFontSheet);
	public static Sprite smlowQ = new Sprite(8, 3, 3, SpriteSheet.smallFontSheet);
	public static Sprite smlowR = new Sprite(8, 4, 3, SpriteSheet.smallFontSheet);
	public static Sprite smlowS = new Sprite(8, 5, 3, SpriteSheet.smallFontSheet);
	public static Sprite smlowT = new Sprite(8, 6, 3, SpriteSheet.smallFontSheet);
	public static Sprite smlowU = new Sprite(8, 7, 3, SpriteSheet.smallFontSheet);
	public static Sprite smlowV = new Sprite(8, 8, 3, SpriteSheet.smallFontSheet);
	public static Sprite smlowW = new Sprite(8, 9, 3, SpriteSheet.smallFontSheet);
	public static Sprite smlowX = new Sprite(8, 10, 3, SpriteSheet.smallFontSheet);
	public static Sprite smlowY = new Sprite(8, 11, 3, SpriteSheet.smallFontSheet);
	public static Sprite smlowZ = new Sprite(8, 12, 3, SpriteSheet.smallFontSheet);
	public static Sprite smPeriod = new Sprite(8, 0, 4, SpriteSheet.smallFontSheet);
	public static Sprite smComma = new Sprite(8, 1, 4, SpriteSheet.smallFontSheet);
	public static Sprite smExplPt = new Sprite(8, 2, 4, SpriteSheet.smallFontSheet);
	public static Sprite smQuestion = new Sprite(8, 3, 4, SpriteSheet.smallFontSheet);
	public static Sprite smColon = new Sprite(8, 4, 4, SpriteSheet.smallFontSheet);
	public static Sprite sm1 = new Sprite(8, 5, 4, SpriteSheet.smallFontSheet);
	public static Sprite sm2 = new Sprite(8, 6, 4, SpriteSheet.smallFontSheet);
	public static Sprite sm3 = new Sprite(8, 7, 4, SpriteSheet.smallFontSheet);
	public static Sprite sm4 = new Sprite(8, 8, 4, SpriteSheet.smallFontSheet);
	public static Sprite sm5 = new Sprite(8, 9, 4, SpriteSheet.smallFontSheet);
	public static Sprite sm6 = new Sprite(8, 10, 4, SpriteSheet.smallFontSheet);
	public static Sprite sm7 = new Sprite(8, 11, 4, SpriteSheet.smallFontSheet);
	public static Sprite sm8 = new Sprite(8, 12, 4, SpriteSheet.smallFontSheet);
	public static Sprite sm9 = new Sprite(8, 0, 5, SpriteSheet.smallFontSheet);
	public static Sprite sm0 = new Sprite(8, 1, 5, SpriteSheet.smallFontSheet);
	
	public static Sprite textBox = new Sprite(130,0,0,SpriteSheet.textBox);
	public static Sprite background = new Sprite(400,0,0,SpriteSheet.background);
	public static Sprite invSlot = new Sprite(400, 0, 0, SpriteSheet.invSlot);
	public static Sprite select = new Sprite(8, 0, 0, SpriteSheet.select);
	public static Sprite endSelect = new Sprite(8, 0, 0, SpriteSheet.endSelect);
	public static Sprite actionMenu = new Sprite(64, 0, 0, SpriteSheet.actionMenu);
	public static Sprite taco = new Sprite(16, 0, 0, SpriteSheet.taco);
	
	public static Sprite particle = new Sprite(4, 0, 0, SpriteSheet.particle);
	public static Sprite rain1 = new Sprite(16, 0, 0, SpriteSheet.rain);
	public static Sprite rain2 = new Sprite(16, 1, 0, SpriteSheet.rain);
	public static Sprite rain3 = new Sprite(16, 2, 0, SpriteSheet.rain);
	
	//////////////TOOL SPRITES////////////////////////////////////////////////////////////////////////////////////////////
	public static Sprite hoe = new Sprite(16, 0, 0, SpriteSheet.tools);
	public static Sprite wateringCan = new Sprite(16, 1, 0, SpriteSheet.tools);
	public static Sprite tomatoSeedBag = new Sprite(16, 0, 1, SpriteSheet.tools);
	public static Sprite cornSeedBag = new Sprite(16, 1, 1, SpriteSheet.tools);
	
	public static Sprite tomatoSeed = new Sprite(16, 0, 0, SpriteSheet.testPlant);
	public static Sprite tomatoSprout = new Sprite(16, 1, 0, SpriteSheet.testPlant);
	public static Sprite tomato1 = new Sprite(16, 2, 0, SpriteSheet.testPlant);
	public static Sprite tomato2 = new Sprite(16, 3, 0, SpriteSheet.testPlant);
	public static Sprite tomato3 = new Sprite(16, 4, 0, SpriteSheet.testPlant);
	public static Sprite tomatoFull = new Sprite(16, 5, 0, SpriteSheet.testPlant);
	public static Sprite tomato1Dead = new Sprite(16, 0, 1, SpriteSheet.testPlant);
	public static Sprite tomato2Dead = new Sprite(16, 1, 1, SpriteSheet.testPlant);
	public static Sprite tomato3Dead = new Sprite(16, 2, 1, SpriteSheet.testPlant);
	public static Sprite tomato = new Sprite(16, 0, 0, SpriteSheet.fruit);
	
	public static Sprite cornSeed = new Sprite(16, 0, 2, SpriteSheet.testPlant);
	public static Sprite cornSprout = new Sprite(16, 1, 2, SpriteSheet.testPlant);
	public static Sprite corn1 = new Sprite(16, 2, 2, SpriteSheet.testPlant);
	public static Sprite corn2 = new Sprite(16, 3, 2, SpriteSheet.testPlant);
	public static Sprite corn3 = new Sprite(16, 4, 2, SpriteSheet.testPlant);
	public static Sprite cornFull = new Sprite(16, 5, 2, SpriteSheet.testPlant);
	public static Sprite corn = new Sprite(16, 1, 0, SpriteSheet.fruit);
	
	
	//////////////////////////HOUSE TILE SPRITES///////////////////////////////////////////////////////////////////////
	public static Sprite houseRoof1 = new Sprite(16, 0, 7, SpriteSheet.tiles);
	public static Sprite houseRoof2 = new Sprite(16, 1, 7, SpriteSheet.tiles);
	public static Sprite houseRoof3 = new Sprite(16, 2, 7, SpriteSheet.tiles);
	public static Sprite houseRoof4 = new Sprite(16, 3, 7, SpriteSheet.tiles);
	public static Sprite houseRoof5 = new Sprite(16, 4, 7, SpriteSheet.tiles);
	public static Sprite houseRoof6 = new Sprite(16, 5, 7, SpriteSheet.tiles);
	public static Sprite houseRoof7 = new Sprite(16, 6, 7, SpriteSheet.tiles);
	public static Sprite houseBotRoof1 = new Sprite(16, 0, 8, SpriteSheet.tiles);
	public static Sprite houseBotRoof2 = new Sprite(16, 1, 8, SpriteSheet.tiles);
	public static Sprite houseBotRoof3 = new Sprite(16, 2, 8, SpriteSheet.tiles);
	public static Sprite houseBotRoof4 = new Sprite(16, 3, 8, SpriteSheet.tiles);
	public static Sprite houseBotRoof5 = new Sprite(16, 4, 8, SpriteSheet.tiles);
	public static Sprite houseBotRoof6 = new Sprite(16, 5, 8, SpriteSheet.tiles);
	public static Sprite houseBotRoof7 = new Sprite(16, 6, 8, SpriteSheet.tiles);
	public static Sprite houseTop1 = new Sprite(16, 0, 9, SpriteSheet.tiles);
	public static Sprite houseTop2 = new Sprite(16, 1, 9, SpriteSheet.tiles);
	public static Sprite houseTop3 = new Sprite(16, 2, 9, SpriteSheet.tiles);
	public static Sprite houseTop4 = new Sprite(16, 3, 9, SpriteSheet.tiles);
	public static Sprite houseTop5 = new Sprite(16, 4, 9, SpriteSheet.tiles);
	public static Sprite houseTop6 = new Sprite(16, 5, 9, SpriteSheet.tiles);
	public static Sprite houseTop7 = new Sprite(16, 6, 9, SpriteSheet.tiles);
	public static Sprite houseMid1 = new Sprite(16, 0, 10, SpriteSheet.tiles);
	public static Sprite houseMid2 = new Sprite(16, 1, 10, SpriteSheet.tiles);
	public static Sprite houseMid3 = new Sprite(16, 2, 10, SpriteSheet.tiles);
	public static Sprite houseMid4 = new Sprite(16, 3, 10, SpriteSheet.tiles);
	public static Sprite houseMid5 = new Sprite(16, 4, 10, SpriteSheet.tiles);
	public static Sprite houseMid6 = new Sprite(16, 5, 10, SpriteSheet.tiles);
	public static Sprite houseMid7 = new Sprite(16, 6, 10, SpriteSheet.tiles);
	public static Sprite houseBot1 = new Sprite(16, 0, 11, SpriteSheet.tiles);
	public static Sprite houseBot2 = new Sprite(16, 1, 11, SpriteSheet.tiles);
	public static Sprite houseBot3 = new Sprite(16, 2, 11, SpriteSheet.tiles);
	public static Sprite houseBot4 = new Sprite(16, 3, 11, SpriteSheet.tiles);
	public static Sprite houseBot5 = new Sprite(16, 4, 11, SpriteSheet.tiles);
	public static Sprite houseBot6 = new Sprite(16, 5, 11, SpriteSheet.tiles);
	public static Sprite houseBot7 = new Sprite(16, 6, 11, SpriteSheet.tiles);
	
	public static Sprite hoeAnimRight1 = new Sprite(32,0,0,SpriteSheet.animHoe);
	public static Sprite hoeAnimRight2 = new Sprite(32,1,0,SpriteSheet.animHoe);
	public static Sprite hoeAnimLeft1 = new Sprite(32,3,0,SpriteSheet.animHoe);
	public static Sprite hoeAnimLeft2 = new Sprite(32,2,0,SpriteSheet.animHoe);
	public static Sprite hoeAnimDown1 = new Sprite(32, 0, 1, SpriteSheet.animHoe);
	public static Sprite hoeAnimDown2 = new Sprite(32, 1, 1, SpriteSheet.animHoe);

	
	
		public Sprite(int[] pixels, int width, int height) {
		if (width == height) SIZE = width;
		else SIZE = -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
		}
		
		
		protected Sprite(SpriteSheet sprite, int width, int height) {
			if (width == height) SIZE = width;
			else SIZE = -1;
			this.width = width;
			this.height = height;
			this.sheet = sheet;
		}
	//Sprite declaration for more detailed sprites requires a size and x and y coords on sheet and requires a sheet to pull it from.
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size; //Sprite size is the same as int that is passed
		pixels = new int[SIZE * SIZE]; //Create a pixel array the size of the sprite
		this.x = x * size; //x * size to go from pixel precision to tile precision
		//Ex. if x passed is 2 then the pixel needed is 32
		this.y = y * size; //y * size to go from pixel to tile precision
		this.sheet = sheet; //The spritesheet that our sprite is pulled from
		load();
	}
	//Sprite declaration for void sprite only requires a size and a color
	public Sprite(int size, int color) {
		SIZE = size; //Sets sprite's size to the size passed in the method
		pixels = new int[SIZE * SIZE]; //Pixel array the size of the sprite
		setColor(color); //Take the color passed by the method and call the set color method to fill the array.
	}
	
	
	//Method cycles through all pixels within a sprite and sets them equal to color
	private void setColor(int color) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;
		}
	}
	
	//Method to load the sprite from the sheet
	private void load() {
		//Cycle through all pixels in the sprite
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE]; //Sets the sprite's pixel array equal to
				//the sheets array for in specific area (1 tiles worth)
			}
		}
	}
}
