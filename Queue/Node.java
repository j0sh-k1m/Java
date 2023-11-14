package Queue;

public class Node<T> {
    private T data; 
    private Node<T> next; 
    private Node<T> prev;

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
     * Set the previous Node of a Node
     * @param n - previous Node
     */
    public void setPrev(Node<T> n) {
        prev = n;
    }

    /**
     * Get the previous Node
     * @return - the previous Node
     */
    public Node<T> getPrev() {
        return prev;
    }

    /**
     * Get the data that is stored within the Node object 
     * @return - the data stored in the Node
     */
    public T getData() {
        return data; 
    }
}
