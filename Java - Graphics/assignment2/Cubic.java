/**
 * Class Name: Cubic
 * Assignment Name: Function OOP
 * Author's Name: Josh Kim
 * Date: March 8th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class inherits from the Polynomial class. It only has a constructor 
 * in which it will pass the data inputed to Polynomial 
 */

package assignment2;

public class Cubic extends Polynomial{
	
	public Cubic(double a, double b, double c, double d, double x1) {
		super(new double[] {d, c, b, a});
		super.x1 = x1;
	}
}
