package com.chickenachiever.achievements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.TreeMap;

public class Achieve {

    public final String ACTIVE_IF_GREATER = ">";
    public final String ACTIVE_IF_EQUAL = "==";
    public final String ACTIVE_IF_LESS = "<";

    private HashMap<String, Property> myProperties;
    private TreeMap<String, Achievement> myAchievements;
    private ArrayList<Achievement> myAchievementList;

    public Achieve() {
	myProperties = new HashMap<String, Property>();
	myAchievements = new TreeMap<String, Achievement>();
	myAchievementList = new ArrayList<Achievement>();
	/*
	 * createProperty("testproperty",1,ACTIVE_IF_EQUAL,1); Property[]
	 * testarray= {myProperties.get("testproperty")};
	 * createAchievement("testachievement",testarray);
	 * createProperty("testproperty1",0,ACTIVE_IF_EQUAL,1); Property[]
	 * testarray1= {myProperties.get("testproperty1")};
	 * createAchievement("testachievement1",testarray1);
	 * //System.out.println("hi");
	 */

    }

    public HashMap<String, Property> getProperties() {
	return myProperties;
    }

    public TreeMap<String, Achievement> getAchievements() {
	return myAchievements;
    }

    public void createProperty(String name, int initVal, String activate, int activeValue) {
	myProperties.put(name, new Property(name, initVal, activate, activeValue));
    }

    public void createAchievement(String name, Property[] props) {
	myAchievements.put(name, new Achievement(name, props));
	myAchievementList.add(myAchievements.get(name));
    }

    public int getPropValue(String name) {
	return myProperties.get(name).getValue();
    }

    public void setPropValue(String name, int val) {
	myProperties.get(name).setValue(val);
    }

    public ArrayList<Achievement> checkAchievements() {
	ArrayList<Achievement> unlocked = new ArrayList<Achievement>();
	Iterator<String> iter = myAchievements.keySet().iterator();
	while (iter.hasNext()) {
	    String name = iter.next();
	    if (myAchievements.get(name).getUnlocked()) {
		unlocked.add(myAchievements.get(name));
	    } else {
		if (myAchievements.get(name).isUnlocked()) {
		    unlocked.add(myAchievements.get(name));
		}
	    }
	}
	return unlocked;
    }

    public void drawOnPlayableArea(Graphics2D graph, ArrayList<Achievement> Achievements) {
	ArrayList<Achievement> newAchievements = new ArrayList<Achievement>();
	for (int i = 0; i < Achievements.size(); i++) {
	    if (Achievements.get(i).timeSinceAchieved() < 5000) {
		newAchievements.add(Achievements.get(i));
	    }
	}

	int height = 40;
	for (int i = 0; i < newAchievements.size(); i++) {
	    String name = newAchievements.get(i).getName();
	    graph.setColor(new Color(0, 255, 255, 175));
	    // 768 is the bottom of the screen
	    graph.fillRect(0, 640 - height - (height * i), 450, height);
	    graph.setColor(new Color(255, 255, 255, 200));

	    graph.setFont(new Font("Arial", Font.PLAIN, 28));
	    FontMetrics metrics = graph.getFontMetrics();
	    int Swidth = metrics.stringWidth(name + " unlocked!");
	    int Sheight = metrics.getHeight();
	    graph.drawString(name + " unlocked!", 0 + (450 / 2) - (Swidth / 2), 645 - height - (height * i) + Sheight / 2 + 5);
	    if (newAchievements.get(i).timeSinceAchieved() > 5000) {
		newAchievements.remove(i);
		i--;
	    }

	}
    }

    public void draw(Graphics2D graph) {
	ArrayList<Achievement> Achievements = checkAchievements();
	drawOnPlayableArea(graph, Achievements);
	// draw achievements beginning at position x: x = 720
	int height = 30;
	int i = 0;
	// try something
	Iterator<Achievement> iter = myAchievementList.iterator();
	while (iter.hasNext()) {
	    String name = iter.next().name;
	    if (myAchievements.get(name).getUnlocked()) {
		// graph.drawRect(850,300,200,200);//i just put in random
		// position numbers for now
		graph.setColor(Color.CYAN);
		graph.fillRect(895, 0 + (height * i), 320, height);
		graph.setColor(Color.WHITE);

		graph.setFont(new Font("Arial", Font.PLAIN, 28));
		FontMetrics metrics = graph.getFontMetrics();
		int width = metrics.stringWidth(name);
		graph.drawString(name, 895 + (320 / 2) - (width / 2), (height * i) + 50 / 2);

	    } else {
		graph.setColor(new Color(0, 178, 178));
		graph.fillRect(895, 0 + (height * i), 320, height);
		graph.setColor(Color.WHITE);

		graph.setFont(new Font("Arial", Font.PLAIN, 28));
		FontMetrics metrics = graph.getFontMetrics();
		int width = metrics.stringWidth(name);
		graph.drawString(name, 895 + (320 / 2) - (width / 2), (height * i) + 50 / 2);
	    }
	    // draw myAchievements.get(name) somehow
	    i++;
	}
    }

}
