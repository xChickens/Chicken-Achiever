package com.chickenachiever.map;

import java.awt.Graphics2D;
import java.awt.Image;
import javax.swing.ImageIcon;

public class Background {
	private Image image;
	private double x;
	private double y;

	public Background(ImageIcon i) {
		try {
			this.image = i.getImage();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setPosition(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void draw(Graphics2D g) {
		g.drawImage(this.image, (int) this.x, (int) this.y, null);
	}
}
