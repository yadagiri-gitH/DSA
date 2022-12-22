package com.take.u.forward.dp;

import java.util.Arrays;

public class UniquePaths {
    //Recursion
    public static int totalUniquePaths(int row, int col) {
        if (row == 0 && col == 0)
            return 1;
        if (row < 0 || col < 0)
            return 0;

        //int upward = totalUniquePathsUsingRecursion(row - 1, col);
        //int left = totalUniquePathsUsingRecursion(row, col - 1);

        return totalUniquePaths(row - 1, col) + totalUniquePaths(row, col - 1);
    }

    //Memorization
    public static int totalUniquePaths(int row, int col, int[][] dp) {
        if (row == 0 && col == 0)
            return 1;
        if (row < 0 || col < 0)
            return 0;

        if (dp[row][col] != -1) {
            return dp[row][col];
        }

        // int upward = totalUniquePathsWithMemorization(row - 1, col,matrix);
        // int left = totalUniquePathsWithMemorization(row, col - 1,matrix);

        return dp[row][col] = totalUniquePaths(row - 1, col, dp) + totalUniquePaths(row, col - 1, dp);
    }

    //Tabulation
    public static int totalUniquePathsUsingTabulation(int m, int n, int[][] dp) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    dp[i][j] = 1;
                } else {
                    int upward = 0, left = 0;
                    if (i > 0)
                        upward = dp[i - 1][j];
                    if (j > 0)
                        left = dp[i][j - 1];
                    dp[i][j] = upward + left;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    //Tabulation with 1D Array
    public static int totalUniquePathsUsingTabulation(int m, int n) {
        int[] previous = new int[n];
        for (int i = 0; i < m; i++) {
            int[] current = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) {
                    current[j] = 1;
                } else {
                    int upward = 0, left = 0;
                    if (i > 0)
                        upward = previous[j];
                    if (j > 0)
                        left = current[j - 1];
                    current[j] = upward + left;
                }
            }
            previous = current;
        }
        return previous[n - 1];
    }

    //https://www.youtube.com/watch?v=rBAxUTqvlQA
    //4c2=3*4/2*1
    public static int uniquePaths(int m, int n) {
        int N = m + n - 2;
        int r = m - 1;
        double res = 1;

        for (int i = 1; i <= r; i++) {
            res = res * (N - r + i) / i;
        }

        return (int) res;
    }

    public static void main(String[] args) {
        int m = 3, n = 3;

        int[][] dp = new int[m][n];

        for (int i = 0; i < m; i++) {
            Arrays.fill(dp[i], -1);
        }


        // 3*3 Matrix,Passing the value of row & column (m-1,n-1)
        System.out.println("Total Unique Paths Using Recursion : " + totalUniquePaths(2, 2));
        // 3*3 Matrix,Passing the value of row & column (m-1,n-1)
        System.out.println("Total Unique Paths Using Memorization : " + totalUniquePaths(2, 2, dp));
        // 3*3 Matrix,Passing the value of m*n length
        System.out.println("Total Unique Paths Using Tabulation : " + totalUniquePathsUsingTabulation(3, 3, dp));
        // 3*3 Matrix,Passing the value of  m*n length
        System.out.println("Total Unique Paths Using Tabulation(Space Optimization) : " + totalUniquePathsUsingTabulation(3, 3));

        System.out.println("Total Unique Paths With Optimization : " + uniquePaths(3, 3));
    }
}
