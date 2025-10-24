package practice.cracking.coding.interview.stack;

public class MyQueue<T> {
    private Stack<T> inStack;
    private Stack<T> outStack;

    public MyQueue() {
        this.inStack = new Stack<>();
        this.outStack = new Stack<>();
    }

    public void enqueue(T data) {
        inStack.push(data);
    }

    public T dequeue() throws Exception {

        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }

        if (outStack.isEmpty())
            throw new EmptyQueueException("Queue Empty !!");

        return outStack.pop();
    }

    public T peek() throws Exception {
        if (outStack.isEmpty()) {
            while (!inStack.isEmpty()) {
                outStack.push(inStack.pop());
            }
        }

        if (outStack.isEmpty())
            throw new EmptyQueueException("Queue Empty !!");

        return outStack.peek();
    }

    public int size() {
        return inStack.size + outStack.size;
    }

    public boolean isEmpty() {
        return inStack.isEmpty() && outStack.isEmpty();
    }


    public static void main(String[] args) {
        MyQueue<Integer> queue = new MyQueue<>();

        System.out.println("Enqueuing elements: 10, 20, 30");
        queue.enqueue(10);
        queue.enqueue(20);
        queue.enqueue(30);

        try {
            System.out.println("Peek: " + queue.peek()); // Expected: 10
            System.out.println("Dequeue: " + queue.dequeue()); // Expected: 10
            System.out.println("Dequeue: " + queue.dequeue()); // Expected: 20
            queue.enqueue(40);
            System.out.println("Peek: " + queue.peek()); // Expected: 30
            System.out.println("Dequeue: " + queue.dequeue()); // Expected: 30
            System.out.println("Dequeue: " + queue.dequeue()); // Expected: 40

            // This will throw an exception
            System.out.println("Dequeue: " + queue.dequeue());
        } catch (Exception e) {
            System.out.println("Caught Exception: " + e.getMessage());
        }
    }

}
