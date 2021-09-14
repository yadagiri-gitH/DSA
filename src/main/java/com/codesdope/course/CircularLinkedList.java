package com.codesdope.course;

public class CircularLinkedList {

    public Node last;

    public CircularLinkedList() {

    }

    public CircularLinkedList(int data) {
        Node temp = new Node(data);
        temp.next = temp;
        this.last = temp;
    }

    public void traversal() {
        Node temp = last;
        if (this.last != null) {
            while (temp.next != last) {
                System.out.println(temp.next.data);
                temp = temp.next;
            }
            System.out.println(temp.next.data);
        }
    }

    public void add(int data) {
        Node n = new Node(data);
        //single node or element or data
        if (this.last == null) {
            this.last = n;
            this.last.next = last;
        } else if (this.last.next == last) {
            this.last.next = n;
            n.next = last;
            this.last = n;
        } else {
            n.next = last.next;
            this.last.next = n;
            this.last = n;
        }
    }

    public void insertAfter(Node n, Node a) {
        n.next = a.next;
        a.next = n;
    }

    public void delete(int data) {
        Node temp = this.last;

        if (this.last != null) {
            if (this.last.data == data) {
                if (this.last.next == last) {
                    this.last = null;
                } else {
                    while (temp.next != last) {
                        temp = temp.next;
                    }
                    temp.next = last.next;
                    this.last = temp;
                }
            } else {
                while (temp.next != last) {
                    if (temp.next.data == data) {
                        temp.next = temp.next.next;
                        break;
                    }
                    temp = temp.next;
                }
            }
        }
    }

    public void delete(Node n) {
        Node temp =this.last;
        while(temp.next != n) {
            temp = temp.next;
        }
        if(n == this.last) { //last node
            if(n.next == n) { //only one node
                this.last = null;
            }
            else {//more than one node and last node
                temp.next = n.next;
                this.last = temp; //updating last pointer
            }
        }
        else {//not last node
            temp.next = n.next;
        }
    }

    public void insertAtLast(Node n) {
        n.next = this.last.next;
        this.last.next = n;
        this.last = n;
    }

    static class Node {
        public int data;
        public Node next;

        public Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    public static void main(String[] args) {
        CircularLinkedList list = new CircularLinkedList();

        list.add(1);
        list.add(3);
        list.add(4);
        list.add(8);

        System.out.println("Printing Circular Linked List after adding 1,3,4,8");
        list.traversal();

        list.delete(2);
        list.delete(3);
        System.out.println("After deleting 2 and 3 & Now Printing Current Circular Linked List");
        list.traversal();

        list.delete(1);
        list.delete(29);
        list.delete(8);
        list.delete(4);//list becomes empty over here
        list.delete(200);

        System.out.println("After deleting all data & Now Printing Current Circular Linked List");
        list.traversal();

        //CodesDope

        CircularLinkedList l = new CircularLinkedList(10);

        Node a, b, c;
        a = new Node(20);
        b = new Node(30);
        c = new Node(40);

        l.last.next = a;
        a.next = b;
        b.next = c;
        c.next = l.last;

        System.out.println("Now Printing CodesDope Circular Linked List");
        l.traversal();

        Node z;
        z = new Node(50);
        l.insertAfter(z, c);
        z = new Node(25);
        l.insertAfter(z, a);
        z = new Node(100);
        l.insertAtLast(z);

        System.out.println("Now Printing CodesDope Circular Linked List");
        l.traversal();

        l.delete(l.last);
        l.delete(b);

        System.out.println("Now Printing CodesDope Circular Linked List after deleting 100 & 30");
        l.traversal();
    }
}
