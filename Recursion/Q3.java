package Recursion;

public class Q3 {
    public static void main(String[] args) {
        printNums(15);
    }

    public static void printNums(int n) {
        if (n <= 0) {
            return;
        }
        printNums(n-1);
        System.out.print(n + ", ");
    }
}
