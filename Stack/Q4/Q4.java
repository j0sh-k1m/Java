/**
 * Class Name: Q4 
 * Assignment Name: ADT (stack) Questions
 * Author's Name: Josh Kim
 * Date: April 30th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class will find and print a path from the starting Node S to the 
 * ending Node E within the maze. If there is no valid escape (path) from 
 * S to E, then my program will print "NO ESCAPE PATH".
 * My code also assumes that any given maze is a rectangle
 * 
 * Example 
 * maze = [0, 0, 0, 0
 *         S, 1, J, 0
 *         0, 1, 1, E]
 * Coordinates
 * S -> (0, 1)  
 * J -> (2, 1)
 * E -> (3, 2)
 */

package Stack.Q4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;
import Stack.Stack;
import Stack.Node;

public class Q4 { 
    public static void main(String[] args) throws FileNotFoundException {
        String name = ""; // File directory

        File file = new File(name);
        int rows = (int) countLines(name);
        int size = sizeOfFile(file);

        Cell[][] maze = getArrayGrid(file, rows, size/rows); // Assuming that rows > 0
        printEscape(solveMaze(maze, findStart(maze)));
    }

    /**
     * Method that will find a path from a starting Node to the ending Node
     * @param maze - 2d array of the maze
     * @param start - the starting Cell
     * @return - a stack containing the escape route 
     */
    private static Stack solveMaze(Cell[][] maze, Cell start) {
        Stack stack = new Stack();
        stack.push(new Node(start));

        while (!stack.isEmpty()) {

            Node temp = stack.peek();
            int x = ((Cell) temp.getData()).getCoords()[0];
            int y = ((Cell) temp.getData()).getCoords()[1];
            //stack.push(temp);

            if (((Cell) stack.peek().getData()).getValue() == 'E') {
                return stack;
            } else {
                
                // Look the Node that is up 
                if (validNode(maze, x-1, y)) {
                    maze[x-1][y].visited();
                    stack.push(new Node(maze[x-1][y]));
                } 
                // Look at the Node to the right
                else if (validNode(maze, x, y+1)) {
                    maze[x][y+1].visited();
                    stack.push(new Node(maze[x][y+1]));
                }
                // Look at the Node that is down
                else if (validNode(maze, x+1, y)) {
                    maze[x+1][y].visited();
                    stack.push(new Node(maze[x+1][y]));
                }
                // Look at the Node is to the left 
                else if (validNode(maze, x, y-1)) {
                    maze[x][y-1].visited();
                    stack.push(new Node(maze[x][y-1]));
                } else {
                    stack.pop();
                }
            }
        }
        return stack;
    }    

    /**
     * Method to determine if a Node in the maze is a valid Node to move to
     * @param maze - 2D array of Cells that holds the "maze"
     * @param x - the starting "x" location (First index of 2d array -> 2dArray[x][])
     * @param y - the starting "y" location (Second index of the 2d array -> 2dArray[][y])
     * @return - true if the given Node is a valid Node that can be traversed 
     */
    private static boolean validNode(Cell[][] maze, int x, int y) {
        // Check if Node is within Maze
        if (x < 0 || y < 0 || y >= maze[0].length || x >= maze.length) {
            return false; 
        } else if (maze[x][y].isVisited()) {
            return false; 
        } else {
            return (maze[x][y].getValue() == '1' || maze[x][y].getValue() == 'E');
        }
    } 

    /**
     * Method what will find the starting point 'S' in the maze
     * @param maze - 2d Array of Cells that is the maze
     * @return - the Cell that contains 'S' (starting location)
     */
    private static Cell findStart(Cell[][] maze) {
        for (int i=0; i < maze.length; i++) {
            for (int j=0; j < maze[0].length; j++) {
                if (maze[i][j].getValue() == 'S') {
                    return maze[i][j];
                }
            }
        }
        return maze[0][0];
    }

    /**
     * Method that will print the coordinates of the escape route from start to end
     * @param stack - the stack that contains the escape route
     */
    private static void printEscape(Stack stack) {
        if (stack.isEmpty()) {
            System.out.println("NO ESCAPE PATH");
        }
        for (int i=0; i < stack.size(); i++) {
            Node temp = stack.seek((stack.size()-1) - i);
            int x = ((Cell) temp.getData()).getCoords()[0];
            int y = ((Cell) temp.getData()).getCoords()[1];
            System.out.println("(" + y + "," + x + ")");
        }
    }

    /**
     * Method to convert txt file mazes to a 2D array
     * @param f - file for input 
     * @param rows - amount of rows that the maze has
     * @param columns - amount of columns the maze has
     * @return - a 2D array of Cells 
     * @throws FileNotFoundException
     */
    private static Cell[][] getArrayGrid(File f, int rows, int columns) throws FileNotFoundException {
        Scanner scan = new Scanner(f);
        Cell[][] grid = new Cell[rows][columns];
        for (int i=0; i < rows; i++) {
            for (int j=0; j < columns; j++) {
                grid[i][j] = new Cell(i, j, scan.next().charAt(0));
            }
        }
        scan.close();
        return grid;
    }

    /**
     * Method that will get the amount of lines in a text file 
     * (This is from the internet https://mkyong.com/java/how-to-get-the-total-number-of-lines-of-a-file-in-java/)
     * @param fileName - Name of the file
     * @return - the number of lines in a text file
     */
    private static long countLines(String fileName) {
        Path path = Paths.get(fileName);
        long lines = 0;
        try {
            lines = Files.lines(path).count();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    /**
     * Method that will get amount of grid positions in the text file
     * @param f - the text file that contains the maze 
     * @return - the number of Nodes in the maze
     * @throws FileNotFoundException
     */
    private static int sizeOfFile(File f) throws FileNotFoundException {
        Scanner scan = new Scanner(f);
        ArrayList array = new ArrayList<>();
        while (scan.hasNext()) {
            array.add(scan.next().charAt(0));
        }
        scan.close();
        return array.size();
    }
}