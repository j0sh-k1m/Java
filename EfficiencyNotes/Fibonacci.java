package EfficiencyNotes;

public class Fibonacci {

    private int t_0 = 1; 
    private int t_1 = 1; 

    public static void main(String[] args) {
        Fibonacci f = new Fibonacci();
        System.out.println(f.Fib1(100));
        
    }

    public int Fib2(int n) {
        int F;
        if (n == 1 || n == 0) {
            return 1;
        } else {
            F = Fib2(n-1) + Fib2(n-2);
        }
        return F;
    }

    public long Fib1(int n) {
        long t_n = t_0 + t_1;
        long t_n1 = t_0;
        long t_n2 = t_1;
        for (int i=2; i <= n; i++) {
            t_n = t_n1 + t_n2;
            t_n2 = t_n1;
            t_n1 = t_n; 
        }
        return t_n;
    }
}
