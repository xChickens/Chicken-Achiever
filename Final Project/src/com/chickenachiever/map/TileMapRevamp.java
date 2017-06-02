package com.chickenachiever.map;

import java.awt.Graphics2D;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import com.chickenachiever.main.GamePanel;
import com.chickenachiever.model.BlockRevamp;
import com.chickenachiever.model.MapElementRevamp;

public class TileMapRevamp {

	// Position
	private double x;
	private double y;

	// Bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;

	// Map
	private int tileSize;
	private int numRows;
	private int numCols;
	private int width;
	private int height;

	// Tileset
	private ArrayList<MapElementRevamp> elements;
	private int numTilesAcross;
	private Tile[][] tiles;

	// Drawing
	private int rowOffset;
	private int colOffset;
	private int numRowsToDraw;
	private int numColsToDraw;

	public TileMapRevamp(int tileSize) {
		elements = new ArrayList<MapElementRevamp>();
		this.tileSize = tileSize;
		numRowsToDraw = (GamePanel.PHEIGHT / tileSize) * GamePanel.SCALE;
		numColsToDraw = (GamePanel.PWIDTH / tileSize) * GamePanel.SCALE;
	}

	public void loadTiles(String s) {
		ArrayList<String> stringList = loadMap(s);
		for (int h = 0; h < stringList.size(); h++) {
			String currentLine = stringList.get(h);
			if (h > 2 && h < 23) {
				for (int l = 0; l < currentLine.length(); l++) {
					if (currentLine.charAt(l) == 'p') {
					} else if (currentLine.charAt(l) == 'B')
						elements.add(new BlockRevamp(this, (tileSize * l), (tileSize * (h))));
				}
			}
		}
	}

	public ArrayList<String> loadMap(String s) {
		ArrayList<String> list = new ArrayList<String>();
		try {
			InputStream in = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			numCols = Integer.parseInt(br.readLine());
			numRows = Integer.parseInt(br.readLine());
			width = (numCols * tileSize);
			height = (numRows * tileSize);

			xmin = (GamePanel.PWIDTH - width);
			xmax = 0;
			ymin = (GamePanel.PHEIGHT - height);
			ymax = 0;

			String line;
			while ((line = br.readLine()) != null)
				list.add(line);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public int getTileSize() {
		return tileSize;
	}

	public void addElement(MapElementRevamp e) {
		elements.add(e);
	}

	public int getx() {
		return (int) x;
	}

	public int gety() {
		return (int) y;
	}

	public ArrayList<MapElementRevamp> getElements(){
		//System.out.println("element size" + elements.size());
		return elements;
	}
	
	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public int getType(int row, int col) {
		return 0;
	}

	public void setPosition(double x, double y) {
		this.x += (x - this.x);
		this.y += (y - this.y);

		fixBounds();

		colOffset = ((int) -this.x / tileSize);
		rowOffset = ((int) -this.y / tileSize);
	}

	private void fixBounds() {
		if (x < xmin) {
			x = xmin;
		}
		if (y < ymin) {
			y = ymin;
		}
		if (x < xmax) {
			x = xmax;
		}
		if (x < ymax) {
			y = ymax;
		}
	}

	public void draw(Graphics2D g2) {
		for (int i = 0; i < elements.size(); i++){
			try{
			elements.get(i).draw(g2);
			//System.out.println(eleme.getx());
			}catch(Exception f){
				f.printStackTrace();
			}
		}
	}
}
