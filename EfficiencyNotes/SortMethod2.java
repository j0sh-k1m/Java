package EfficiencyNotes;


public class SortMethod2 {
    public static void main(String[] args) {

        int[] list = new int[] {4, 2, 14, 9, 5, 7, 1, 10};
        for (int i=0; i < list.length; i++) {
            for (int j=i+1; j < list.length; j++) {
                if (list[i] > list[j]) {
                    int large = list[i];
                    list[i] = list[j];
                    list[j] = large;
                }
            }
        }
        for (int i=0; i < list.length; i++) { 
            System.out.print(list[i] + ", ");
        }
    }
}

