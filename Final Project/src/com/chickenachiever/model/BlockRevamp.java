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

	public BlockRevamp(TileMap map) {
		super(map);
	}

	public BlockRevamp(TileMapRevamp map) {
		super(map);
	}

	public void update() {
		calculateCorners(x,y);
		if(topLeft || topRight || bottomLeft || bottomRight){
			if(touched == false){
				touched = true;
			}
		}
	}

	public void draw(Graphics2D graph) {
	}
}