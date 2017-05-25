package com.chickenachiever.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.chickenachiever.map.TileMap;

public class Corpse extends Player {

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 1 };
	private static final int IDLE = 0;
	private static final int MOVING = 1;
	private static final int FALLING = 2;
	private Player player;
	private long spawnTime;

	public Corpse(TileMap map, Player player) {
		super(map);
		this.player = player;
		setPosition(player.getx(), player.gety());
		dx = player.dx;
		dy = player.dy;
		facingRight = player.facingRight;
		jumping = player.jumping;
		falling = player.falling;
		
		spawnTime = System.nanoTime()/1000000;
		
	}

	public void update() {
		super.update();
	}

	public void draw(Graphics2D graph) {
		//if (((System.nanoTime()*1000000) - spawnTime) < 10000) {
			super.draw(graph);
		//}
	}
	
	public int timeSinceSpawned(){
		return (int)((System.nanoTime()/1000000) - spawnTime);
	}
	
}
