package com.chickenachiever.model;

import com.chickenachiever.map.TileMap;

public class DSpike extends MapElement {

    private Player p;

    public DSpike(TileMap tm, int x, int y) {
	super(tm, x, y);
	cwidth = 20;
	cheight = 20;
	updateImage("DSpikes.png");
    }

    public void setPlayer(Player p) {
	this.p = p;
    }

    public void update() {
	if (p != null) {
	    if (intersects(p)) {
		touched = true;
		updateImage("DSpikesTouched.png");
		p.kill();
	    }
	}
    }

}
