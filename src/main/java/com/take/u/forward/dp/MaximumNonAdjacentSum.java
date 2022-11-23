package com.take.u.forward.dp;

public class MaximumNonAdjacentSum {
    public static int maximumNonAdjacentSum(int index, int[] nums) {
        if (index == 0) {
            return nums[index];
        }

        if (index < 0) {
            return 0;
        }

        //not adjacent
        int pick = nums[index] + maximumNonAdjacentSum(index - 2, nums);
        int notPick = 0 + maximumNonAdjacentSum(index - 1, nums);
        return Math.max(pick, notPick);
    }

    public static int maximumNonAdjacentSum(int index, int[] nums, int[] nonAdjSumAns) {
        if (index == 0) {
            return nums[index];
        }

        if (index < 0) {
            return 0;
        }

        if (nonAdjSumAns[index] != -1) {
            return nonAdjSumAns[index];
        }

        //not adjacent
        int pick = nums[index] + maximumNonAdjacentSum(index - 2, nums);
        int notPick = 0 + maximumNonAdjacentSum(index - 1, nums);
        return nonAdjSumAns[index] = Math.max(pick, notPick);
    }

    public static int maximumNonAdjacentSum(int[] nums, int[] ans) {

        ans[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int pick = nums[i] + maximumNonAdjacentSum(i - 2, nums);
            int notPick = 0 + maximumNonAdjacentSum(i - 1, nums);
            ans[i] = Math.max(pick, notPick);
        }

        return ans[nums.length - 1];
    }

    public static int maximumNonAdjacentSum(int[] nums) {
        int lastPrev = 0;
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int pick = lastPrev + nums[i];//n-2
            int notPick = prev;
            int current = Math.max(pick, notPick);
            lastPrev = prev;
            prev = current;
        }

        return prev;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] nums = new int[]{2, 8, 4, 9};
        int nonAdjSumAns[] = new int[nums.length];
        for (int i = 0; i < nonAdjSumAns.length; i++) {
            nonAdjSumAns[i] = -1;
        }
        if (nums.length > 0) {
            System.out.println(maximumNonAdjacentSum(n - 1, nums));
            System.out.println(maximumNonAdjacentSum(n - 1, nums, nonAdjSumAns));
            System.out.println(maximumNonAdjacentSum(nums, nonAdjSumAns));
            System.out.println(maximumNonAdjacentSum(nums));
        }

    }
}
