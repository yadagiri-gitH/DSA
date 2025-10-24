package practice.cracking.coding.interview.list;

import java.util.Stack;

import static practice.cracking.coding.interview.list.BasicLinkedList.Node;

public class Palindrome {

    public static Node frontier = null;

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

    public static boolean palindromeCheckOnStack(Node head) {
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

    public static boolean checkPalindrome1(Node head) {
        frontier = head;
        return recursivePalindromeCheck(head);
    }

    public static boolean recursivePalindromeCheck(Node head) {

        if (head == null) {
            return true;
        }

        boolean isPalindrome = recursivePalindromeCheck(head.next);

        if (!isPalindrome)
            return false;

        if (head.data != frontier.data) {
            return false;
        }

        frontier = frontier.next;

        return true;
    }


    public static boolean checkPalindrome2(Node head) {
        int length = length(head);
        Result result = recursivePalindromeCheck2(head, length);
        return result.isPalindrome;
    }

    private static int length(Node head) {
        int size = 0;
        while (head != null) {
            size++;
            head = head.next;
        }
        return size;
    }

    public static Result recursivePalindromeCheck2(Node head, int length) {
        if (head == null || length <= 0) {//even
            return new Result(head, true);
        } else if (length == 1) {//odd
            return new Result(head.next, true);
        }

        Result result = recursivePalindromeCheck2(head.next, length - 2);

        if (!result.isPalindrome || result.node == null) {
            return result;
        }

        result.isPalindrome = head.data == result.node.data;
        result.node = result.node.next;

        return result;
    }

    public static boolean checkPalindrome3(Node head) {
        return recursivePalindromeCheck3(head, head).isPalindrome;
    }

    public static Result recursivePalindromeCheck3(Node head, Node current) {
        if (current == null) {
            return new Result(head, true);
        }

        Result result = recursivePalindromeCheck3(head, current.next);

        if (!result.isPalindrome || result.node == null) {
            return result;
        }

        result.isPalindrome = current.data == result.node.data;
        result.node = result.node.next;

        return result;
    }


    private static class Result {
        public Node node = null;
        public boolean isPalindrome;

        public Result(Node head, boolean res) {
            this.node = head;
            this.isPalindrome = res;
        }
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
        System.out.println(nums.print(nums.getHead()) + " is Palindrome : " + palindromeCheckOnStack(nums.getHead()));
        BasicLinkedList<Integer> nums2 = new BasicLinkedList<>();
        nums2.add(8);
        nums2.add(9);
        nums2.add(8);
        nums2.add(9);
        System.out.println(nums.print(nums2.getHead()) + " is Palindrome : " + checkPalindrome1(nums2.getHead()));

        BasicLinkedList<Integer> nums3 = new BasicLinkedList<>();
        nums3.add(8);
        nums3.add(9);
        nums3.add(8);
        nums3.add(9);
        nums3.add(8);
        System.out.println(nums.print(nums3.getHead()) + " is Palindrome : " + checkPalindrome2(nums3.getHead()));

        BasicLinkedList<Integer> nums4 = new BasicLinkedList<>();
        nums4.add(9);
        nums4.add(9);
        System.out.println(nums.print(nums4.getHead()) + " is Palindrome : " + checkPalindrome3(nums4.getHead()));
    }

}
