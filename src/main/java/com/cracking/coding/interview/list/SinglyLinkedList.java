package com.cracking.coding.interview.list;

public class SinglyLinkedList<E> {
    private int size = 0;
    private Node<E> head;

    public static void main(String[] args) {

        SinglyLinkedList<Integer> linkedList = new SinglyLinkedList<>();

      /*  linkedList.add(3);
        linkedList.add(5);
        linkedList.add(8);
        linkedList.add(5);
        linkedList.add(10);
        linkedList.add(2);
        linkedList.add(1);*/
        /*linkedList.add(1);
        linkedList.add(5);
        linkedList.add(3);
        linkedList.add(2);
        linkedList.add(6);
        Node orderNode = linkedList.partition(linkedList.head, 5);
        System.out.println(linkedList.print(orderNode));*/

        Node l1node1 = new Node(7);
        Node l1node2 = new Node(1);
        Node l1node3 = new Node(6);
        l1node2.next = l1node3;
        l1node1.next = l1node2;

        Node l2node1 = new Node(5);
        Node l2node2 = new Node(9);
        l2node1.next = l2node2;

        Node sumNode = linkedList.addList(l1node1, l2node1);

        System.out.println(linkedList.print(sumNode));

    }

    public boolean add(E e) {
        Node node = new Node(e);
        if (head == null)
            head = node;
        else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
        size++;
        return true;
    }

    public Node<E> partition(Node<E> node, int partitionNum) {
        Node<E> left = node;
        Node<E> right = node;

      /*  System.out.println(print(left));
        System.out.println(print(right));*/

        while (node != null) {
            Node next = node.next;
            int data = (Integer) node.item;
            if (data < partitionNum) {
                node.next = left;
                left = node;
            } else {
                right.next = node;
                right = node;
                System.out.println(print(right));
            }
            //System.out.println(print(left));
            //System.out.println(print(right));
            node = next;
        }

        right.next = null;
        return left;
    }

    public Node<E> orderByPartitionNumber(Node<E> node, int partitionNum) {
        Node<E> left = null;
        Node<E> right = null;

        while (node != null) {
            Node next = node.next;
            int data = (Integer) node.item;
            node.next = null;
            if (data < partitionNum) {
                if (left == null) {
                    left = node;
                } else {
                    left.next = node;
                    left = left.next;
                }
            } else {
                if (right == null) {
                    right = node;
                } else {
                    right.next = node;
                    right = right.next;
                }
            }
            //System.out.println(print(left));
            //System.out.println(print(right));
            node = next;
        }

        right.next = null;
        return left;
    }

    public String print(Node node) {
        StringBuilder sb = new StringBuilder();
        while (node != null) {
            sb.append(" ");
            sb.append(node.item);
            node = node.next;
        }
        return sb.toString();
    }

    //suppose if the elements data stored in forward order instead of reverse order
    public Node addList(Node list1, Node list2) {
        int length1 = length(list1);
        int length2 = length(list2);
        if (length1 < length2) {
            list1 = padListWithZero(list1, length2 - length1);
        } else {
            list2 = padListWithZero(list2, length1 - length2);
        }

        PartialSum sum = reverseSumList(list1, list2);

        if (sum.carry == 0) {
            return sum.sum;
        } else {
            Node result = insertBefore(sum.sum, sum.carry);
            return result;
        }

    }

    public PartialSum reverseSumList(Node l1, Node l2) {
        if (l1 == null && l2 == null) {
            PartialSum sum = new PartialSum();
            return sum;
        }
        PartialSum sum = reverseSumList(l1.next, l2.next);
        int val = sum.carry + (int) l1.item + (int) l2.item;
        Node result = insertBefore(sum.sum, val % 10);
        sum.sum = result;
        sum.carry = val / 10;
        return sum;
    }

    public Node padListWithZero(Node list, int count) {
        Node head = list;
        for (int i = 0; i < count; i++) {
            head = insertBefore(head, 0);
        }
        return head;
    }

    public Node insertBefore(Node list, int data) {
        Node node = new Node(data);
        if (list != null) {
            node.next = list;
        }
        return node;
    }

    public int length(Node list) {
        int count = 0;
        Node node = list;
        while (node != null) {
            count++;
            node = node.next;
        }
        return count;
    }

    private static class Node<E> {
        E item;
        Node<E> next;

        Node() {
            this.item = null;
            this.next = null;
        }

        Node(E element) {
            this.item = element;
            this.next = null;
        }
    }

    private static class PartialSum {
        public Node sum = null;
        public int carry = 0;
    }
}
