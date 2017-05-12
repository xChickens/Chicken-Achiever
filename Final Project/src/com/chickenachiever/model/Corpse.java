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

	public Corpse(TileMap map) {
		super(map);
	}

	public void update() {
	}

	public void draw(Graphics2D graph) {
	}

}
