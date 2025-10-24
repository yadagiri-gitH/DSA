package practice.take.u.forward.dp;

import java.util.ArrayList;
import java.util.List;

public class ClimbStair {
    public static int waysToClimbStair(int step, int n, List<Integer> climbSteps, List<List<Integer>> climbStepsList) {
        if (step == n) {
            climbStepsList.add(new ArrayList<>(climbSteps));
            return 1;
        } else if (step > n) {
            return 0;
        }
        climbSteps.add(1);
        int oneStepCount = waysToClimbStair(step + 1, n, climbSteps, climbStepsList);
        climbSteps.remove(climbSteps.size() - 1);
        climbSteps.add(2);
        int twoStepCount = waysToClimbStair(step + 2, n, climbSteps, climbStepsList);
        climbSteps.remove(climbSteps.size() - 1);
        return oneStepCount + twoStepCount;
    }

    /*public static int countDistinctWayToClimbStair(int n) {
        //n==0 means to standing at zero step/stair there is only one way to stand,n== 1 handles edge case ,for example 3-2=1(so it avoid going to negate case,result of give example is 1 and 1-1(n-1) can be done but 1-2 can't be done as it is -1 ,that's why we are returning 1 as and when they reach step 1)
        //you can notice this converted as fibonacci series except the base case
        if (n == 0 || n == 1) {
            return 1;
        }
        return countDistinctWayToClimbStair(n - 1) + countDistinctWayToClimbStair(n - 2);
    }*/

    //converting above one to optimization,its count or number of ways that we are holding in below variables
    public static int countDistinctWayToClimbStair(int n) {

        int lastPrev = 1;
        int prev = 1;

        for (int i = 2; i < n + 1; i++) {
            int current = lastPrev + prev;
            lastPrev = prev;
            prev = current;
        }
        return prev;
    }

    public static void main(String[] args) {
        int n = 6;
        List<List<Integer>> climbStepsList = new ArrayList<>();
        int countWays = waysToClimbStair(0, n, new ArrayList<>(), climbStepsList);
        System.out.println("Distinct Count Ways : " + countWays);
        System.out.println(climbStepsList);
        countWays = countDistinctWayToClimbStair(n);
        System.out.println("Distinct Count Ways : " + countWays);
    }
}
