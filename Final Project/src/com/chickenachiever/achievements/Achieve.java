package com.chickenachiever.achievements;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
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
		createProperty("testproperty1",0,ACTIVE_IF_EQUAL,1);
		Property[] testarray1= {myProperties.get("testproperty1")};
		createAchievement("testachievement1",testarray1);
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
		//draw achievements beginning at position x: x = 720
		int height = 30;
		int i = 0;
		Iterator<String> iter = myAchievements.keySet().iterator();
		while(iter.hasNext()){
			String name = iter.next();
			if(myAchievements.get(name).getUnlocked()){
				//graph.drawRect(850,300,200,200);//i just put in random position numbers for now
				graph.setColor(Color.GRAY);
				graph.fillRect(720,0+ (height*i), 320,  height);
				graph.setColor(Color.GREEN);
				
				graph.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
				FontMetrics metrics = graph.getFontMetrics();
				int width = metrics.stringWidth(name);
				graph.drawString(name, 720 + (320/2) - (width/2), (height*i) + 50/2);
				
			}
			else{
				graph.setColor(Color.BLACK);
				graph.fillRect(720,0+ (height*i), 320,  height);
				graph.setColor(Color.GREEN);
				
				graph.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
				FontMetrics metrics = graph.getFontMetrics();
				int width = metrics.stringWidth(name);
				graph.drawString(name, 720 +(320/2) - (width/2), (height*i) + 50/2);
			}
			// draw myAchievements.get(name) somehow	
			i++;
		}
	}
	
	
	/* add a method to update an array of properties, if needed*/
}
