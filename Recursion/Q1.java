package Recursion;

public class Q1 {
    public static void main(String[] args) {    
        printAsterisks(5);      
    }

    public static void printAsterisks(int n) {
        if (n <= 0) {
            return;
        }
        System.out.print("*");
        printAsterisks(n-1);
    }
}
