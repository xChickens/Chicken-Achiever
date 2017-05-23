package com.chickenachiever.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.chickenachiever.map.TileMap;

public class Point extends MapElement {
	public boolean isCovered = false;
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 1 };
	private static final int IDLE = 0;
	private static final int COVERED = 1;

	public Point(TileMap tm) {
		super(tm);
	}

	public void update() {
	}

	public void draw(Graphics2D g2d) {
	}
}
