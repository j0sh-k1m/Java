package Recursion;

public class Q2 {
    public static void main(String[] args) {
        printReverse(9867);
    }

    public static void printReverse(int n) {
        if (n < 10) {
            System.out.print(n);
            return;
        }
        int digit = n%10;
        System.out.print(digit);
        printReverse(n/10);
    }
}
