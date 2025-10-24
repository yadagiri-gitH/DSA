package practice.cracking.coding.interview.stack;

public class StackMin extends Stack<Integer> {
    private Stack<Integer> stack;

    public StackMin() {
        this.stack = new Stack<>();
    }

    public void push(int value) throws Exception {
        super.push(value);
        if (value <= min()) {    //<= (CCI),in case if duplicate min elements are added
            stack.push(value);
        }
    }

    public Integer pop() throws EmptyStackException {
        int data = super.pop();
        if (data == min()) {
            stack.pop();
        }
        return data;
    }

    public Integer min() throws EmptyStackException {
        if (stack.isEmpty()) {
            return Integer.MAX_VALUE;
        } else {
            return stack.peek();
        }
    }

    public static void main(String[] args) throws Exception {
        StackMin stack = new StackMin();
        int[] values = {4, -89, 9, 2, 4, 3, 0, -671};

        for (int value : values) {
            stack.push(value);
        }

        System.out.println("Current Min element = " + stack.min());
    }
}
