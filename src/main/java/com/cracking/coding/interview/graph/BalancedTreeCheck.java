package com.cracking.coding.interview.graph;

public class BalancedTreeCheck {

    private int getHeight(Node node) {
        if (node == null)
            return -1;
        return Math.max(getHeight(node.left), getHeight(node.right)) + 1;
    }

    public boolean isBalancedTree(Node root) {
        if (root == null)
            return true;

        int heightDifference = Math.abs(getHeight(root.left) - getHeight(root.right));
        if (heightDifference > 1) {
            return false;
        } else {
            return isBalancedTree(root.left) && isBalancedTree(root.right);
        }
    }

    public boolean checkOptimalBalanced(Node root) {
        return checkHeight(root) != Integer.MIN_VALUE;
    }

    //bottom-up approach and ensures that the tree is balanced by returning an early Integer.MIN_VALUE
    // as soon as an imbalance is detected, which is more efficient than repeatedly calculating the height for each node.
    private int checkHeight(Node node) {
        if (node == null)
            return -1;

        int leftHeight = checkHeight(node.left);
        if (leftHeight == Integer.MIN_VALUE)
            return Integer.MIN_VALUE; // left subtree unbalanced

        int rightHeight = checkHeight(node.right);
        if (rightHeight == Integer.MIN_VALUE)
            return Integer.MIN_VALUE; // right subtree unbalanced

        if (Math.abs(leftHeight - rightHeight) > 1) {
            // current node unbalanced and instantly stop further traversing and if balanced then go to further top traversal
            //you can return something constant instead of MIN_VALUE,need to be corrected in left and right subtree height validation in above blocks
            return Integer.MIN_VALUE;
        } else {
            return Math.max(leftHeight, rightHeight) + 1;
        }
    }

    public static void main(String[] args) {
        BalancedTreeCheck checker = new BalancedTreeCheck();

        // Balanced tree
        Node balanced = new Node(10);
        balanced.left = new Node(5);
        balanced.right = new Node(15);
        balanced.left.left = new Node(3);
        balanced.left.right = new Node(7);
        balanced.right.right = new Node(18);

        System.out.println("Is balanced tree? " + checker.isBalancedTree(balanced)); // true
        System.out.println("Is balanced tree? " + checker.checkOptimalBalanced(balanced)); // true

        // Unbalanced tree
        Node unbalanced = new Node(10);
        unbalanced.left = new Node(5);
        unbalanced.left.left = new Node(3);
        unbalanced.left.left.left = new Node(1); // Heavy on left

        System.out.println("Is unbalanced tree? " + checker.isBalancedTree(unbalanced)); // false
        System.out.println("Is unbalanced tree? " + checker.checkOptimalBalanced(unbalanced)); // false
    }
}
