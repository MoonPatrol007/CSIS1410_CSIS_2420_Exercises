package Maze;

import edu.princeton.cs.algs4.*;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.canvas.GraphicsContext;
import javafx.util.Duration;

/**
 * @author Hector Juarez and William Nguyen
 *
 */

public class MazeSolver {
	private Timeline timelineDFS;
	private Timeline timelineBFS;
	private GraphicsContext gc;
	private int currentTime;
	private int keyframeTime = 50;
	
	private double scale;
	private boolean solved;
	private int mazeSize;
	private boolean[][] visited;
	private boolean[][] north;
	private boolean[][] south;
	private boolean[][] west;
	private boolean[][] east;
	
	// Constructor initializing the MazeSolver
	public MazeSolver(Maze maze) {
		mazeSize = maze.getSize();
		visited = new boolean[mazeSize + 2][mazeSize + 2];
		north = maze.getNorth();
		south = maze.getSouth();
		west = maze.getWest();
		east = maze.getEast();
		scale = MazeGUI.getScale();
		timelineDFS = MazeGUI.getTimelineDFS();
		timelineBFS = MazeGUI.getTimelineBFS();
		gc = MazeGUI.getGC();
	}
	
	// solve the maze starting from the start state
    public void solveMaze() {
    	currentTime = 0;
        for (int x = 1; x <= mazeSize; x++) {
            for (int y = 1; y <= mazeSize; y++) {
                visited[x][y] = false;
            }
        }
        solved = false;
        solveDFS(1, mazeSize);

    	currentTime = 0;
        for (int x = 1; x <= mazeSize; x++) {
            for (int y = 1; y <= mazeSize; y++) {
                visited[x][y] = false;
            }
        }
        solved = false;
        solveBFS(1, mazeSize);
    }
    
    // Point class represents a (x,y) coordinate point
    private static class Point {
    	int x;
    	int y;
    	
    	public Point(int x, int y) {
    		this.x = x;
    		this.y = y;
    	}
    	
    	public String toString() {
    		return "("+x+","+y+")";
    	}
    }

	// solve the maze using depth-first search using recursion
    private void solveDFS(int x, int y) {
        if (x == 0 || y == 0 || x == mazeSize + 1 || y == mazeSize + 1) return;
        
        // if the maze is solved or the current point is already been visited then return
        if (solved || visited[x][y]) return;
        // mark this point as visited
        visited[x][y] = true;
        // draws a rectangle at this point
        drawRectangle(x,y, timelineDFS, gc);
        
        // if the point has reached top right then the maze is solved
        if (x == mazeSize && y == 1) {
        	solved = true;
        }
        
        // if the path above the (x,y) coordinates is open
        // then move there and call itself again
        if (!north[x][y]) {
        	solveDFS(x, y + 1);
        }
        // if the path to the right of the (x,y) coordinates is open
        // then move there and call itself again
        if (!east[x][y])  {
        	solveDFS(x + 1, y);
        }
        // if the path below the (x,y) coordinates is open
        // then move there and call itself again
        if (!south[x][y]) {
        	solveDFS(x, y - 1);
        }
        // if the path to the left of the (x,y) coordinates is open
        // then move there and call itself again
        if (!west[x][y])  {
        	solveDFS(x - 1, y);
        }

        // check if the maze has been solved after going through the recursion
        if (solved) {
        	return;
        }
    }
    
    // solve the maze using Breadth First Search
    private void solveBFS(int x, int y) {
    	if (x == 0 || y == 0 || x == mazeSize + 1 || y == mazeSize + 1) return;
    	
        Queue<Point> queue = new Queue<Point>();
        // enqueues the (x,y) coordinate point
        queue.enqueue(new Point(x,y));
        
        // while the queue is not empty
	    while (!queue.isEmpty()) {
	    	// dequeue a point
	    	Point point = queue.dequeue();
	    	// check if the point has the coordinates of the exit
	    	if (point.x == mazeSize && point.y == 1) {
	    		solved = true; 
	    		break;
	    	}
	    	// check if the path to the north of the point is open
	    	// if so move there and mark it as visited
	    	if (!north[point.x][point.y]) {
	    		visited[point.x][point.y] = true;
	    		if (!visited[point.x][point.y + 1]) {
	        		queue.enqueue(new Point(point.x, point.y + 1));
	        		drawRectangle(point.x,point.y + 1, timelineBFS, gc);
	    		}
	    	} 
	    	// check if the path to the east of the point is open
	    	// if so move there and mark it as visited
	    	if (!east[point.x][point.y]) {
	    		visited[point.x][point.y] = true;
	    		if (!visited[point.x+1][point.y]) {
	        		queue.enqueue(new Point(point.x + 1, point.y));
	        		drawRectangle(point.x + 1,point.y, timelineBFS, gc);
	    		}
	    	}
	    	// check if the path to the south of the point is open
	    	// if so move there and mark it as visited
	    	if (!south[point.x][point.y]) {
	    		visited[point.x][point.y] = true;
	    		if (!visited[point.x][point.y - 1]) {
	        		queue.enqueue(new Point(point.x, point.y - 1));
	        		drawRectangle(point.x,point.y - 1, timelineBFS, gc);
	    		}
	    	}
	    	// check if the path to the west of the point is open
	    	// if so move there and mark it as visited
	    	if (!west[point.x][point.y]) {
	    		visited[point.x][point.y] = true;
	    		if (!visited[point.x - 1][point.y]) {
	        		queue.enqueue(new Point(point.x - 1, point.y));
	        		drawRectangle(point.x - 1, point.y, timelineBFS, gc);
	    		}
	    	}
	    }
    }
    
    // draws a rectangle at (x,y) coordinates adds it to a animation time line
    private void drawRectangle(int x, int y, Timeline timeline, GraphicsContext gc) {
    	KeyFrame kf = new KeyFrame(Duration.millis(currentTime), actionEvent -> {
			gc.fillRect(x*scale+1.5, y*scale+1.5, scale-3, scale-3); 
		});
		timeline.getKeyFrames().add(kf);
		currentTime += keyframeTime;
    }   
}
