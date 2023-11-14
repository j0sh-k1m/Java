/**
 * Class Name: Line
 * Assignment Name: Function OOP
 * Author's Name: Josh Kim
 * Date: March 8th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class inherits from the Polynomial Class and contains 
 * a single constructor which will pass the data inputed to polynomial.
 */

package assignment2;

public class Line extends Polynomial {
	
	public Line(double m, double b, double x1) {
		super(new double[] {b, m});
		super.x1 = x1;
	}
	
}