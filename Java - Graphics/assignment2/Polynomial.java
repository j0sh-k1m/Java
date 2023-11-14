/**
 * Class Name: Polynomial
 * Assignment Name: Function OOP
 * Author's Name: Josh Kim
 * Date: March 8th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class inherits from Function and implements a toString, value and derivative method.
 * These methods will be used for all polynomials (Cubic, Quadratic, Line) hence why the 
 * they empty aside from a constructor. 
 */

package assignment2;

public class Polynomial extends DrawFunction {

	protected double[] coefficients;
	protected double x1;

	public Polynomial(double[] a) {
		this.coefficients = a;
		// System.out.println(coefficients[0]);
	}

	public String toString() {
		String str = "";
		// Loop from the end of the array to beginning
		for (int i = this.coefficients.length - 1; i > 0; i--) {

			// First term of polynomial
			// Checks all the different cases of "+" "-" in the equation
			if (i == this.coefficients.length - 1 && i != 1) {
				if (x1 == 0) {
					str += this.coefficients[i] + "*x^" + i + " ";
				} else if (x1 > 0) {
					str += this.coefficients[i] + "*(x - " + Math.abs(this.x1) + ")^" + i + " ";
				} else if (x1 < 0) {
					str += this.coefficients[i] + "*(x + " + Math.abs(this.x1) + ")^" + i + " ";
				}
			} else if (i == this.coefficients.length - 1 && i == 1) {
				if (x1 == 0) {
					str += this.coefficients[i] + "*x ";
				} else if (x1 > 0) {
					str += this.coefficients[i] + "*(x - " + Math.abs(this.x1) + ") ";
				} else if (x1 < 0) {
					str += this.coefficients[i] + "*(x + " + Math.abs(this.x1) + ") ";
				}
			}

			// Terms after the first of polynomial
			else if (i != this.coefficients.length - 1 && i != 1) {
				if (x1 == 0 && this.coefficients[i] > 0) {
					str += "+ " + Math.abs(this.coefficients[i]) + "*x^" + i + " ";
				} else if (x1 == 0 && this.coefficients[i] < 0) {
					str += "- " + Math.abs(this.coefficients[i]) + "*x^" + i + " ";
				} else if (x1 > 0 && this.coefficients[i] > 0) {
					str += "+ " + Math.abs(this.coefficients[i]) + "*(x - " + Math.abs(this.x1) + ")^" + i + " ";
				} else if (x1 > 0 && this.coefficients[i] < 0) {
					str += "- " + Math.abs(this.coefficients[i]) + "*(x - " + Math.abs(this.x1) + ")^" + i + " ";
				} else if (x1 < 0 && this.coefficients[i] > 0) {
					str += "+ " + Math.abs(this.coefficients[i]) + "*(x + " + Math.abs(this.x1) + ")^" + i + " ";
				} else if (x1 < 0 && this.coefficients[i] < 0) {
					str += "- " + Math.abs(this.coefficients[i]) + "*(x + " + Math.abs(this.x1) + ")^" + i + " ";
				}
			}

			// Check to see if any (x - x1) are raised to the exponent of 1
			else if (i != this.coefficients.length - 1 && i == 1) {
				if (x1 == 0 && this.coefficients[i] > 0) {
					str += "+ " + Math.abs(this.coefficients[i]) + "*x ";
				} else if (x1 == 0 && this.coefficients[i] < 0) {
					str += "- " + Math.abs(this.coefficients[i]) + "*x ";
				} else if (x1 > 0 && this.coefficients[i] > 0) {
					str += "+ " + Math.abs(this.coefficients[i]) + "*(x - " + Math.abs(this.x1) + ") ";
				} else if (x1 > 0 && this.coefficients[i] < 0) {
					str += "- " + Math.abs(this.coefficients[i]) + "*(x - " + Math.abs(this.x1) + ") ";
				} else if (x1 < 0 && this.coefficients[i] > 0) {
					str += "+ " + Math.abs(this.coefficients[i]) + "*(x + " + Math.abs(this.x1) + ") ";
				} else if (x1 < 0 && this.coefficients[i] < 0) {
					str += "- " + Math.abs(this.coefficients[i]) + "*(x + " + Math.abs(this.x1) + ") ";
				}
			}
		}

		// Add the last term of the polynomial to the string
		if (this.coefficients[0] > 0) {
			str += "+ " + Math.abs(this.coefficients[0]);
		} else if (this.coefficients[0] < 0) {
			str += "- " + Math.abs(this.coefficients[0]);
		}

		return str;
	}

	@Override
	public double value(double x) {
		if (this.undefined(x)) {
			return Double.NaN;
		} else {
			// Get the values of each term in polynomial and then add them
			double val = coefficients[0];
			for (int i = 1; i < coefficients.length; i++) {
				val += coefficients[i] * Math.pow((x - x1), i);
			}
			return val;
		}
	}

	@Override
	public double derivative(double x) {
		if (this.undefined(x)) {
			return Double.NaN;
		} else {
			// Apply the power rule to each term in equation 
			// to get the derivative 
			double dyDx = 0;
			for (int i = 1; i < coefficients.length; i++) {
				dyDx += coefficients[i] * i * Math.pow(x - x1, i - 1);
			}
			return dyDx;
		}
	}

}
