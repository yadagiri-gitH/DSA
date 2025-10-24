package practice.cracking.coding.interview.stack;

class MinStack {
    public int value;
    public int min;

    MinStack(int value, int min) {
        this.value = value;
        this.min = min;
    }
}

public class StackMinimum extends Stack {

    public void push(int value) throws Exception {
        int newMin = Math.min(value, min());
        MinStack minStack = new MinStack(value, newMin);
        super.push(minStack);
    }

    public int min() throws Exception {
        if (this.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            MinStack minStack = (MinStack) this.peek();
            return minStack.min;
        }
    }

    public static void main(String[] args) throws Exception {
        StackMinimum stack = new StackMinimum();
        int[] values = {4, 8, 9, 9, 4, 3, 1, 6};

        for (int value : values) {
            stack.push(value);
        }

        MinStack current = (MinStack) stack.peek();

        System.out.println("Current Min element = " + current.min);
    }
}

