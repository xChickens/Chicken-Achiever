package com.chickenachiever.model;

import com.chickenachiever.map.TileMapRevamp;

public class DSpike extends MapElementRevamp {

	private PlayerRevamp p;

	public DSpike(TileMapRevamp tm, int x, int y) {
		super(tm, x, y);
		updateImage("DSpikes.png");
	}

	public void setPlayer(PlayerRevamp p) {
		this.p = p;
	}

	public void update() {
		//System.out.println("hi");
		if (p != null) {
			if (intersects(p)) {
				updateImage("DSpikesTouched.png");
				p.kill();
			}
		}
	}	

}
