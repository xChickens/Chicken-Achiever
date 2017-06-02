package com.chickenachiever.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.chickenachiever.achievements.Achieve;
import com.chickenachiever.achievements.Property;
import com.chickenachiever.main.GamePanel;
import com.chickenachiever.map.TileMapRevamp;
import com.chickenachiever.model.Corpse;
import com.chickenachiever.model.MapElementRevamp;
import com.chickenachiever.model.Player;
import com.chickenachiever.model.PlayerRevamp;

public class LevelStateRevamp extends GameState {

	private TileMapRevamp tileMap;

	private PlayerRevamp player;
	private ArrayList<Corpse> corpses;
	private Achieve achieve;
	
	private int blocksTouched;
	private int spikesTouched;
	private int launchersTouched;
	
	private final int totalBlocks = 50;
	private final int totalSpikes = 20;
	private final int totalLaunchers = 8;
	private int totalAchievements;
	
	private int deathCount = 0;
	private long lifeStart;
	private long gameStart;
	private long currentTime;

	public LevelStateRevamp(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		deathCount = 0;
		lifeStart = System.currentTimeMillis()/1000;
		gameStart = System.currentTimeMillis()/1000;
		blocksTouched = 0;
		spikesTouched = 0;
		launchersTouched = 0;
		
		tileMap = new TileMapRevamp(32);
		try {
			tileMap.loadTiles("/Maps/Level 1.txt");
		} catch (Exception e) {
			e.printStackTrace();
		}
		player = new PlayerRevamp(tileMap, 100, 100);
		corpses = new ArrayList<Corpse>();
		achieve = new Achieve();

		/* template for touching x blocks
		 * 	
		 * 
		 * createProperty("xblocks", 0, achieve.ACTIVE_IF_GREATER, x - 1);
		 * setValue(blocksTouched); Property[] xblocks =
		 * {myProperties.get("xblocks")}; achieve.createAchievement("xblocks",
		 * xblocks);
		 */
		
		achieve.createProperty("fiveBlocks", 0, achieve.ACTIVE_IF_GREATER, 4);
		Property[] fiveBlocks = {achieve.getProperties().get("fiveBlocks")}; 
		achieve.createAchievement("Gettin' Touchy",fiveBlocks);
		 

		achieve.createProperty("allblocks", 0, achieve.ACTIVE_IF_EQUAL, totalBlocks);
		Property[] allblocks = { achieve.getProperties().get("allblocks") };
		achieve.createAchievement("Painter", allblocks);

		
		/* template for touching x amount of spikes
		 * 
		 * createProperty("xspikes", 0, achieve.ACTIVE_IF_GREATER, x - 1);
		 * Property[] xspikes = {achieve.getProperties().get("xspikes")};
		 * achieve.createAchievement("xspikes", xspikes);
		 */
		
		  
		 achieve.createProperty("fourSpikes", 0, achieve.ACTIVE_IF_GREATER, 4);
		 Property[] fiveSpikes = {achieve.getProperties().get("fourSpikes")};
		 achieve.createAchievement("These Blasted Spikes", fiveSpikes);
		 

		achieve.createProperty("allSpikes", 0, achieve.ACTIVE_IF_EQUAL, totalSpikes);
		Property[] allspikes = { achieve.getProperties().get("allSpikes") };
		achieve.createAchievement("Minced Chicken", allspikes);

		/*
		 * template for launcher achievements
		 * 
		 * achieve.createProperty("xlaunchers", 0, achieve.ACTIVE_IF_GREATER, x- 1); 
		 * Property[] xlaunchers = {achieve.getProperties().get("xlaunchers")};
		 * achieve.createAchievement("xlaunchers", xlaunchers);
		 */
		
		 achieve.createProperty("fiveLaunchers", 0, achieve.ACTIVE_IF_GREATER, 4); 
		 Property[] fiveLaunchers = {achieve.getProperties().get("fiveLaunchers")};
		 achieve.createAchievement("WHEEE!", fiveLaunchers); 
		 

		achieve.createProperty("allLaunchers", 0, achieve.ACTIVE_IF_EQUAL, totalLaunchers);
		Property[] allLaunchers = { achieve.getProperties().get("allLaunchers") };
		achieve.createAchievement("Chickens can Fly", allLaunchers);

	
		//template for dying x times
		 achieve.createProperty("dieOnce", 0, achieve.ACTIVE_IF_GREATER, 0);
		 Property[] dieOneTime = {achieve.getProperties().get("dieOnce")};
		 achieve.createAchievement("First Blood", dieOneTime);
		 
		 achieve.createProperty("dieTenTimes", 0, achieve.ACTIVE_IF_GREATER, 9);
		 Property[] dieTenTimes = {achieve.getProperties().get("dieTenTimes")};
		 achieve.createAchievement("Used to It", dieTenTimes);
		 
		// all achievements unlocked
		achieve.createProperty("allUnlocked", 0, achieve.ACTIVE_IF_EQUAL, totalAchievements - 1);
		Property[] allAchievements = {achieve.getProperties().get("allUnlocked")};
		achieve.createAchievement("Caught 'em All", allAchievements);
		
		/* template for timeAlive
		 * 
		 * achieve.createProperty("spent xtime Alive", 0, achieve.ACTIVE_IF_GREATER, xtime);
		Property[] timeAlive = {achieve.getProperties().get("spent xtime Alive")};
		achieve.createAchievement("spent xtime Alive", timeAlive); */

		achieve.createProperty("10sec Alive", 0, achieve.ACTIVE_IF_GREATER, 9);
		Property[] tenSecondsAlive = {achieve.getProperties().get("10sec Alive")};
		achieve.createAchievement("Stayin' Alive", tenSecondsAlive);
		
		achieve.createProperty("60sec Alive", 0, achieve.ACTIVE_IF_GREATER, 9);
		Property[] sixtySecondsAlive = {achieve.getProperties().get("60sec Alive")};
		achieve.createAchievement("Old Mother Hen", sixtySecondsAlive);
		
		
		
		achieve.createProperty("Played for 1min", 0, achieve.ACTIVE_IF_GREATER, 59);
		Property[] oneMin = {achieve.getProperties().get("Played for 1min")};
		achieve.createAchievement("Forgot to Close Game", oneMin);
	}

