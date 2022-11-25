package com.take.u.forward.dp;

public class NinjaTraining {

    //recursion bottom up
    public static int ninjaTraining(int n, int day, int tasks, int lastTask, int[][] points) {
        int maxPoints = 0;

        if (day == n - 1) {
            for (int task = 0; task < tasks; task++) {
                if (task != lastTask) {
                    maxPoints = Math.max(maxPoints, points[day][task]);
                }
            }
            return maxPoints;
        }

        for (int task = 0; task < tasks; task++) {
            if (task != lastTask) {
                /*int point = points[day][task] + ninjaTraining(n, day + 1, tasks, task, points);
                maxPoints = Math.max(maxPoints, point);*/
                maxPoints = Math.max(maxPoints, points[day][task] + ninjaTraining(n, day + 1, tasks, task, points));
            }
        }

        return maxPoints;
    }

    //recursion topdown(take u forward) //tasks size is hardcoded as it is N*3
    public static int getMaxEarnedPoints(int day, int last, int[][] points) {

        if (day == 0) {
            int maxPoints = 0;
            for (int task = 0; task < 3; task++) {
                if (task != last) {
                    maxPoints = Math.max(maxPoints, points[0][task]);
                }
            }
            return maxPoints;
        }

        int maxPoints = 0;

        for (int task = 0; task < 3; task++) {
            if (task != last) {
                int point = points[day][task] + getMaxEarnedPoints(day - 1, task, points);
                maxPoints = Math.max(point, maxPoints);
            }
        }

        return maxPoints;
    }

    public static int ninjaTraining(int n, int[][] points) {
        return getMaxEarnedPoints(n - 1, 3, points);
    }

    public static void main(String[] args) {
        int[][] points = {{134, 28, 5}, {9, 87, 14}, {3, 138, 3}};
        int n = points.length, taskSize = points[0].length;
        System.out.println("Max Earned Points Using Recursion(Bottom Up) " + ninjaTraining(n, 0, taskSize, -1, points));
        System.out.println("Max Earned Points Using Recursion(Top down) " + ninjaTraining(n, points));
    }
}
