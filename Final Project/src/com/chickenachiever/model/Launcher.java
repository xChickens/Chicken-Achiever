package com.chickenachiever.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.chickenachiever.map.TileMap;
import com.chickenachiever.map.TileMapRevamp;

public class Launcher extends MapElementRevamp {

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 1 };
	private static final int IDLE = 0;
	private PlayerRevamp p;

	public Launcher(TileMapRevamp map, int x, int y) {
		super(map, x , y);
		cwidth = 32;
		cheight = 32;
		touched = false;
		//updateImage("Laucher.png");
	}

	public void update() {
		if (intersects(p)){
			p.fallSpeed = -0.4;
		}
		else 
			p.fallSpeed = 0.15;
		
	}

	public void draw(Graphics2D g2d) {
	}

}
