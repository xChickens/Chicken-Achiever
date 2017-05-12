package model;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class Point extends MapElement{
	  public boolean isPressed = false;
	  private ArrayList<BufferedImage[]> sprites;
	  private final int[] numFrames = { 1 };
	  private static final int IDLE = 0;
	  private static final int PRESSED = 1;
}
