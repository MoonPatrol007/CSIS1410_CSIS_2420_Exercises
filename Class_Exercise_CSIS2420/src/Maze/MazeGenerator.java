package Maze;

import edu.princeton.cs.algs4.*;

/**
 * @author Hector Juarez and William Nguyen
 *
 */

public class MazeGenerator {
	private int mazeSize;
	
	private boolean[][] explored;
	private boolean[][] goUp;
	private boolean[][] goDown;
	private boolean[][] goLeft;
	private boolean[][] goRight;
	
	// constructor for MazeGenerator to generate maze of a certain size
    public MazeGenerator(int size) {
        mazeSize = size;
        initialize();
        generateMaze();
    }
    
    // method to initialize the possible moves
    private void initialize() {
    	
        // initialize border cells as already visited
    	explored = new boolean[mazeSize + 2][mazeSize + 2];
        for (int x = 0; x < mazeSize + 2; x++) {
            explored[x][0] = true;
            explored[x][mazeSize + 1] = true;
        }
        for (int y = 0; y < mazeSize + 2; y++) {
            explored[0][y] = true;
            explored[mazeSize + 1][y] = true;
        }


        
        goUp = new boolean[mazeSize + 2][mazeSize + 2];
        goDown = new boolean[mazeSize + 2][mazeSize + 2];
        goLeft  = new boolean[mazeSize + 2][mazeSize + 2];
        goRight  = new boolean[mazeSize + 2][mazeSize + 2];
        
        // initialize every wall in the maze to exist
        for (int x = 0; x < mazeSize + 2; x++) {
            for (int y = 0; y < mazeSize + 2; y++) {
                goUp[x][y] = true;
                goRight[x][y]  = true;
                goDown[x][y] = true;
                goLeft[x][y]  = true;
            }
        }
    }
	
    // method to generate maze from the point (1,1) being the bottom left of the maze
	public Maze generateMaze() {
		generate(1,1);
		return new Maze(goUp, goDown, goLeft, goRight);
	}
	
	// method to generate the maze from a point given
	private void generate(int x, int y) {
		explored[x][y] = true;

        // while there is an unexplored neighbor
        while (!explored[x][y + 1] || !explored[x + 1][y] || !explored[x][y - 1] || !explored[x - 1][y]) {

            // pick random neighbor out of 4 options
            while (true) {
                double r = StdRandom.uniform(4);
                
                if (r == 0 && !explored[x][y + 1]) {
                    goUp[x][y] = false;
                    goDown[x][y + 1] = false;
                
                    generate(x, y + 1);
                    break;
                }
                else if (r == 1 && !explored[x + 1][y]) {
                    goRight[x][y] = false;
                    goLeft[x + 1][y] = false;
                    
                    generate(x + 1, y);
                    break;
                }
                else if (r == 2 && !explored[x][y - 1]) {
	                goDown[x][y] = false;
	                goUp[x][y - 1] = false;
	                
                	generate(x, y - 1);
	                break;
                }
                else if (r == 3 && !explored[x - 1][y]) {
                    goLeft[x][y] = false;
                    goRight[x - 1][y] = false;
                    
                    generate(x - 1, y);
                    break;
                }
            }
        }
	}
	
	// getter methods
	public int getSize() {
		return mazeSize;
	}

}
