package practice.take.u.forward.dp;

import java.util.Arrays;

public class MinSumPath {

    // Use (int)1e9 inplace of Integer.MAX_VALUE, to prevent from overflow, because if we return Integer.MAX_VALUE from recursion and go on adding 1 to it as in this question then it will lead to overflow.
    // 1e9 simply means (1) * (10^9)
    // Typecast 1e9 to int since by default is is double => (int)1e9

    public static int minSumPath(int i, int j, int[][] path) {
        if (i == 0 && j == 0) return path[i][j];
        else if (i < 0 || j < 0) return (int) 1e9; //this not working if return Integer.MAX_VALUE ??
        else {
            int up = path[i][j] + minSumPath(i - 1, j, path);
            int left = path[i][j] + minSumPath(i, j - 1, path);
            return Math.min(up, left);
        }
    }

    public static int minSumPath(int i, int j, int[][] path, int[][] dp) {
        if (i == 0 && j == 0) return path[i][j];
        else if (i < 0 || j < 0) return (int) 1e9;
        if (dp[i][j] != -1) return dp[i][j];
        else {
            int up = path[i][j] + minSumPath(i - 1, j, path, dp);
            int left = path[i][j] + minSumPath(i, j - 1, path, dp);
            return dp[i][j] = Math.min(up, left);
        }
    }

    public static int minSumPathUsingTabulation(int m, int n, int[][] path, int[][] dp) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) dp[i][j] = path[i][j];
                else {
                    int up = path[i][j];
                    if (i > 0) up += dp[i - 1][j];
                    else up += (int) 1e9;
                    int left = path[i][j];
                    if (j > 0) left += dp[i][j - 1];
                    else left += (int) 1e9;
                    dp[i][j] = Math.min(up, left);
                }
            }
        }
        return dp[m - 1][n - 1];
    }

    public static int minSumPathUsingTabulation(int m, int n, int[][] path) {
        int[] prev = new int[m];
        for (int i = 0; i < m; i++) {
            int[] cur = new int[n];
            for (int j = 0; j < n; j++) {
                if (i == 0 && j == 0) cur[j] = path[i][j];
                else {
                    int up = path[i][j];
                    if (i > 0) up += prev[j];
                    else up += (int) 1e9;
                    int left = path[i][j];
                    if (j > 0) left += cur[j - 1];
                    else left += (int) 1e9;
                    cur[j] = Math.min(up, left);
                }
            }
            prev = cur;
        }
        return prev[n - 1];
    }

    public static void main(String[] args) {
        int[][] ninjaLand = {{5, 9, 6}, {11, 5, 2}};
        int m = ninjaLand.length;
        int n = ninjaLand[0].length;
        System.out.println(minSumPath(m - 1, n - 1, ninjaLand));

        //Memorization
        int[][] mem = new int[m][n];
        for (int i = 0; i < mem.length; i++) {
            Arrays.fill(mem[i], -1);
        }
        System.out.println(minSumPath(m - 1, n - 1, ninjaLand, mem));

        //Tabulation
        System.out.println(minSumPathUsingTabulation(m, n, ninjaLand, new int[m][n]));
        System.out.println(minSumPathUsingTabulation(m, n, ninjaLand));
    }
}
