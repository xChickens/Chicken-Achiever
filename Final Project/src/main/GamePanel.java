package main;

import javax.swing.JPanel;
import java.awt.Dimension;

public class GamePanel extends JPanel implements Runnable {
    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    // Dimensions
    private static final int WIDTH = 1024;
    private static final int HEIGHT = 768;

    public void run() {
    }

    public GamePanel() {
	setPreferredSize(new Dimension(WIDTH, HEIGHT));
	setFocusable(true);
	requestFocus();
    }
}
