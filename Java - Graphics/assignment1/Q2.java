package assignment1;
import java.util.Scanner;

public class Q2 {

	public static void main(String[] args) {
		 
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter an integer X: ");
        int X = input.nextInt();
        
        // Check for zero divisors
        if (X == 0) {
            System.out.println("Please enter a non-zero divisor value");
        } else {
            Scanner num = new Scanner(System.in);
            System.out.println("Please enter the number of integers that you will input: ");
            int length = num.nextInt();
            
            int[] numbers = new int[length];
            for (int i=0; i < length; i++) {
                Scanner scan = new Scanner(System.in);
                System.out.println("Please enter an integer: ");
                int nums = scan.nextInt();
                numbers[i] = nums;
            }
    
            for (int i=0; i < numbers.length; i++) {
                int output = numbers[i] % X; 
                System.out.print(output + ", ");
            }
        }	
		
	}

}
