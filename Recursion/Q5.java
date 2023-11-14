package Recursion;

public class Q5 {
    public static void main(String[] args) {
        System.out.print(factorial(10000));
    }

    public static int factorial(int n) {
        if (n < 0) {
            return 0;
        } 
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n-1);
    }
}
