package LinkedList;

public class TestADP {
    public static void main(String[] args) {
        LList list = new LList();
        list.insert(new Node(5)); // Insert the number 5 
        list.insert(new Node(7)); // Insert the number 7
        list.insert(new Node(12)); // Insert the number 12
        list.insert(new Node(6)); // Insert the number 6

        list.delete(3);
    }
}
