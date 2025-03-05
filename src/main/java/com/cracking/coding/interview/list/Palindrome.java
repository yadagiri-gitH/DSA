package com.cracking.coding.interview.list;

import java.util.Stack;

import static com.cracking.coding.interview.list.BasicLinkedList.Node;

public class Palindrome {

    public static boolean isPalindrome(Node head) {
        Node slow = head;
        Node fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        Node reverse = reverseAndClone(slow);

        while (reverse != null) {
            if (head.data != reverse.data) {
                return false;
            } else {
                head = head.next;
                reverse = reverse.next;
            }
        }

        return true;
    }

    private static Node reverseAndClone(Node node) {
        Node head = null;
        while (node != null) {
            Node next = node.next;
            node.next = head;
            head = node;
            node = next;
        }
        return head;
    }

    public static boolean isPalindromeCheckOnStack(Node head) {
        Node slow = head;
        Node fast = head;

        Stack stack = new Stack();

        while (fast != null && fast.next != null) {
            stack.push(slow.data);
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (slow.data != stack.pop()) {
                return false;
            } else {
                slow = slow.next;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        BasicLinkedList<Integer> nums = new BasicLinkedList<>();
        nums.add(6);
        nums.add(9);
        nums.add(9);
        nums.add(6);
        nums.add(9);
        nums.add(9);
        nums.add(6);
        System.out.println(nums.print(nums.getHead()) + " is Palindrome : " + isPalindrome(nums.getHead()));
        nums = new BasicLinkedList<>();
        nums.add(6);
        System.out.println(nums.print(nums.getHead()) + " is Palindrome : " + isPalindromeCheckOnStack(nums.getHead()));
    }

}
