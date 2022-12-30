package com.take.u.forward.dp;

import java.util.Arrays;

public class TriangleSumPath {

    public static int findMinimumSumPath(int n, int i, int j, int[][] triangle) {
        if (i == n - 1) {
            return triangle[n - 1][j];//triangle[i][j];
        }

        int d = triangle[i][j] + findMinimumSumPath(n, i + 1, j, triangle);
        int dg = triangle[i][j] + findMinimumSumPath(n, i + 1, j + 1, triangle);
        return Math.min(d, dg);
    }

    public static int findMinimumSumPath(int n, int i, int j, int[][] triangle, int[][] mem) {
        if (i == n - 1) {
            return triangle[n - 1][j];
        }

        if (mem[i][j] != -1)
            return mem[i][j];

        int d = triangle[i][j] + findMinimumSumPath(n, i + 1, j, triangle, mem);
        int dg = triangle[i][j] + findMinimumSumPath(n, i + 1, j + 1, triangle, mem);
        return mem[i][j] = Math.min(d, dg);
    }

    public static int findMinimumSumPath(int n, int[][] triangle, int[][] mem) {
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j <= i; j++) {
                if (i == n - 1) {
                    mem[i][j] = triangle[i][j];//triangle[n-1][j];
                } else {
                    int up = triangle[i][j] + mem[i + 1][j];
                    int diaUp = triangle[i][j] + mem[i + 1][j + 1];
                    mem[i][j] = Math.min(up, diaUp);
                }
            }
        }

        return mem[0][0];
    }

    //Striver solution
    public static int findMinimumSumPathUsingTUF(int n, int[][] triangle, int[][] dp) {
        for (int col = 0; col < n; col++) { //last row columns length
            dp[n - 1][col] = triangle[n - 1][col];
        }
        for (int i = n - 2; i >= 0; i--) {
            for (int j = i; j >= 0; j--) {
                int d = triangle[i][j] + dp[i + 1][j];
                int dg = triangle[i][j] + dp[i + 1][j + 1];
                dp[i][j] = Math.min(d, dg);
            }
        }
        return dp[0][0];
    }

    public static int findMinimumSumPath(int n, int[][] triangle) {
        int[] prev = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            int[] current = new int[n];
            for (int j = 0; j <= i; j++) {
                if (i == n - 1) {
                    current[j] = triangle[i][j];
                } else {
                    int d = triangle[i][j] + prev[j];
                    int dg = triangle[i][j] + prev[j + 1];
                    current[j] = Math.min(d, dg);
                }
            }
            prev = current;
        }

        return prev[0];
    }

    //Striver
    public static int findMinimumSumPathUsingTUF(int n, int[][] triangle) {
        int[] last = new int[n];

        for (int col = 0; col < n; col++) {//last row columns length
            last[col] = triangle[n - 1][col];
        }
        for (int i = n - 2; i >= 0; i--) {
            int[] current = new int[n];
            for (int j = i; j >= 0; j--) {
                int d = triangle[i][j] + last[j];
                int dg = triangle[i][j] + last[j + 1];
                current[j] = Math.min(d, dg);
            }
            last = current;
        }

        return last[0];//finally first row first column
    }

    public static void main(String[] args) {
        int[][] triangle = {{1}, {2, 3}, {3, 6, 7}, {8, 9, 6, 10}};
        int n = triangle.length;
        int m = triangle[n - 1].length;
        System.out.println(findMinimumSumPath(n, 0, 0, triangle));
        int[][] mem = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(mem[i], -1);
        }

        System.out.println(findMinimumSumPath(n, 0, 0, triangle, mem));
        System.out.println(findMinimumSumPath(n, triangle, new int[n][m]));
        System.out.println(findMinimumSumPath(n, triangle));
        System.out.println(findMinimumSumPathUsingTUF(n, triangle, new int[n][m]));
        System.out.println(findMinimumSumPathUsingTUF(n, triangle));
    }
}
