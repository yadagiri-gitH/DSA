package com.cracking.coding.interview.list;

public class Intersection {

    public static class Node<T> {
        T data;
        Node next;

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

    }

    private static Node intersect(Node node1, Node node2) {
        int length1 = length(node1);
        int length2 = length(node2);

        if (length1 < length2)
            node1 = padNode(node1, length2 - length1);
        else
            node2 = padNode(node2, length1 - length2);

        boolean isIntersected = isInterSected(node1, node2);

        if (isIntersected)
            return intersectNode(node1, node2);
        return null;
    }

    private static Node intersectNode(Node node1, Node node2) {
        while (node1 != null && node1.next != null && node2 != null && node2.next != null) {
            if (node1 == node2) {
                break;
            }
            node1 = node1.next;
            node2 = node2.next;
        }

        if (isAllNodesEqual(node1, node2))
            return node1;

        return null;
    }

    private static boolean isAllNodesEqual(Node node1, Node node2) {
        while (node1 != null && node2 != null) {
            if (node1.data != node2.data) {
                return false;
            }
            node1 = node1.next;
            node2 = node2.next;
        }
        return true;
    }

    private static boolean isInterSected(Node node1, Node node2) {
        while (node1 != null && node1.next != null) {
            node1 = node1.next;
        }

        while (node2 != null && node2.next != null) {
            node2 = node2.next;
        }

        return node1 == node2;
    }

    private static Node padNode(Node node, int offset) {
        if (offset == 0) {
            return node;
        } else {
            for (int i = 0; i < offset; i++) {
                Node padding = new Node("\0");
                padding.next = node;
                node = padding;
            }
            return node;
        }
    }

    private static int length(Node node) {
        int size = 0;
        while (node != null) {
            size++;
            node = node.next;
        }
        return size;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        //1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        Node node5 = new Node(5);
        Node node6 = new Node(6);
        Node node7 = new Node(7);
        Node node8 = new Node(8);

        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
        node6.next = node7;
        node7.next = node8;

        //1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
        Node node11 = new Node(11);
        Node node21 = new Node(21);

        //11 -> 21 -> -> 5 -> 6 -> 7 -> 8
        node11.next = node21;
        node21.next = node5;

        Node intersect = intersect(node1, node11);

        System.out.print("InterSect Node of ");
        printList(node1);
        System.out.print("& ");
        printList(node11);
        System.out.print("is - ");
        System.out.print(intersect != null ? intersect.data : null);
    }

}
