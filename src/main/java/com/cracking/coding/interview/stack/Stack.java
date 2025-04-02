package com.cracking.coding.interview.stack;

public class Stack<T> {
    public Node<T> top;
    public int size;

    private class Node<T> {
        public T value;
        public Node<T> next;

        public Node(T value) {
            this.value = value;
            this.next = null;
        }
    }

    public void push(T value) {

        Node node = new Node(value);
        node.next = top;
        top = node;
        this.size++;

       /* if (top == null) {
            top = new Node(value);
        } else {
            Node node = new Node(value);
            top.next = node;
        }*/
    }

    public T pop() throws EmptyStackException {
        if (top == null) {
            throw new EmptyStackException("Stack is Empty !!");
        }

        T value = top.value;
        top = top.next;
        this.size--;
        return value;
    }

    public T peek() throws EmptyStackException {
        if (top == null) {
            throw new EmptyStackException("Stack is Empty !!");
        }
        return top.value;
    }

    public boolean isEmpty() {
        return top == null;
    }

    public int getSize() {
        return size;
    }

}
