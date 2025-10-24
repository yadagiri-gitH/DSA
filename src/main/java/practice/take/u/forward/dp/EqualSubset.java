package practice.take.u.forward.dp;

import java.util.Arrays;

public class EqualSubset {
    public static boolean isEqualSubSetSum(int[] ints, int index, int target) {

        if (target == 0)
            return true;

        if (index == 0)
            return ints[0] == target;

        boolean take = false;
        if (ints[index] <= target)
            take = isEqualSubSetSum(ints, index - 1, target - ints[index]);
        boolean notTake = isEqualSubSetSum(ints, index - 1, target);

        return take || notTake;

    }

    public static boolean isEqualSubSetSum(int[] ints, int index, int target, int[][] dp) {

        if (target == 0)
            return true;
        if (index == 0)
            return ints[0] == target;
        if (dp[index][target] != -1)
            return true;

        boolean take = false;
        if (ints[index] <= target)
            take = isEqualSubSetSum(ints, index - 1, target - ints[index]);
        boolean notTake = isEqualSubSetSum(ints, index - 1, target);

        dp[index][target] = (take || notTake) ? 0 : -1;

        return dp[index][target] == 0;
    }

    public static void main(String[] args) {

        int[] ints = {12, 3, 3, 7, 14, 5, 2};

        int n = ints.length;

        int sum = 0;

        for (int i = 0; i < n; i++)
            sum += ints[i];

        if (sum % 2 == 0) {
            System.out.println(isEqualSubSetSum(ints, n - 1, sum / 2));
            int target = sum / 2;
            int[][] dp = new int[n][target + 1];
            for (int i = 0; i < dp.length; i++) {
                Arrays.fill(dp[i], -1);
            }
            System.out.println(isEqualSubSetSum(ints, n - 1, sum / 2, dp));
        } else {
            System.out.println("No Equal Subset Sum");
        }


    }
}

