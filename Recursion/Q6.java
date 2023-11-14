package Recursion;

public class Q6 {
    public static void main(String[] args) {
        
        System.out.println(Fib(3));
    }

    public static int Fib(int n) {
        if (n <= 1) {
            return n;
        } else {
            return Fib(n - 1) + Fib(n - 2);
        }
    }
}
