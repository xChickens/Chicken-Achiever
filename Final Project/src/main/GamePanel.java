package main;

import javax.swing.JPanel;

import state.GameStateManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // Dimensions
    public static final int WIDTH = 1024;
    public static final int HEIGHT = 768;

    // Thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;

    // Images
    private BufferedImage image;
    private Graphics2D g;
    
    // GSM
    private GameStateManager gsm;

    public void run() {
	init();

	long start;
	long elapsed;
	long wait;

	while (running) {
	    start = System.nanoTime();

	    update();
	    draw();
	    drawToScreen();

	    elapsed = System.nanoTime() - start;

	    wait = targetTime - elapsed / 1000000;

	    try {
		Thread.sleep(wait);
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}
    }

    private void drawToScreen() {
	Graphics g2 = getGraphics();
	g2.drawImage(image, 0, 0, null);
	g2.dispose();
    }

    private void draw() {
	gsm.draw(g);
    }

    private void update() {
	gsm.update();
    }

    private void init() {
	// TODO Auto-generated method stub
	image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	g = (Graphics2D) image.getGraphics();

	running = true;
	
	gsm = new GameStateManager();
    }

    public GamePanel() {
	setPreferredSize(new Dimension(WIDTH, HEIGHT));
	setFocusable(true);
	requestFocus();
    }

    public void addNotify() {
	if (thread == null) {
	    thread = new Thread(this);
	    addKeyListener(this);
	    thread.start();
	}
    }

    public void keyPressed(KeyEvent key) {
	gsm.keyPressed(key.getKeyCode());
    }

    public void keyReleased(KeyEvent key) {
	gsm.keyReleased(key.getKeyCode());
    }

    @Override
    public void keyTyped(KeyEvent key) {
	// TODO Auto-generated method stub

    }
}
