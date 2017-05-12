package model;

import java.awt.image.BufferedImage;

public class Animation {
	private BufferedImage[] frames;
	private int currentFrame;
	private long startTime;
	private long delay;
	private boolean playedOnce;

	public Animation() {
		playedOnce = false;
	}

	public void setFrames(BufferedImage[] frames) {

	}

	public void setDelay(long d) {

	}

	public void update() {

	}

	public int getFrame() {
		return 0;

	}

	public BufferedImage getImage() {
		return null;

	}

	public boolean hasPlayedOnce() {
		return playedOnce;

	}
}
