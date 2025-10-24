package practice.codesdope.course;

public class Stack {
    public int top;
    public int size;
    public int[] array;

    public Stack(int size) {
        this.size = size;
        this.array = new int[size + 1];
    }

    public boolean isStackFull() {
        return top > size;
    }

    public void push(int data) {
        top = top + 1;//-->1
        if (isStackFull()) {
            System.out.println("Stack Overflow");
        } else {
            array[top] = data;
            //array[++top] = data; --in case if 1 is commented
        }
    }

    public boolean isStackEmpty() {
        return top == 0;
    }

   /* public int pop() {
        if (isEmpty()) {
            System.out.println("Stack Underflow");
            return -1000;
        } else {
            int data = array[top--];
            return data;
        //top=top--;
        //return array[top+1];
        }
    } */

    public int pop() {
        if (isStackEmpty()) {
            System.out.println("Stack Underflow");
            return -1000;
        } else {
            top = top - 1;
            return array[top + 1];
        }
    }

    public void print() {
        System.out.println("Print Stack :");
       /* for (int i : array) {
            System.out.println(i);
        }*/

        for (int i = 1; i <= top; i++) {//not the size ,it is top
            System.out.println(array[i]);
        }
    }

    public static void main(String[] args) {
        Stack stack = new Stack(10);
        System.out.println("POP : " + stack.pop());
        stack.push(10);
        System.out.println("POP : " + stack.pop());
        stack.push(20);
        stack.push(30);
        stack.push(40);
        stack.push(50);
        stack.push(60);
        stack.push(70);
        stack.push(80);
        stack.push(90);
        stack.push(100);
        stack.push(110);
        stack.print();
        System.out.println("POP : " + stack.pop());
        stack.print();
    }
}
