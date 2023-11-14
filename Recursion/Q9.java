package Recursion;

public class Q9 {
    public static void main(String[] args) {
        System.out.println(cos(2, -6));
    }

    public static double sin(int n, double x) {
        if (n < 0) {
            n *= -1;
            x *= -1;
        }
        if (n == 1) return Math.sin(x);
        return Math.sin(x)*cos(n-1, x) + Math.cos(x)*sin(n-1, x);
    }

    public static double cos(int n, double x) {
        if (n < 0) {
            n *= -1;
            x *= -1; 
        }
        if (n == 1) return Math.cos(x);
        return Math.cos(x)*cos(n-1, x) - Math.sin(x)*sin(n-1, x);
    }
}
