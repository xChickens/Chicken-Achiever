package com.chickenachiever.main;

import javax.swing.JPanel;

import com.chickenachiever.state.GameStateManager;

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
    public static final int WIDTH = 512;
    public static final int HEIGHT = 384;
    public static final int SCALE = 2;

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

    public void addNotify()
    {
      super.addNotify();
      if (thread == null)
      {
        thread = new Thread(this);
        addKeyListener(this);
        thread.start();
      }
    }
    
    private void init()
    {
      image = new BufferedImage(1024, 768, 1);
      
      g = ((Graphics2D)image.getGraphics());
      
      running = true;
      
      gsm = new GameStateManager();
    }
    
    public void run()
    {
      init();
      while (running)
      {
        long start = System.nanoTime();
        
        update();
        draw();
        drawToScreen();
        
        long elapsed = System.nanoTime() - start;
        
        long wait = targetTime - elapsed / 1000000L;
        if (wait < 0L) {
          wait = 5L;
        }
        try
        {
          Thread.sleep(wait);
        }
        catch (Exception e)
        {
          e.printStackTrace();
        }
      }
    }
    
    private void update()
    {
      gsm.update();
    }

    public GamePanel() {
	setPreferredSize(new Dimension(WIDTH*SCALE, HEIGHT*SCALE));
	setFocusable(true);
	requestFocus();
    }
    
    private void draw()
    {
      gsm.draw(g);
    }
    
    private void drawToScreen()
    {
      Graphics g2 = getGraphics();
      g2.drawImage(image, 0, 0, WIDTH*SCALE, HEIGHT*SCALE, null);
      g2.dispose();
    }
    
    public void keyTyped(KeyEvent key) {}
    
    public void keyPressed(KeyEvent key)
    {
      gsm.keyPressed(key.getKeyCode());
    }
    
    public void keyReleased(KeyEvent key)
    {
      gsm.keyReleased(key.getKeyCode());
    }
}
