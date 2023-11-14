/**
 * Class Name: GraphingFX
 * Assignment Name: Function OOP
 * Author's Name: Josh Kim
 * Date: March 8th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class runs JavaFX to draw the functions to the canvas. It has a method that 
 * will draw the x and y axis. NOTE: the GraphicsContext must be defined for 
 * each individual function within the Start method as follows, 
 * function.setGC( "input graphics context here" ).
 */

package assignment2;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class GraphingFX extends Application {
	
		// define the width and height of the window
		private final int WIDTH = 800;
		private final int HEIGHT = 800;
		
		private Color backgroundColor = Color.WHITE;
		
		private GraphicsContext gc;	// object that will allow us to draw on the screen
		
		
		// Define new objects 
		Line f = new Line(1, -2, 0);
		Cubic c = new Cubic(5, -4, -1, 4, 0);
		Arc a = new Arc(5, 9, 0);
		Logarithm l = new Logarithm(1, 0, -9);
		Quadratic q = new Quadratic(0, 0, 20, -20);
		Parabola p = new Parabola(-1.0/40, 9, -2);
		
		/**
		 * Method that will draw the x and y axis along with the 
		 * values at each point. 
		 */
		public void drawAxis() {
			// Draw the axis 
			gc.setFill(Color.BLACK);
			// Vertical Line
			gc.strokeLine(WIDTH/2, 0, WIDTH/2, HEIGHT);
			// Horizontal line 
			gc.strokeLine(0, HEIGHT/2, WIDTH, HEIGHT/2);
			
			double counter = -40;
			for (int i=0; i < WIDTH; i+=10) {
				if (i < 400) {
					if (i/10 % 2 == 0) {
						gc.strokeLine(i, (HEIGHT/2)+3, i, (HEIGHT/2)-3);
						gc.fillText(Double.toString(counter), i, HEIGHT/2, 12);
						//gc.strokeText(Double.toString(counter), i, (HEIGHT/2)-4);

						gc.strokeLine(WIDTH/2+3, i, WIDTH/2-3, i);
						gc.fillText(Double.toString(-1*counter), WIDTH/2, i, 12);
						counter += 2;
					}
				} else if (i > 390 && i != 400) {
					if (i/10 % 2 == 0) {
						gc.strokeLine(i, (HEIGHT/2)+3, i, (HEIGHT/2)-3);
						gc.fillText(Double.toString(counter+2), i, HEIGHT/2, 12);
						//gc.strokeText(Double.toString(counter), i, (HEIGHT/2)-4);
						
						gc.strokeLine(WIDTH/2+3, i, WIDTH/2-3, i);
						gc.fillText(Double.toString(-1*(counter+2)), WIDTH/2, i, 12);
						counter += 2;
					}
				}
			}
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
			
			// Set the graphics context for each function
			f.setGC(gc);
			c.setGC(gc);
			a.setGC(gc);
			l.setGC(gc);
			q.setGC(gc);
			p.setGC(gc);

			// Create the Pane
			Pane root = new Pane();

			// Add the Canvas to the Pane
			root.getChildren().add(canvas);
			// Create the Scene
			Scene scene = new Scene(root);
			
			render(gc);

			// Add the Scene to the Stage
			stage.setScene(scene);
			// Set the Title of the Stage
			stage.setTitle("GraphingFX");
			// Display the Stage
			stage.show();
			

		}

		/**
		 * This method handles all of the drawing or rendering of your scene. 
		 * Write your code the draws all of your objects here.
		 * 
		 * @param gc - the graphics context that we want to draw to (defined in the start() method)
		 */
		public void render(GraphicsContext gc) {
			
			gc.setFill(backgroundColor);
			gc.fillRect(0, 0, WIDTH, HEIGHT);
			
			drawAxis();
			
			// Set the colours of each function
			c.setColour(Color.AQUA);
			a.setColour(Color.PURPLE);
			l.setColour(Color.RED);
			q.setColour(Color.DARKCYAN);
			p.setColour(Color.DEEPPINK);
			
			// Set Domain of each function
			f.setDomain(-40, 40);
			c.setDomain(-40, 40);
			a.setDomain(-40, 40);
			l.setDomain(-40, 40);
			q.setDomain(-40, 40);
			p.setDomain(-40, 40);
			
			// Draw the functions
			f.draw();
			c.draw();
			a.draw();
			l.draw();
			q.draw();
			p.draw();
		}
}
