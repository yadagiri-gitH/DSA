package com.cracking.coding.interview.stack;

import java.util.ArrayList;

class Node {
    public int value;
    public Node below;
    public Node above;

    public Node(int value) {
        this.value = value;
    }
}

class Stack1 {
    public int capacity;
    public int size;
    public Node top;
    public Node bottom;

    public Stack1(int capacity) {
        this.capacity = capacity;
    }

    public void push(int value) {
        Node node = new Node(value);
        this.size++;
        join(node, top);
        if (size == 1) this.bottom = node;
        top = node;
    }

    public int pop() throws EmptyStackException {
        if (top == null) throw new EmptyStackException("Stack is Empty !!");
        int val = top.value;
        top = top.below;
        this.size--;
        return val;
    }

    public int removeBottom() throws EmptyStackException {
        if (bottom == null) throw new EmptyStackException("Stack is Empty !!");
        int val = bottom.value;
        bottom = bottom.above;
        if (bottom != null) {
            bottom.below = null;
        }
        return val;
    }

    private void join(Node above, Node below) {
        if (above != null) above.below = below;
        if (below != null) below.above = above;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public boolean isEmpty() {
        return size == 0;
    }

}

public class SetofStacks {
    public int capacity;
    public ArrayList<Stack1> stacks = new ArrayList<>();

    public SetofStacks(int capacity) {
        this.capacity = capacity;
    }

    public Stack1 getLastStack() {
        if (stacks.size() == 0) {
            return null;
        }
        return stacks.get(stacks.size() - 1);
    }

    public void push(int value) {
        Stack1 stack = getLastStack();
        if (stack != null && !stack.isFull()) {
            stack.push(value);
        } else {
            Stack1 st = new Stack1(capacity);
            st.push(value);
            stacks.add(st);
        }
    }

    public int pop() throws EmptyStackException {
        Stack1 stack = getLastStack();
        if (stack == null || stack.isEmpty()) {
            throw new EmptyStackException("Stack is Empty !!");
        }
        int value = stack.pop();
        if (stack.size == 0) {
            stacks.remove(stacks.size() - 1);//stacks.remove(stack);
        }
        return value;
    }

    public int pop(int index) throws EmptyStackException {

        if (index >= stacks.size() || index < 0) {
            throw new EmptyStackException("Index is Invalid");
        }
        return leftShift(index, true);
    }

    private int leftShift(int index, boolean removeTop) throws EmptyStackException {
        Stack1 stack = stacks.get(index);
        if (stack == null || stack.isEmpty())
            throw new EmptyStackException("Either Stack is not Set or Stack is Empty !!");
        int value;
        if (removeTop) {
            value = stack.pop();
        } else {
            value = stack.removeBottom();
        }

        if (index + 1 < stacks.size()) {
            int data = leftShift(index + 1, false);
            stack.push(data);
        }

        if (stack.isEmpty()) {
            stacks.remove(index);
        }

        return value;
    }

    public static void main(String[] args) throws EmptyStackException {
        int capacity_per_substack = 5;
        SetofStacks stacks = new SetofStacks(capacity_per_substack);
        for (int i = 0; i < 34; i++) {
            stacks.push(i);
        }

        /*for (int i = 0; i < 34; i++) {
            System.out.println("Popped " + stacks.pop());
        }*/

        System.out.println("Popped " + stacks.pop(5));
    }

}
