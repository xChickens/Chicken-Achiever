package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Corpse extends MapElement {

	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 1 };
	private static final int IDLE = 0;
	private static final int MOVING = 1;
	private static final int FALLING = 2;
}
