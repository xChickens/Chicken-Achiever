package com.chickenachiever.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.chickenachiever.achievements.Achieve;
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

	public LevelStateRevamp(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		tileMap = new TileMapRevamp(32);
		try {
			tileMap.loadTiles("/Maps/level1.map");
			tileMap.setPosition(0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		player = new PlayerRevamp(tileMap, 100, 100);
		corpses = new ArrayList<Corpse>();
		achieve = new Achieve();
		// System.out.println("levelstate init");
		// System.out.println(player.getx() + " " + player.gety());
		// System.exit(0);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		ArrayList<MapElementRevamp> objectList = tileMap.getElements();
		
		int count = 0;
		while ((objectList != null) && (count < objectList.size())) {
			MapElementRevamp e = objectList.get(count);
			e.update();
			count++;
		}
		
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
