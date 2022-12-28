package com.take.u.forward.dp;

import java.util.Arrays;

//Similar to UniquePaths
public class MazeObstacles {
    public static int mazeObstacles(int i, int j, int maze[][]) {
        int mod = (int) 1e9 + 7;
        if (i < 0 || j < 0 || maze[i][j] == -1) return 0;
        else if (i == 0 && j == 0) return 1;
        else {
            int up = mazeObstacles(i - 1, j, maze);
            int left = mazeObstacles(i, j - 1, maze);
            return (up + left) % mod;
        }
    }

    public static int mazeObstacles(int i, int j, int maze[][], int mem[][]) {
        int mod = (int) 1e9 + 7;
        if (i < 0 || j < 0 || maze[i][j] == -1) return 0;
        else if (i == 0 && j == 0) return 1;
        if (mem[i][j] != -1)
            return mem[i][j];
        else {
            int up = mazeObstacles(i - 1, j, maze);
            int left = mazeObstacles(i, j - 1, maze);
            return mem[i][j] = (up + left) % mod;
        }
    }

    public static int mazeObstaclesUsingTabulation(int m, int n, int maze[][], int dp[][]) {
        int mod = (int) (1e9 + 7);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (maze[i][j] == -1) dp[i][j] = 0;
                else if (i == 0 && j == 0) dp[i][j] = 1;
                else {
                    int up = 0, left = 0;
                    if (i > 0) up = dp[i - 1][j];

                    if (j > 0) left = dp[i][j - 1];

                    dp[i][j] = (up + left) % mod;
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    //Space Optimization
    public static int mazeObstaclesUsingTabulation(int m, int n, int maze[][]) {
        int mod = (int) (1e9 + 7);
        int[] prev = new int[m];
        for (int i = 0; i < m; i++) {
            int[] cur = new int[n];
            for (int j = 0; j < n; j++) {
                if (maze[i][j] == -1) cur[j] = 0;
                else if (i == 0 && j == 0) cur[j] = 1;
                else {
                    int up = 0, left = 0;
                    if (i > 0) up = prev[j];
                    if (j > 0) left = cur[j - 1];
                    cur[j] = (up + left) % mod;
                }
            }
            prev = cur;
        }
        return prev[n - 1];
    }

    public static void main(String[] args) {
        int[][] maze = {{0, 0, 0}, {0, -1, 0}, {0, 0, 0}};
        int m = maze.length;
        int n = maze[0].length;
        System.out.println(mazeObstacles(m - 1, n - 1, maze));

        //Memorization
        int[][] mem = new int[m][n];
        for (int i = 0; i < mem.length; i++) {
            Arrays.fill(mem[i], -1);
        }
        System.out.println(mazeObstacles(m - 1, n - 1, maze, mem));

        //Tabulation
        System.out.println(mazeObstaclesUsingTabulation(m, n, maze, new int[m][n]));
        System.out.println(mazeObstaclesUsingTabulation(m, n, maze));
    }
}
