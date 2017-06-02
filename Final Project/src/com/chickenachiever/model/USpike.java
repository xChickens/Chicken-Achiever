package com.chickenachiever.model;

import com.chickenachiever.map.TileMapRevamp;

public class USpike extends MapElementRevamp {

	private PlayerRevamp p;
	private boolean touched;

	public USpike(TileMapRevamp tm, int x, int y) {
		super(tm, x, y);
		cwidth = 10;
		cheight = 10;
		updateImage("USpikes.png");
	}

	public void setPlayer(PlayerRevamp p) {
		this.p = p;
	}

	public void update() {
		if (p != null) {
			if (intersects(p)) {
				touched = true;
				updateImage("USpikesTouched.png");
				p.kill();
			}
		}
	}
	
	public boolean isTouched()
	{
		return touched;
	}
}
