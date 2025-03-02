package com.cracking.coding.interview.list;

import static com.cracking.coding.interview.list.BasicLinkedList.Node;


public class SumLists {
    //reverse numbers sum and result in reverse,but below result node not returning the head element
    public static Node addLists(Node nums1, Node nums2, Node sumLists, int carry) {

        if (nums1 == null && nums2 == null && carry == 0) {
            sumLists.next = null;
            return sumLists;
        }

        int data = (nums1 != null ? (int) nums1.data : 0) + (nums2 != null ? (int) nums2.data : 0) + carry;

        if (sumLists != null) {
            sumLists.next = new Node<>(data % 10);
            sumLists = sumLists.next;
        } else {
            sumLists = new Node(data % 10);
        }

        Node node = addLists(nums1 != null ? nums1.next : null, nums2 != null ? nums2.next : null, sumLists, data > 10 ? 1 : 0);

        return node;
    }

    public static Node addLists(Node nums1, Node nums2, int carry) {
        if (nums1 == null && nums2 == null && carry == 0) {
            return null;
        }
        int data = (nums1 != null ? (int) nums1.data : 0) + (nums2 != null ? (int) nums2.data : 0) + carry;
        Node node = new Node(data % 10);
        // Node addList = addLists(nums1 != null ? nums1.next : null, nums2 != null ? nums2.next : null, data > 10 ? 1 : 0);
        // node.next = addList;
        node.next = addLists(nums1 != null ? nums1.next : null, nums2 != null ? nums2.next : null, data > 10 ? 1 : 0);
        return node;
    }

    public static Node addTwoNumbers(Node nums1, Node nums2) {

        Node dummy = new Node(0);
        Node current = dummy;
        int carry = 0;

        while (nums1 != null || nums2 != null || carry > 0) {

            int data = (nums1 != null ? (int) nums1.data : 0) + (nums2 != null ? (int) nums2.data : 0) + carry;
            carry = data / 10;
            current.next = new Node(data % 10);
            current = current.next;
            if (nums1 != null)
                nums1 = nums1.next;
            if (nums2 != null)
                nums2 = nums2.next;
        }

        return dummy.next;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }

    public static void main(String[] args) {
        BasicLinkedList<Integer> nums1 = new BasicLinkedList<>();
        nums1.add(3);
        nums1.add(8);
        nums1.add(5);

        BasicLinkedList<Integer> nums2 = new BasicLinkedList<>();
        nums2.add(3);
        nums2.add(8);
        nums2.add(5);

        Node node1 = nums1.getHead();
        Node node2 = nums2.getHead();
        Node sumList = addLists(node1, node2, 0);
        printList(sumList);
        printList(addTwoNumbers(node1, node2));
    }
}
