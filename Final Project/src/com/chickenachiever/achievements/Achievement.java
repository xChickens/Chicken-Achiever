package com.chickenachiever.achievements;

public class Achievement {
	protected String name;
	protected Property[] properties;// achievement is unlocked when all the
									// properties are met
	protected boolean unlocked;
	protected int activeProps;

	public Achievement(String name, Property[] properties) {
		this.name = name;
		this.properties = properties; //set the properties in the child classes
		unlocked = false;
		activeProps = 0;
	}

	public String toString() {
		return null;
	}

	/*
	 * public void setUnlocked() { unlocked = true; }
	 */
	public boolean getUnlocked() {
		return unlocked;
	}

	public boolean isUnlocked() {

		for (int i = 0; i < properties.length; i++) {
			if (properties[i].isActive()) {
				activeProps++;
			}
		}
		if (activeProps == properties.length) {
			unlocked = true;
		}
		return unlocked;
	}

	public String getName() {
		return name;
	}

	public Property[] getProperties() {
		return properties;
	}
}
