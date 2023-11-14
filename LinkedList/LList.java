package LinkedList;

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
     * Delete a node at a specified index
     * @param i - index to be deleted
     */
    public void delete(int i) {
        if (i > size || i < 0) return;
        Node n = startNode; 
        int index = 1;
        while (index < i-1) {
            n = n.getNext();
            index++; 
        }
        n.setNext(n.getNext().getNext());
        size--;
    }

    public Node get(int i) {
        Node n = startNode; 

        if (i>size) return null;

        for (int j=0; j < i; j++) {
            n = n.getNext();
        }
        return n; 
    }

    public int getSize() {
        return size; 
    }
}
