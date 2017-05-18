package com.chickenachiever.model;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import com.chickenachiever.map.TileMap;

public class Player extends MapElement {
    private ArrayList<BufferedImage[]> sprites;
    private final int[] numFrames = { 1, 1, 1, 1 };// need to figure out how
													// many frames
	private static final int IDLE = 0;
	private static final int MOVING = 1;
	private static final int JUMPING = 2;
    private static final int FALLING = 3;
    private boolean alive;

	


    public Player(TileMap map) {
	super(map); // set tile map and tile size

		width = 40;// need to figure out the size of the sprites
		height = 40;
		cwidth = 30;
		cheight = 30;

		moveSpeed = 0.3;
		maxSpeed = 1.6;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		// maxFallSpeed = 4.0
		jumpStart = -4.8;
		stopJumpSpeed = 0.3;

		try {
			BufferedImage loadSprites = ImageIO.read(getClass().getResourceAsStream("insert spritesheet location"));

			sprites = new ArrayList<BufferedImage[]>();
			for (int i = 0; i < 4; i++) {
				BufferedImage[] images = new BufferedImage[numFrames[i]];
				for (int j = 0; j < images.length; j++) {
					images[j] = loadSprites.getSubimage(j * width, i * height, width, height);
				}
				sprites.add(images);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(sprites.get(IDLE));
		animation.setDelay(400);

	}

	public void update() {

		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

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

		if (alive) {

			setMapPosition();
			// draw player
			if (facingRight) {
				graph.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2),
						null);
				// change the input based on the position of the image
			} else {// facing left
				graph.drawImage(animation.getImage(), (int) (x + xmap - width) / 2 + width,
						(int) (y + ymap - height / 2), -width, height, null);
			}
		}
	}
	
	private void getNextPosition(){
		//movement
		if(left){
			dx -= moveSpeed;//increase the speed to the left until you hit left speed
			if(dx < -maxSpeed){
				dx = -maxSpeed;
			}
		}
		else if(right){
			dx+=moveSpeed;
			if(dx>maxSpeed){
				dx = maxSpeed;
			}
		}
		else{
			if(dx>0){
				dx -=stopSpeed; //slow down until you reach 0;
				if (dx < 0){
					dx = 0;
				}
			}
			else if (dx < 0){
				dx += stopSpeed;
				if (dx > 0){
					dx = 0;
				}
			}
		}
		
		if (jumping && !falling){
			dy =jumpStart;
			falling = true;
		}
		
		if (falling){
			
			dy += fallSpeed;
			if (dy > 0){
				jumping = false;
			}
			if (dy < 0 && !jumping){
				dy+=stopJumpSpeed;
			}
			if (dy > maxFallSpeed){
				dy = maxFallSpeed;
			}
		}
	}

	
	public void kill() {
		alive = false;
	}

	public void respawn() {
		alive = true;
	}

	public boolean isAlive() {
		return alive;
	}
}
