package com.chickenachiever.state;

import java.awt.event.MouseEvent;
import java.util.ArrayList;

import com.chickenachiever.audio.AudioPlayer;

public class GameStateManager {

    private ArrayList<GameState> gameStates;
    private int currentState;

    public static final int MENUSTATE = 0;
    public static final int CREDITSSTATE = 1;
    public static final int LEVELSTATE = 2;
    public static final int ENDSTATE = 3;
    private AudioPlayer bgmusic; 
    
    public GameStateManager() {
	gameStates = new ArrayList<GameState>();
	bgmusic = new AudioPlayer("/Music/bgmusic.wav");
	bgmusic.loop();

	currentState = MENUSTATE;
	gameStates.add(new MenuState(this));
	gameStates.add(new CreditsState(this));
	gameStates.add(new LevelState(this));
	gameStates.add(new EndState(this));
    }

    public void setState(int state) {
	currentState = state;
	gameStates.get(currentState).init();
	if (state == ENDSTATE)
	    gameStates.set(LEVELSTATE, new LevelState(this));
    }

    public void update() {
	gameStates.get(currentState).update();
    }

    public void draw(java.awt.Graphics2D g) {
	gameStates.get(currentState).draw(g);
    }

    public void keyPressed(int k) {
	gameStates.get(currentState).keyPressed(k);
    }

    public void keyReleased(int k) {
	gameStates.get(currentState).keyReleased(k);
    }

    public void mouseClicked(MouseEvent e) {
	gameStates.get(currentState).mouseClicked(e);
    }

    public void mouseDragged(MouseEvent e) {
	gameStates.get(currentState).mouseDragged(e);
    }

    public void mouseMoved(MouseEvent e) {
	gameStates.get(currentState).mouseMoved(e);
    }

    public void mouseReleased(MouseEvent e) {
	gameStates.get(currentState).mouseReleased(e);
    }
}
