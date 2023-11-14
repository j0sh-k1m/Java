/**
 * Class Name: IntegerSorter 
 * Assignment Name: Sorting Algorithms
 * Author's Name: Josh Kim
 * Date: March 25th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * In this class, the three algorithms are programmed  
 */

package Assignment3;

public class IntegerSorter implements Sorter {

    protected int[] list;

    @Override
    public void setList(int[] list) {
        this.list = list;
        
    }

    @Override
    public int[] getList() {
        return this.list;
    }

    @Override
    public void sort(int type) {
        if (type == 1) {
            sort_method1();
        } else if (type == 2) {
            sort_method2();
        } else if (type == 3) {
            sort_method3(this.list);
        }
    }

    @Override
    public String toString() {
        String str = "";
        for (int i=0; i < getList().length; i++) {
            str += getList()[i] + ", ";
        }
        return str;
    }

    /**
     * Method 1 AKA Bubble Sort
     * @return - a sorted array in increasing order 
     */
    public void sort_method1() {
        for (int i=0; i < this.list.length-1; i++) {
            for (int j=0; j < this.list.length-1; j++) {
                if (this.list[j] > this.list[j+1]) {
                    int larger = this.list[j];
                    int smaller = this.list[j+1];
                    this.list[j] = smaller;
                    this.list[j+1] = larger; 
                }
            }
        }
    }

    /**
     * Method 2
     * @return - a sorted array in increasing order
     */
    public void sort_method2() {
        for (int i=0; i < this.list.length; i++) {
            for (int j=i+1; j < this.list.length; j++) {
                if (this.list[i] > this.list[j]) {
                    int large = this.list[i];
                    this.list[i] = this.list[j];
                    this.list[j] = large;
                }
            }
        }
    }

    /**
     * Mergesort Algorithm 
     * @param array - array to be mergesorted 
     */
    private void sort_method3(int[] array) {
        int arrayLength = array.length; 
        if (array.length == 1) {
            return; 
        }

        // Create two new arrays that will be populated with the 
        // previous array split in the centre 
        int middle = arrayLength / 2; 
        int[] left = new int[middle];
        int[] right = new int[arrayLength - middle];

        for (int i=0; i < middle; i++) {
            left[i] = array[i];
        }
        for (int i=middle; i < arrayLength; i++) {
            right[i - middle] = array[i];
        } 

        // Call the methods again
        sort_method3(left);
        sort_method3(right);
        merge(array, left, right);
    }
    
    /**
     * Merges two arrays in increasing values 
     * @param array - array that is to be sorted 
     * @param sub1 - sub array1 to be merged into "array"
     * @param sub2 - sub array2 to be merged into "array"
     * @return - an array of integers in increasing order
     */
    private void merge(int[] array, int[] sub1, int[] sub2) {
        // Three pointers 
        int p1 = 0; 
        int p2 = 0; 
        int pl = 0; 
        // Merges and sorts the two arrays until one of the arrays are fully merged
        while (p1 != sub1.length && p2 != sub2.length) {
            if (sub1[p1] <= sub2[p2]) {
                array[pl] = sub1[p1];
                p1++;
            } else if (sub1[p1] > sub2[p2]) {
                array[pl] = sub2[p2];
                p2++;
            }
            pl++;
        }
        // Adds the rest of the elements from one of two arrays that have remaining 
        // elements that need to be added 
        if (p1 == sub1.length) {
            for (int i=p2; i < sub2.length; i++) {
                array[pl] = sub2[i];
                pl++;
            }
        } else if (p2 == sub2.length) {
            for (int i=p1; i < sub1.length; i++) {
                array[pl] = sub1[i];
                pl++;
            }
        }
    }

    /**
     * Checks if this.list is sorted 
     * @return - true or false to state whether the list is sorted in increasing values
     */
    public boolean isSorted() {
        for (int i=0; i < this.list.length-1; i++) {
            if (this.list[i] > this.list[i+1]) {
                return false;
            } 
        }
        return true; 
    }
}
