package practice.grokking.coding.interview.slowfast;

import java.util.Stack;

class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}

public class PalindromicLinkedList {

    private static ListNode left;

    public static boolean isPalindrome1(ListNode head) {
        ListNode slow = head, fast = head;
        Stack<Integer> stack = new Stack<>();
        while (fast != null && fast.next != null) {
            stack.push(slow.value);
            slow = slow.next;
            fast = fast.next.next;
        }

        if (fast != null) {
            slow = slow.next;
        }

        while (slow != null) {
            if (stack.pop() != slow.value)
                return false;
            slow = slow.next;
        }
        return true;
    }

    public static boolean isPalindrome(ListNode head) {
        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        /* conceptually correct ,need not skip the middle elemnent unless you want to skip in case stack approach

        for more information look into this https:chatgpt.com/c/692e37b7-607c-8322-9635-b2fcc81df7dc
        if (fast != null) {
            slow = slow.next;
        } */

        // Step 3: Reverse second half (non-destructive copy)
        ListNode reversed = reverseCopy(slow);

        // Step 4: Compare first half and reversed second half
        ListNode p1 = head;
        ListNode p2 = reversed;

        while (p2 != null) {  // only need to check second half
            if (p1.value != p2.value) {
                return false;
            }
            p1 = p1.next;
            p2 = p2.next;
        }

        return true;
    }

    //original list will be modified//destructive means original being changed
    //1->2->3->4->5->null
    private static ListNode reverse1(ListNode head) {
        if (head == null || head.next == null)//5.next->null
        {
            return head;//5
        }

        ListNode revNode = reverse1(head.next);//5//5//5

        head.next.next = head;//5->4//5->4-->3//5->4-->3->2//5->4-->3->2->1

        head.next = null;

        return revNode;//5//5//5//5
    }

    //original changes
    private static ListNode reverse2(ListNode head) {
        ListNode current = head;
        ListNode revNode = null;

        while (current != null) {
            ListNode nextNode = current.next;//2//3
            current.next = revNode;//1->null//2->1->null
            revNode = current;//1->null//2->1->null
            current = nextNode;//2//3
        }

        return revNode;
    }

    //original list won't be changed
    private static ListNode reverseCopy(ListNode head) {
        ListNode current = head;
        ListNode revNode = null;

        while (current != null) {
            ListNode newNode = new ListNode(current.value);
            newNode.next = revNode;
            revNode = newNode;
            current = current.next;
            ;
        }

        return revNode;
    }

    //original list won't be changed
    private static boolean isPalindrome3(ListNode head) {
        left = head;
        //isRecursiveNodesAreEqual(ListNode head, ListNode current) won't work as described in the method
        return recurse(head);
    }

    private static boolean recurse(ListNode right) {
        if (right == null)
            return true;

        if (!recurse(right.next))
            return false;

        if (left.value != right.value)
            return false;

        left = left.next;
        return true;
    }

    private static boolean isRecursiveNodesAreEqual(ListNode head, ListNode current) {
        if (current == null)
            return true;

        boolean isPalindrome = isRecursiveNodesAreEqual(head, current.next);

        if (!isPalindrome)
            return false;

        if (head.value != current.value)
            return false;

        /*Java is pass - by - value, so updating head = head.next inside recursion does NOT update the caller’
        s copy, which is why this code compares:
        head(always starting at first node)
        vs
        current(moving backwards via recursion)*/


        /*Java passes the value of the reference
        Reassigning head = head.next does not propagate upward
        So the left pointer never moves*/

       //head = head.next;//so go with wrapper or static reference

        return true;

    }


    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(4);
        head.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head));
        System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome3(head));

        head.next.next.next.next.next = new ListNode(2);
        System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome(head));
        System.out.println("Is palindrome: " + PalindromicLinkedList.isPalindrome3(head));
    }
}