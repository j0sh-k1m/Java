/**
 * Class Name: Stack 
 * Assignment Name: ADT (stack) Questions
 * Author's Name: Josh Kim
 * Date: April 30th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class is a custom Abstract Data Type which is a stack. 
 * It contains various methods that will add and delete from the stack, 
 * check if the stack is empty, and get the size. It is based off of 
 * the LinkedList ADT which uses Nodes exactly like how it was implemented in this class. 
 */

package Stack;

public class Stack {
    private Node startNode; 
    private int size; 

    /**
     * Insert a node at the beginning of the linked list
     * @param n - Node to be inserted 
     */
    public void push(Node n) {
        n.setNext(startNode);
        startNode = n;
        size++; 
    }

    /**
     * Deletes a Node at a certain position in the linkedList
     * @param i - The position at which to delete the node 
     */
    public void delete(int i) {
        if (i > size || i < 0) return;
        Node n = startNode; 
        if (i == 0) {
            startNode = n.getNext();
        } else {
            int index = 0;
            while (index < i) {
                n = n.getNext();
                index++; 
            }
            n.setNext(n.getNext().getNext());
            
        }
        size--;
    }

    /**
     * Removes the element at the top of the stack 
     * @return - the deleted element at the top of the stack 
     */
    public Node pop() {
        Node n = startNode;
        delete(0);
        return n;
    }

    /**
     * Gets the top element of the Stack 
     * @return - the top of the stack as a Node
     */
    public Node peek() {
        return startNode; 
    }

    /**
     * Checks if the stack is empty
     * @return - true if the stack is empty 
     */
    public boolean isEmpty() {
        return (size == 0); 
    }

    /**
     * Checks of the the stack is full 
     * @return - true if the stack is full 
     */
    public boolean isFull() {
        return (size != 0);
    }

    /**
     * Gets the size of the Stack
     * @return - size of stack 
     */
    public int size() {
        return size; 
    }

    /** UTILITY METHODS */

    /**
     * Prints the stack
     */
    public void print() { 
        Node n = startNode;
        for (int i=0; i < size; i++) {
            System.out.println(n.getData());
            n = n.getNext();
        }
        System.out.println(" ");
    }

    /**
     * Gets the node at a specified index from the stack
     * @param i - index to seek
     * @return - the node at index i (starts from 0)
     */
    public Node seek(int i) {
        Node n = startNode; 
        for (int j=0; j < i; j++) {
            n = n.getNext();
        }
        return n; 
    }
}
