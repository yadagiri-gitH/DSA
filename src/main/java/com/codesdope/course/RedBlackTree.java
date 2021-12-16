package com.codesdope.course;

enum Color {
    Red, Black;
}

class Node {
    public int data;
    public Node left;
    public Node right;
    public Node parent;
    public Color color;

    public Node() {

    }

    public Node(int data) {
        this.data = data;
        this.color = Color.Red;
    }
}

public class RedBlackTree {

    private Node root;
    private Node NIL;

    public RedBlackTree() {
        Node leaf = new Node();
        leaf.color = Color.Black;
        this.NIL = leaf;
        this.root = leaf;
    }

    public void leftRotation(Node x) {
        Node y = x.right;
        x.right = y.left;

        if (y.left != this.NIL) {
            y.left.parent = x;
        }

        y.parent = x.parent;

        if (x.parent == this.NIL)
            this.root = y;
        else if (x == x.parent.left)
            x.parent.left = y;
        else if (x == x.parent.right)
            x.parent.right = y;

        y.left = x;
        y.left.parent = y;//x.parent = y;
    }

    public void rightRotation(Node y) {
        Node x = y.left;
        y.left = x.right;
        if (x.right != this.NIL) {
            x.right.parent = y;
        }

        x.parent = y.parent;

        if (y.parent == this.NIL)
            this.root = x;
        else if (y == y.parent.right)
            y.parent.right = x;
        else //if (y == y.parent.left)
            y.parent.left = x;

        x.right = y;
        y.parent = x;//x.right.parent = x;
    }

    public void transplant(Node u, Node v) {
        if (u.parent == this.NIL)
            this.root = v;
        else if (u == u.parent.left)
            u.parent.left = v;
        else
            u.parent.left = v;
        v.parent = u.parent;
    }

    public Node minimum(Node n) {
        while (n.left != this.NIL) {
            n = n.left;
        }
        return n;
    }

    public void insert(int data) {
        Node z = new Node(data);
        Node temp = this.root;
        Node y = this.NIL;

        while (temp != this.NIL) {
            y = temp;
            //if (z.data == temp.data) {//you shouldn't add as y becomes parent to same element
            //   break;
            //}
            if (z.data < temp.data)
                temp = temp.left;
            else
                temp = temp.right;
        }

        z.parent = y;

        if (y == this.NIL)
            this.root = z;
        else if (z.data < y.data)
            y.left = z;
        else
            y.right = z;

        z.left = this.NIL;
        z.right = this.NIL;

        insertFixUp(z);
    }

    private void insertFixUp(Node z) {

        while (z.parent.color == Color.Red) {
            if (z.parent == z.parent.parent.left) {
                Node y = z.parent.parent.right;
                if (y.color == Color.Red) {
                    z.parent.color = Color.Black;
                    y.color = Color.Black;
                    z.parent.parent.color = Color.Red;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.right) {
                        z = z.parent;
                        leftRotation(z);
                    }
                    z.parent.color = Color.Black;
                    z.parent.parent.color = Color.Red;
                    rightRotation(z.parent.parent);
                }
            } else {
                Node y = z.parent.parent.left;
                if (y.color == Color.Red) {
                    z.parent.color = Color.Black;
                    y.color = Color.Black;
                    z.parent.parent.color = Color.Red;
                    z = z.parent.parent;
                } else {
                    if (z == z.parent.left) {
                        z = z.parent;
                        rightRotation(z);
                    }
                    z.parent.color = Color.Black;
                    z.parent.parent.color = Color.Red;
                    leftRotation(z.parent.parent);
                }
            }
        }
        this.root.color = Color.Black;
    }

    public void delete(int data) {

        Node temp = this.root;
        Node z = this.NIL;
        while (temp != this.NIL) {
            z = temp;
            if (data == temp.data)
                break;
            else if (data < temp.data)
                temp = temp.left;
            else
                temp = temp.right;

        }

        Node y = z;
        Node x;
        Color y_original_color = y.color;

        if (y.left == this.NIL) {
            x = y.right;
            transplant(y, y.right);
        } else if (y.right == this.NIL) {
            x = y.left;
            transplant(y, y.left);
        } else {
            y = minimum(z.right);
            y_original_color = y.color;
            x = y.right;
            if (y.parent != z) {
                transplant(y, y.right);
                y.right = z.right;
                y.right.parent = y;
            }
            transplant(z, y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }

        if (y_original_color == Color.Black) {
            deleteFixUp(x);
        }
    }

    private void deleteFixUp(Node x) {
        while (x != this.root && x.color == Color.Black) {
            if (x == x.parent.left) {
                Node w = x.parent.right;
                if (w.color == Color.Red) {
                    w.color = Color.Black;
                    x.parent.color = Color.Red;
                    leftRotation(x.parent);
                    w = x.parent.right;
                }
                if (w.left.color == Color.Black && w.right.color == Color.Black) {
                    w.color = Color.Red;
                    x = x.parent;
                } else {
                    if (w.right.color == Color.Black) {
                        w.left.color = Color.Black;
                        w.color = Color.Red;
                        rightRotation(w);
                        w = x.parent.right;
                    }
                    w.color = x.parent.color;
                    w.right.color = Color.Black;
                    x.parent.color = Color.Black;
                    leftRotation(x.parent);
                    x = this.root;
                }
            } else {
                Node w = x.parent.left;
                if (w.color == Color.Red) {
                    w.color = Color.Black;
                    x.parent.color = Color.Red;
                    rightRotation(x.parent);
                    w = x.parent.left;
                }
                if (w.left.color == Color.Black && w.right.color == Color.Black) {
                    w.color = Color.Red;
                    x = x.parent;
                } else {
                    if (w.left.color == Color.Black) {
                        w.right.color = Color.Black;
                        w.color = Color.Red;
                        leftRotation(w);
                        w = x.parent.left;
                    }
                    w.color = x.parent.color;
                    w.left.color = Color.Black;
                    x.parent.color = Color.Black;
                    rightRotation(x.parent);
                    x = this.root;
                }
            }
        }
        x.color = Color.Black;
    }

    public void inorder(Node n) {
        if (n != this.NIL) {
            inorder(n.left);
            System.out.println(n.data);
            inorder(n.right);
        }
    }

    public static void main(String[] args) {
        RedBlackTree t = new RedBlackTree();

        t.insert(10);
        t.insert(20);
        t.insert(30);
        t.insert(100);
        t.insert(90);
        t.insert(40);
        t.insert(50);
        t.insert(60);
        t.insert(70);
        t.insert(80);
        t.insert(150);
        t.insert(110);
        t.insert(120);
        t.inorder(t.root);
        t.delete(10);
        t.delete(120);
        t.inorder(t.root);
    }
}
