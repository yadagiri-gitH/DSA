package com.cracking.coding.interview.stack;
//if its 0th based index ,we don't need to include -1 to refer the index position in below code snippets
public class FixedMultiStack {
    public int numberOfStacks;
    public int[] sizes;
    public int[] values;
    public int stackSize;

    public FixedMultiStack() {
        this.numberOfStacks = 3;
        this.stackSize = 10;
        this.sizes = new int[numberOfStacks];
        this.values = new int[numberOfStacks * stackSize];
    }

    public FixedMultiStack(int numberOfStacks, int stackSize) {
        this.numberOfStacks = numberOfStacks;
        this.stackSize = stackSize;
        this.sizes = new int[numberOfStacks];
        this.values = new int[numberOfStacks * stackSize];
    }

    public void push(int stackNum, int data) throws IllegalAccessException {
        if (isFullStack(stackNum - 1)) {
            throw new IllegalAccessException("Stack is Full !! Please clear and fill.");
        }
        values[(stackNum - 1) * stackSize + sizes[stackNum - 1]] = data;
        sizes[stackNum - 1]++;
    }

    public int pop(int stackNum) throws IllegalAccessException {
        if (isEmptyStack(stackNum - 1)) {
            throw new IllegalAccessException("Stack is Empty !! Please Add and clear.");
        }
        int data = values[(stackNum - 1) * stackSize + sizes[stackNum - 1] - 1];
        values[(stackNum - 1) * stackSize + (sizes[stackNum - 1] - 1)] = 0;
        sizes[stackNum - 1]--;
        return data;
    }

    public int peek(int stackNum) throws IllegalAccessException {
        if (isEmptyStack(stackNum - 1)) {
            throw new IllegalAccessException("Stack is Empty !! Please Add and clear .");
        }
        return values[(stackNum - 1) * stackSize + (sizes[stackNum - 1] - 1)];
    }

    private boolean isFullStack(int stackNum) {
        return stackSize <= sizes[stackNum];
    }

    private boolean isEmptyStack(int stackNum) {
        return sizes[stackNum] == 0;
    }

    public static void printStacks(FixedMultiStack stacks) {
        System.out.println(arrayToString(stacks.getValues()));
    }

    public static String arrayToString(int[] array) {
        if (array == null) return "";
        return arrayToString(array, 0, array.length - 1);
    }

    public static String arrayToString(int[] array, int start, int end) {
        StringBuilder sb = new StringBuilder();
        for (int i = start; i <= end; i++) {
            int v = array[i];
            sb.append(v + ", ");
        }
        return sb.toString();
    }

    public int[] getStackValues(int stackNum) {
        int[] items = new int[sizes[stackNum - 1]];
        for (int i = 0; i < items.length; i++) {
            items[i] = values[(stackNum - 1) * stackSize + i];
        }
        return items;
    }

    public int[] getValues() {
        return values;
    }

    public static void main(String[] args) throws Exception {
        FixedMultiStack stacks = new FixedMultiStack(3, 4);
        printStacks(stacks);
        //stacks.pop(1);
        stacks.push(1, 10);//followed 1st based index so code snippets are around index-1
        printStacks(stacks);
        stacks.push(2, 20);
        printStacks(stacks);
        stacks.push(3, 30);
        printStacks(stacks);

        stacks.push(2, 21);
        printStacks(stacks);
        stacks.push(1, 11);
        printStacks(stacks);
        stacks.push(1, 12);
        printStacks(stacks);

        stacks.pop(1);
        printStacks(stacks);

        stacks.push(3, 31);
        printStacks(stacks);

        stacks.push(1, 13);
        printStacks(stacks);
        stacks.push(2, 22);
        printStacks(stacks);

        stacks.push(3, 31);
        printStacks(stacks);
        stacks.push(3, 32);
        printStacks(stacks);

        stacks.push(1, 14);
        printStacks(stacks);

        //stacks.push(1, 15);
        //printStacks(stacks);

        System.out.println(stacks.peek(1));
        System.out.println(stacks.peek(2));
        System.out.println(stacks.peek(3));
    }

}



