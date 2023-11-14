package assignment1;
/**
 * Class name: Button
 * Assignment name: Review Assignment
 * Author's name: Joshua Kim
 * Date: February 20, 2022
 * Teacher's name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class is used to create the button that is used to pause and play the simulation.
 * It was made to ease the creation of the button by assigning positions and using the getter methods
 * to retrieve data that would then be used to detect for button clicks. 
 */


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Button {
	
	private Color colour;
	private double posX; 
	private double posY; 
	private double length;
	private double width;
	
	/**
	 * Constructor 
	 * @param c color of the button
	 * @param x position of the button
	 * @param y position of the button
	 * @param l length of the button
	 * @param w width of the button
	 */
	public Button(Color c, double x, double y, double l, double w) {
		this.colour = c;
		this.posX = x;
		this.posY = y;
		this.length = l;
		this.width = w;
		
	}
	
	/**
	 * Draw Method
	 * @param gc graphics context
	 * @param offsetX move the text to the left or right
	 * @param offsetY move the text up or down
	 * @param t text for the button
	 */
	public void drawButton(GraphicsContext gc, double offsetX, double offsetY, String t) {
		gc.setFill(this.colour);
		gc.fillRect(this.posX, this.posY, this.length, this.width);
		gc.setFill(Color.BLACK);
		gc.fillText(t, this.posX+this.length/2-offsetX, this.posY+this.width/2+offsetY);
	}
	
	/**
	 * Method that gets the position of the button
	 * @return a double array with the x and y positions 
	 */
	public double[] getPosition() {
		double[] positions = new double[2];
		positions[0] = this.posX;
		positions[1] = this.posY;
		return positions;
	}
	
	/**
	 * Gets the length of the button
	 * @return the length of the button
	 */
	public double getLength() {
		return this.length;
	}
	
	/**
	 * Gets the width of the button
	 * @return the width of the button
	 */
	public double getWidth() {
		return this.width;
	}
	
}
