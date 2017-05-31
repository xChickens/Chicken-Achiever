package com.chickenachiever.state;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.chickenachiever.achievements.Achieve;
import com.chickenachiever.achievements.Achievement;
import com.chickenachiever.main.GamePanel;
import com.chickenachiever.map.TileMap;
import com.chickenachiever.model.Corpse;
import com.chickenachiever.model.Player;

public class LevelState extends GameState {

	private TileMap tileMap;

	private Player player;
	private ArrayList<Corpse> corpses;
	private Achieve achieve;

	public LevelState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		tileMap = new TileMap(30);
		try {
			BufferedImage i = ImageIO.read(getClass().getResourceAsStream("/Elements/testtileset.gif"));
			tileMap.loadTiles(i);
			tileMap.loadMap("/Maps/level1.map");
			tileMap.setPosition(0, 0);
		} catch (Exception e) {
			e.printStackTrace();
		}
		player = new Player(tileMap);
		player.setPosition(100, 100);
		corpses = new ArrayList<Corpse>();
		achieve = new Achieve();
		// System.out.println("levelstate init");
		// System.out.println(player.getx() + " " + player.gety());
		// System.exit(0);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		player.update();
		if(!player.isAlive()){
			corpses.add(player.spawnCorpse());
			//player.respawn();
		}
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
