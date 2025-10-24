package practice.cracking.coding.interview.list;

import static practice.cracking.coding.interview.list.BasicLinkedList.Node;

public class Partition {
    public static Node partitionInOrder(Node node, int x) {
        Node beforeStart = null;
        Node beforeEnd = null;
        Node afterStart = null;
        Node afterEnd = null;

        while (node != null) {
            Node next = node.next;
            node.next = null;
            if ((int) node.data < x) {
                if (beforeStart == null) {
                    beforeStart = node;
                    beforeEnd = beforeStart;
                } else {
                    beforeEnd.next = node;
                    beforeEnd = beforeEnd.next;//beforeEnd=node;
                }

            } else {
                if (afterStart == null) {
                    afterStart = node;
                    afterEnd = afterStart;
                } else {
                    afterEnd.next = node;
                    afterEnd = afterEnd.next;//afterEnd=node;
                }
            }
            node = next;
        }

        if (beforeStart == null) {
            return afterStart;
        }

        beforeEnd.next = afterStart;

        return beforeStart;
    }

    public static Node partition(Node node, int x) {
        Node head = node;
        Node tail = node;

        while (node != null) {
            Node next = node.next;
            if ((int) node.data < x) {
                node.next = head;
                head = node;
            } else {
                tail.next = node;
                tail = node;
            }
            node = next;
        }
        tail.next = null;
        return head;
    }

    public static void main(String[] args) {
        BasicLinkedList<Integer> list = new BasicLinkedList<>();
        list.add(3);
        list.add(5);
        list.add(8);
        list.add(5);
        list.add(10);
        list.add(2);
        list.add(1);
        Node head = list.getHead();
        Node node = partitionInOrder(head, 5);
        System.out.println(list.print(node));//head
        node = partition(head, 5);
        System.out.println(list.print(node));//head
    }

}

