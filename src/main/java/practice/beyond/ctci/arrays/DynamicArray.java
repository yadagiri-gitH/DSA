package practice.beyond.ctci.arrays;

public class DynamicArray<T> {
    private int size;
    private T[] array;
    private static final int INITIAL_CAPACITY = 10;

    @SuppressWarnings("unchecked")
    public DynamicArray() {
        this.array = (T[]) new Object[INITIAL_CAPACITY];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    public DynamicArray(int initialCapacity) {
        if (initialCapacity <= 0) {
            throw new IllegalArgumentException("Initial capacity must be positive.");
        }
        this.array = (T[]) new Object[initialCapacity];
        this.size = 0;
    }

    public int size() {
        return this.size;
    }

    @SuppressWarnings("unchecked")
    public void append(T value) {
        if (this.size == array.length) {
            T[] newArray = (T[]) new Object[array.length * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i++] = array[i];
            }
            array = newArray;
        }

        array[size++] = value;
    }

    public void set(int index, T value) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }

        array[index] = value;
    }

    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }

        return array[index];
    }

    public T pop_back() {
        if (size == 0) {
            throw new IllegalStateException("Cannot pop from an empty dynamic array.");
        }
        T poppedElement = array[--size];
        array[size] = null;
        return poppedElement;
    }

    public static void main(String[] args) {
        // Example 1
        System.out.println("--- Example 1 ---");
        DynamicArray<Integer> d1 = new DynamicArray<>();
        d1.append(1);
        d1.append(2);
        System.out.println("Element at index 0: " + d1.get(0)); // returns 1
        System.out.println("Element at index 1: " + d1.get(1)); // returns 2
        System.out.println("Size: " + d1.size()); // returns 2

        // Example 2
        System.out.println("\n--- Example 2 ---");
        DynamicArray<Integer> d2 = new DynamicArray<>();
        d2.append(1);
        d2.set(0, 10);
        System.out.println("Element at index 0: " + d2.get(0)); // returns 10

        // Example 3
        System.out.println("\n--- Example 3 ---");
        DynamicArray<Integer> d3 = new DynamicArray<>();
        d3.append(1);
        d3.append(2);
        d3.pop_back();
        System.out.println("Size: " + d3.size()); // returns 1
        System.out.println("Element at index 0: " + d3.get(0)); // returns 1
    }
}
