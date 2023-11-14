/**
 * Class Name: FunctionTester
 * Assignment Name: Function OOP
 * Author's Name: Josh Kim
 * Date: March 8th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class is used to test the Function class with all of the its 
 * child classes (Polynomial, Arc, Logarithm) for any bugs or errors. 
 */


package assignment2;

public class FunctionTester {

	public static void main(String[] args) {
		
		Line l = new Line(1, 0, 0);
		l.setDomain(-29, -100);
		System.out.println(l.toString());
		System.out.println(l.value(-29));
		//System.out.println(l.derivative(90));
		
		Line l1 = new Line(20, 0, -20);
		
		Quadratic q = new Quadratic(-1, 0, -9, -3);
		System.out.println(q.toString());
		//System.out.println(q.value(3));
		//System.out.println(q.derivative(1));
		
		Cubic c = new Cubic(-1, 0, -9, -81, -3);
		System.out.println(c.toString());
		//System.out.println(c.value(3));
		
		Arc a = new Arc(5, 6, -9);
		System.out.println(a.value(0));
		System.out.println(a.derivative(-5));
		System.out.println(a.toString());
		
		Parabola p = new Parabola(1, 0, 0);
		System.out.println(p.toString());
		System.out.println(p.value(9));
		System.out.println(p.derivative(-2));
		
		Logarithm log = new Logarithm(-1, 9, -1);
		log.setDomain(-20, -10);
		System.out.println(log.toString());
		System.out.println(log.value(1));
		System.out.println(log.derivative(0));
		
		
	}

}
