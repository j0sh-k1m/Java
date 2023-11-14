package Queue;

public class TestQueue {
    public static void main(String[] args) {
        Queue queue = new Queue();

        queue.enqueue(new Node("YOSH"));
        queue.enqueue(new Node("JOSHUA"));
        queue.enqueue(new Node("JOSH"));
        queue.enqueue(new Node("JOSHY"));

        // System.out.println(queue.last().getData());
        // System.out.println(queue.peek().getNext().getNext().getPrev().getData());
        System.out.println(queue.dequeue().getData());
        // System.out.println(queue.peek().getData());
        System.out.println(queue.size());

    }
}
