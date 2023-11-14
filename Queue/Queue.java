package Queue;

public class Queue {
    private Node startNode; 
    private Node endNode; 
    private int size; 

    public void enqueue(Node n) {
        if (size == 0) {
            n.setNext(startNode);
            startNode = n;
            endNode = n;
            size++;
        } else {
            n.setNext(startNode);
            startNode = n;
            startNode.getNext().setPrev(startNode);
            size++;
        }
    }

    public Node dequeue() {
        Node n = startNode;
        Node last = endNode;
        Node sec_last = endNode.getPrev();
        if (size == 0) {
            return null;
        } else if (size == 1) {
            startNode = n.getNext();
            endNode = null;
        } else {
            sec_last.setNext(null);
            endNode.setPrev(null);
            endNode = sec_last;
        }
        size--;
        return last;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        if (size > 0) return false; 
        return true; 
    }

    public boolean isFull() {
        if (size > 0) return true; 
        return false; 
    }

    public Node peek() {
        return endNode;
    }
}
