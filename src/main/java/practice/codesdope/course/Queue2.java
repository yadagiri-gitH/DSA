package practice.codesdope.course;

public class Queue2 {

    public Node head;
    public Node tail;
    public int size;

    public int size() {
        return this.size;
    }

    public void traversal() {
        Node temp = this.head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public boolean isEmpty() {
        return this.head == null;
    }

    public void enqueue(int data) {
        this.size++;
        Node n = new Node(data);
        if (this.head == null) {
            this.head = n;
            this.tail = n;
        } else {
            this.tail.next = n;
            this.tail = n;
        }
    }

    public int dequeue() {
        if (this.head == null) {
            System.out.println("Queue Underflow");
            return -1000;
        } else {
            this.size--;
            int data = this.head.data;
            this.head = this.head.next;
            return data;
        }
    }


    class Node {
        public int data;
        public Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        Queue2 q = new Queue2();
        System.out.println("Enqueue");
        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);
        q.enqueue(60);
        q.enqueue(70);
        q.enqueue(80);
        q.enqueue(90);
        q.enqueue(100);
        System.out.println("Print All");
        q.traversal();
        System.out.println("Current Queue Size :"+q.size());

        System.out.println("Dequeue");

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

        System.out.println("Print All");

        q.traversal();

        System.out.println("Current Queue Size :"+q.size());

        System.out.println("");

        q.enqueue(110);
        q.enqueue(120);
        q.enqueue(130);

        System.out.println("");

        System.out.println("Print All");
        q.traversal();
        System.out.println("Current Queue Size :"+q.size());
    }
}
