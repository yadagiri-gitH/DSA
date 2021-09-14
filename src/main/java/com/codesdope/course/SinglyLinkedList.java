package com.codesdope.course;

/*class Node {
    public int data;
    public Node next;

    public Node(int data) {
        this.data = data;
        this.next=null;
    }
}*/

public class SinglyLinkedList {
    private Node head;

    public SinglyLinkedList() {
    }

    public SinglyLinkedList(int data) {
        Node node = new Node(data);
        this.head = node;
    }


    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public void add(int data) {
        Node node = new Node(data);
        if (this.head == null) {
            this.head = node;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = node;
        }
    }

    public void insertAtFirst(int data) {
        Node firstNode = new Node(data);
        firstNode.next = head;
        this.head = firstNode;
    }

    public void insertAtLast(int data) {
        Node lastNode = new Node(data);
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = lastNode;
    }

    public void insertNodeAfter(Node n, Node a) {
        n.next = a.next;
        a.next = n;
    }

    public void delete(int data) {
        Node temp = head;
        if (temp.data == data) {
            this.head = temp.next;
        } else {
            while (temp.next != null) {
                if (temp.next.data == data) {
                    temp.next = temp.next.next;
                    break;
                }
                temp = temp.next;
            }
        }
    }

    public void insertAtFirst(Node n) {
        n.next = head;
        head = n;
    }

    //insert new node at last
    public void insertAtLast(Node n) {
        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = n;
    }

    public void delete(Node n) {
        Node temp = head;
        if (temp == n) { //node to be deleted is head
            head = n.next;
        } else { //node to be deleted is not head
            while (temp != null) {
                if (temp.next == n) { //node previous to node to be deleted
                    temp.next = n.next;
                    break; //breaking the loop after deleting the node
                }
                temp = temp.next;
            }
        }
    }


    public static void main(String[] args) {

        SinglyLinkedList list1 = new SinglyLinkedList();

        list1.add(1);
        list1.add(3);
        list1.add(4);
        list1.add(8);

        System.out.println("Print Current List 1");

        list1.print();
        list1.delete(2);
        System.out.println("After deleting 2");
        System.out.println("Print Current List 1");
        list1.print();

        list1.delete(3);
        System.out.println("After deleting 3");
        System.out.println("Print Current List 1");
        list1.print();

        list1.insertAtLast(200);
        System.out.println("Print Current List 1");
        list1.print();
        list1.insertAtLast(89);
        System.out.println("Print Current List 1");
        list1.print();

        list1.insertAtFirst(29);
        System.out.println("Print Current List 1");
        list1.print();

        SinglyLinkedList list2 = new SinglyLinkedList(15);

        Node a = new Node(20);
        Node b = new Node(50);
        Node c = new Node(60);

        list2.head.next = a;
        a.next = b;
        b.next = c;

        System.out.println("Print Current List 2");
        list2.print();

        Node z = new Node(0);
        list2.insertAtFirst(z);

        z = new Node(-10);
        list2.insertAtFirst(z);

        System.out.println("Print Current List 2 after node insertion at first");
        list2.print();

        Node last= new Node(100);
        list2.insertAtLast(last);

        System.out.println("Print Current List 2 after node insertion at last");
        list2.print();

        z = new Node(30);
        list2.insertNodeAfter(z, a);

        System.out.println("Print Current List 2 after insertion node after");
        list2.print();

        z = new Node(40);
        list2.insertNodeAfter(z, a.next);

        z = new Node(500);
        list2.insertNodeAfter(z, a.next.next);

        System.out.println("Print Current List 2 after insertion node after");
        list2.print();

        list2.delete(list2.head);
        list2.delete(z);
        list2.delete(last);

        System.out.println("Print Current List 2 after deletion");
        list2.print();
    }

}
