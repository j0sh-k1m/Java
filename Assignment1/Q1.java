package Assignment1;

import java.util.Scanner;

public class Q1 {

    /**
     * Method that finds number of divisors of input n 
     * @param n integer which the number of divisors is computed from
     * @return an integer that is the number of divsors that n has 
     */

    static int numberOfDivisors(int n) {
        int count = 0; 
        for (int i=1; i < n+1; i++) {
            if (n % i == 0) {
                count++;
            }
        }
        return count;
    }

    public static void main(String[] args) {
        
		// Creating new Scanner object to read user input 
        Scanner input = new Scanner(System.in);
        System.out.println("Enter an integer: ");
        int N = input.nextInt();
        
        //Assigning variables initial values 
        int largest = 1;
        int numDivisors = 1; 
        int answer = 0; 
        
        // Loop through all numbers from 1 to N
        for (int i=1; i <= N; i++) {
        	// Calls a method that returns the number of divisors from the input (param)
        	numDivisors = numberOfDivisors(i);
        	if (numDivisors > largest) {
        		largest = numDivisors; 
        		answer = i;
        	}
        }

        System.out.println("The integer with the greatest number of divisors from 1 to " + N + " is: " + answer);  
    }
}