package com.chickenachiever.model;

import com.chickenachiever.map.TileMap;

public class Block extends MapElement {

    private Player p;

    public Block(TileMap map, int x, int y) {
	super(map, x, y);
	cwidth = 32;
	cheight = 32;
	updateImage("Block.png");
    }

    public void setPlayer(Player p) {
	this.p = p;
    }

    public void update() {
	if (p != null) {
	    if (intersects(p) && touched == false) {
		updateImage("BlockTouched.png");
		touched = true;
	    }
	}
    }

}