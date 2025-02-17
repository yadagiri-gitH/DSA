package com.cracking.coding.interview.list;

import static com.cracking.coding.interview.list.BasicLinkedList.Node;

public class MiddleNode {

    public static Node findMiddleNode(Node head) {
        if (head == null) {
            return null; // Empty list
        }

        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow; // slow is now at the middle
    }

    public static void deleteMiddleNode(Node head) {
        Node slow = head;
        Node fast = head;
        Node prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        if (prev != null) {
            prev.next = slow.next;
        }

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

        deleteMiddleNode(head);

        System.out.println("Node Chain Elements Data : " + list.print(head));

    }
}
