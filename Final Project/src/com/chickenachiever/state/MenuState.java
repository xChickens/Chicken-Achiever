package state;

import map.Background;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

public class MenuState extends GameState {
	private Background bg;
	private int currentChoice = 0;
	ImageIcon i = new ImageIcon(getClass().getResource("/Backgrounds/Menu.png"));

	public MenuState(GameStateManager gsm) {
		this.gsm = gsm;
		try {
			this.bg = new Background(this.i, 1.0D);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void init() {
	}

	public void update() {
	}

	public void draw(Graphics2D g) {
		this.bg.draw(g);

	}

	public void keyReleased(int k) {
	}

	public void keyPressed(int k) {
	}
}