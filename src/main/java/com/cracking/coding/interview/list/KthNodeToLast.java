package com.cracking.coding.interview.list;

import static com.cracking.coding.interview.list.BasicLinkedList.Node;

class Index {
    public static int value;
}

public class KthNodeToLast {

    public static int counter = 0;

    public static Node kthNodeToLast(Node head, int k) {
        if (head == null)
            return null;
        Node node = kthNodeToLast(head.next, k);
        counter++;
        if (counter == k)
            return head;
        return node;
    }

    public static int printKthNodeToLast(Node head, int k) {
        if (head == null)
            return 0;
        int count = printKthNodeToLast(head.next, k) + 1;
        if (count == k) {
            System.out.println("Kth Node Data - " + head.data);
        }
        return count;
    }

    public static Node KthNodeToLastN(Node head, int k) {
        if (head == null)
            return null;
        Node node = KthNodeToLastN(head.next, k);
        Index.value++;
        if (Index.value == k)
            return head;
        return node;
    }

    //Iterative way to find Kth element
    public static Node findKthNodeToLast(Node head, int k) {
        Node node1 = head;
        Node node2 = head;

        for (int i = 0; i < k && node1 != null; i++) {
            node1 = node1.next;
        }

        while (node1 != null) {
            node1 = node1.next;
            node2 = node2.next;
        }

        return node2;
    }

    //two pointer approach
    public static Node findKthNodeToLastN(Node head, int k) {
        Node node1 = head;
        Node node2 = head;

        int count = 0;

        while (node1 != null && node1.next != null) {
            node1 = node1.next.next;
            count++;
        }

        int size = count * 2;

        if (node1 != null) {
            size += 1;
        }

        int counter = 0;

        while (node2 != null) {
            // counter++;
            if (counter++ == size - k) { //counter == size - k+1
                break;
            }
            node2 = node2.next;
        }
        return node2;
    }

    public static void main(String[] args) {
        BasicLinkedList<Integer> list = new BasicLinkedList<>();
        list.add(3);
        list.add(4);
        list.add(6);
        list.add(29);
        list.add(34);
        list.add(67);
        list.add(86);

        Node head = list.getHead();

        Node current = kthNodeToLast(head, 4);
        System.out.println("Kth Node Data - " + current.data);
        printKthNodeToLast(head, 4);
        current = KthNodeToLastN(head, 4);
        System.out.println("Kth Node Data - " + current.data);
        current = findKthNodeToLast(head, 4);
        System.out.println("Kth Node Data - " + current.data);
        current = findKthNodeToLastN(head, 4);
        System.out.println("Kth Node Data - " + current.data);
    }
}
