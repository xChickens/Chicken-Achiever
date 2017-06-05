package com.chickenachiever.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.chickenachiever.map.TileMap;
import com.chickenachiever.map.TileMapRevamp;

public class Corpse extends PlayerRevamp {

	private PlayerRevamp player;
	private long spawnTime;

	public Corpse(TileMapRevamp map, PlayerRevamp player) {
		super(map, player.getx(), player.gety());
		this.player = player;
		setPosition(player.getx(), player.gety());
		dx = player.dx;
		dy = player.dy;
		facingRight = player.facingRight;
		jumping = player.jumping;
		falling = player.falling;
		loadSprites("DeadChickens.gif");
		
		spawnTime = System.nanoTime()/1000000;
		
	}

	public void update() {
		super.update();
	}

	public void draw(Graphics2D graph) {
		//if (((System.nanoTime()*1000000) - spawnTime) < 10000) {
	    	
	    	setMapPosition();
		// draw player
		if (facingRight) {
			graph.drawImage(animation.getImage(), (int) (x + xmap - width / 2) + width, (int) (y + ymap + height / 2), -width, -height, null);
			
			// change the input based on the position of the image
		} else {// facing left
			graph.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap + height / 2), width, -height, null);
		}
		//}
	}
	
	public int timeSinceSpawned(){
		return (int)((System.nanoTime()/1000000) - spawnTime);
	}
	
	
}
