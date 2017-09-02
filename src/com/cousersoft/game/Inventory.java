package com.cousersoft.game;

import java.util.ArrayList;
import java.util.List;

import com.cousersoft.game.entity.item.Item;
import com.cousersoft.game.level.Level;

public class Inventory {
	
	//Array list of items to represent inventory
	public List<Item> inventory = new ArrayList<Item>();
	public Item equipped;
	
	
	public void addItem(Item item, Level level) {
		item.remove();
		level.removeItem(item);
		item.x = -40;
		item.y = -1;
		for (int x = 0; x < inventory.size(); x++) {
			if (inventory.get(x).name == item.name) {
				inventory.get(x).quantity++;
				inventory.get(x).multiples.add(item);
				return;
			}
		}
		inventory.add(item);
	}
	
	public void removeItem(Item item) {
			if (inventory.contains(item)) inventory.remove(item);
	}
	
	public void dropItem(Item item, int x, int y, Level level) {
		if (item.quantity > 1) {
			item.multiples.get(item.quantity - 2).x = x;
			item.multiples.get(item.quantity - 2).y = y;
			item.multiples.get(item.quantity - 2).add();
			level.add(item.multiples.get(item.quantity - 2));
			item.quantity--;
			} else {
			inventory.remove(item);
			item.quantity = 1;
			item.x = x;
			item.y = y;
			item.add();
			level.add(item);
		}
		if (item.quantity < 1) item.quantity = 1;
	}
	
	public void eatItem(Item item, Level level) {
		if (item.quantity > 1) {
			item.multiples.get(item.quantity - 2).x = - 50;
			item.multiples.get(item.quantity - 2).y = 0;
			level.removeItem(item.multiples.get(item.quantity - 2));
			item.quantity--;
		} else {
			level.removeItem(item);
			inventory.remove(item);
			item.quantity = 1;
			item.x = -50;
			item.y = 0;
		}
	}
	
	public void equipItem(Item item, Level level) {
		if (equipped == null) {
			equipped = item;
		} else {
			addItem(equipped, level);
			equipped = item;
		}
		if (item.quantity > 1) item.quantity--;
		else { inventory.remove(item);
		}
	}
	
	
}
