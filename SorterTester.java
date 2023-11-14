/**
 * Class Name: SorterTester 
 * Assignment Name: Sorting Algorithms
 * Author's Name: Josh Kim
 * Date: March 25th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class is used to test the three different sorting algorithms that were
 * programmed in IntegerSorter. It also gets a run-time for each algorithm relative to
 * my personal computer. 
 */

package Assignment3;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SorterTester {
    public static void main(String[] args) throws FileNotFoundException {
        
        // Add File Path
        File file = new File("");

        // Declare an instance of IntegerSorter
        IntegerSorter sort = new IntegerSorter();
        int[] nums = putNumsInArray(file);
        sort.setList(nums);

        long start = System.nanoTime();
        sort.sort(1);
        long end = System.nanoTime();
        double time = (end - start) / 1000000000.0;
        System.out.printf("%f\n", time);
    }

    /**
     * Method to get all the numbers from the txt file and add them to an array
     * @param f - the file 
     * @return - an array with all the numbers from the txt file
     * @throws FileNotFoundException
     */
    private static int[] putNumsInArray(File f) throws FileNotFoundException {
        Scanner scan = new Scanner(f);
        ArrayList list1 = new ArrayList<>();
        while (scan.hasNextInt()) {
            list1.add(scan.nextInt());
        }
        int[] nums = new int[list1.size()];
        for (int i=0; i < nums.length; i++) {
            nums[i] = (int) list1.get(i);
        }
        scan.close();
        return nums;
    }
    
}
