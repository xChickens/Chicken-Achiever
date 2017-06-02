package com.chickenachiever.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.chickenachiever.map.TileMapRevamp;

public class Launcher extends MapElementRevamp {

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 1 };
	private static final int IDLE = 0;
	private PlayerRevamp p;

	public Launcher(TileMapRevamp tm, int x, int y) {
		super(tm, x, y);
	}

	public void setPlayer(PlayerRevamp p) {
		this.p = p;
	}

	public void update(PlayerRevamp p) {
		if (intersects(p)){
			p.dy = -6;
		}
		
	}

	public void draw(Graphics2D g2d) {
	}

}
