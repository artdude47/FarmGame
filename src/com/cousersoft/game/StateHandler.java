package com.cousersoft.game;

public class StateHandler {
	
	private String currentState = "Menu";
	private String lastState;
	
	
	private void enterState(String state) {
	}
	
	private void exitState(String lastState) {
	}
	
	public String getState() {
		return currentState;
	}
	
	public void setState(String state) {
		lastState = currentState;
		currentState = state;
	}
}
