package practice.grokking.coding.interview.slowfast;


 /*class ListNode {
    int value = 0;
    ListNode next;

    ListNode(int value) {
        this.value = value;
    }
}*/

public class RearrangeList {

    public static void reorder(ListNode head) {

        if (head == null || head.next == null) {
            return;
        }

        ListNode slow = head, fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        ListNode first = head;//2,4,6,8,10,12
        ListNode second = reverse(slow);//12,10,8->null//first=2,4,6,8->null

        while (first != null && second != null) {
            ListNode n1 = first.next;
            ListNode n2 = second.next;

            first.next = second;//2>12>4>10>6>8
            // if(n1==null) break; //not required
            second.next = n1;//first.next;12>4>10>6>8>8(cyclic)

            first = n1;
            second = n2;
        }

        if (first != null) {
            first.next = null;
        }

    }

    public static ListNode reverse(ListNode node) {
        ListNode previous = null;
        ListNode current = node;
        while (current != null) {
            ListNode nextNode = current.next;
            current.next = previous;
            previous = current;
            current = nextNode;
        }

        return previous;
    }

    public static void main(String[] args) {
        ListNode head = new ListNode(2);
        head.next = new ListNode(4);
        head.next.next = new ListNode(6);
        head.next.next.next = new ListNode(8);
        head.next.next.next.next = new ListNode(10);
        head.next.next.next.next.next = new ListNode(12);
        RearrangeList.reorder(head);
        while (head != null) {
            System.out.print(head.value + " ");
            head = head.next;
        }
    }
}
