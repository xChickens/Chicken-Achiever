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
		//System.out.println("hi");
		

	}
	
	public HashMap<String,Property> getProperties(){
		return myProperties;
	}
	
	public HashMap<String, Achievement> getAchievements(){
		return myAchievements;
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
	
	public ArrayList<Achievement> checkAchievements(){//returns array of newly achieved achievements
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
	public void drawOnPlayableArea(Graphics2D graph, ArrayList<Achievement> Achievements){//draw the achievement on the actual level once it is achieved
		ArrayList<Achievement> newAchievements = new ArrayList<Achievement>();
		for(int i = 0; i < Achievements.size();i++){
			if(Achievements.get(i).timeSinceAchieved() < 5000){
				newAchievements.add(Achievements.get(i));
			}
		}
		
		int height = 40;
		for (int i = 0; i < newAchievements.size(); i++){
			String name = newAchievements.get(i).getName();
			graph.setColor(new Color(0, 0, 0, 60));
			//768 is the bottom of the screen
			graph.fillRect(0,640-height- (height*i), 400,  height);
			graph.setColor(new Color(0, 255, 0, 90));
			
			graph.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
			FontMetrics metrics = graph.getFontMetrics();
			int Swidth = metrics.stringWidth(name + " unlocked!");
			int Sheight = metrics.getHeight();
			graph.drawString(name + " unlocked!", 0 + (400/2) - (Swidth/2), 640-height - (height*i)+Sheight/2+5);
			if(newAchievements.get(i).timeSinceAchieved() > 5000){
				newAchievements.remove(i);
				i--;
			}
			
		}
	}
	public void draw(Graphics2D graph){
		ArrayList<Achievement> Achievements = checkAchievements();
		drawOnPlayableArea(graph, Achievements);
		//draw achievements beginning at position x: x = 720
		int height = 30;
		int i = 0;
		Iterator<String> iter = myAchievements.keySet().iterator();
		while(iter.hasNext()){
			String name = iter.next();
			if(myAchievements.get(name).getUnlocked()){
				//graph.drawRect(850,300,200,200);//i just put in random position numbers for now
				graph.setColor(Color.BLACK);
				graph.fillRect(880,0+ (height*i), 320,  height);
				graph.setColor(Color.GREEN);
				
				graph.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
				FontMetrics metrics = graph.getFontMetrics();
				int width = metrics.stringWidth(name);
				graph.drawString(name, 880 + (320/2) - (width/2), (height*i) + 50/2);
				
			}
			else{
				graph.setColor(Color.GRAY);
				graph.fillRect(880,0+ (height*i), 320,  height);
				graph.setColor(Color.GREEN);
				
				graph.setFont(new Font("Comic Sans MS",Font.PLAIN,30));
				FontMetrics metrics = graph.getFontMetrics();
				int width = metrics.stringWidth(name);
				graph.drawString(name, 880 +(320/2) - (width/2), (height*i) + 50/2);
			}
			// draw myAchievements.get(name) somehow	
			i++;
		}
	}
	
	
	/* add a method to update an array of properties, if needed*/
}
