package com.cousersoft.game.menu;

import com.cousersoft.game.entity.item.Item;
import com.cousersoft.game.graphics.Screen;
import com.cousersoft.game.graphics.Sprite;
import com.cousersoft.game.graphics.text.Font;

public class ActionMenu {
	
	public Font action;
	public Font action1;
	public Font action2;
	
	public String actionText;
	public String action1Text;
	public String action2Text;
	
	public ActionMenu(Item item, Screen screen) {
		action = new Font();
		action.initChars("Small");
		action1 = new Font();
		action1.initChars("Small");
		action2 = new Font();
		action2.initChars("Small");	
		
		if(item.type == "Tool") {
			actionText = "Equip";
			action1Text = "Drop";
			action2Text = "Cancel";
		}
		
		if(item.type == "Food") {
			actionText = "Eat";
			action1Text = "Drop";
			action2Text = "Cancel";
		}
	}
}