package com.cracking.coding.interview.list;

import java.util.HashSet;

public class RemoveDups<E> {
    int size = 0;
    private Node<E> head;

    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E element) {
            this.item = element;
            this.next = null;
        }
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

    public String print(Node node) {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (node != null) {
            sb.append(" ");
            sb.append(node.item);
            node = node.next;
        }
        sb.append(" ]");
        return sb.toString();
    }

    public boolean removeDups(Node head) {
        Node current = head;
        Node previous = null;
        HashSet<E> set = new HashSet<>();
        while (current != null) {
            if (set.contains(current.item)) {
                previous.next = current.next;
            } else {
                set.add((E) current.item);
                previous = current;
            }
            current = current.next;
        }
        return true;
    }

    public boolean removeDupsWithoutBuffer(Node head) {
        Node current = head;
        while (current != null) {
            Node runner = current;
            while (runner.next != null) {
                if (current.item == runner.next.item) {
                    runner.next = runner.next.next;
                } else {
                    runner = runner.next;
                }
            }

            current = current.next;
        }
        return true;
    }

    public static void main(String[] args) {

        RemoveDups<Integer> list = new RemoveDups<>();
        list.add(2);
        list.add(1);
        list.add(2);
        list.add(5);
        list.add(1);
        list.add(2);

        /*System.out.println(list.print(list.head));
        System.out.println("Duplicates Removed - " + list.removeDups(list.head));
        System.out.println(list.print(list.head));*/

        System.out.println(list.print(list.head));
        System.out.println("Duplicates Removed - " + list.removeDupsWithoutBuffer(list.head));
        System.out.println(list.print(list.head));

    }
}

