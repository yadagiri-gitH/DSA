package com.codesdope.course;

class Node {
    public int data;
    public Node next;
    public Node prev;

    public Node(int data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

public class DoublyLinkedList {
    public Node head;

    public DoublyLinkedList() {

    }

    public DoublyLinkedList(int data) {
        Node a = new Node(data);
        head = a;
    }

    public void print() {
        Node temp = head;
        while (temp != null) {
            System.out.println(temp.data);
            temp = temp.next;
        }
    }

    public void add(int data) {
        Node nodeData = new Node(data);
        if (this.head == null) {
            this.head = nodeData;
        } else {
            Node temp = head;
            while (temp.next != null) {
                temp = temp.next;
            }
            temp.next = nodeData;
            nodeData.prev = temp;
        }
    }

    public void insertAtFirst(int data) {
        Node nodeData = new Node(data);
        nodeData.next = head;
        this.head.prev = nodeData;
        this.head = nodeData;
    }

    public void insertAtEnd(int data) {
        Node nodeData = new Node(data);
        Node temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = nodeData;
        nodeData.prev = temp;
    }

    public void delete(int data) {
        if (this.head.data == data) {
            this.head = head.next;
        } else {
            Node temp = head;
            while (temp.next != null) {
                if (temp.next.data == data) {
                    temp.next = temp.next.next;
                    if (temp.next.next != null) {
                        temp.next.next.prev = temp;
                    }
                    break;
                }
                temp = temp.next;
            }
        }
    }

    //new node before head
    public void insertAtFront(Node n) {
        n.next = head;
        head.prev = n;
        head = n;
    }

    //insert new node at last
    public void insertAtTail(Node n) {
        Node temp = head;

        while (temp.next != null) {
            temp = temp.next;
        }

        temp.next = n;
        n.prev = temp;
    }

    //function to insert a node after a node
    public void insertAfter(Node n, Node a) {
        n.next = a.next;
        a.next.prev = n;
        a.next = n;
        n.prev = a;
    }

    //function to delete
    public void delete(Node a) {
        if (a.prev != null) { //node is not head
            a.prev.next = a.next;
        } else { //node a is head
            head = a.next;
        }
        if (a.next != null) {
            a.next.prev = a.prev;
        }
    }

    public static void main(String[] args) {
        DoublyLinkedList list = new DoublyLinkedList();

        list.add(1);
        list.add(3);
        list.add(4);
        list.add(8);

        System.out.println("Printing Double Linked List after adding");
        list.print();

        list.delete(2);
        list.delete(3);
        System.out.println("After deleting 2 and 3 & Now Printing current Double Linked List");
        list.print();

        list.insertAtEnd(200);
        System.out.println("After inserting 200 at End & Now Printing current Double Linked List");
        list.print();

        list.insertAtEnd(89);
        System.out.println("After inserting 89 at End & Now Printing current Double Linked List");
        list.print();

        list.insertAtFirst(29);
        System.out.println("After inserting 29 at Beginning & Now Printing current Double Linked List");
        list.print();


        list.delete(1);
        list.delete(29);
        list.delete(8);
        list.delete(4);
        list.delete(200);
        list.delete(89);

        System.out.println("After deleting all data & Now Printing current Double Linked List");
        list.print();


        //CodesDope

        DoublyLinkedList l = new DoublyLinkedList(10);

        Node a, b, c;
        a = new Node(20);
        b = new Node(50);
        c = new Node(60);


        l.head.next = a;
        a.next = b;
        b.next = c;

        System.out.println("Now Printing CodesDope Double Linked List");
        l.print();

        Node z;

        z = new Node(0);
        l.insertAtFront(z);
        z = new Node(-10);
        l.insertAtFront(z);

        z = new Node(100);
        l.insertAtTail(z);

        z = new Node(30);
        l.insertAfter(z, a);
        z = new Node(40);
        l.insertAfter(z, a.next);
        z = new Node(500);
        l.insertAfter(z, a.next.next);

        System.out.println("After Inserting 30,40,500 and Now Printing current Double Linked List");
        l.print();

        l.delete(l.head);
        l.delete(z);

        System.out.println("After deleting head & 500 and Now Printing current Double Linked List");

        l.print();

    }
}
