package com.chickenachiever.state;

import java.awt.Graphics2D;

import javax.swing.ImageIcon;

import com.chickenachiever.main.GamePanel;
import com.chickenachiever.map.Background;
import com.chickenachiever.map.Button;

public class MenuState extends GameState {
    private Background bg;
    private int currentChoice = 0;
    private ImageIcon i = new ImageIcon(getClass().getResource("/Backgrounds/Menu.png"));
    private Button playButton;
    private Button quitButton;
    private Button creditsButton;

    public MenuState(GameStateManager gsm) {
	this.gsm = gsm;
	try {
	    this.bg = new Background(this.i, 1.0D);
	} catch (Exception e) {
	    e.printStackTrace();
	}

	playButton = new Button(GamePanel.WIDTH / 2 - 110, 220, 220, 60, "Play");
	quitButton = new Button(GamePanel.WIDTH / 2 - 110, 310, 220, 60, "Quit");
	creditsButton = new Button(GamePanel.WIDTH / 2 - 110, 400, 220, 60, "Credits");

    }

    public void init() {
    }

    public void update() {
    }

    public void draw(Graphics2D g) {
	bg.draw(g);
	playButton.draw(g);
	quitButton.draw(g);
	creditsButton.draw(g);

    }

    public void keyReleased(int k) {
    }

    public void keyPressed(int k) {
    }
}