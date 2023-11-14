package EfficiencyNotes;

class SortMethod1 {
    public static void main(String[] args) {
        
        // Bubble Sort
        int[] list = new int[] {4, 2, 14, 9, 5, 7, 1, 10};
  
        for (int i = 0; i < list.length-1; i++) {
            for (int j = 0; j < list.length-1; j++) {
                if (list[j] > list[j+1]) {
                    int l = list[j];
                    int s = list[j+1];
                    list[j] = s;
                    list[j+1] = l; 
                }
            }
        }

        for (int i=0; i < list.length; i++) {
            System.out.print(list[i] + ", ");
        }
    }
}