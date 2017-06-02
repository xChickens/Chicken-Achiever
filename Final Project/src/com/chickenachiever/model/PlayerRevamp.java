package com.chickenachiever.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.chickenachiever.map.TileMap;
import com.chickenachiever.map.TileMapRevamp;

public class PlayerRevamp extends MapElementRevamp {
	protected ArrayList<BufferedImage[]> sprites;
	protected final int[] numFrames = { 1, 5, 3, 3 };// need to figure out how
	// many frames
	protected static final int IDLE = 0;
	protected static final int MOVING = 1;
	protected static final int JUMPING = 2;
	protected static final int FALLING = 3;
	private boolean alive;

	public PlayerRevamp(TileMapRevamp map, int x , int y) {
		super(map, x, y); // set tile map and tile size

		width = 32;// need to figure out the size of the sprites
		height = 34;
		cwidth = 28;
		cheight = 30;

		moveSpeed = 0.7;
		maxSpeed = 3;
		stopSpeed = 0.4;
		fallSpeed = 0.5;
		maxFallSpeed = 4.0;
		jumpStart = -9;
		stopJumpSpeed = 0.3;

		facingRight = true;
		loadSprites();
		animation = new Animation();
		currentAction = IDLE;
		if(animation == null){
			System.out.println("hi");
		}
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);
		alive = true;

	}
	
	public void update() {

		// update position
		// System.out.println("player update");
		// System.out.println(getx() + " " + gety());
		getNextPosition();
		// System.out.println(getx() + " " + gety());
		checkTileMapCollision();
		// System.out.println(getx() + " " + gety());
		setPosition(xtemp, ytemp);
		// System.out.println(getx() + " " + gety());
		// System.out.println(xtemp + " " + ytemp);

		// set animation
		if (dy > 0) {
			if (currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(sprites.get(FALLING));
				animation.setDelay(100);
				width = 40;
			}
		} else if (dy < 0) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(sprites.get(JUMPING));
				animation.setDelay(-1);
				width = 40;
			}
		} else if (left || right) {
			if (currentAction != MOVING) {
				currentAction = MOVING;
				animation.setFrames(sprites.get(MOVING));
				animation.setDelay(40);
				width = 40;
			}
		} else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(sprites.get(IDLE));
				animation.setDelay(400);
				width = 40;
			}
		}

		animation.update();

		if (right) {
			facingRight = true;
		}
		if (left) {
			facingRight = false;
		}

	}

	public void draw(Graphics2D graph) {

		// if (alive) {

		setMapPosition();
		// draw player
		if (facingRight) {
			graph.drawImage(animation.getImage(), (int) (x + xmap - width / 2) + width, (int) (y + ymap - height / 2), -width, height, null);
			
			// change the input based on the position of the image
		} else {// facing left
			graph.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);
		}
		// }
	}

	private void getNextPosition() {
		// movement
		if (left) {
			dx -= moveSpeed;// increase the speed to the left until you hit left
			// speed
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= stopSpeed; // slow down until you reach 0;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}

		if (jumping && !falling) {
			dy = jumpStart;
			falling = true;
		}

		if (falling) {

			dy += fallSpeed;
			if (dy > 0) {
				jumping = false;
			}
			if (dy < 0 && !jumping) {
				dy += stopJumpSpeed;
			}
			if (dy > maxFallSpeed) {
				dy = maxFallSpeed;
			}
		}
	}
	protected void loadSprites(){
		try {
			BufferedImage loadSprites = ImageIO.read(getClass().getResourceAsStream("/Elements/Chickenv4.gif"));

			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 4; i++) {
				BufferedImage[] images = new BufferedImage[numFrames[i]];
				for (int j = 0; j < images.length; j++) {
					if (i == 3){
						images[j] = loadSprites.getSubimage(j * (width+10), 5+ (i * height), width+10, height);
					}
					else {
						images[j] = loadSprites.getSubimage(5+j * width, 5+i * height, width, height);
					}
				}
				sprites.add(images);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void kill() {
		alive = false;
	}

	public void respawn() {
		alive = true;
		this.setPosition(450, 230);
	}

	public boolean isAlive() {
		return alive;
	}
	
	public Corpse spawnCorpse(){
		return new Corpse(tileMap,this);
	}
}
