package assignment1;
/**
 * Class name: SpaceShip
 * Assignment name: Review Assignment
 * Author's name: Joshua Kim
 * Date: February 20, 2022
 * Teacher's name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class is made to create the space ships that will be used for the simulation. 
 * It contains methods that set and get positions, and speeds. It also includes many methods
 * that perform movement tasks, and collision detection etc. 
 */


import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class SpaceShip {
	private final double radius;
	private Color colour;  
	private double[] location; 
	private double[] velocity; 
	private double speed; 
	private double ORIGIN_X = 400; 
	private double ORIGIN_Y = 400; 
	
	/**
	 * Constructor 
	 */
	public SpaceShip() {
		radius = 10; 
		location = new double[2];		
		velocity = new double[2];
	}
	
	/**
	 * Constructor
	 * @param c takes a colour input for the circle 
	 */
	public SpaceShip(Color c) {
		this();
		colour = c; 
	}
	
	/**
	 * Constructor 
	 * @param c takes colour input
	 * @param s takes speed input
	 */
	public SpaceShip(Color c, double s) {
		this();
		colour = c; 
		speed = s; 
	}
	
	/**
	 * Draws the graphics to the canvas
	 * @param gc takes graphics context input
	 */
	public void draw(GraphicsContext gc) {
		gc.setFill(colour);
		gc.fillOval(withCentre(location[0]), withCentre(location[1]), radius*2, radius*2);
	}
	
	/**
	 * Converts the top-left corner coordinates of the circle to the centre of the circle 
	 * @param pos position of the circle 
	 * @return the centre position of the circle for the x or y value
	 */
	private double withCentre(double pos) {
		return pos - radius;
	}
	
	/**
	 * Gets the radius of object 
	 * @return the radius of the circle object 
	 */
	public double getRadius() {
		return radius; 
	}
	
	/**
	 * gets the velocity 
	 * @return the velocity of the object 
	 */
	public double[] getVelocity() {
		return velocity;
	}
	
	/**
	 * Gets the speed
	 * @return the speed of the object 
	 */
	public double getSpeed() {
		return speed; 
	}
	
	/**
	 * Gets the location of the object 
	 * @return the location of the object 
	 */
	public double[] getLocation() {
		return location;
	}
	
	/**
	 * Converts the angle of a spaceship relative to the origin of plasmazone into degrees
	 * @return the angle in degrees 
	 */
	public double getAngleInDegrees() {
		double angle = this.getAngle();
		if (angle < 0) {
			double converted_angle = Math.abs(Math.toDegrees(angle));
			return converted_angle;
		} else {
			double converted_angle = 360 - Math.abs(Math.toDegrees(angle));
			return converted_angle;
		}
	}
	
	/**
	 * Sets the velocity of the spaceship 
	 * @param velocity of the spaceship
	 */
	public void setVelocity(double[] velocity) {
		this.velocity = velocity;
	}
	
	/**
	 * Sets the location of the circle 
	 * @param x the x position of the circle 
	 * @param y the y position of the circle 
	 */
	public void setLocation(double x, double y) {
		location[0] = x; 
		location[1] = y; 
	}
	
	/**
	 * Sets the speed of the spaceship
	 * @param s speed 
	 */
	public void setSpeed(double s) {
		this.speed = s;
	}
	
	/**
	 * Moves a SpaceShip to a coordinate on the canvas by updating the velocity
	 * @param x x-position of the object 
	 * @param y y-position of the object 
	 * @param speed takes the speed of the object 
	 */
	private void moveTo(double x, double y) {
		double xPos = x - location[0];
		double yPos = y - location[1];
		
		double angle = Math.atan2(yPos, xPos);
		
		velocity[0] = this.speed * Math.cos(angle); 
		velocity[1] = this.speed * Math.sin(angle);
	}
	
	/**
	 * Gets the angle of an object in radians 
	 * @return the angle of the object 
	 */
	private double getAngle() {
		double xPos = this.location[0] - ORIGIN_X; 
		double yPos = this.location[1] - ORIGIN_Y; 
		double angle = Math.atan2(yPos, xPos);
		return angle;
	}
	
	/**
	 * Gets the angle of segments that divide the circle into small pieces 
	 * @param radius this is the radius of the circle
	 * @param speed the speed of the object 
	 * @return the angle of a segment 
	 */
	private double angleOfSegments(double radius, double speed) {
		double distance = speed;
		double circumference = 2 * Math.PI * radius; 
		double slices = circumference / distance; 
		double angle = (2 * Math.PI) / slices; 
		return angle;
	}
	
	/**
	 * Moves a spaceship clockwise along the circumference of a circle
	 * @param radius of the circle the object will move around
	 * @param x centre of the circle the object will move around
	 * @param y centre of the circle the object will move around
	 * @param speed of the object 
	 */
	public void moveOnCircleClockwise(double radius, double x, double y) {
		double xPos = radius * Math.cos(this.getAngle() + angleOfSegments(radius, this.speed)) + x;
		double yPos = radius * Math.sin(this.getAngle() + angleOfSegments(radius, this.speed)) + y;	
		this.moveTo(xPos, yPos);
	}
	
	/**
	 * Moves a spaceship counter-clockwise along the circumference of a circle
	 * @param radius of the circle the object will move around
	 * @param x centre of the circle the object will move around
	 * @param y centre of the circle the object will move around
	 * @param speed of the object 
	 */
	public void moveOnCircleCounterClockwise(double radius, double x, double y) {
		double xPos = radius * Math.cos(this.getAngle() - angleOfSegments(radius, this.speed)) + x;
		double yPos = radius * Math.sin(this.getAngle() - angleOfSegments(radius, this.speed)) + y;	
		this.moveTo(xPos, yPos);
	}
	
	/**
	 * Method that will make the redShip chase the blueShip around the circumference of the plasmazone
	 * @param radius the radius of the circle, in this case the plasma zone 
	 * @param centreX of the circle (plasma zone)
	 * @param centreY of the circle (plasma zone) 
	 */
	public void chaseBlueShip(double angleB, double radius, double centreX, double centreY) {
		double diff;
		double half_circle_degrees = 180;
		if (angleB > this.getAngleInDegrees()) {
			diff = angleB - this.getAngleInDegrees();
			if (diff < half_circle_degrees) {
				this.moveOnCircleCounterClockwise(radius, centreX, centreY);
			} else if (diff > half_circle_degrees) {
				this.moveOnCircleClockwise(radius, centreX, centreY);
			}
		} else if (this.getAngleInDegrees() > angleB) {
			diff = this.getAngleInDegrees() - angleB;
			if (diff < half_circle_degrees) {
				this.moveOnCircleClockwise(radius, centreX, centreY);
			} else if (diff > half_circle_degrees) {
				this.moveOnCircleCounterClockwise(radius, centreX, centreY);
			}
		}
	}
	
	/**
	 * Get's the slope of a line between two points
	 * @param x1 x position of point 1
	 * @param y1 y position of point 1
	 * @param x2 x position of point 2
	 * @param y2 y position of point 2
	 * @return the slope of the line
	 */
	private double slopeOfLine(double x1, double y1) {
		double slope = (this.location[1] - y1) / (this.location[0] - x1);
		return slope; 
	}

	/**
	 * Method for blueShip to escape by calculating the slope from the centre of the plasmazone to the centre
	 * of the blueShip. The blueShip will move along the previously calculated slope to the
	 *  circumference of the plasmazone. 
	 */
	public void escape() {
		double path = this.slopeOfLine(ORIGIN_Y, ORIGIN_X);
		if (this.location[0] > ORIGIN_X) {
			this.moveTo(this.location[0]+1, this.location[1]+path);
		} else if (this.location[0] < ORIGIN_X) {
			this.moveTo(this.location[0]-1, this.location[1]-path);
		} 
	}
	
	/**
	 * Calculates the distance between the spaceship and the closest point on the circumference of plasmazone
	 * @param radius of the plasmazone 
	 * @return the distance from the centre of the plasmazone. 
	 */
	public double distanceFromCircumference(double radius) {
		double valX = Math.pow((this.location[0] - ORIGIN_X), 2);
		double valY = Math.pow(this.location[1] - ORIGIN_Y, 2);
		double distance = Math.sqrt(valX + valY);
		return radius - distance; 
	}
	
	/**
	 * Method to start the escape sequence for the blueShip
	 * @param angle1 angle of the blueShip
	 * @param angle2 angle of the redShip
	 * @return true or false on whether to start the sequence
	 */
	public boolean executeEscape(double angle2) {
		double angle_diff = 0;
		double complementary_to_360 = 0;
		double max_range = 180.01;
		double min_range = 179.99;
		// Ensuring that I get a positive value for the difference in angle
		if (this.getAngleInDegrees() > angle2) {
			angle_diff = this.getAngleInDegrees() - angle2;
			complementary_to_360 = 360 - angle_diff;
		} else if (angle2 > this.getAngleInDegrees()) {
			angle_diff = 360 - (angle2 - this.getAngleInDegrees());
			complementary_to_360 = 360 - angle_diff;
		}
		if (complementary_to_360 < max_range && complementary_to_360 > min_range) {
			return true;		
		}
		return false; 
	}
	
	/**
	 * Method to detect of the two space ships are touching 
	 * @param x position of the redShip
	 * @param y position of the redShip
	 * @return true or false depending if they are touching (colliding)
	 */
	public boolean isTouching(double x, double y) {
		double valX = Math.pow(this.location[0] - x, 2);
		double valY = Math.pow(this.location[1] - y, 2);
		double distance = Math.sqrt(valX + valY);
		if (distance < this.radius * 2) {
			return true;
		}
		return false;
	}
	
	
	/**
	 * Updates the location of the object by adding the velocity to its location
	 */
	public void update() {
		location[0] += velocity[0];
		location[1] += velocity[1];
	}
}