	@Override
	public void update() {
		
		// TODO Auto-generated method stub
		ArrayList<MapElementRevamp> objectList = tileMap.getElements();
		//System.out.println("objectlist size" + objectList.size());
		
		currentTime = System.currentTimeMillis()/1000;
		achieve.setPropValue("10sec Alive", (int)(currentTime - lifeStart));
		achieve.setPropValue("Played for 1min", (int) (currentTime - gameStart));
		achieve.setPropValue("60sec Alive", (int)(currentTime - lifeStart)); 
		
		int count = 0;
		while ((objectList != null) && (count < objectList.size())) {
			MapElementRevamp e = objectList.get(count);
			e.update();
			//System.out.println(count);
			count++;
		}
		//System.out.println("Count:" + count);
		
		player.update();
		/*if(!player.isAlive()){
			corpses.add(player.spawnCorpse());
			//player.respawn();
		}*/
		/*achieve.setPropValue("testproperty", 1);
		ArrayList<Achievement> achieved = achieve.checkAchievements();
		for (int i = 0; i < achieved.size(); i++){
			System.out.println(achieved.get(i).getName());
		}*/
		for (int i = 0; i < corpses.size(); i++) {
			corpses.get(i).update();
			if (corpses.size() > 0) {
				if (corpses.get(i).timeSinceSpawned() > 5000) {
					corpses.remove(i);
					i--;
				}
			}
		}
	}

	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub
		g.setColor(Color.WHITE);
		g.fillRect(0, 0, GamePanel.WIDTH * GamePanel.SCALE, GamePanel.HEIGHT * GamePanel.SCALE);

		tileMap.draw(g);
		player.draw(g);
		for (int i = 0; i < corpses.size(); i++) {
			corpses.get(i).draw(g);
			if (corpses.size() > 0) {
				if (corpses.get(i).timeSinceSpawned() > 5000) {
					corpses.remove(i);
					i--;
				}
			}

		}	
		achieve.draw(g);
	}

	public void keyPressed(int k) {
		// TODO Auto-generated method stub

		if (k == KeyEvent.VK_LEFT) {
			player.setLeft(true);
		}
		if (k == KeyEvent.VK_RIGHT) {
			player.setRight(true);
		}
		if (k == KeyEvent.VK_UP) {
			player.setJumping(true);
		}
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		if (k == KeyEvent.VK_LEFT) {
			player.setLeft(false);
		}
		if (k == KeyEvent.VK_RIGHT) {
			player.setRight(false);
		}
		if (k == KeyEvent.VK_UP) {
			player.setJumping(false);
		}

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
