package com.chickenachiever.state;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import com.chickenachiever.map.TileMap;
import com.chickenachiever.model.Player;

public class LevelState extends GameState {

	private TileMap tileMap;

	private Player player;
	
	public LevelState(GameStateManager gsm) {
		this.gsm = gsm;
		init();
	}

	public void init() {

		//player = new Player(tileMap);
		//player.setPosition(100, 100);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

		//player.update();
	}

	@Override
	public void draw(Graphics2D g) {
		// TODO Auto-generated method stub

		//player.draw(g);
	}

	@Override
	public void keyPressed(int k) {
		// TODO Auto-generated method stub
/*
		if (k == KeyEvent.VK_LEFT){
			player.setLeft(true);
		}
		if (k == KeyEvent.VK_RIGHT){
			player.setRight(true);
		}
		if (k == KeyEvent.VK_UP){
			player.setJumping(true);
		}*/
	}

	@Override
	public void keyReleased(int k) {
		// TODO Auto-generated method stub
		/*if (k == KeyEvent.VK_LEFT){
			player.setLeft(false);
		}
		if (k == KeyEvent.VK_RIGHT){
			player.setRight(false);
		}
		if (k == KeyEvent.VK_UP){
			player.setJumping(false);
		}*/

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
