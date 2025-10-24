package practice.cracking.coding.interview.stack;

import java.util.Arrays;

public class MultiStack {
    private StackInfo[] info;
    private int values[];

    private class StackInfo {
        public int start, size, capacity;

        public StackInfo(int start, int capacity) {
            this.start = start;
            this.capacity = capacity;
        }

        public boolean isWithinStackCapacity(int index) {
            if (index < 0 || index >= values.length)
                return false;

            /* index + values.length not required as start<=index
            will not satisfy if index goes to previous stack */
            /*
            int contiguousIndex = index < start ? index + values.length : index;
            int end = start + capacity;
            return start <= contiguousIndex && contiguousIndex < end;
            */

            return start <= index && index < start + capacity;
        }

        public int lastCapacityIndex() {
            return adjustIndex(start + capacity - 1);
        }

        public int lastElementIndex() {
            return adjustIndex(start + size - 1);
        }

        public boolean isFull() {
            return size == capacity;
        }

        public boolean isEmpty() {
            return size == 0;
        }
    }

    public MultiStack(int numberOfStacks, int defaultSize) {
        info = new StackInfo[numberOfStacks];
        for (int i = 0; i < numberOfStacks; i++) {
            info[i] = new StackInfo(i * defaultSize, defaultSize);
        }
        values = new int[numberOfStacks * defaultSize];
    }

    private int adjustIndex(int index) {
        int max = values.length;
        return ((index % max) + max) % max;
    }

    private int previousIndex(int index) {
        return adjustIndex(index - 1);
    }

    private int nextIndex(int index) {
        return adjustIndex(index + 1);
    }

    public boolean allStacksAreFull() {
        int size = 0;

        for (StackInfo stack : info) {
            size += stack.size;
        }

        return size == values.length;
    }

    private void expand(int stackNum) {
        StackInfo stack = info[stackNum];
        shift((stackNum + 1) % info.length);
        stack.capacity++;
    }

    private void shift(int stackNum) {
        StackInfo stack = info[stackNum];
        if (stack.isFull())//stack.size>=stack.capacity (CCI)
        {
            shift((stackNum + 1) % info.length);
            stack.capacity++;
        }

        /* Shift all elements in stack over by one. */
        int index = stack.lastElementIndex() + 1;//stack.lastCapacityIndex() (CCI)
        while (stack.isWithinStackCapacity(index)) {
            values[index] = values[previousIndex(index)];
            index = previousIndex(index);
        }

        /* Adjust stack data. */
        values[stack.start] = 0; // Clear item
        stack.start = nextIndex(stack.start); // move start
        stack.capacity--; // Shrink capacity
    }

    public void push(int stackNum, int value) throws FullStackException {
        if (allStacksAreFull())
            throw new FullStackException("All Stacks Full !! Please pop the stack if you want to push");

        StackInfo stack = info[stackNum];

        if (stack.isFull())
            expand(stackNum);

        stack.size++;
        values[stack.lastElementIndex()] = value;//either you keep adjustIndex here or in StackInfo to handle if current stack is full and trying get the space(index) from next stack
    }

    public int pop(int stackNum) throws EmptyStackException {
        StackInfo stack = info[stackNum];

        if (stack.isEmpty())
            throw new EmptyStackException("Stack is Empty !! Please push the value to the stack if you want to pop");

        int value = values[stack.lastElementIndex()];
        values[stack.lastElementIndex()] = 0;
        stack.size--;
        return value;
    }

    public int peek(int stackNum) throws EmptyStackException {
        StackInfo stack = info[stackNum];

        if (stack.isEmpty())
            throw new EmptyStackException("Stack is Empty !! Please push the value to the stack if you want to pop");

        return values[stack.lastElementIndex()];
    }

    public int[] getValues() {
        return values;
    }

    public int[] getStackValues(int stackNum) {
        StackInfo stack = info[stackNum];
        int[] items = new int[stack.size];
        for (int i = 0; i < items.length; i++) {
            items[i] = values[adjustIndex(stack.start + i)];
        }
        return items;
    }

    public static void printStacks(MultiStack stacks) {
        System.out.println(Arrays.toString(stacks.getValues()));
    }

    public static void main(String[] args) throws Exception {
        MultiStack stacks = new MultiStack(3, 4);
        printStacks(stacks);
        stacks.push(0, 10);
        printStacks(stacks);
        stacks.push(1, 20);
        printStacks(stacks);
        stacks.push(2, 30);
        printStacks(stacks);

        stacks.push(1, 21);
        printStacks(stacks);
        stacks.push(0, 11);
        printStacks(stacks);
        stacks.push(0, 12);
        printStacks(stacks);

        stacks.pop(0);
        printStacks(stacks);

        stacks.push(2, 31);
        printStacks(stacks);

        stacks.push(0, 13);
        printStacks(stacks);
        stacks.push(1, 22);
        printStacks(stacks);

        stacks.push(2, 31);
        printStacks(stacks);
        stacks.push(2, 32);
        printStacks(stacks);
        stacks.push(2, 33);
        printStacks(stacks);
        stacks.push(2, 34);
        printStacks(stacks);

        stacks.pop(1);
        printStacks(stacks);
        stacks.push(2, 35);
        printStacks(stacks);

        System.out.println("Final Stack: " + Arrays.toString(stacks.getValues()));
    }

}
