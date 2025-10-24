package practice.take.u.forward.dp;

import java.util.Arrays;

//max Sum path from starting any cell in first row to any cell in the last row
public class MaxPathSum {

    public static int getMaxPathSum(int i, int j, int[][] matrix, int n) {
        //you should handle if blocks in correct order while writing base case,first block should be j case and then i case
        //commented block won't work or you can go with
        // if (i == 0) {
        //            return matrix[i][j];
        //        } else if (i < 0 || j < 0 || j > n - 1) {
        //            return Integer.MIN_VALUE;
        //        } instead of below commented block

        /* if (i == 0) {
            return matrix[i][j];//matrix[0][j];
        } else if (j < 0 || j >= n) {
            return (int) -1e8;
        }*/

        if (j < 0 || j >= n) {
            return (int) -1e8;//do not go with Integer.MIN_VALUE as it may overflow during the addition;
        } else if (i == 0) {
            return matrix[i][j];//matrix[0][j];
        } else {
            int u = matrix[i][j] + getMaxPathSum(i - 1, j, matrix, n);
            int udl = matrix[i][j] + getMaxPathSum(i - 1, j - 1, matrix, n);
            int udr = matrix[i][j] + getMaxPathSum(i - 1, j + 1, matrix, n);
            return Math.max(u, Math.max(udl, udr));
        }
    }

    public static int getMaxPathSum(int i, int j, int[][] matrix, int n, int[][] dp) {
        if (j < 0 || j >= n) {
            return (int) -1e8;//do not go with Integer.MIN_VALUE as it may overflow during the addition;
        } else if (i == 0) {
            return matrix[i][j];//matrix[0][j];
        }
        if (dp[i][j] != -1) {
            return dp[i][j];
        } else {
            int u = matrix[i][j] + getMaxPathSum(i - 1, j, matrix, n, dp);
            int udl = matrix[i][j] + getMaxPathSum(i - 1, j - 1, matrix, n, dp);
            int udr = matrix[i][j] + getMaxPathSum(i - 1, j + 1, matrix, n, dp);
            return dp[i][j] = Math.max(u, Math.max(udl, udr));
        }
    }

    public static int getMaxPathSum(int m, int n, int[][] matrix, int[][] dp) {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (i == 0) {
                    dp[i][j] = matrix[i][j];
                } else {
                    int u = matrix[i][j] + dp[i - 1][j];
                    int udl = matrix[i][j];
                    if (j > 0) { //i > 0 doesn't needs to be handled as we already covering with i==0
                        udl += dp[i - 1][j - 1];
                    } else {
                        udl += (int) -1e8;
                    }
                    int udr = matrix[i][j];
                    if (j < n - 1) {//j+1<n
                        udr += dp[i - 1][j + 1];
                    } else {
                        udr += (int) -1e8;
                    }
                    dp[i][j] = Math.max(u, Math.max(udl, udr));
                }
            }
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, dp[m - 1][i]);
        }
        return max;
    }

    public static int getMaxPathSum(int m, int n, int[][] matrix) {
        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            prev[i] = matrix[0][i];
        }

        for (int i = 1; i < m; i++) {
            int[] current = new int[n];
            for (int j = 0; j < n; j++) {
                int u = matrix[i][j] + prev[j];
                int udl = matrix[i][j];
                if (j > 0) {
                    udl += prev[j - 1];
                } else {
                    udl += (int) -1e8;
                }
                int udr = matrix[i][j];
                if (j < n - 1) {
                    udr += prev[j + 1];
                } else {
                    udr += (int) -1e8;
                }

                current[j] = Math.max(u, Math.max(udl, udr));
            }
            prev = current;
        }

        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, prev[i]);
        }
        return max;
    }

    public static void main(String[] args) {
        //int[][] matrix = {{1, 2, 10, 4}, {100, 3, 2, 1}, {1, 1, 20, 2}, {1, 2, 2, 1}};
        int[][] matrix = {{1, 2, 3}, {9, 8, 7}, {4, 5, 6}};
        int m = matrix.length;
        int n = matrix[0].length;
        int max = Integer.MIN_VALUE;//we can give here Integer.MIN_VALUE as it is not involved in any addition  ;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, getMaxPathSum(m - 1, i, matrix, n));
        }

        System.out.println("Max Sum Path is " + max);

        int[][] dp = new int[n][m];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            max = Math.max(max, getMaxPathSum(m - 1, i, matrix, n, dp));
        }

        System.out.println("Max Sum Path is Using Memorization " + max);

        System.out.println("Max Sum Path is Using Tabulation " + getMaxPathSum(m, n, matrix, dp));
        System.out.println("Max Sum Path is Using Tabulation(Optimization) " + getMaxPathSum(m, n, matrix));
    }

}
