package com.chickenachiever.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.chickenachiever.map.TileMap;
import com.chickenachiever.map.TileMapRevamp;

public class USpike extends MapElementRevamp {

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 1 };
	private static final int IDLE = 0;
	private PlayerRevamp p;

	public USpike(TileMapRevamp tm, int x, int y) {
		super(tm, x, y);
		cwidth = 10;
		cheight = 10;
	}

	public void setPlayer(PlayerRevamp p) {
		this.p = p;
	}

	public void update() {
		//System.out.println("hi");
		if (p != null) {
			if (intersects(p)) {
				p.kill();
			}
		}
	}

	public void draw(Graphics2D g2d) {
	}

}
