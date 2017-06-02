package com.chickenachiever.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.chickenachiever.map.TileMapRevamp;

public class BlockRevamp extends MapElementRevamp {

	private static final int UNTOUCHED = 0;
	private static final int TOUCHED = 1;
	private PlayerRevamp p;

	public BlockRevamp(TileMapRevamp map, int x, int y) {
		super(map, x , y);
		cwidth = 32;
		cheight = 32;
		updateImage("Block.png");
	}

	public void setPlayer(PlayerRevamp p) {
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