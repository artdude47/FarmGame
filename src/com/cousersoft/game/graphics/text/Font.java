package com.cousersoft.game.graphics.text;

import java.util.ArrayList;
import java.util.List;

import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;

public class Font {
	
	
	protected List<Sprite> characters = new ArrayList<Sprite>();	
	protected String charIndex = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
			+ "abcdefghijklmnopqrstuvwxyz.,!?:1234567890";
	
	public void initChars(String size) {
		if (size == "Small") {
			characters.add(Sprite.smcapA);
			characters.add(Sprite.smcapB);
			characters.add(Sprite.smcapC);
			characters.add(Sprite.smcapD);
			characters.add(Sprite.smcapE);
			characters.add(Sprite.smcapF);
			characters.add(Sprite.smcapG);
			characters.add(Sprite.smcapH);
			characters.add(Sprite.smcapI);
			characters.add(Sprite.smcapJ);
			characters.add(Sprite.smcapK);
			characters.add(Sprite.smcapL);
			characters.add(Sprite.smcapM);
			characters.add(Sprite.smcapN);
			characters.add(Sprite.smcapO);
			characters.add(Sprite.smcapP);
			characters.add(Sprite.smcapQ);
			characters.add(Sprite.smcapR);
			characters.add(Sprite.smcapS);
			characters.add(Sprite.smcapT);
			characters.add(Sprite.smcapU);
			characters.add(Sprite.smcapV);
			characters.add(Sprite.smcapW);
			characters.add(Sprite.smcapX);
			characters.add(Sprite.smcapY);
			characters.add(Sprite.smcapZ);
			characters.add(Sprite.smlowA);
			characters.add(Sprite.smlowB);
			characters.add(Sprite.smlowC);
			characters.add(Sprite.smlowD);
			characters.add(Sprite.smlowE);
			characters.add(Sprite.smlowF);
			characters.add(Sprite.smlowG);
			characters.add(Sprite.smlowH);
			characters.add(Sprite.smlowI);
			characters.add(Sprite.smlowJ);
			characters.add(Sprite.smlowK);
			characters.add(Sprite.smlowL);
			characters.add(Sprite.smlowM);
			characters.add(Sprite.smlowN);
			characters.add(Sprite.smlowO);
			characters.add(Sprite.smlowP);
			characters.add(Sprite.smlowQ);
			characters.add(Sprite.smlowR);
			characters.add(Sprite.smlowS);
			characters.add(Sprite.smlowT);
			characters.add(Sprite.smlowU);
			characters.add(Sprite.smlowV);
			characters.add(Sprite.smlowW);
			characters.add(Sprite.smlowX);
			characters.add(Sprite.smlowY);
			characters.add(Sprite.smlowZ);
			characters.add(Sprite.smPeriod);
			characters.add(Sprite.smComma);
			characters.add(Sprite.smExplPt);
			characters.add(Sprite.smQuestion);
			characters.add(Sprite.smColon);
			characters.add(Sprite.sm1);
			characters.add(Sprite.sm2);
			characters.add(Sprite.sm3);
			characters.add(Sprite.sm4);
			characters.add(Sprite.sm5);
			characters.add(Sprite.sm6);
			characters.add(Sprite.sm7);
			characters.add(Sprite.sm8);
			characters.add(Sprite.sm9);
			characters.add(Sprite.sm0);

		} else {
			characters.add(Sprite.capA);
			characters.add(Sprite.capB);
			characters.add(Sprite.capC);
			characters.add(Sprite.capD);
			characters.add(Sprite.capE);
			characters.add(Sprite.capF);
			characters.add(Sprite.capG);
			characters.add(Sprite.capH);
			characters.add(Sprite.capI);
			characters.add(Sprite.capJ);
			characters.add(Sprite.capK);
			characters.add(Sprite.capL);
			characters.add(Sprite.capM);
			characters.add(Sprite.capN);
			characters.add(Sprite.capO);
			characters.add(Sprite.capP);
			characters.add(Sprite.capQ);
			characters.add(Sprite.capR);
			characters.add(Sprite.capS);
			characters.add(Sprite.capT);
			characters.add(Sprite.capU);
			characters.add(Sprite.capV);
			characters.add(Sprite.capW);
			characters.add(Sprite.capX);
			characters.add(Sprite.capY);
			characters.add(Sprite.capZ);
			characters.add(Sprite.lowA);
			characters.add(Sprite.lowB);
			characters.add(Sprite.lowC);
			characters.add(Sprite.lowD);
			characters.add(Sprite.lowE);
			characters.add(Sprite.lowF);
			characters.add(Sprite.lowG);
			characters.add(Sprite.lowH);
			characters.add(Sprite.lowI);
			characters.add(Sprite.lowJ);
			characters.add(Sprite.lowK);
			characters.add(Sprite.lowL);
			characters.add(Sprite.lowM);
			characters.add(Sprite.lowN);
			characters.add(Sprite.lowO);
			characters.add(Sprite.lowP);
			characters.add(Sprite.lowQ);
			characters.add(Sprite.lowR);
			characters.add(Sprite.lowS);
			characters.add(Sprite.lowT);
			characters.add(Sprite.lowU);
			characters.add(Sprite.lowV);
			characters.add(Sprite.lowW);
			characters.add(Sprite.lowX);
			characters.add(Sprite.lowY);
			characters.add(Sprite.lowZ);
			characters.add(Sprite.period);
			characters.add(Sprite.comma);
			characters.add(Sprite.expPt);
			characters.add(Sprite.question);
		}
	}
	
	
	public void render(Screen screen, int size, String text, int xp, int yp, int textColor, boolean fixed) {
			int xOffset = 0;
			int yOffset = 0;
			int charNum = 0;
			int charYOff = 0;
			int originalCol = textColor;
		for (int i = 0; i < text.length(); i++) {
			xOffset =  charNum * size;
			charYOff = 0;
			char currentChar = text.charAt(i);
			int index = charIndex.indexOf(currentChar);
			if (currentChar != '\r' && currentChar != '\b' && currentChar != '\"' && currentChar != '\'') charNum++;
			if (currentChar == '\n') {
				if (size == 8) yOffset += 10;
				if (size == 16) yOffset += 20;
				xOffset = 0;
				charNum = 0;
			}
			
			if (currentChar == 'g' || currentChar == 'y' || currentChar == 'p') {
				if (size == 8) charYOff += 3;
				if (size == 16) charYOff += 10;
			}
			
			if (currentChar == '\r') textColor = 0xffff0000;
			if (currentChar == '\b') textColor = 0xff0000ff;
			if (currentChar == '\"') textColor = 0xff00ff00;
			if (currentChar == '\'') textColor = originalCol;
			
			if (index != -1) {
				if (size == 8) {
					screen.renderSmallText(xp + xOffset, yp + yOffset + charYOff, characters.get(index), textColor, fixed);
				} else
					screen.renderText(xp + xOffset, yp + yOffset, characters.get(index), textColor, fixed);
			}
		}
	}
}
