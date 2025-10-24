package practice.take.u.forward.dp;

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
        int nonPick = 0 + maximumNonAdjacentSum(index - 1, nums);
        return Math.max(pick, nonPick);
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
        int nonPick = 0 + maximumNonAdjacentSum(index - 1, nums);
        return nonAdjSumAns[index] = Math.max(pick, nonPick);
    }

    public static int maximumNonAdjacentSum(int[] nums, int[] nonAdjSumAns) {

        nonAdjSumAns[0] = nums[0];

        for (int i = 1; i < nums.length; i++) {//12, 8, 4, 9
            int pick = nums[i];
            if (i > 1) {
                pick += nonAdjSumAns[i - 2];//it won't work if it is pick += nums[i - 2] obviously as you are not considering the already computed values
            }

            int nonPick = 0 + nonAdjSumAns[i - 1];//it won't work if it isnonPick = 0 + nums[i - 1]

            nonAdjSumAns[i] = Math.max(pick, nonPick);
        }

        return nonAdjSumAns[nums.length - 1];
    }

    public static int maximumNonAdjacentSum(int[] nums) {
        int lastPrev = 0;
        int prev = nums[0];
        for (int i = 1; i < nums.length; i++) {
            //int pick = lastPrev + nums[i];
            int pick = nums[i];
            if (i > 1) {
                pick += lastPrev;
            }
            int nonPick = prev;//0+prev;
            int current = Math.max(pick, nonPick);
            lastPrev = prev;
            prev = current;
        }
        return prev;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] nums = new int[]{12, 8, 4, 9};
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
