package com.chickenachiever.model;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;

import javax.swing.ImageIcon;

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
    public double dx;
    public double dy;

    // Dimensions
    protected int imageWidth;
    protected int imageHeight;
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
    protected boolean touched;
    protected boolean checked = false;

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

    // image
    protected Image image;

    public MapElement(TileMap tm, int x, int y) {
	this.x = x;
	this.y = y;
	tileMap = tm;
	tileSize = tm.getTileSize();
    }

    public boolean intersects(Player p) {
	Rectangle r1 = getRectangle();
	Rectangle r2 = p.getRectangle(cwidth, cheight);
	return r1.intersects(r2);

    }

    public Rectangle getRectangle() {
	return new Rectangle((int) (x), (int) (y), cwidth, cheight);
    }

    public Rectangle getRectangle(int cwidth, int cheight) {
	return new Rectangle((int) x - cwidth / 2, (int) y - cheight / 2, cwidth, cheight);
    }

    public void draw(Graphics g) {
	int xCoord = (int) ((x + (width / 2)));
	int yCoord = (int) ((y + (height / 2)));
	g.drawImage(image, xCoord, yCoord, null);
    }

    public void update() {
    }

    public void setPlayer(Player p) {

    }

    protected void updateImage(String path) {
	image = new ImageIcon(getClass().getResource("/Elements/" + path)).getImage();
	imageWidth = image.getWidth(null);
	imageHeight = image.getHeight(null);
    }

    public void calculateCorners(double x, double y) {
	int leftTile = (int) (x - cwidth / 2) / tileSize;
	int rightTile = (int) (x + cwidth / 2 - 1) / tileSize;
	int topTile = (int) (y - cheight / 2) / tileSize;
	int bottomTile = (int) (y + cheight / 2 - 1) / tileSize;

	int tleft = tileMap.getType(topTile, leftTile);// find out what type of
						       // block it is
	int tright = tileMap.getType(topTile, rightTile);
	int bleft = tileMap.getType(bottomTile, leftTile);
	int bright = tileMap.getType(bottomTile, rightTile);

	topLeft = (tleft == TileMap.BLOCKED);
	topRight = (tright == TileMap.BLOCKED);
	bottomLeft = (bleft == TileMap.BLOCKED);
	bottomRight = (bright == TileMap.BLOCKED);
    }

    public void checkTileMapCollision() {
	currCol = (int) x / tileSize;
	currRow = (int) y / tileSize;

	xdest = x + dx;
	ydest = y + dy;

	xtemp = x;
	ytemp = y;

	calculateCorners(x, ydest);
	if (dy < 0) {
	    if (topLeft || topRight) {
		dy = 0;
		ytemp = currRow * tileSize + cheight / 2;
	    } else {
		ytemp += dy;
	    }
	}
	if (dy > 0) {
	    if (bottomLeft || bottomRight) {
		dy = 0;
		falling = false;
		ytemp = (currRow + 1) * tileSize - cheight / 2;
	    } else {
		ytemp += dy;
	    }
	}

	calculateCorners(xdest, y);
	if (dx < 0) {
	    if (topLeft || bottomLeft) {
		dx = 0;
		xtemp = currCol * tileSize + cwidth / 2;
	    } else {
		xtemp += dx;
	    }
	}
	if (dx > 0) {
	    if (topRight || bottomRight) {
		dx = 0;
		xtemp = (currCol + 1) * tileSize - cwidth / 2;
	    } else {
		xtemp += dx;
	    }
	}
	if (!falling) {
	    calculateCorners(x, ydest + 1);
	    if (!bottomLeft && !bottomRight) {
		falling = true;
	    }
	}
    }

    public int getx() {
	return (int) this.x;
    }

    public int gety() {
	return (int) this.y;
    }

    public int getWidth() {
	return this.width;
    }

    public int getHeight() {
	return this.height;
    }

    public int getCWidth() {
	return this.cwidth;
    }

    public int getCHeight() {
	return this.cheight;
    }

    public void setPosition(double x, double y) {
	this.x = x;
	this.y = y;
    }

    public void setVector(double dx, double dy) {
	this.dx = dx;
	this.dy = dy;
    }

    public void setMapPosition() {
	this.xmap = this.tileMap.getx();
	this.ymap = this.tileMap.gety();
    }

    public void setLeft(boolean b) {
	this.left = b;
    }

    public void setRight(boolean b) {
	this.right = b;
    }

    public void setUp(boolean b) {
	this.up = b;
    }

    public void setDown(boolean b) {
	this.down = b;
    }

    public void setJumping(boolean b) {
	this.jumping = b;
    }

    public boolean isTouched() {
	return touched;
    }

    public void nowChecked() {
	checked = true;
    }

    public boolean checked() {
	return checked;
    }
    // public boolean onScreen() {
    // return x + xmap + width < 0 || x + xmap - width > GamePanel.WIDTH || y +
    // ymap + height < 0 || y + ymap - height > GamePanel.HEIGHT;
    // }
}
