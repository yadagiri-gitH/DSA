package com.take.u.forward.dp;

public class FrogJump {

    public static int frogJump(int n, int[] heights) {
        if (n < 0) {
            return -1;
        }

        //recursion
        //return findFrogMinimumJumps(n - 1, heights);

        //dp memorization
        int[] dp = new int[n];
        for (int i = 0; i < n; i++) {
            dp[i] = -1;
        }
        //return findFrogMinimumJumps(n - 1, heights, dp);

        //return findFrogMinimumLostJumps(n , heights, dp);

        //tabulation
        return findFrogMinimumLostJumps(n, heights);
    }

    //recursion
    /*public static int findFrogMinimumJumps(int index, int[] heights) {
        if (index == 0)
            return 0;
        int firstStep = findFrogMinimumJumps(index - 1, heights) + Math.abs(heights[index] - heights[index - 1]);
        int secondStep = Integer.MAX_VALUE;
        if (index > 1) {
            secondStep = findFrogMinimumJumps(index - 2, heights) + Math.abs(heights[index] - heights[index - 2]);
        }
        return Math.min(firstStep, secondStep);
    }*/

    //memorization
   /* public static int findFrogMinimumJumps(int index, int[] heights, int[] dp) {
        if (index == 0)
            return 0;
        if (dp[index] != -1)
            return dp[index];

        int firstStep = findFrogMinimumJumps(index - 1, heights, dp) + Math.abs(heights[index] - heights[index - 1]);
        int secondStep = Integer.MAX_VALUE;
        if (index > 1) {
            secondStep = findFrogMinimumJumps(index - 2, heights, dp) + Math.abs(heights[index] - heights[index - 2]);
        }
        return dp[index] = Math.min(firstStep, secondStep);
    }*/

    //tabulation
    public static int findFrogMinimumLostJumps(int n, int[] heights, int[] dp) {
        dp[0] = 0;
        for (int i = 1; i < n; i++) {
            int firstStep = dp[i - 1] + Math.abs(heights[i] - heights[i - 1]);
            int secondStep = Integer.MAX_VALUE;
            if (i > 1) {
                secondStep = dp[i - 2] + Math.abs(heights[i] - heights[i - 2]);
            }
            dp[i] = Math.min(firstStep, secondStep);
        }

        return dp[n - 1];
    }

    //tabulation optimization
    public static int findFrogMinimumLostJumps(int n, int[] heights) {
        int lastPrev = 0;//index-2
        int prev = 0;//index-1;

        for (int i = 1; i < n; i++) {

            int fs = prev + Math.abs(heights[i] - heights[i - 1]);
            int ss = Integer.MAX_VALUE;
            if (i > 1) {
                ss = lastPrev + Math.abs(heights[i] - heights[i - 2]);
            }

            int current = Math.min(fs, ss);
            lastPrev = prev;
            prev = current;

        }

        return prev;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] heights = {10, 20, 30, 10};
        //here frogJump method takes care of passing n or n-1 based on recursion or tabulation
        System.out.println("Minimum number of height that Frog takes " + frogJump(n, heights));
    }
}
