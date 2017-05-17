package com.chickenachiever.achievements;

public abstract class Achievement {
	private String name;
	private Property[] properties;
	private boolean unlocked;

	public Achievement(String name, Property[] properties) {
		this.name = name;
		this.properties = properties;
	}

	public String toString() {
		return null;
	}

	public void setUnlocked() {
		unlocked = true;
	}

	public boolean getUnlocked() {
		return unlocked;
	}

	public String getName() {
		return name;
	}

	public Property[] getProperties() {
		return properties;
	}
}
