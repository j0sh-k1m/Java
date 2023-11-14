/**
 * Class Name: Quadratic 
 * Assignment Name: Function OOP
 * Author's Name: Josh Kim
 * Date: March 8th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class inherits from Polynomial and will pass any data 
 * to the polynomial. 
 */

package assignment2;

public class Quadratic extends Polynomial {
	
	public Quadratic(double a, double b, double c, double x1) {
		super(new double[] {c, b, a});
		super.x1 = x1;
	}

}
