package com.chickenachiever.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.chickenachiever.map.TileMap;

public class Corpse extends MapElement {

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 1 };
	private static final int IDLE = 0;
	private static final int MOVING = 1;
	private static final int FALLING = 2;
	private Player player;

	public Corpse(TileMap map, Player player) {
		super(map);
		this.player = player;
		width = 15;// need to figure out the size of the sprites
		height = 15;
		cwidth = 10;
		cheight = 10;
		
		moveSpeed = player.moveSpeed;
		maxSpeed = player.maxSpeed;
		stopSpeed = player.stopSpeed;
		fallSpeed = player.fallSpeed;
		maxFallSpeed = player.maxFallSpeed;
		jumpStart = player.jumpStart;
		stopJumpSpeed = player.stopJumpSpeed;
		
		facingRight = player.facingRight;
		
		x = player.x;
		y = player.y;
		dx = player.dx;
		dy = player.dy;
		
	}

	public void update() {
		
		
	}

	public void draw(Graphics2D graph) {
		if (!player.isAlive()) {
			setMapPosition();
			if (facingRight) {
				graph.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2),
						null);
				// change the input based on the position of the image
			} else {// facing left
				graph.drawImage(animation.getImage(), (int) (x + xmap - width) / 2 + width,
						(int) (y + ymap - height / 2), -width, height, null);
			}
		}
	}
}
