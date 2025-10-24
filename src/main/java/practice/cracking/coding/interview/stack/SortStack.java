package practice.cracking.coding.interview.stack;

public class SortStack extends Stack {

    public static void sort(Stack<Integer> stack) throws Exception {
        if (stack.isEmpty())
            throw new EmptyStackException("Stack Empty !!");

        Stack<Integer> tempStack = new Stack();

        while (!stack.isEmpty()) {
            int data = stack.pop();
            while (!tempStack.isEmpty() && tempStack.peek() > data) {
                stack.push(tempStack.pop());
            }
            tempStack.push(data);
        }

        while (!tempStack.isEmpty()) {
            stack.push(tempStack.pop());
        }
    }

    public static void main(String[] args) throws Exception {
        Stack<Integer> stack = new Stack<>();
        stack.push(3);
        stack.push(1);
        stack.push(4);
        stack.push(2);
        stack.push(5);

        System.out.println("Before sorting: " + stack);
        SortStack.sort(stack);
        System.out.println("After sorting: " + stack); // [1, 2, 3, 4, 5]
    }
}
