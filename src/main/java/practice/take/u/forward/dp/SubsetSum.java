package practice.take.u.forward.dp;

import java.util.Arrays;

public class SubsetSum {

    //This works if k(target sum) is in positive numbers(k>0)
    public static boolean subSetSumToK(int index, int k, int[] arr) {
        if (k == 0)
            return true;
        if (index == 0)
            return arr[index] == k;
        else {
            boolean noTake = subSetSumToK(index - 1, k, arr);
            boolean take = false;
            if (arr[index] <= k) {
                take = subSetSumToK(index - 1, k - arr[index], arr);
            }
            return take || noTake;
        }
    }

    public static boolean subSetSumToK(int index, int k, int[] arr, int[][] dp) {
        if (k == 0)
            return true;
        if (index == 0)
            return arr[index] == k;
        if (dp[index][k] != -1) {
            return true;
        } else {
            boolean noTake = subSetSumToK(index - 1, k, arr, dp);
            boolean take = false;
            if (arr[index] <= k) {
                take = subSetSumToK(index - 1, k - arr[index], arr, dp);
            }
            dp[index][k] = (take || noTake) ? 0 : -1;
            return dp[index][k] == 0;
        }
    }


    public static boolean subSetSumToK(int k, int[] arr) {

        int n = arr.length;

        boolean[][] dp = new boolean[arr.length][k + 1];

        for (int index = 0; index < arr.length; index++) {
            dp[index][0] = true;
        }

        //if index=0 and if target is equal to left-over/last/first(arr[0]) element then it will be true,it can be expressed using below statement,think think :)
        if (arr[0] <= k) {
            dp[0][arr[0]] = true;
        }

        for (int index = 1; index < arr.length; index++) {
            for (int target = 1; target <= k; target++) {
                boolean notTake = dp[index - 1][target];
                boolean take = false;
                if (arr[index] <= target)
                    take = dp[index - 1][target - arr[index]];
                dp[index][target] = notTake || take;
            }
        }

        return dp[n - 1][k];
    }

    public static boolean subSetSumToKUsingSpaceOptimization(int k, int[] arr) {

        boolean[] prev = new boolean[k + 1];
        boolean[] curr = new boolean[k + 1];

        prev[0] = curr[0] = true;
        prev[arr[0]] = true;

        for (int index = 1; index < arr.length; index++) {
            for (int target = 1; target <= k; target++) {
                boolean notTake = prev[target];
                boolean take = false;
                if (arr[index] <= target)
                    take = prev[target - arr[index]];
                curr[target] = notTake || take;
            }
            prev = curr;
        }
        return prev[k];
    }

    public static void main(String[] args) {
        int[] arr = {1, 9, 8, 4};
        int n = arr.length;
        //target
        int k = 7;
        System.out.println(subSetSumToK(n - 1, k, arr));
        //Memorization
        int[][] dp = new int[n][k + 1];
        for (int i = 0; i < n; i++) {
            Arrays.fill(dp[i], -1);
        }

        System.out.println(subSetSumToK(n - 1, k, arr, dp));
        //tabulation
        System.out.println(subSetSumToK(k, arr));
        //space optimization
        System.out.println(subSetSumToKUsingSpaceOptimization(k, arr));
    }
}
