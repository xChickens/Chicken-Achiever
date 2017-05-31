package com.chickenachiever.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.chickenachiever.map.TileMap;
import com.chickenachiever.map.TileMapRevamp;

public class BlockRevamp extends MapElementRevamp {

	private ArrayList<BufferedImage[]> sprites;
	private static final int UNTOUCHED = 0;
	private static final int TOUCHED = 1;
	private boolean touched = false;

	public BlockRevamp(TileMapRevamp map, int x, int y) {
		super(map, x, y);
	}

	public void update() {
		calculateCorners(x,y);
		if(topLeft || topRight || bottomLeft || bottomRight){
			if(touched == false){
				touched = true;
			}
		}
	}
}