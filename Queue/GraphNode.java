package Queue;

public class GraphNode<T> {
    // Stores the pointers to other nodes in a graph 
    LList pointers = new LList();
    T value;

    /**
     * Constructor to create a new GraphNode
     * @param value - value that this Node is to hold 
     */
    public GraphNode(T value) {
        this.value = value; 
    }

    /**
     * Adds a Node within the pointers linkedlist 
     * @param pointer - the pointer to another Node from this Node
     */
    public void addPointer(Node pointer) {
        pointers.insert(pointer);
    }

    /**
     * Get the value that is stored inside of a Node
     * @return - return a Node that stores the value
     */
    public T getValue() {
        return this.value;
    }

    /**
     * Gets a pointer from this Node to another 
     * @param index - index of the pointer 
     * @return - the pointer and "index" in the linkedlist of pointers 
     */
    public Node getPointer(int index) {
        if (index < 0 || index > pointers.getSize()) {
            return null;
        }
        return pointers.get(index);
    }
}
