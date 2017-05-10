package state;

import map.Background;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import javax.swing.ImageIcon;

public class MenuState extends GameState {
    private Background bg;
    private int currentChoice = 0;
    private String[] options = { "Start", "Credits", "Quit" };
    private Font font;
    ImageIcon i = new ImageIcon(getClass().getResource(
	    "/Resources/Backgrounds/Untitled.png"));

    public MenuState(GameStateManager gsm) {
	this.gsm = gsm;
	try {
	    this.bg = new Background(this.i, 1.0D);
	    this.bg.setVector(-0.1D, 0.0D);
	    this.font = new Font("Helvetica", 0, 20);
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void init() {
    }

    public void update() {
	bg.update();
    }

    public void draw(Graphics2D g) {
	bg.draw(g);

	g.setFont(font);
	for (int i = 0; i < this.options.length; i++) {
	    if (i == currentChoice) {
		g.setColor(Color.GREEN);
	    } else {
		g.setColor(Color.RED);
	    }
	    if (i == 1) {
		g.drawString(this.options[1], 240, 170 + i * 50);
	    } else {
		g.drawString(this.options[i], 270, 170 + i * 50);
	    }
	}
    }

    private void select() {
	if (this.currentChoice == 0) {
	    this.gsm.setState(1);
	}
	if (this.currentChoice == 1) {
	    this.gsm.setState(14);
	}
	if (this.currentChoice == 2) {
	    System.exit(0);
	}
    }

    public void keyPressed(int k) {
	if (k == 10) {
	    select();
	}
	if (k == 38) {
	    this.currentChoice -= 1;
	    if (this.currentChoice == -1) {
		this.currentChoice = 2;
	    }
	}
	if (k == 40) {
	    this.currentChoice += 1;
	    if (this.currentChoice == 3) {
		this.currentChoice = 0;
	    }
	}
    }

    public void keyReleased(int k) {
    }
}