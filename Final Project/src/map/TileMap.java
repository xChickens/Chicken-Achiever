package map;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class TileMap {

    // Position
    private double x;
    private double y;
    
    // Bounds
    private int xmin;
    private int ymin;
    private int xmax;
    private int ymax;
    
    // Map
    private int[][] map;
    private int tileSize;
    private int numRows;
    private int numCols;
    private int width;
    private int height;
    
    // Tileset
    private BufferedImage tileset;
    private int numTilesAcross;
    private Tile[][] tiles;
    
    // Drawing
    private int rowOffset;
    private int colOffset;
    private int numRowsToDraw;
    private int numColsToDraw;

    public TileMap(int tileSize) {
	this.tileSize = tileSize;
	this.numRowsToDraw = (368 / tileSize + 2);
	this.numColsToDraw = (512 / tileSize + 2);
    }

    public void loadTiles(BufferedImage i) {
	try {
	    this.tileset = i;
	    this.numTilesAcross = (this.tileset.getWidth() / this.tileSize);
	    this.tiles = new Tile[2][this.numTilesAcross];
	    for (int col = 0; col < this.numTilesAcross; col++) {
		BufferedImage subimage = this.tileset.getSubimage(col
			* this.tileSize, 0, this.tileSize, this.tileSize);

		this.tiles[0][col] = new Tile(subimage, 0);
		subimage = this.tileset.getSubimage(col * this.tileSize,
			this.tileSize, this.tileSize, this.tileSize);

		this.tiles[1][col] = new Tile(subimage, 1);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public void loadMap(String s) {
	try {
	    InputStream in = getClass().getResourceAsStream(s);
	    BufferedReader br = new BufferedReader(new InputStreamReader(in));
	    this.numCols = Integer.parseInt(br.readLine());
	    this.numRows = Integer.parseInt(br.readLine());
	    this.map = new int[this.numRows][this.numCols];
	    this.width = (this.numCols * this.tileSize);
	    this.height = (this.numRows * this.tileSize);

	    this.xmin = (512 - this.width);
	    this.xmax = 0;
	    this.ymin = (368 - this.height);
	    this.ymax = 0;

	    String delims = "\\s+";
	    for (int row = 0; row < this.numRows; row++) {
		String line = br.readLine();
		String[] tokens = line.split(delims);
		for (int col = 0; col < this.numCols; col++) {
		    this.map[row][col] = Integer.parseInt(tokens[col]);
		}
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }

    public int getTileSize() {
	return this.tileSize;
    }

    public int getx() {
	return (int) this.x;
    }

    public int gety() {
	return (int) this.y;
    }

    public int getWidth() {
	return this.width;
    }

    public int getHeight() {
	return this.height;
    }

    public int getType(int row, int col) {
	int rc = this.map[row][col];
	int r = rc / this.numTilesAcross;
	int c = rc % this.numTilesAcross;
	return this.tiles[r][c].getType();
    }

    public void setPosition(double x, double y) {
	this.x += (x - this.x) * this.tween;
	this.y += (y - this.y) * this.tween;

	fixBounds();

	this.colOffset = ((int) -this.x / this.tileSize);
	this.rowOffset = ((int) -this.y / this.tileSize);
    }

    private void fixBounds() {
	if (this.x < this.xmin) {
	    this.x = this.xmin;
	}
	if (this.y < this.ymin) {
	    this.y = this.ymin;
	}
	if (this.x < this.xmax) {
	    this.x = this.xmax;
	}
	if (this.x < this.ymax) {
	    this.y = this.ymax;
	}
    }

    public void draw(Graphics2D g2) {
	for (int row = this.rowOffset; row < this.rowOffset
		+ this.numRowsToDraw; row++) {
	    if (row >= this.numRows) {
		break;
	    }
	    for (int col = this.colOffset; col < this.colOffset
		    + this.numColsToDraw; col++) {
		if (col >= this.numCols) {
		    break;
		}
		if (this.map[row][col] != 0) {
		    int rc = this.map[row][col];
		    int r = rc / this.numTilesAcross;
		    int c = rc % this.numTilesAcross;

		    g2.drawImage(this.tiles[r][c].getImage(), (int) this.x
			    + col * this.tileSize, (int) this.y + row
			    * this.tileSize, null);
		}
	    }
	}
    }
}
