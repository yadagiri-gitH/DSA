package com.take.u.forward.dp;

import java.util.Arrays;

//https://www.codingninjas.com/codestudio/problems/ninja-and-his-friends_3125885?source=youtube&campaign=striver_dp_videos&utm_source=youtube&utm_medium=affiliate&utm_campaign=striver_dp_videos
public class CherryPickup {

    public static int getMaximumCherries(int r, int c1, int c2, int[][] cBox, int m, int n) {
        //this block should be placed first to handle array index out of bound exception
        if (c1 < 0 || c1 >= n || c2 < 0 || c2 >= n) {
            return (int) -1e8;
        }

        if (r == m - 1) {
            if (c1 != c2) {
                return cBox[r][c1] + cBox[r][c2];
            } else {
                return cBox[r][c1];
            }
        }

        //below commented one also works
        /* int collection = Integer.MIN_VALUE;
        //first column traversal
        for (int i = -1; i <= +1; i++) {
            //2nd column traversal
            for (int j = -1; j <= +1; j++) {
                if (i != j) {
                    collection = Math.max(collection, cBox[r][c1] + cBox[r][c2] + getMaximumCherries(r + 1, c1 + i, c2 + j, cBox, m, n));
                } else {
                    collection = Math.max(collection, cBox[r][c1] + getMaximumCherries(r + 1, c1 + i, c2 + j, cBox, m, n));
                }
            }
         }*/

        int max = (int) -1e8;
        //first column traversal
        for (int i = -1; i <= +1; i++) {
            //2nd column traversal
            for (int j = -1; j <= +1; j++) {
                int collection = 0;
                if (c1 != c2) {
                    collection = cBox[r][c1] + cBox[r][c2];
                } else {
                    collection = cBox[r][c1];
                }
                collection += getMaximumCherries(r + 1, c1 + i, c2 + j, cBox, m, n);
                max = Math.max(max, collection);
            }
        }

        return max;
    }

    public static int getMaximumCherries(int r, int c1, int c2, int[][] cBox, int m, int n, int[][][] dp) {
        //this block should be placed first to handle array index out of bound exception
        if (c1 < 0 || c1 >= n || c2 < 0 || c2 >= n) {
            return (int) -1e8;
        }

        if (r == m - 1) {
            if (c1 != c2) {
                return cBox[r][c1] + cBox[r][c2];
            } else {
                return cBox[r][c1];
            }
        }

        if (dp[r][c1][c2] != -1)
            return dp[r][c1][c2];

        int max = (int) -1e8;
        //first column traversal
        for (int i = -1; i <= +1; i++) {
            //2nd column traversal
            for (int j = -1; j <= +1; j++) {
                int collection = 0;
                if (c1 != c2) {
                    collection = cBox[r][c1] + cBox[r][c2];
                } else {
                    collection = cBox[r][c1];
                }
                collection += getMaximumCherries(r + 1, c1 + i, c2 + j, cBox, m, n, dp);
                max = Math.max(max, collection);
            }
        }

        return dp[r][c1][c1] = max;
    }

    public static int getMaximumCherries(int r, int c, int[][] cBox, int[][][] dp) {
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                if (i == j)
                    dp[r - 1][i][j] = cBox[r - 1][i];
                else
                    dp[r - 1][i][j] = cBox[r - 1][i] + cBox[r - 1][j];
            }
        }

        for (int i = r - 2; i >= 0; i--) {
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < c; k++) {
                    int max = (int) -1e8;
                    //below two for loops require to decide the j & k combination(3*3)
                    for (int l = -1; l <= +1; l++) {
                        for (int m = -1; m <= +1; m++) {
                            int cherryCount = 0;
                            if (j == k)
                                cherryCount = cBox[i][j];
                            else
                                cherryCount = cBox[i][j] + cBox[i][k];
                            if (j + l >= 0 && j + l < c && k + m >= 0 && k + m < c)
                                cherryCount += dp[i + 1][j + l][k + m];
                            else
                                cherryCount += (int) -1e8;
                            max = Math.max(max, cherryCount);
                        }
                    }
                    dp[i][j][k] = max;
                }
            }
        }
        return dp[0][0][c - 1];//1st row 1st column for alice and last column for bob
    }

    public static int getMaximumCherries(int r, int c, int[][] cBox) {
        int[][] temp = new int[c][c];
        for (int i = 0; i < c; i++) {
            for (int j = 0; j < c; j++) {
                if (i == j)
                    temp[i][j] = cBox[r - 1][i];
                else
                    temp[i][j] = cBox[r - 1][i] + cBox[r - 1][j];
            }
        }

        for (int i = r - 2; i >= 0; i--) {
            int[][] current = new int[c][c];
            for (int j = 0; j < c; j++) {
                for (int k = 0; k < c; k++) {
                    int max = (int) -1e8;
                    //below two for loops require to decide the j & k combination(3*3)
                    for (int l = -1; l <= +1; l++) {
                        for (int m = -1; m <= +1; m++) {
                            int cherryCount = 0;
                            if (j == k)
                                cherryCount = cBox[i][j];
                            else
                                cherryCount = cBox[i][j] + cBox[i][k];
                            if (j + l >= 0 && j + l < c && k + m >= 0 && k + m < c)
                                cherryCount += temp[j + l][k + m];
                            else
                                cherryCount += (int) -1e8;
                            max = Math.max(max, cherryCount);
                        }
                    }
                    current[j][k] = max;
                }
            }
            temp = current;
        }
        return temp[0][c - 1];//1st column for alice and last column for bob
    }


    public static void main(String[] args) {
        int[][] cBox = {{2, 3, 1, 2}, {3, 4, 2, 2}, {5, 6, 3, 5}};
        int m = cBox.length;
        int n = cBox[0].length;
        System.out.println(getMaximumCherries(0, 0, n - 1, cBox, m, n));

        //Memorization-3D Array
        int[][][] dp = new int[m][n][n];
        for (int i = 0; i < dp.length; i++) {
            for (int j = 0; j < dp[0].length; j++) {
                Arrays.fill(dp[i][j], -1);
            }

        }

        System.out.println(getMaximumCherries(0, 0, n - 1, cBox, m, n, dp));
        //Tabulation
        System.out.println(getMaximumCherries(m, n, cBox, new int[m][n][n]));
        System.out.println(getMaximumCherries(m, n, cBox));

    }
}
