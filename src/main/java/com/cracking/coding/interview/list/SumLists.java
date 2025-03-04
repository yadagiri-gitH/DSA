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
        node.next = addLists(nums1 != null ? nums1.next : null, nums2 != null ? nums2.next : null, data >= 10 ? 1 : 0);
        return node;
    }

    // Recursive helper function
   /* private static Node addTwoNumbersHelper(Node l1, Node l2, int carry) {
        // Base case: If both lists are null and no carry, return null
        if (l1 == null && l2 == null && carry == 0) {
            return null;
        }

        // Calculate the sum for the current node
        int val1 = (l1 != null) ? (int) l1.data : 0;
        int val2 = (l2 != null) ? (int) l2.data : 0;
        int sum = val1 + val2 + carry;

        // Create a new node with the sum's unit digit
        Node newNode = new Node(sum % 10);

        // Calculate the new carry
        int newCarry = sum / 10;

        // Recur for the next nodes
        Node nextL1 = (l1 != null) ? l1.next : null;
        Node nextL2 = (l2 != null) ? l2.next : null;
        newNode.next = addTwoNumbersHelper(nextL1, nextL2, newCarry);

        return newNode;
    }*/


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

    public static Node addTwoForwardList(Node nums1, Node nums2) {
        int n1Size = length(nums1);
        int n2Size = length(nums2);

        if (n1Size > n2Size) {
            nums2 = padList(nums2, n1Size - n2Size);
        } else {
            nums1 = padList(nums1, n2Size - n1Size);
        }

        Node result = addTwoNumsList(nums1, nums2);

        return Temp.carry > 0 ? insertBefore(result, Temp.carry) : result;
    }

    public static Node insertBefore(Node list, int data) {
        Node node = new Node(data);

        if (list != null) {
            node.next = list;
        }
        return node;
    }

    //0987+7869 => 8->8->5->6->null
    private static Node addTwoNumsList(Node nums1, Node nums2) {
        if (nums1 == null && nums2 == null) {
            return null;
        }

        Node result = addTwoNumsList(nums1.next, nums2.next);

        int sum = (nums1 != null ? (int) nums1.data : 0) + (nums2 != null ? (int) nums2.data : 0) + Temp.carry;

        Node node = new Node(sum % 10);

        node.next = result;

        Temp.carry = sum / 10;

        return node;
    }

    private static Node padList(Node nums, int offset) {
        if (offset == 0) {
            return nums;
        }
        Node zero = new Node(0);
        while (offset > 1) {
            zero.next = new Node<>(0);
            zero = zero.next;
            offset--;
        }
        zero.next = nums;
        return zero;
    }

    private static int length(Node nums) {
        //recursive
        /*if (l == null) {
            return 0;
        } else {
            return 1 + length(l.next);
        }*/

        int size = 0;
        while (nums != null) {
            size++;
            nums = nums.next;
        }
        return size;
    }

    //  you can use an integer array to store carry instead of inner class (arrays are mutable in Java)
    //  int[] carry = new int[1];
    // carry[0]=sum/10;
    static class Temp {
        public static int carry = 0;
        //public static Node node = null;
    }

    public static void printList(Node head) {
        while (head != null) {
            System.out.print(head.data + " -> ");
            head = head.next;
        }
        System.out.println("null");
    }


    private static PartialSum addListsHelper(Node l1, Node l2) {
        if (l1 == null && l2 == null) {
            PartialSum sum = new PartialSum();
            return sum;
        }
        PartialSum sum = addListsHelper(l1.next, l2.next);
        int val = sum.carry + (int) l1.data + (int) l2.data;
        Node full_result = insertBefore(sum.sum, val % 10);
        sum.sum = full_result;
        sum.carry = val / 10;
        return sum;
    }

    private static Node addLists(Node l1, Node l2) {
        int len1 = length(l1);
        int len2 = length(l2);
        if (len1 < len2) {
            l1 = padList(l1, len2 - len1);
        } else {
            l2 = padList(l2, len1 - len2);
        }
        PartialSum sum = addListsHelper(l1, l2);
        if (sum.carry == 0) {
            return sum.sum;
        } else {
            Node result = insertBefore(sum.sum, sum.carry);
            return result;
        }
    }

   /* private static Node padList(Node l, int padding) {
        Node head = l;
        for (int i = 0; i < padding; i++) {
            head = insertBefore(head, 0);
        }
        return head;
    }
     private static Node insertBefore(Node list, int data) {
        Node node = new Node(data);
        if (list != null) {
            node.next = list;
        }
        return node;
    }*/

    static class PartialSum {
        public Node sum = null;
        public int carry = 0;
    }

    public static int linkedListToInt(Node node) {
        int value = 0;
        while (node != null) {
            value = value * 10 + (int) node.data;
            node = node.next;
        }
        return value;
    }


    public static void main(String[] args) {
        BasicLinkedList<Integer> nums1 = new BasicLinkedList<>();
        nums1.add(9);
        nums1.add(8);
        nums1.add(7);

        BasicLinkedList<Integer> nums2 = new BasicLinkedList<>();
        nums2.add(7);
        nums2.add(8);
        nums2.add(6);
        nums2.add(9);

        Node node1 = nums1.getHead();
        Node node2 = nums2.getHead();
        Node sumList = addLists(node1, node2, 0);
        printList(sumList);
        printList(addTwoNumbers(node1, node2));
        //printList(addTwoNumbersHelper(node1, node2, 0));
        Node result = addTwoForwardList(node1, node2);
        printList(result);
        printList(addLists(node1, node2));
    }
}
