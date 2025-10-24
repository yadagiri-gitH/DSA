package practice.cracking.coding.interview.list;

public class DoublyLinkedList<E> {
    // Node class representing each element in the list
    private static class Node<E> {
        E data;
        Node<E> next;
        Node<E> prev;

        Node(E data) {
            this.data = data;
            this.next = null;
            this.prev = null;
        }
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoublyLinkedList() {
        this.head = null;
        this.tail = null;
        this.size = 0;
    }

    // Add element at the end
    public void add(E data) {
        Node<E> newNode = new Node<>(data);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
        size++;
    }

    // Add element at a specific position
    public void add(int index, E data) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node<E> newNode = new Node<>(data);
        if (index == 0) {
            newNode.next = head;
            if (head != null) {
                head.prev = newNode;
            }
            head = newNode;
            if (tail == null) {
                tail = head;
            }
        } else if (index == size) {
            add(data);
            return;
        } else {
            Node<E> temp = getNode(index);
            newNode.prev = temp.prev;
            newNode.next = temp;
            temp.prev.next = newNode;
            temp.prev = newNode;
        }
        size++;
    }

    // Remove element from a specific position
    public E remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Invalid index");
        }

        Node<E> temp;
        if (index == 0) {
            temp = head;
            head = head.next;
            if (head != null) {
                head.prev = null;
            } else {
                tail = null;
            }
        } else if (index == size - 1) {
            temp = tail;
            tail = tail.prev;
            tail.next = null;
        } else {
            temp = getNode(index);
            temp.prev.next = temp.next;
            temp.next.prev = temp.prev;
        }

        size--;
        return temp.data;
    }

    // Get node at a specific position
    private Node<E> getNode(int index) {
        Node<E> temp;
        if (index < size / 2) {
            temp = head;
            for (int i = 0; i < index; i++) {
                temp = temp.next;
            }
        } else {
            temp = tail;
            for (int i = size - 1; i > index; i--) {
                temp = temp.prev;
            }
        }
        return temp;
    }

    // Add all elements from another DoublyLinkedList
    public void addAll(DoublyLinkedList<E> otherList) {
        if (otherList == null || otherList.size == 0) return;

        if (this.head == null) {
            // If the current list is empty, just copy head and tail
            this.head = otherList.head;
            this.tail = otherList.tail;
        } else {
            // Connect the last node of this list to the first node of the other list
            this.tail.next = otherList.head;
            otherList.head.prev = this.tail;
            this.tail = otherList.tail;
        }
        size += otherList.size;
    }

    // Display elements forward
    public void displayForward() {
        Node<E> temp = head;
        while (temp != null) {
            System.out.print(temp.data + " ⇄ ");
            temp = temp.next;
        }
        System.out.println("null");
    }

    // Display elements backward
    public void displayBackward() {
        Node<E> temp = tail;
        while (temp != null) {
            System.out.print(temp.data + " ⇄ ");
            temp = temp.prev;
        }
        System.out.println("null");
    }

    // Get size of the list
    public int size() {
        return size;
    }

    public static void main(String[] args) {
        DoublyLinkedList<Integer> list1 = new DoublyLinkedList<>();
        list1.add(1);
        list1.add(2);
        list1.add(3);

        DoublyLinkedList<Integer> list2 = new DoublyLinkedList<>();
        list2.add(4);
        list2.add(5);
        list2.add(6);

        System.out.println("List 1 before addAll:");
        list1.displayForward();

        System.out.println("List 2:");
        list2.displayForward();

        list1.addAll(list2);

        System.out.println("List 1 after addAll:");
        list1.displayForward();

        System.out.println("Reverse List 1:");
        list1.displayBackward();
    }
}

