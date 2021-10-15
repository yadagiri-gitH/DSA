package com.codesdope.course;

public class BST {

    public Node root;

    public void insert(int data) {
        Node n = new Node(data);
        Node temp = this.root;
        Node p = null;
        while (temp != null) {
            p = temp;
            if (data < temp.data)
                temp = temp.left;
            else
                temp = temp.right;
        }

        n.parent = p;

        if (p == null)
            this.root = n;
        else if (data < p.data)
            p.left = n;
        else
            p.right = n;

    }

    public void delete(int data) {
        Node temp = this.root;
        Node p = null;

        while (temp != null) {
            p = temp;
            if (data == temp.data) {
                break;
            } else if (data < temp.data)
                temp = temp.left;
            else if (data > temp.data)
                temp = temp.right;
        }

        if (p.left == null) {
            transplant(p, p.right);
        } else if (p.right == null) {
            transplant(p, p.left);
        } else {
            Node m = minimum(p.right);
            if (m.parent != p) {
                transplant(m, m.right);
                m.right = p.right;
                m.right.parent = m;
            }
            transplant(p, m);
            m.left = p.left;
            m.left.parent = m;
        }
    }

    public Node minimum(Node n) {
        while (n.left != null) {
            n = n.left;
        }
        return n;
    }


    public void transplant(Node x, Node y) {
        if (x.parent == null) //this.root.data == x.data
            this.root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else
            x.parent.right = y;
        if (y != null) {
            y.parent = x.parent;
        }
    }

    public boolean isExist(int data) {
        Node temp = this.root;
        while (temp != null) {
            if (temp.data == data) {
                return true;
            }
            if (data < temp.data)
                temp = temp.left;
            else
                temp = temp.right;
        }

        return false;
    }

    class Node {
        public int data;
        public Node left;
        public Node right;
        public Node parent;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
            this.parent = null;
        }
    }

    public void inorder(Node n) {
        if (n != null) {
            inorder(n.left);
            System.out.println(n.data);
            inorder(n.right);
        }
    }

    public static void main(String[] args) {
        BST tree = new BST();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(100);
        tree.insert(90);
        tree.insert(40);
        tree.insert(50);
        tree.insert(60);
        tree.insert(70);
        tree.insert(80);
        tree.insert(150);
        tree.insert(110);
        tree.insert(120);

        tree.delete(10);
        tree.delete(120);

        tree.inorder(tree.root);
    }
}
