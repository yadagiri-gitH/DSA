package practice.take.u.forward.dp;

import java.util.Arrays;

public class HouseRobber {
    public static int houseRobber(int[] money) {//first and last houses in street are adjacent as these are in circular fashion
        int house = money.length;

        if (house <= 0) {
            return 0;
        }

        if (house == 1) {
            return money[0];
        }
        //maxStolenAmount(0, totalNoOfHouses - 2, nums) >> ignoring the last element
        //maxStolenAmount(1, totalNoOfHouses - 1, nums) >> ignoring the first element
        //this make sure we are not adding the first and last in max sum of Stolen amount
        int maxSum = Math.max(maxStolenAmount(0, house - 2, money), maxStolenAmount(1, house - 1, money));
        System.out.println("Max Sum Using Recursion : " + maxSum);

        int nonAdjSumAns[] = new int[money.length];

        for (int i = 0; i < nonAdjSumAns.length; i++) {
            nonAdjSumAns[i] = -1;
        }

        maxSum = Math.max(maxNonAdjStolenAmount(0, house - 2, money, nonAdjSumAns), maxNonAdjStolenAmount(1, house - 1, money, nonAdjSumAns));
        System.out.println("Max Sum Using Memorization : " + maxSum);

        maxSum = Math.max(maxStolenAmount(0, house - 2, money, nonAdjSumAns), maxStolenAmount(1, house - 1, money, nonAdjSumAns));
        System.out.println("Max Sum Using Tabulation : " + maxSum);

        maxSum = Math.max(maxNonAdjStolenAmount(0, house - 1, money), maxNonAdjStolenAmount(1, house, money));
        System.out.println("Max Sum Using Tabulation Optimization : " + maxSum);

        maxSum = robber(house, money);
        System.out.println("Max Sum Using Tabulation Optimization (TUF approach) : " + maxSum);

        return maxSum;
    }

    public static int maxStolenAmount(int start, int end, int[] money) {
        if (start == end) {//if (end == 0) {
            return money[start];//money[end];
        }

        if (end < start) {
            return 0;
        }

        int nonAdjSumPick = money[end] + maxStolenAmount(start, end - 2, money);
        int nonAdjSumNonPick = 0 + maxStolenAmount(start, end - 1, money);
        return Math.max(nonAdjSumPick, nonAdjSumNonPick);
    }

    //memorization
    public static int maxNonAdjStolenAmount(int start, int end, int[] money, int[] sum) {
        if (start == end) {//if (end == 0) {
            return money[start];//money[end];
        }

        if (end < start) {
            return 0;
        }

        if (sum[end] != -1) {
            return sum[end];
        }

        int nonAdjSumPick = money[end] + maxStolenAmount(start, end - 2, money);
        int nonAdjSumNonPick = 0 + maxStolenAmount(start, end - 1, money);
        return sum[end] = Math.max(nonAdjSumPick, nonAdjSumNonPick);
    }

    //tabulation
    public static int maxStolenAmount(int start, int end, int[] money, int[] sum) {
        sum[start] = money[start];

        for (int i = start + 1; i < end; i++) {
            int nonAdjSumPick = sum[i];
            if (i > 1) {
                nonAdjSumPick += sum[i - 2];
            }
            int nonAdjSumNonPick = 0 + sum[i - 1];
            sum[i] = Math.max(nonAdjSumPick, nonAdjSumNonPick);
        }

        return sum[end];
    }

    //tabulation optimization
    public static int maxNonAdjStolenAmount(int start, int end, int[] money) {
        int lastPrev = 0;
        int prev = 0;
        if (start <= 1) {
            prev = money[start];
        }

        for (int i = start + 1; i < end; i++) {
            int nonAdjSumPick = money[i];
            if (i > 1) {
                nonAdjSumPick += lastPrev;
            }
            int nonAdjSumNonPick = 0 + prev;
            int current = Math.max(nonAdjSumPick, nonAdjSumNonPick);
            lastPrev = prev;
            prev = current;
        }

        return prev;
    }

    //tabulation
    public static int maxNonAdjStolen(int n, int[] money) { //take u forward approach in dp programs
        int lastPrev = 0;
        int prev = money[0];
        for (int i = 1; i < n; i++) {
            int take = money[i];
            if (i > 1) {
                take += lastPrev;
            }
            int nonTake = prev;
            int current = Math.max(take, nonTake);
            lastPrev = prev;
            prev = current;
        }
        return prev;
    }

    //tabulation
    public static int robber(int n, int[] money) {  //take u forward approach in dp programs
        int[] firstExclude = Arrays.copyOfRange(money, 0, n - 1);//better to use ArrayList
        int[] lastExclude = Arrays.copyOfRange(money, 1, n);
        return Math.max(maxNonAdjStolen(n - 1, firstExclude), maxNonAdjStolen(n - 1, lastExclude));
    }

    public static void main(String[] args) {
        int[] money = new int[]{16, 2, 3, 17, 78, 23};
        System.out.println("Final Max Sum is " + houseRobber(money));
    }
}
