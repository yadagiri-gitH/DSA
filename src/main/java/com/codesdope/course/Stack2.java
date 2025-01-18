package com.codesdope.course;

public class Stack2 {
    public Node head;
    public Node top;
    public int size;

    public int size() {
        return this.size;
    }

    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public void push(int data) {
        this.size++;
        Node n = new Node(data);
        if (this.top == null) {
            this.head = n;
            this.top = n;
        } else {
            this.top.next = n;
            this.top = n;
        }
    }

    /*public int pop() {
        if (isEmpty()) {
            System.out.println("Stack underflow. No elements to pop.");
            return -1;
        }
        int value = top.data;
        top = top.next; // Move the top to the next node
        return value;
    }*/

    public int pop() {//above pop looks valid as it doesn't traverse entire stack
        if (this.top == null) {
            System.out.println("Stack Underflow");
            return -1000;
        } else {
            this.size--;
            int data = this.top.data;
            if (this.head == this.top) {
                this.head = null;
                this.top = null;
            } else {
                Node temp = head;
                while (temp.next != top) {
                    temp = temp.next;
                }
                temp.next = null;
                this.top = temp;
            }
            return data;
        }
    }

    public int top() {//peek()
        if (top == null) {
            return -1000;
        } else {
            return this.top.data;
        }
    }

    public boolean isEmpty() {
        return top == null;
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
        Stack2 s = new Stack2();

        s.pop();
        s.push(10);
        s.push(20);
        s.push(30);

        s.print();
        s.pop();
        s.print();
    }

}
