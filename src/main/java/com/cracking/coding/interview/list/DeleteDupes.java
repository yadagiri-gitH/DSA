package com.cracking.coding.interview.list;

import java.util.HashSet;
import java.util.Set;

import static com.cracking.coding.interview.list.BasicLinkedList.Node;

public class DeleteDupes<T> {

    public static void deleteDupes(Node head) {
        Set<Integer> set = new HashSet<>();
        Node previous = null;
        while (head != null) {
            if (set.contains(head.data)) {
                previous.next = head.next;
            } else {
                previous = head;
                set.add((Integer) head.data);
            }
            head = head.next;
        }
    }

    public static void deleteDupesWithBF(Node head) {
        Node current = head;
        while (current != null) {
            Node runner = current;
            while (runner.next != null) {
                if (current.data == runner.next.data) {
                    runner.next = runner.next.next;
                }
                runner = runner.next;
            }
            current = current.next;
        }
    }

    public static void main(String[] args) {
        BasicLinkedList<Integer> list = new BasicLinkedList<>();
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(3);
        list.add(5);
        list.add(7);
        list.add(2);
        deleteDupes(list.getHead());
        System.out.println(list.print(list.getHead()));
        deleteDupesWithBF(list.getHead());
        System.out.println(list.print(list.getHead()));
    }

}
