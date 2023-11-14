package Recursion;

public class Q4 {
    public static void main(String[] args) {
        printNums(15);
    }

    public static void printNums(int n) {
        if (n <= 0) {
            return;
        }
        System.out.print(n + ", ");
        printNums(n-1);
    }
}
