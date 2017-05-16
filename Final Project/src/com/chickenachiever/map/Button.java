package com.chickenachiever.map;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.font.TextLayout;
import java.util.ArrayList;

public class Button {

    private State currentState = State.RELEASED;
    private Rectangle box;
    private ArrayList<ActionListener> acLs;
    private String text = "";
    private Color released;
    private Color hover;
    private Color pressed;
    private Font font = new Font("Arial", Font.PLAIN, 20);

    public Button(int x, int y, int width, int height, String text) {
	box = new Rectangle(x, y, width, height);
	acLs = new ArrayList<ActionListener>();
	released = new Color(0, 205, 0);
	hover = new Color(0, 154, 0);
	pressed = new Color(0, 103, 0);
	this.text = text;
    }
    
    public Button(int x, int y, int width, int height, String text, Font font) {
	box = new Rectangle(x, y, width, height);
	acLs = new ArrayList<ActionListener>();
	released = new Color(0, 205, 0);
	hover = new Color(0, 154, 0);
	pressed = new Color(0, 103, 0);
	this.text = text;
	this.font = font;
    }

    public void update() {

    }

    public void draw(Graphics2D g) {
	if (currentState == State.RELEASED) {
	    g.setColor(released);
	    g.fill(box);
	} else if (currentState == State.HOVER) {
	    g.setColor(hover);
	    g.fill(box);
	} else {
	    g.setColor(pressed);
	    g.fill(box);
	}
	g.setColor(Color.white);
	g.setFont(font);
	g.drawString(text, box.x + box.width / 2 - (int) (g.getFontMetrics().getStringBounds(text, g).getWidth() / 2),
		box.y + box.height / 2 + (int) (new TextLayout(text, font, g.getFontRenderContext()).getBounds().getHeight() / 2));
    }

    public void addActionListener(ActionListener listener) {
	acLs.add(listener);
    }

    private enum State {
	RELEASED, HOVER, PRESSED
    }

    public void mouseClicked(MouseEvent e) {
	if (box.contains(e.getPoint()))
	    currentState = State.PRESSED;
    }

    public void mouseDragged(MouseEvent e) {
	if (box.contains(e.getPoint()))
	    currentState = State.PRESSED;
	else
	    currentState = State.RELEASED;
    }

    public void mouseMoved(MouseEvent e) {
	if (box.contains(e.getPoint()))
	    currentState = State.HOVER;
	else
	    currentState = State.RELEASED;
    }

    public void mouseReleased(MouseEvent e) {
	if (box.contains(e.getPoint())) {
	    for (ActionListener al : acLs)
		al.actionPerformed(null);
	}
	currentState = State.RELEASED;
    }

    public int getX() {
	return box.x;
    }

    public int getY() {
	return box.y;
    }

    public int getWidth() {
	return box.width;
    }

    public int getHeight() {
	return box.height;
    }

    public void setText(String text) {
	this.text = text;
    }
}
