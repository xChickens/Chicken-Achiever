package com.chickenachiever.achievements;

public class Property {

	private int value;
	private String activate;
	private int activeValue;

	public Property(String name, int initVal, String activate, int activeValue) {
		this.activate = activate;
		this.activeValue = activeValue;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int val) {
		value = val;
	}

	public boolean isActive() {
		boolean active = false;
		switch (activate) {
		case ">":
			active = value > activeValue;
			break;
		case "==":
			active = value ==activeValue;
			break;
		case "<":
			active = value < activeValue;
			break;
		}
		return active;
	}
}
