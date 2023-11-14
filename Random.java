
public class Random {
    public static void main(String[] args) {
        calculateCurrent();
    }

    private static void calculateCurrent() {
        for (int i=0; i <= 12; i++) {
            double res = 218.0*1000;
            System.out.println((12-i)/(res)*1000);
        }
    }
}
