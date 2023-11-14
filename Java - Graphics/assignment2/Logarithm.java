/**
 * Class Name: Logarithm
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

public class Logarithm extends DrawFunction {
	
	protected double a; 
	protected double b; 
	protected double x1; 
	
	public Logarithm(double a, double b, double x1) {
		this.a = a; 
		this.b = b;
		this.x1 = x1;
	}
	
	/**
	 * Method that will return the function as a string. 
	 */
	public String toString() {
		String str = "";
		str += a + "*In(x";
		if (x1 > 0) {
			str += " - " + Math.abs(x1) + ")";
		} else if (x1 < 0) {
			str += " + " + Math.abs(x1) + ")";
		} else if (x1 == 0) {
			str += ")";
		}
		if (b > 0) {
			str += " + " + Math.abs(b);
		} else if (b < 0) {
			str += " - " + Math.abs(b);
		}
		return str;
	}
	
	/**
	 * Returns true or false depending on if the function is defined
	 * at a certain point x. 
	 */
	public boolean undefined(double x) {
		if (x - this.x1 <= 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * Returns the y-value of the logarithm at any point x
	 * of which the x value is defined on the function.
	 */
	@Override
	public double value(double x) { 
		if (this.undefined(x)) {
			return Double.NaN;
		} else {
			return a * Math.log(x - this.x1) + b;
		}
	}
	
	/**
	 * Returns the derivative value of the logarithm 
	 * at any point x of which the x value is defined for the function.
	 */
	@Override
	public double derivative(double x) {
		if (this.undefined(x)) {
			return Double.NaN;
		} else {
			return a / (x - x1);
		}
	}
}
