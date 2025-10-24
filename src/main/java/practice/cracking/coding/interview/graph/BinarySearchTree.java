package practice.cracking.coding.interview.graph;

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

public class BinarySearchTree {
    public Node root;

    public void insert(int data) {
        if (this.root == null) {
            this.root = new Node(data);
        } else {
            insertData(this.root, data);
        }
    }

    private void insertData(Node root, int data) {

        Node current = root;
        Node parent = null;

        while (current != null) {
            parent = current;
            if (data < current.data) {
                current = current.left;
            } else if (data > current.data) {
                current = current.right;
            } else // Ignore duplicates for BST: early exit if value already exists
            {
                return;
            }
        }

        Node node = new Node(data);
        node.parent = parent;

        if (data < parent.data) {
            parent.left = node;
        } else {
            parent.right = node;
        }
    }

    public void delete(int data) {
        Node node = searchNode(data, this.root);

        if (node == null) {
            System.out.println("To be deleted element " + data + " not found in BST!!");
            return;
        }

        if (node.left == null) {
            transplant(node, node.right);
        } else if (node.right == null) {
            transplant(node, node.left);
        } else {
            Node successor = min(node.right);
            if (successor.parent != node) {
                transplant(successor, successor.right);
                successor.right = node.right;
                if (successor.right != null) {
                    successor.right.parent = successor;
                }
            }
            transplant(node, successor);
            successor.left = node.left;
            if (successor.left != null) {
                successor.left.parent = successor;
            }
        }
    }

    private Node searchNode(int data, Node root) {
        while (root != null) {
            if (root.data == data) {
                break;
            } else if (data < root.data) {
                root = root.left;
            } else if (data > root.data) {
                root = root.right;
            }
        }
        return root;
    }

    public Node min(Node node) {
        while (node != null && node.left != null) {
            node = node.left;
        }
        return node;
    }

    public void transplant(Node u, Node v) {
        if (u.parent == null) {
            this.root = v;
        } else if (u.parent.left == u) {
            u.parent.left = v;
        } else if (u.parent.right == u) {
            u.parent.right = v;
        }
        if (v != null) {
            v.parent = u.parent;
        }
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Test Case 1: Insertion of nodes
        System.out.println("Inserting elements into the BST...");
        bst.insert(15);
        bst.insert(10);
        bst.insert(20);
        bst.insert(8);
        bst.insert(12);
        bst.insert(17);
        bst.insert(25);
        bst.insert(10); // Duplicate - Should be ignored
        printTree(bst.root); // Print BST structure

        // Test Case 2: Searching for nodes
        System.out.println("\nSearching for elements...");
        testSearch(bst, 10); // Exists
        testSearch(bst, 25); // Exists
        testSearch(bst, 30); // Does not exist

        // Test Case 3: Deletion of nodes
        System.out.println("\nDeleting elements...");
        bst.delete(8); // Node with no children
        bst.delete(10); // Node with one child
        bst.delete(15); // Node with two children (Root Node)
        printTree(bst.root); // Print BST structure after deletion

        // Test Case 4: Edge cases
        System.out.println("\nEdge Cases...");
        bst.delete(100); // Non-existing node
        bst.delete(15); // Re-deleting root node after it has already been removed
        printTree(bst.root); // Verify tree structure

        System.out.println("\nTests completed.");
    }

    // Helper method to print the BST in-order
    public static void printTree(Node root) {
        if (root != null) {
            printTree(root.left);
            System.out.print(root.data + " ");
            printTree(root.right);
        }
    }

    // Helper method to test search functionality
    public static void testSearch(BinarySearchTree bst, int value) {
        Node result = bst.searchNode(value, bst.root);
        if (result != null) {
            System.out.println("Element " + value + " found.");
        } else {
            System.out.println("Element " + value + " not found.");
        }
    }

}
