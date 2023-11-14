package Queue;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import LinkedList.Node;

public class CellMutations {
    public static void main(String[] args) throws FileNotFoundException {
        GraphNode node = new GraphNode<String>("Hello");

        File file = new File("/Users/joshkim/Desktop/ICS4U/Queue/Cell.txt");
        Scanner scan = new Scanner(file);

        int cell_size = scan.nextInt();
        int total_cells = scan.nextInt();
        String[] valid_cells = new String[total_cells];
        for (int i=0; i < total_cells; i++) {
            valid_cells[i] = scan.next();
        }

        int mutations_allowed = scan.nextInt();

        int total_start_cells = scan.nextInt();
        String[] start_cells = new String[total_start_cells];
        for (int i=0; i < total_start_cells; i++) {
            start_cells[i] = scan.next();
        }

        String[] end_cells = new String[total_start_cells];
        for (int i=0; i < total_start_cells; i++) {
            end_cells[i] = scan.next();
        }

        scan.close();


        
    }

    private static boolean getAdjacent(String cell, String possible_cell) {
        int count = 0;
        for (int i=0 ; i < cell.length(); i++) { 
            if (cell.charAt(i) != possible_cell.charAt(i)) {
                count++;
            }
        }
        if (count >= 3) {
            return false; 
        } else if (count == 1) {
            return true;
        } 
        // Check if any adjacent characters can be swapped
        else if (count == 2) {
            return swapPossible(cell, possible_cell);
        }
        return false;
    }

    private static boolean swapPossible(String cell, String possible_cell) {
        for (int i=0; i < cell.length(); i++) {
            String temp = "";
            temp += possible_cell.substring(i+1);
            temp += possible_cell.substring(i);
            if (cell.substring(i, i+1).equals(temp)) {
                return true; 
            }
        }
        return false; 
    }
}