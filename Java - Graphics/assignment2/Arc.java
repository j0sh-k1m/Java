/**
 * Class Name: Arc
 * Assignment Name: Function OOP
 * Author's Name: Josh Kim
 * Date: March 8th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class inherits from the Function class and has methods that will return 
 * values of derivatives and y-values of the graph at any point x, assuming that
 * the function is defined at that point x. 
 */


package assignment2;

public class Arc extends DrawFunction {
	
	protected double r;
	protected double xcenter;
	protected double ycenter;
	
	public Arc(double r, double xcenter, double ycenter) {
		this.r = r;
		this.xcenter = xcenter;
		this.ycenter = ycenter;
	}
	
	/**
	 * Method what will return a string that represents the equation of this arc function
	 */
	public String toString() {
		String str = "";
		str += "\u221A(" + r + "^2 - (x";
		if (xcenter < 0) {
			str += " + " + Math.abs(xcenter) + ")^2)";
		} else if (xcenter > 0) {
			str += " - " + Math.abs(xcenter) + ")^2)";
		} else if (xcenter == 0) {
			str += ")^2)";
		}
		if (ycenter > 0) {
			str += " + " + Math.abs(ycenter);
		} else if (ycenter < 0) {
			str += " - " + Math.abs(ycenter);
		}
		return str;
	}
	
	/**
	 * Method that will return true if this arc is undefined at any value x
	 */
	public boolean undefined(double x) {
		if (Math.pow(r, 2) - Math.pow(x - xcenter, 2) < 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the value of the arc function at any point x on the graph
	 */
	@Override
	public double value(double x) {
		if (this.undefined(x)) {
			return Double.NaN;
		} else {
			return Math.sqrt(Math.pow(this.r, 2) - Math.pow(x - this.xcenter, 2)) + this.ycenter;
		}
	}

	/**
	 * Returns the derivative value for this arc at any point x on the graph
	 */
	@Override
	public double derivative(double x) {
		if (this.undefined(x)) {
			return Double.NaN;
		} else {
			return -1 * ( (x - xcenter) / (Math.sqrt(Math.pow(this.r, 2) - Math.pow(x - this.xcenter, 2))) );
		}
	}
	
}
