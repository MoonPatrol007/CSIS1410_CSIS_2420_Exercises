package Maze;

public class Maze {
	private int size;
	
	private boolean[][] north;
	private boolean[][] south;
	private boolean[][] west;
	private boolean[][] east;
	
	// Constructor to initialize the maze with walls
    public Maze(boolean[][] north, boolean[][] south, boolean[][] west, boolean[][] east) {
    	// initialize the walls of the maze
        this.north = north;
        this.south = south;
        this.west = west;
        this.east = east;
        size = ((north.length + south.length + west.length + east.length) / 4) - 2;
    }
    
    // public getter methods
    public int getSize() {
		return size;
	}
    
	public boolean[][] getNorth() {
		return north;
	}
	
	public boolean[][] getSouth() {
		return south;
	}
	
	public boolean[][] getWest() {
		return west;
	}
	
	public boolean[][] getEast() {
		return east;
	}
}
