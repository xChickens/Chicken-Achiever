package com.chickenachiever.model;

import java.awt.Graphics2D;

import com.chickenachiever.map.TileMap;

public class Launcher extends MapElement {

	private Player p;

	public Launcher(TileMap tm, int x, int y) {
		super(tm, x, y);
		updateImage("Launcher.png");
		cwidth = 10;
		cheight = 10;
	}

	public void setPlayer(Player p) {
		this.p = p;
	}

	public void update() {
		if (p != null) {
			if (intersects(p)) {
				touched = true;
				p.dy += -2;
				updateImage("LauncherTouched.png");
			}
		}
		
	}

	public void draw(Graphics2D g2d) {
	}

}
