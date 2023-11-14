/**
 * Class Name: Mutations 
 * Assignment Name: Assignment 6 
 * Author's Name: Josh Kim
 * Date: May 30th 2022 
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This is the class the will create an adjcency matrix and will also get the shortest path 
 * between a start node and the end node  
 */

package Assignment6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Mutations {
    public static void main(String[] args) throws FileNotFoundException {

        File file = new File("/Users/joshkim/Desktop/ICS4U/Assignment6/Data.txt");
        Scanner scan = new Scanner(file);

        // Read variable inputs 
        int cell_length = scan.nextInt();
        int benign = scan.nextInt();
        int disease = scan.nextInt(); 

        // Create and populate array with cells
        String[] cells = new String[benign+disease];
        for (int i=0; i < cells.length; i++) {
            cells[i] = scan.next();
        }

        int max_mutations = scan.nextInt();

        LList start_end = new LList();
        while (scan.hasNext()) {
            start_end.insert(new Node(scan.next()));
        }

        scan.close();

        double[][] graph = validCellMatrix(cells);

        for (int i=start_end.getSize()-1; i > 0; i-=2) {
            double[][] matrix = adjacencyMatrix((String)start_end.get(i).getData(), (String)start_end.get(i-1).getData(), cells, graph);
            double probability = dijkstras(matrix, max_mutations);
            if (probability > 0.0) {
                System.out.println("YES\n" + probability);
            } else {
                System.out.println("NO");
            }
        }
    }

    /**
     * Dijkstras Algorithm to find the shortest path (largest probability) to mutate from start node to end node
     * @param matrix - 2D array that contains all of the adjecent cells
     * @param max_mutations - maximum number of mutations allowed
     * @return - the largest probability that the start cell can mutate into the end cell (returns -1.0 if not possible)
     */
    public static double dijkstras(double[][] matrix, int max_mutations) {
        double[] weights = new double[matrix.length];
        boolean[] visited = new boolean[matrix.length];
        double[] mutations = new double[matrix.length];

        // Initalize mutations array to infinity 
        for (int i=0; i < mutations.length; i++) {
            mutations[i] = Integer.MAX_VALUE;
        }
        
        for (int i=0; i < matrix.length - 1; i++) {
            int max_index = maxProbability(weights, visited); 

            visited[max_index] = true;

            for (int j=0; j < matrix.length; j++) {
                
                // Starting Node (checking neighbors)
                if (weights[j] == 0.0 && i == 0) {
                    weights[j] = matrix[max_index][j];
                    // Modify mutations to keep track of number of mutations
                    if (weights[j] != 0) {
                        mutations[j] = 1;
                    }
                }
                // All other Nodes (Checking neighbors)
                else if (!visited[j] && matrix[max_index][j] != 0 && weights[max_index] * matrix[max_index][j] > weights[j]) {    
                    // Update weight if new probability path is greater 
                    weights[j] = weights[max_index] * matrix[max_index][j];
                    // Update mutations 
                    if (mutations[max_index] < mutations[j]) {
                        mutations[j] = mutations[max_index] + 1;
                    }
                }
            }
        }
        // Check if the number of mutatios to get from start to end exceeds the max number of mutations allowed
        // Also check if there is a valid path from start node to end node 
        if (mutations[mutations.length-1] > max_mutations || mutations[mutations.length-1] == Integer.MAX_VALUE) {
            return -1.0;
        }
        return weights[weights.length-1];
    }

    /**
     * Gets the index of the largest probability in an array
     * @param weights - array that contains probabilities 
     * @param visited - array that stores whether a cell has been visited
     * @return - the index where the largest probability is located
     */
    public static int maxProbability(double[] weights, boolean[] visited) {
        double max = weights[0];
        int index = 0; 
        // Loop over probabilites and check if node has been visited and get largest
        for (int i=0; i < weights.length; i++) {
            if (!visited[i] && weights[i] > max) {
                max = weights[i];
                index = i;
            }
        }
        return index; 
    }   

    /**
     * Create the adjacency matrix for all cells (including start and end cells)
     * @param start_cell - the starting cell
     * @param end_cell - the end cell (target)
     * @param cells - valid cells
     * @param matrix - the graph with all valid cells included
     * @return - 2d array that is the adjacency matrix 
     */
    public static double[][] adjacencyMatrix(String start_cell, String end_cell, String[] cells, double[][] matrix) {
        // Connect start_cell to other cells in the graph
        for (int i=1; i < matrix.length - 1; i++) {
            matrix[0][i] = isAdjacent(start_cell, cells[i-1]);
        }
        // Connect all valid cells to the end_cell
        for (int i=0; i < cells.length; i++) {
            matrix[i+1][matrix.length-1] = isAdjacent(cells[i], end_cell);
        }
        // Connect start_cell with the end_cell
        matrix[0][matrix.length-1] = isAdjacent(start_cell, end_cell);

        return matrix;
    }

    /**
     * Create a graph from the valid cells 
     * @param cells - String array of cells
     * @return - a 2d array (adjacency matrix)
     */
    public static double[][] validCellMatrix(String[] cells) {
        double[][] matrix = new double[cells.length+2][cells.length+2];
        
        // Add probabilites to the graph 
        for (int i=0; i < cells.length; i++) {
            for (int j=0; j < cells.length; j++) {
                // If adjacent add them to the graph 
                matrix[i+1][j+1] = isAdjacent(cells[i], cells[j]);
            }
        }
        return matrix; 
    }

    /**
     * Method that will check if one gene can mutate to another
     * @param start_cell - the cell that can potentially mutate
     * @param end_cell - the cell that the start_cell may be able to mutate to
     * @return - true if start_cell can mutate into end_cell
     */
    public static double isAdjacent(String start_cell, String end_cell) {
        // Condition for mutation rule 1 
        if (mutationRuleOne(start_cell, end_cell)) {
            // Times by 1/4 because AGTT -> ATTT the G is 1/4 chance to make
            return 0.06/100;
        }

        // Condition for mutation rule 2
        else if (mutationRuleTwo(start_cell, end_cell)) {
            return 0.04/100;
        }

        // Condition for mutation rule 3
        else if (mutationRuleThree(start_cell, end_cell)) {
            return 0.05/100;
        }
        return 0.00;
    }

    /**
     * Method to find adjacent identical letters in a string
     * Time Complexity: O(3n) --> O(n)
     * @param cell - string to be checked
     * @return - the index of the fist occurence of the adjacent identical letter within string
     */
    private static int[] findAdjacentIdentical(String cell) {
        LList indexes = new LList();
        // Insert node with the index value of the first occurrence of an adjacent identical pair of letters 
        for (int i=0; i < cell.length()-1; i++) {
            if (cell.charAt(i) == cell.charAt(i+1)) {
                indexes.insert(new Node(i));
            }
        }
        // add these indexes to an array 
        int[] fin = new int[indexes.getSize()];
        for (int i=0; i < indexes.getSize(); i++) {
            fin[i] = (int) indexes.get(i).getData();
        }
        return fin;
    }

    /**
     * Checks if mutation rule one can be used to get from one gene to another
     * Assumes that start_cell is one character longer than end_cell
     * @param start_cell - the start gene
     * @param end_cell - the end gene that the start gene can potentially mutate into
     * @return - true if the start gene can mutate into the end gene 
     */
    private static boolean mutationRuleOne(String start_cell, String end_cell) {
        if (Math.abs(start_cell.length() - end_cell.length()) != 1 || start_cell.length() < 2) {
            return false; 
        }
        // get the adjacent identical indexes
        int[] index = findAdjacentIdentical(start_cell);
        for (int i=0; i < index.length; i++) {
            String temp = "";
            String temp2 = "";
            int count = 0; 
            // Splice start_cell and end_cell so that adjacent identical letters are removed from start_cell
            // and the equivalent indexes are removed from end_cell
            temp += start_cell.substring(0, index[i]);
            temp += start_cell.substring(index[i] + 2, start_cell.length());
            temp2 += end_cell.substring(0, index[i]);
            temp2 += end_cell.substring(index[i] + 1, end_cell.length());

            // Check if the new substrings are indentical 
            for (int j=0; j < temp.length(); j++) {
                if (temp.charAt(j) == temp2.charAt(j)) {
                    count++; 
                }
            }
            if (count == temp.length() && temp.length() == temp2.length()) return true;
        }
        return false; 
    }

    /**
     * Check if mutation rule two can be used to mutate from one gene to another
     * @param start_cell - the starting gene
     * @param end_cell - the end gene 
     * @return - true if start_cell can mutate into end_cell 
     */
    private static boolean mutationRuleTwo(String start_cell, String end_cell) {
        if (start_cell.length() - end_cell.length() != 0) {
            return false; 
        }
        // Check if the end of start_cell and start of end_cell are the same
        if (start_cell.charAt(start_cell.length() - 1) == end_cell.charAt(0)) {
            // Loop over remaining characters and check for identicalness 
            for (int i=0; i < start_cell.length() - 1; i++) {
                if (start_cell.charAt(i) != end_cell.charAt(i+1)) {
                    return false;
                }
            }
        } else {
            return false; 
        }
        return true; 
    }

    /**
     * Check if mutation rule three can be used to mutate from one gene to another
     * @param start_cell - the starting gene 
     * @param end_cell - the ending gene
     * @return - true if start_cell can mutate into end_cell
     */
    private static boolean mutationRuleThree(String start_cell, String end_cell) {
        if (end_cell.length() - start_cell.length() != 1) {
            return false; 
        }
        int count1 = 0; 
        int count2 = 0; 
        // Loop from start to end of both genes
        for (int i=0; i < start_cell.length(); i++) {
            // Compare the characters at each index 
            if (start_cell.charAt(i) == end_cell.charAt(i)) {
                count1++;
            } else if (start_cell.charAt(i) != end_cell.charAt(i)) {
                break;
            }
        }
        // Loop from end to start of both genes 
        for (int i=start_cell.length()-1; i > 0; i--) {
            // Compare the characters at each index 
            if (start_cell.charAt(i) == end_cell.charAt(i+1)) {
                count2++; 
            } else if (start_cell.charAt(i) != end_cell.charAt(i+1)) {
                break; 
            }
        }
        // Subtract count1 and count2 from the length of both genes to check number of differing characters 
        if (start_cell.length() - count1 - count2 <= 1 && end_cell.length() - count1 - count2 <= 2) {
            return true; 
        }
        return false; 
    }

    /**
     * Print a 2d array in the format of a graph
     * @param graph - the graph to be printed 
     */
    public static void printGraph(double[][] graph) {
        int count = 0; 
        for (int i=0; i < graph[0].length; i++) {
            for (int j=0; j < graph.length; j++) {
                count++;
                if (count % graph[0].length == 0) {
                    if (graph[i][j] == 0.05/100) {
                        System.out.print(3);
                    } else if (graph[i][j] == 0.0) {
                        System.out.print(0);
                    } else if (graph[i][j] == 0.06/100) {
                        System.out.print(1);
                    } else if (graph[i][j] == 0.04/100) {
                        System.out.print(2);
                    }
                    System.out.print("\n");
                } else {
                    // Check values of the graph and print out (1, 2, 3) depending on which mutation rule is used
                    if (graph[i][j] == 0.05/100) {
                        System.out.print(3 + " ");
                    } else if (graph[i][j] == 0.0) {
                        System.out.print(0 + " ");
                    } else if (graph[i][j] == 0.06/100) {
                        System.out.print(1 + " ");
                    } else if (graph[i][j] == 0.04/100) {
                        System.out.print(2 + " ");
                    }
                }
            }
        } 
    }
}