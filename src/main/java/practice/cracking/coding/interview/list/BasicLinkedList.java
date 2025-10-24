package practice.cracking.coding.interview.list;

public class BasicLinkedList<T> {

    private Node<T> first;
    private Node<T> last;
    private int nodeCount;

    public BasicLinkedList() {
        this.first = null;
        this.last = null;
        this.nodeCount = 0;
    }

    public static class Node<T> {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    public void add(T data) {
        Node item = new Node(data);

        if (first == null) {
            first = item;
            //last = first;
        } else {
            last.next = item;
            //last = item;
        }

        last = item;

        nodeCount++;
    }

    public T remove() {
        if (first == null) {
            throw new IllegalStateException("The Linked List is empty and there are no items to remove");
        }

        T data = first.data;
        first = first.next;
        nodeCount--;
        return data;
    }

    public void insertAt(T data, int position) {
        if (size() < position) {
            throw new IllegalStateException("The LinkedList is smaller than the position you want to insert At");
        }

        Node current = first;

        for (int i = 1; i < position && current != null; i++) {
            current = current.next;
        }

        Node newData = new Node(data);
        Node nextData = current.next;
        current.next = newData;
        newData.next = nextData;
        nodeCount++;
    }

    public T removeAt(int position) {

        if (first == null) {
            throw new IllegalStateException("The Linked List is empty and there are not items to remove");
        }

        Node current = first;
        Node prev = first;

        for (int i = 1; i < position && current != null; i++) {
            prev = current;
            current = current.next;
        }

        T data = (T) current.data;

        prev.next = current.next;
        nodeCount--;
        return data;
    }

    public int size() {
        return nodeCount;
    }

    public Node getHead() {
        return first;
    }

    public String toString() {
        StringBuffer contents = new StringBuffer();

        Node current = first;
        while (current != null) {
            contents.append(current.data);
            current = current.next;
            if (current != null) {
                contents.append(",");
            }
        }
        return contents.toString();
    }

    public String print(Node node) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (node != null) {
            sb.append(" ");
            sb.append(node.data);
            node = node.next;
        }
        sb.append(" ]");
        return sb.toString();
    }
}
