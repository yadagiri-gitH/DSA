package com.cracking.coding.interview.graph;

import java.util.HashMap;

public class PathsWithSum {
    //Brute Force
    public int countPathsWithSum(Node root, int targetSum) {
        if (root == null)
            return 0;

        // Count paths starting from the root
        int pathsFromRoot = countPathsFromNode(root, targetSum, 0);

        // Count paths starting from the left and right children
        int pathsFromLeft = countPathsWithSum(root.left, targetSum);
        int pathsFromRight = countPathsWithSum(root.right, targetSum);

        return pathsFromRoot + pathsFromLeft + pathsFromRight;
    }

    private int countPathsFromNode(Node node, int targetSum, int currentSum) {
        if (node == null)
            return 0;

        currentSum += node.data;

        int totalPaths = 0;

        if (currentSum == targetSum)
            totalPaths++;

        totalPaths += countPathsFromNode(node.left, targetSum, currentSum) + countPathsFromNode(node.right, targetSum, currentSum);

        return totalPaths;
    }

    //Optimized
    public int countPathsWithSum(Node node, int targetSum, int currentSum, HashMap<Integer, Integer> map) {
        if (node == null)
            return 0;

        currentSum += node.data;

        int totalPaths = 0;

        if (currentSum == targetSum)
            totalPaths++;

        totalPaths += map.getOrDefault(currentSum - targetSum, 0);

        map.put(currentSum, map.getOrDefault(currentSum, 0) + 1);

        totalPaths += countPathsWithSum(node.left, targetSum, currentSum, map);
        totalPaths += countPathsWithSum(node.right, targetSum, currentSum, map);

        map.put(currentSum, map.get(currentSum) - 1);

        return totalPaths;
    }


    private static class Node {
        public int data;
        public Node left;
        public Node right;

        public Node(int data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    public static void main(String[] args) {

        //  structure:
        //       10
        //      /  \
        //     5   -3
        //    / \    \
        //   3   2   11
        //  / \   \
        // 3  -2   1

        Node root = new Node(10);
        root.left = new Node(5);
        root.right = new Node(-3);
        root.left.left = new Node(3);
        root.left.right = new Node(2);
        root.right.right = new Node(11);
        root.left.left.left = new Node(3);
        root.left.left.right = new Node(-2);
        root.left.right.right = new Node(1);

        int targetSum = 8;

        PathsWithSum pathsWithSum=new PathsWithSum();
        System.out.println("--- Testing Brute-Force Solution ---");
        int bruteForceResult = pathsWithSum.countPathsWithSum(root, targetSum);
        System.out.println("Number of paths with sum " + targetSum + ": " + bruteForceResult); // Expected: 3

        System.out.println("\n--- Testing Optimal (Memoization) Solution ---");
        int optimalResult = pathsWithSum.countPathsWithSum(root, targetSum,0,new HashMap<>());
        System.out.println("Number of paths with sum " + targetSum + ": " + optimalResult); // Expected: 3
    }
}

