/**
 * Class Name: DrawFunction
 * Assignment Name: Function OOP
 * Author's Name: Josh Kim
 * Date: March 8th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class inherits from the Function class and implements the Drawable interface. 
 * It contains the methods to set the graphics Context for each individual function
 * and also set the colour of each function. Additionally, it has the draw method 
 * that will draw the Function. 
 */

package assignment2;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public abstract class DrawFunction extends Function implements Drawable {
	
	protected GraphicsContext gc; 
	protected double ORIGIN_X = 800/2; 
	protected double ORIGIN_Y = 800/2;
	protected Color colour = Color.PINK; 
	protected double scale = 10;
	
	/**
	 * Set the graphics context for a new function object. 
	 * @param gc
	 */
	public void setGC(GraphicsContext gc) {
		this.gc = gc;
	}
	
	/**
	 * Sets the colour of the function 
	 * @param c JavaFX color 
	 */
	public void setColour(Color c) {
		this.colour = c;
	}
	
	/**
	 * This method will draw the function onto the canvas
	 */
	@Override
	public void draw() {
		// set the colour
		gc.setStroke(this.colour);
		// get the starting points according to the domain
		double start_x = this.getStartDomain();
		double start_y = this.value(start_x);
		// Invert the y axis because the "original" origin is the top left corner
		// and we are in the 4th quadrant. 
		start_y = start_y * -1;
		
		// Graph the function be looping from the start domain to the end
		// adding very small increments to the get the next point.
		// Then connect the two consecutive points with a line and then update for new points.
		for (double i=start_x; i <= this.getEndDomain(); i+=0.001) {
			double y_val = this.value(i)*-1;
			gc.strokeLine((start_x*scale)+ORIGIN_X, (start_y*scale)+ORIGIN_Y, (i*scale)+ORIGIN_X, (y_val*scale)+ORIGIN_Y);
			start_x = i;
			start_y = y_val;
		}
	}

	
}
