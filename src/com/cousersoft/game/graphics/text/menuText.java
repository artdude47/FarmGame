package com.cousersoft.game.graphics.text;

import com.cousersoft.game.graphics.Screen;

public class menuText extends Font{
	
	public boolean isSelected = false; 
	public String text;
	public int x;
	public int y;
	public boolean centered;
	
	public menuText(String text, int x, int y, boolean cent) {
		this.text = text;
		this.centered = cent;
		if (centered) this.x = (x) - (this.text.length() * 16 / 2);
		else this.x = x;
		this.y = y;
		initChars("Large");
	}
	
	
	public void render(Screen screen) {
		int xOffset = 0;
		int yOffset = 0;
		int charNum = 0;
		for (int i = 0; i < text.length(); i++) {
			xOffset = i * 16;
			char currentChar = text.charAt(i);
			int index = charIndex.indexOf(currentChar);
			if (index != -1) {
				if (isSelected == true) {
					screen.renderText(x + xOffset, y + yOffset, characters.get(index), 0xffff0000, false);
				} else {
					screen.renderText(x + xOffset, y + yOffset, characters.get(index), 0, false);
				}
			}

		}
	}

}
