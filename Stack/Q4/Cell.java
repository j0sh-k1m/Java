/**
* Class Name: Cell 
* Assignment Name: ADT (stack) Questions
* Author's Name: Josh Kim
* Date: April 30th 2022
* Teacher's Name: Mr.Radulovic 
* Course Code: ICS4U
* Description:
* This class is used to store information about each Cell (node) in the grid
* such as the coordinates the value in this case (0, 1, S, E) and whether the Cell
* has been visited. 
*/

package Stack.Q4;

public class Cell {
    private int x; 
    private int y; 
    private char value; 
    private boolean visited = false; //Automatically set visited to false

    /**
     * Constructor for a Cell object that represents a Node
     * @param x - x coordinate of the Node in the grid
     * @param y - y coordinate of the Node in the grid
     * @param val - the value of the Node
     */
    public Cell(int x, int y, char val) {
        this.x = x; 
        this.y = y;
        this.value = val; 
    }

    /**
     * Get the value of the Cell
     * @return - value of the Cell
     */
    public char getValue() {
        return this.value; 
    }

    /**
     * Get the coordinates of the Cell within the Grid
     * @return - an int array with the x and y coordinates 
     */
    public int[] getCoords() {
        return new int[]{x, y};
    }

    /**
     * Mark a Cell as visited 
     */
    public void visited() {
        this.visited = true; 
    }

    /**
     * Method to check if a Cell has been visited 
     * @return - true if the Cell has been visited
     */
    public boolean isVisited() {
        return this.visited;
    }


}
