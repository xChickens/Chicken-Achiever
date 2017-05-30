package com.chickenachiever.achievements;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class Achieve {

	public final String ACTIVE_IF_GREATER = ">";
	public final String ACTIVE_IF_EQUAL = "==";
	public final String ACTIVE_IF_LESS = "<";

	private HashMap<String,Property> myProperties;
	private HashMap<String,Achievement> myAchievements;

	public Achieve(){
		myProperties = new HashMap<String,Property>();
		myAchievements = new HashMap<String,Achievement>();
		createProperty("testproperty",1,ACTIVE_IF_EQUAL,1);
		Property[] testarray= {myProperties.get("testproperty")};
		createAchievement("testachievement",testarray);
		//System.out.println("hi");
	}
	
	public void createProperty(String name, int initVal, String activate, int activeValue){
		myProperties.put(name, new Property(name,initVal,activate,activeValue));
	}
	public void createAchievement(String name, Property[] props){
		myAchievements.put(name,new Achievement(name,props));
	}
	
	public int getPropValue(String name){
		return myProperties.get(name).getValue();
	}
	
	public void setPropValue(String name, int val){
		myProperties.get(name).setValue(val);
	}
	
	public ArrayList<Achievement> checkAchievements(){
		ArrayList<Achievement> unlocked = new ArrayList<Achievement>();
		Iterator<String> iter = myAchievements.keySet().iterator();
		while(iter.hasNext()){
			String name = iter.next();
			if(myAchievements.get(name).getUnlocked()){
				unlocked.add(myAchievements.get(name));
			}
			else{
				if(myAchievements.get(name).isUnlocked()){
					unlocked.add(myAchievements.get(name));
				}
			}
		}
		return unlocked;
	}
	
	public void draw(Graphics2D graph){
		checkAchievements();
		Iterator<String> iter = myAchievements.keySet().iterator();
		while(iter.hasNext()){
			String name = iter.next();
			if(myAchievements.get(name).getUnlocked()){
				graph.drawRect(700,300,10,10);//i just put in random position numbers for now
				graph.setColor(Color.BLACK);
				graph.fillRect(700,300, 10, 10);
				graph.drawString(name, 700, 700);
			}
			// draw myAchievements.get(name) somehow	
		}
	}
	
	
	/* add a method to update an array of properties, if needed*/
}
