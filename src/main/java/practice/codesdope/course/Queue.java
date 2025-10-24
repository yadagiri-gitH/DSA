package practice.codesdope.course;

public class Queue {
    public int size;
    public int[] array;
    public int first;
    public int last;

    public Queue(int size) {
        this.size = size;
        array = new int[size+1];
    }

    public int size() {
        return this.size;
    }

    public boolean isQueueFull() {
        return last+1 >= size;
    }

    public void enqueue(int data) {
        if (isQueueFull()) {
            System.out.println("Queue Full while inserting "+data);
        } else if (this.first == 0 && this.last == 0) {
            first = first + 1;
            last = last + 1;
            array[first] = data;
            array[last] = data;
        } else {
            last = last + 1;
            array[last] = data;
        }
    }

    public int dequeue() {
        if (this.first == 0 && this.last == 0) {//first=0 & last=0
            return -1000;
        } else if (this.first == this.last) {//only one element
            this.last = 0;
            this.first = 0;
            return this.first;
        } else {
            int data = array[first];
            first = first + 1;
            return data;
        }
    }

    public void print() {
        for (int i = first; i <= last; i++) {
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        Queue q = new Queue(10);
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
        q.print();

        System.out.println("Dequeue");

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

        System.out.println("Print All");

        q.print();

        System.out.println("");

        q.enqueue(110);
        q.enqueue(120);

        System.out.println("");

        System.out.println("Print All");
        q.print();
    }
}
