package com.chickenachiever.model;

import java.awt.Rectangle;

import com.chickenachiever.map.TileMap;

public abstract class MapElement {
	
	// Tiles
	protected TileMap tileMap;
	protected int tileSize;
	protected double xmap;
	protected double ymap;
	
	// Position
	protected double x;
	protected double y;
	protected double dx;
	protected double dy;
	
	// Dimensions
	protected int width;
	protected int height;
	
	// Collision Dimensions
	protected int cwidth;
	protected int cheight;
	
	// Collision
	protected int currRow;
	protected int currCol;
	protected double xdest;
	protected double ydest;
	protected double xtemp;
	protected double ytemp;
	protected boolean topLeft;
	protected boolean topRight;
	protected boolean bottomLeft;
	protected boolean bottomRight;
	
	// Animation
	protected Animation animation;
	protected int currentAction;
	protected int previousAction;
	protected boolean facingRight;
	
	// Movement
	protected boolean left;
	protected boolean right;
	protected boolean up;
	protected boolean down;
	protected boolean jumping;
	protected boolean falling;
	
	// Movement Speeds
	protected double moveSpeed;
	protected double maxSpeed;
	protected double stopSpeed;
	protected double fallSpeed;
	protected double maxFallSpeed;
	protected double jumpStart;
	protected double stopJumpSpeed;

	public MapElement(TileMap tm) {
		tileMap = tm;
		tileSize = tm.getTileSize();
	}

	public boolean intersects(MapElement e) {
		return false;

	}

	public Rectangle getRectangle() {
		return null;
	}

	public void calculateCorners(double x, double y) {
	}

	public void checkTileMapCollision() {
	}
	
	public int getx()
	  {
	    return (int)this.x;
	  }
	  
	  public int gety()
	  {
	    return (int)this.y;
	  }
	  
	  public int getWidth()
	  {
	    return this.width;
	  }
	  
	  public int getHeight()
	  {
	    return this.height;
	  }
	  
	  public int getCWidth()
	  {
	    return this.cwidth;
	  }
	  
	  public int getCHeight()
	  {
	    return this.cheight;
	  }
	  
	  public void setPosition(double x, double y)
	  {
	    this.x = x;
	    this.y = y;
	  }
	  
	  public void setVector(double dx, double dy)
	  {
	    this.dx = dx;
	    this.dy = dy;
	  }
	  
	  public void setMapPosition()
	  {
	    this.xmap = this.tileMap.getx();
	    this.ymap = this.tileMap.gety();
	  }
	  
	  public void setLeft(boolean b)
	  {
	    this.left = b;
	  }
	  
	  public void setRight(boolean b)
	  {
	    this.right = b;
	  }
	  
	  public void setUp(boolean b)
	  {
	    this.up = b;
	  }
	  
	  public void setDown(boolean b)
	  {
	    this.down = b;
	  }
	  
	  public void setJumping(boolean b)
	  {
	    this.jumping = b;
	  }
	  
}
