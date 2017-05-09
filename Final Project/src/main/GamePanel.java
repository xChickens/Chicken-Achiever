package main;

import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

public class GamePanel extends JPanel implements Runnable, KeyListener{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // Dimensions
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;
    
    // Thread
    private Thread thread;
    private boolean running;
    private int FPS = 60;
    private long targetTime = 1000 / FPS;
    
    // Images
    private BufferedImage image;
    private Graphics2D graphics;

    public void run() {
	init();
    }

    private void init() {
	// TODO Auto-generated method stub
	image = new BufferedImage(WIDTH, HEIGHT);
    }

    public GamePanel() {
	setPreferredSize(new Dimension(WIDTH, HEIGHT));
	setFocusable(true);
	requestFocus();
    }
    
    public void addNotify(){
	if (thread == null)
	{
	    thread = new Thread(this);
	    addKeyListener(this);
	    thread.start();
	}
    }

    @Override
    public void keyPressed(KeyEvent arg0) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void keyReleased(KeyEvent e) {
	// TODO Auto-generated method stub
	
    }

    @Override
    public void keyTyped(KeyEvent e) {
	// TODO Auto-generated method stub
	
    }
}
