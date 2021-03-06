package com.chickenachiever.model;

import java.awt.Graphics2D;

import com.chickenachiever.map.TileMap;

public class Corpse extends Player {

    private long spawnTime;

    public Corpse(TileMap map, Player player) {
	super(map, player.getx(), player.gety());
	setPosition(player.getx(), player.gety());
	dx = player.dx;
	dy = player.dy;
	facingRight = player.facingRight;
	jumping = player.jumping;
	falling = player.falling;
	loadSprites("DeadChickenSet.gif");

	spawnTime = System.nanoTime() / 1000000;

    }

    public void update() {
	super.update();
    }

    public void draw(Graphics2D graph) {

	setMapPosition();
	// draw player
	if (facingRight) {
	    graph.drawImage(animation.getImage(), (int) (x + xmap - width / 2) + width, (int) (y + ymap + height / 2 + 10), -width, -height, null);

	    // change the input based on the position of the image
	} else {// facing left
	    graph.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap + height / 2 + 10), width, -height, null);
	}
    }

    public int timeSinceSpawned() {
	return (int) ((System.nanoTime() / 1000000) - spawnTime);
    }

}
