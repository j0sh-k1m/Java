/**
 * Class Name: Function
 * Assignment Name: Function OOP
 * Author's Name: Josh Kim
 * Date: March 8th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class various methods that return values at certain points of the graph
 * define the domain in which the function exists and implements two abstract methods
 * that are to be used to get derivatives and y-values of the function.
 */

package assignment2;

public abstract class Function {
	
	
	protected double min_x;
	protected double max_x; 
	protected String name; 
	
	/**
	 * Constructor 
	 */
	public Function() {
		min_x = -1;
		max_x = 1; 
	}
	
	/**
	 * Constructor
	 * @param x1 start domain for the function
	 * @param x2 end domain for the function
	 */
	public Function(double x1, double x2) {
		min_x = x1; 
		max_x = x2;
	}
	
	/**
	 * Method to set the domain of a function
	 * @param x1 start domain of the function
	 * @param x2 end domain of the function
	 */
	public void setDomain(double x1, double x2) {
		if (x1 < x2) {
			min_x = x1;
			max_x = x2;
		} else {
			min_x = x2;
			max_x = x1;
		}
	}
	
	/**
	 * Method to get the start domain of a function
	 * @return the start domain value 
	 */
	public double getStartDomain() {
		return min_x;
	}
	
	/**
	 * Method to get the start domain of a function
	 * @return the end domain value 
	 */
	public double getEndDomain() {
		return max_x;
	}
	
	/**
	 * Method to determine whether a function is defined at a certain value of x
	 * @param x the value to inspect 
	 * @return true or false whether if the function is defined 
	 */
	public boolean undefined(double x) {
		if (x < this.getStartDomain() || x > this.getEndDomain()) {
			return true;
		}
		return false;
	}
	
	/**
	 * Set the name of the graph
	 * @param name of the graph
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Get the name of the graph
	 * @return the name of the graph
	 */
	public String getName() {
		return name; 
	}
	
	/**
	 * Method to determine the y-value at any point x on the function
	 * @param x the value to be used to calculate the y-value
	 * @return the y-value of the graph at the point x
	 */
	public abstract double value(double x);
	
	/**
	 * Method to determine the derivative of the graph at a point x
	 * @param x the value to be used to calculate the derivative
	 * @return the derivative of the graph at point x
	 */
	public abstract double derivative(double x);
	
	
}
