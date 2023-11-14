package Recursion;

public class Q8 {
    public static void main(String[] args) {
        System.out.println(GCD(1737, 1028472));
    }

    public static int GCD(int a, int b) {
        if (a == 0) {
            return b;
        } else if (b == 0) {
            return a;
        }
        if (a > b) {
            int num = a % b;
            return GCD(b, num);
        } else {
            int num = b % a;
            return GCD(a, num);
        }
    }
}
