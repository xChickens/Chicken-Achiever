package com.chickenachiever.model;

import com.chickenachiever.map.TileMapRevamp;

public class DSpike extends MapElementRevamp {

	private PlayerRevamp p;
	private boolean touched;
	
	public DSpike(TileMapRevamp tm, int x, int y) {
		super(tm, x, y);
		cwidth = 10;
		cheight = 10;
		updateImage("DSpikes.png");
	}

	public void setPlayer(PlayerRevamp p) {
		this.p = p;
	}

	public void update() {
		//System.out.println("hi");
		if (p != null) {
			if (intersects(p)) {
				touched = true;
				updateImage("DSpikesTouched.png");
				p.kill();
			}
		}
	}	
	public boolean isTouched()
	{
		return touched;
	}
}
