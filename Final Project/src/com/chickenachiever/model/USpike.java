package com.chickenachiever.model;

import com.chickenachiever.map.TileMap;

public class USpike extends MapElement {

	private Player p;

	public USpike(TileMap tm, int x, int y) {
		super(tm, x, y);
		cwidth = 10;
		cheight = 10;
		updateImage("USpikes.png");
	}

	public void setPlayer(Player p) {
		this.p = p;
	}

	public void update() {
		if (p != null) {
			if (intersects(p)) {
				touched = true;
				updateImage("USpikesTouched.png");
				p.kill();
			}
		}
	}
	
}
