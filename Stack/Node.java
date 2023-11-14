/**
 * Class Name: Node 
 * Assignment Name: ADT (stack) Questions
 * Author's Name: Josh Kim
 * Date: April 30th 2022
 * Teacher's Name: Mr.Radulovic 
 * Course Code: ICS4U
 * Description:
 * This class is used to create Node objects that will store
 * any type of data. It has a single pointer to the next node 
 * and will store data. It is used to create my stack ADT
 */

package Stack; 

public class Node<T> {
    private T data; 
    private Node<T> next; 

    /**
     * Constructor for Node
     * @param n - data to be stored in the Node
     */
    public Node(T n) {
        data = n;
    }

    /**
     * Set the pointer to the next Node of a Node
     * @param n
     */
    public void setNext(Node<T> n) {
        next = n; 
    }

    /**
     * Get the next Node of a Node
     * @return - the Node object that is next
     */
    public Node<T> getNext() {
        return next; 
    }

    /**
     * Get the data that is stored within the Node object 
     * @return - the data stored in the Node
     */
    public T getData() {
        return data; 
    }
}