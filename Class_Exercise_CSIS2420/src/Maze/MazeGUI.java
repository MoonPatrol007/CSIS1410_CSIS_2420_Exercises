package Maze;

import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
/**
 * @author Hector Juarez and William Nguyen
 *
 */

public class MazeGUI extends Application {
	private int canvasWidth;
	private int canvasHeight;
	private static double scale;
	private int mazeSize;
	private Maze maze;
	private static Timeline timelineDFS;
	private static Timeline timelineBFS;
	private static GraphicsContext gc;
	
	// initializes the variables to MazeGUI
	public void initialize() {
		mazeSize = 25;
		canvasWidth = canvasHeight = 1000;
		
		scale = canvasWidth / 27;
		timelineDFS = new Timeline();
		timelineBFS = new Timeline();
	}
	
	// starts the Maze application
	@Override
    public void start(Stage stage) {  
		initialize();
		
		stage.setTitle("Maze");
		Group root = new Group();
		
		try {
			displayMaze(root);
		} catch (Throwable e) {
			e.printStackTrace();
		}

		Scene scene = new Scene(root, Color.WHITE);  
		stage.setScene(scene); 
	
		stage.show();
	}
	
	// initializes the GraphicsContext
	private GraphicsContext initGraphicsContext(Canvas canvas) {
		gc = canvas.getGraphicsContext2D();
		return gc;
	}
	
	// displays the maze application
	private void displayMaze(Group root) {
		Canvas canvas = new Canvas(canvasWidth, canvasHeight);
		GraphicsContext gc = initGraphicsContext(canvas);
		GridPane buttons = new GridPane();
		BorderPane pane = new BorderPane();
		GridPane grid = new GridPane();
		Circle startMarker = new Circle((scale*1.5), mazeSize*scale+(scale*0.5), scale*0.5, Color.RED);
		Circle finishMarker = new Circle(mazeSize*scale+(scale*0.5), (scale*1.5), scale*0.5, Color.GREEN);

		// button to generate a new maze
		Button btnGenerate = new Button("Generate");
		btnGenerate.setOnAction(event -> {
			System.out.println("Generating new Maze");
			timelineDFS.stop();
			timelineBFS.stop();
			timelineDFS.getKeyFrames().clear();
			timelineBFS.getKeyFrames().clear();
			gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
			MazeGenerator generator = new MazeGenerator(mazeSize);
			maze = generator.generateMaze();
			drawMaze(maze, gc);
			
			if (!pane.getChildren().contains(startMarker) || !pane.getChildren().contains(finishMarker)) {
				pane.getChildren().add(startMarker);
				pane.getChildren().add(finishMarker);
			}
			
		});
		
		Button btnSolveBFS = new Button("SolveDFS");
		btnSolveBFS.setOnAction(event -> {
			System.out.println("Solving the maze");
			
			try {
				MazeSolver solver = new MazeSolver(maze);
				solver.solveMaze();
				
				gc.setFill(Color.AQUA);
				timelineDFS.setCycleCount(1);
				timelineDFS.play();

			} catch (Throwable e) {
				System.out.println("Generate a Maze first before hitting solve");
				e.printStackTrace();
			}
		});
		
		Button btnSolveDFS = new Button("SolveBFS");
		btnSolveDFS.setOnAction(event -> {
			System.out.println("Solving the maze");
			
			try {
				MazeSolver solver = new MazeSolver(maze);
				solver.solveMaze();

				gc.setFill(Color.YELLOW);
				timelineBFS.setCycleCount(1);
				timelineBFS.play();
			} catch (Throwable e) {
				System.out.println("Generate a Maze first before hitting solve");
				e.printStackTrace();
			}
		});
		
		// adds buttons to the button grid
		buttons.add(btnGenerate, 0, 0);
		buttons.add(btnSolveDFS, 1, 0);
		buttons.add(btnSolveBFS, 2, 0);
		
		// adds the maze canvas to the borderpane
		pane.setCenter(canvas);
		BorderPane.setAlignment(canvas, Pos.CENTER);
		pane.setManaged(false);
		
		// adds the button grid and border pane to the grid
		grid.add(pane, 1, 1);
		grid.add(buttons, 0, 1);
		
		// adds the grid to the group
		root.getChildren().add(grid);
	}

	// draws the walls of the maze
	private void drawMaze(Maze maze, GraphicsContext gc) {
		
		boolean[][] goUp = maze.getNorth();
		boolean[][] goDown = maze.getSouth();
	    boolean[][] goLeft = maze.getWest();
	    boolean[][] goRight = maze.getEast();
	    
	    gc.setFill(Color.WHITE);
	    gc.fillRect(0, 0, canvasWidth, canvasHeight);
	    gc.setStroke(Color.BLACK);
	    gc.setLineWidth(1);
	    int size = maze.getSize();
        // draws a line if the array returns true
        for (int x = 1; x <= size; x++) {
            for (int y = 1; y <= size; y++) {
            	if (goUp[x][y]) {
            		gc.strokeLine(x*scale, (y*scale)+scale, (x*scale)+scale, (y*scale)+scale); 
            		
            	}
                if (goDown[x][y]) {
                	gc.strokeLine(x*scale, y*scale, (x*scale)+scale, y*scale); 
                	
                }
                if (goLeft[x][y])  {
                	gc.strokeLine(x*scale, y*scale, x*scale, (y*scale)+scale); 
                	
                }
                if (goRight[x][y])  {
                	gc.strokeLine((x*scale)+scale, y*scale, (x*scale)+scale, (y*scale)+scale); 
                	
                }
            }
        }
	}
	
	
	// public getter methods
	public static double getScale() {
		return scale;
	}
	
	public static GraphicsContext getGC() {
		return gc;
	}
	
    public static Timeline getTimelineDFS() {
    	return timelineDFS;
    }
    
    public static Timeline getTimelineBFS() {
    	return timelineBFS;
    }
	
	public static void main(String[] args) {
		launch(args);
		Application.launch(MazeGUI.class);
	}
}
