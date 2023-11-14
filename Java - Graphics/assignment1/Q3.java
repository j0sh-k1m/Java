package assignment1;
/**
 * Class name: Q3
 * Assignment name: Review Assignment
 * Author's name: Joshua Kim
 * Date: February 20, 2022
 * Teacher's name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class is where simulation is run. It uses the methods from SpaceShip and Button to create 
 * the different components for the simulation. You can pause and play the simulation using the button
 * that can be found in the bottom right corner as well as the space bar. 
 */



import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class Q3 extends Application{
	
		// define the width and height of the window
		private final int WIDTH = 800;
		private final int HEIGHT = 800;	
		
		// Define the px per km
		private double pxKm = WIDTH / 24; 	
		private double RADIUS_OF_PLASMAZONE = 10 * pxKm; 	
		private final double CENTRE_WIDTH = WIDTH/2; 
		private final double CENTRE_HEIGHT = HEIGHT/2;  
		
		// Define the speed of the redShip
		private double REDSHIP_SPEED = (pxKm/60) * 7; 
		
		// Define the centre coordinates of the plasmazone
		private final double X_ORIGIN = 400; 
		private final double Y_ORIGIN = 400;  
		
		// Define the radial path of blueShip from the centre of the plasmazone
		private final double DISTANCE_FROM_CENTRE = 81;
		
		// Define initial states of simulation and conditions
		private boolean pause = true;
		private boolean escaping = false; 
		private boolean start = false; 
		private boolean escaped = false; 
		private boolean captured = false; 
		
		// Starting position of the plasma zone 
		double[] coords = withGrid(RADIUS_OF_PLASMAZONE, CENTRE_WIDTH, CENTRE_HEIGHT);
		
		// Starting position of the red SpaceShip
		SpaceShip redShip = new SpaceShip(Color.RED, REDSHIP_SPEED);	
		
		// New object for blue SpaceShip
		SpaceShip blueShip = new SpaceShip(Color.BLUE, redShip.getSpeed()/4);
		
		// New Button object for starting and pausing
		private final double BUTTON_POSX = 625; 
		private final double BUTTON_POSY = 725;
		private final double BUTTON_LENGTH = 150; 
		private final double BUTTON_WIDTH = 50;
		Button button = new Button(Color.CYAN, BUTTON_POSX, BUTTON_POSY, BUTTON_LENGTH, BUTTON_WIDTH);
		
		// Define offset for text for the button
		double offsetX = 18;
		double offsetY = 5;
		
		// Define background colour
		private Color backgroundColor = Color.WHITE;	
		private GraphicsContext gc;	// object that will allow us to draw on the screen
		
		/**
		 * Method that converts "top-left" positions of circles to the centre
		 * @param radius of the circle 
		 * @param xpos x position of the circle in a Cartesian plain 
		 * @param ypos y position of the circle in a Cartesian plain
		 * @return an array with the converted x and y positions of the circle 
		 */
		public double[] withGrid(double radius, double xpos, double ypos) {
			double[] array = new double[2];
			array[0] = xpos - radius; 
			array[1] = ypos - radius; 
			return array; 
		}
		
		/**
		 * Method that generates the random position of the red circle along the circumference of the plasma zone
		 * @param radius of the plasma zone
		 * @param h horizontal shift of plasma zone
		 * @param k vertical shift of the plasma zone 
		 * @return an array of type double where the indices 0 and 1 are x and y positions respectively 
		 */
		public double[] startCoords(double radius, double h, double k) {
			double MAX = X_ORIGIN + radius; 
			double MIN = Y_ORIGIN - radius;
			double[] start_pos = new double[2];
			Random num = new Random();
			double xpos = MIN + (MAX - MIN) * num.nextDouble(); 
			start_pos[0] = xpos;		
			double[] ypos = solveCircle(xpos, radius, h, k);		
			boolean flag = num.nextBoolean();		
			if (flag == true) {
				start_pos[1] = ypos[0]; 
			} else {
				start_pos[1] = ypos[1];
			}
			return start_pos;	
		}
		
		/**
		 * Method that will solve for the y value of a circle given the x 
		 * @param x value used to solve for y
		 * @param radius of the plasmazone 
		 * @param h horizontal shift of the plasmazone
		 * @param k vertical shift of the plasmazone
		 * @return the two y values of the circle that correspond with the x
		 */
		private double[] solveCircle(double x, double radius, double h, double k) {
			double square_radius = Math.pow(radius, 2);
			double second_term = Math.pow(x - h, 2);
			double ypos1 = Math.sqrt(square_radius - second_term) + k;
			double ypos2 = -1 * (Math.sqrt(square_radius - second_term)) + k;
			double[] solved = new double[2];
			solved[0] = ypos1; 
			solved[1] = ypos2; 
			return solved;
		}
		

	public static void main(String[] args) {
		Application.launch(args);
	}
	
	@Override
	public void start(Stage stage) throws Exception {
		// Create the Canvas
		Canvas canvas = new Canvas(WIDTH, HEIGHT);
		// Set the width of the Canvas
		canvas.setWidth(WIDTH);
		// Set the height of the Canvas
		canvas.setHeight(HEIGHT);

		// Get the graphics context of the canvas
		gc = canvas.getGraphicsContext2D();

		// Create the Pane
		Pane root = new Pane();

		AnimationTimer timer = new AnimationTimer() {
			long lastTime = System.nanoTime();
			@Override
			public void handle(long currentTime) {
				double delta_Time = (currentTime - lastTime)/1000000000.0;
				lastTime = currentTime;
				upDate(delta_Time);
				// draw the scene using the GraphicsContext gc
				render(gc);
			}
		};
		timer.start();	// starts the loop

		// Add the Canvas to the Pane
		root.getChildren().add(canvas);
		// Create the Scene
		Scene scene = new Scene(root);
		
		// define keyboard event handlers
		/**
		 * This method will be automatically called whenever a keyboard button is pressed.  The key pressed is available by using the
		 * event.getCode() method.  Each key value is statically defined in the KeyCode class, which can then be compared to the 
		 * event.getCode() value.  The example below notifies the user if the UP or DOWN arrow keys have been pressed by printing them.
		 */
		scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {
				
				// Pauses the simulation if the space button is pressed
				if(event.getCode() == KeyCode.SPACE && !pause) {
					pause = true;
				} else if (event.getCode() == KeyCode.SPACE && pause) {
					pause = false;
				}
			}
		});
		
		// define mouse event handlers
		/**
		 * This method will be automatically called whenever the left mouse button is clicked on the screen.
		 * The location of the mouse click will be available using the methods event.getX() and event.getY(), as shown below.
		 */
		scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				
				// Spawns the blueShip at the location of the click inside the plasmazone
				if (event.getX() > CENTRE_WIDTH - RADIUS_OF_PLASMAZONE && event.getX() < CENTRE_WIDTH + RADIUS_OF_PLASMAZONE) {
					double[] rangeY = solveCircle(event.getX(), RADIUS_OF_PLASMAZONE, CENTRE_WIDTH, CENTRE_HEIGHT);
					if (event.getY() < rangeY[0] && event.getY() > rangeY[1] && !start) {
						blueShip.setLocation(event.getX(), event.getY());
						start = true;
					}
				}
				// Detects if the button is clicked and sets state to pause or not-paused
				if (event.getX() > button.getPosition()[0] && event.getX() < button.getPosition()[0]+button.getLength() && !pause) {
					if (event.getY() > button.getPosition()[1] && event.getY() < button.getPosition()[1]+button.getWidth()) {
						pause = true;
					}
				} else if (event.getX() > button.getPosition()[0] && event.getX() < button.getPosition()[0]+button.getLength() && pause) {
					if (event.getY() > button.getPosition()[1] && event.getY() < button.getPosition()[1]+button.getWidth()) {
						pause = false;
					}
				}
			}
		});
		
		
		stage.sizeToScene();
		// Add the Scene to the Stage
		stage.setScene(scene);
		// Set the Title of the Stage
		stage.setTitle("Starter Code for ICS4U");
		// Display the Stage
		stage.show();
		
		// Starting coordinates for the redShip
		double[] start_coords_red = startCoords(RADIUS_OF_PLASMAZONE, CENTRE_WIDTH, CENTRE_HEIGHT);
		redShip.setLocation(start_coords_red[0], start_coords_red[1]);
		
		
	}

	/**
	 * This method performs one frame of animation by calculating the new positions of all objects in the scene.
	 * Write code that updates the positions of all your objects here
	 * 
	 * @param deltaT - the amount of time, in seconds, that has passed since the last call to this method
	 */
	public void upDate(double deltaT)
	{	
		// Starts, pauses and resumes the simulation
		if (!pause && start && !escaped && !captured) {
			double angleB = blueShip.getAngleInDegrees();
			double angleR = redShip.getAngleInDegrees();
			
			redShip.chaseBlueShip(angleB, RADIUS_OF_PLASMAZONE, CENTRE_WIDTH, CENTRE_HEIGHT);
			
			// Collision detection
			if (blueShip.isTouching(redShip.getLocation()[0], redShip.getLocation()[1])) {
				captured = true;
			}
			
			// Execute the escape
			if (blueShip.executeEscape(angleR)) {
				escaping = true; 
			}	
			
			// blueShip to move on escape path
			if (escaping) {
				blueShip.escape();
				if (blueShip.distanceFromCircumference(RADIUS_OF_PLASMAZONE) < 0) {
					escaped = true;
				}
			}
			
			// Default movement of the blueShip
			if (!escaping) {
				blueShip.moveOnCircleCounterClockwise(DISTANCE_FROM_CENTRE, CENTRE_WIDTH, CENTRE_HEIGHT);
			}
			
			// Update the positions of the ships
			blueShip.update();
			redShip.update();			
		}
	}

	/**
	 * This method handles all of the drawing or rendering of your scene.  Write your code the draws all of your objects here.
	 * 
	 * @param gc - the graphics context that we want to draw to (defined in the start() method)
	 */
	public void render(GraphicsContext gc)
	{
		// clear the screen
		gc.setFill(backgroundColor);
		gc.fillRect(0, 0, WIDTH, HEIGHT);
		
		// Draw Plasma Zone 	
		gc.setFill(Color.GREENYELLOW);
		gc.fillOval(coords[0], coords[1], RADIUS_OF_PLASMAZONE*2, RADIUS_OF_PLASMAZONE*2);
		
		// Draw Red SpaceShip
		redShip.draw(gc);
		
		// Re-draw the button depending on the state of the game
		if (!pause) {
			button.drawButton(gc, offsetX, offsetY, "PAUSE");
		} else if (pause) {
			button.drawButton(gc, offsetX, offsetY, "START");
		}

		// Draw Blue SpaceShip
		if (start) {
			blueShip.draw(gc);
		}	
		
		// Display text after blueShip escapes
		if (escaped) {
			gc.setFill(Color.BLACK);
			// The 70 is to move the x position of the text
			gc.fillText("BLUESHIP HAS ESCAPED!!", CENTRE_WIDTH - 70, CENTRE_HEIGHT);
		}
		
		// Display text after blueShip is captured
		if (captured) {
			gc.setFill(Color.BLACK);
			// The 70 is to move the x position of the text
			gc.fillText("BLUESHIP HAS BEEN CAPUTRED!!", CENTRE_WIDTH - 70, CENTRE_HEIGHT);
		}
	}

}








