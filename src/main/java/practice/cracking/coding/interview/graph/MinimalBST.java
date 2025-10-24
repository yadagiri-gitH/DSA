package practice.cracking.coding.interview.graph;

public class MinimalBST {
    //create Minimal BST on sorted Array

    private static Node minimalTree(int[] sortArray, int startIndex, int endIndex) {
        if (startIndex > endIndex) {
            return null;
        }
        int midIndex = startIndex + (endIndex - startIndex) / 2;
        Node binaryTree = new Node(sortArray[midIndex]);
        binaryTree.left = minimalTree(sortArray, startIndex, midIndex - 1);
        binaryTree.right = minimalTree(sortArray, midIndex + 1, endIndex);
        return binaryTree;
    }

    public static void printInorderTree(Node tree) {
        if (tree == null) {
            return;
        }
        printInorderTree(tree.left);
        System.out.print(tree.data + " ");
        printInorderTree(tree.right);
    }

    public static Node minimalTree(int[] sortedArray) {
        if (sortedArray == null || sortedArray.length == 0) return null;
        return minimalTree(sortedArray, 0, sortedArray.length - 1);
    }

    public static void main(String[] args) {
        int[] sorted1 = {12, 13, 14, 15, 16, 17, 18};
        Node tree1 = minimalTree(sorted1);
        System.out.println("Inorder of tree1:");
        printInorderTree(tree1);
        int[] sorted2 = {12, 13, 14, 15, 16, 17, 18, 19};
        System.out.println("\nInorder of tree2:");
        Node tree2 = minimalTree(sorted2);
        printInorderTree(tree2);
    }

}
