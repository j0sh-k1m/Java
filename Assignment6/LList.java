/**
 * Class Name: LList 
 * Assignment Name: Assignment 6 
 * Author's Name: Josh Kim
 * Date: May 30th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This is my linked list class   
 */

package Assignment6;

public class LList {
    private Node startNode; 
    private int size = 0; 

    /**
     * Insert a node at the beginning of the linked list
     * @param n - Node to be inserted 
     */
    public void insert(Node n) {
        n.setNext(startNode);
        startNode = n;
        size++; 
    }

    /**
     * Delete the node at a certain index of a linkedlist
     * @param i - index to be deleted (starts form 0)
     */
    public void delete(int i) {
        if (i > size || i<0) return;
        Node n = startNode; 
        int index = 1;
        while (index < i-1) {
            n = n.getNext();
            index++; 
        }
        n.setNext(n.getNext().getNext());
        size--;
    }

    /**
     * Get the node at a specified index in a linkedlist
     * @param i - index to get
     * @return - the node at index "i"
     */
    public Node get(int i) {
        Node n = startNode; 

        if (i>size) return null;

        for (int j=0; j < i; j++) {
            n = n.getNext();
        }
        return n; 
    }

    /**
     * Get the size of the linked list
     * @return - the size of the linkedlist AKA "length"
     */
    public int getSize() {
        return size; 
    }
}
