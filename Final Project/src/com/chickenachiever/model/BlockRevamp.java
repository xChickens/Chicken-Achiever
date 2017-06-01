package com.chickenachiever.model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

import com.chickenachiever.map.TileMapRevamp;

public class BlockRevamp extends MapElementRevamp {

	private static final int UNTOUCHED = 0;
	private static final int TOUCHED = 1;
	private boolean touched;
	private boolean solid;

	public BlockRevamp(TileMapRevamp map, int x, int y) {
		super(map, x , y);
		solid = true;
		touched = false;
		updateImage("testblock.png");
	}

	public void update() {
		/*calculateCorners(x,y);
		if(topLeft || topRight || bottomLeft || bottomRight){
			if(touched == false){
				touched = true;
			}
		}*/
	}

    public boolean isSolid()
    {
        return solid;
    }
}