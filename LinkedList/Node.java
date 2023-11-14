package LinkedList;

public class Node<T> {
    private T data; 
    private Node<T> next; 

    public Node(T n) {
        data = n;
    }

    public void setNext(Node<T> n) {
        next = n; 
    }

    public Node<T> getNext() {
        return next; 
    }
}

// Integer 
// ArrayList<T> list = new ArrayList<T>();

// Use "T" for temporary 