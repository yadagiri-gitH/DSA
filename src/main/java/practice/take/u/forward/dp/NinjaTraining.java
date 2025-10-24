package practice.take.u.forward.dp;

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

    //memorization
    public static int ninjaTraining(int n, int day, int tasks, int lastTask, int[][] points, int[][] result) {
        int maxPoints = 0;

        if (day == n - 1) {
            for (int task = 0; task < tasks; task++) {
                if (task != lastTask) {
                    maxPoints = Math.max(maxPoints, points[day][task]);
                }
            }
            return maxPoints;
        }

        //remove filling of result array in main method if below one goes with result[day][lastTask] != 0
        if (result[day][lastTask] != -1) {
            return result[day][lastTask];
        }

        for (int task = 0; task < tasks; task++) {
            if (task != lastTask) {
                /*int point = points[day][task] + ninjaTraining(n, day + 1, tasks, task, points);
                maxPoints = Math.max(maxPoints, point);*/
                maxPoints = Math.max(maxPoints, points[day][task] + ninjaTraining(n, day + 1, tasks, task, points));
            }
        }

        return result[day][lastTask] = maxPoints;
    }


    //recursion topdown(take u forward)
    //tasks size is hardcoded as it is N*3
    public static int ninjaTraining(int day, int last, int[][] points) {

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
                int point = points[day][task] + ninjaTraining(day - 1, task, points);
                maxPoints = Math.max(point, maxPoints);
            }
        }

        return maxPoints;
    }

    //tabulation
    //tasks size is hardcoded as it is 3(N*3)
    public static int ninjaTraining(int[][] points) {
        int[][] dp = new int[points.length][3];

        dp[0][0] = Math.max(points[0][1], points[0][2]);
        dp[0][1] = Math.max(points[0][0], points[0][2]);
        dp[0][2] = Math.max(points[0][0], points[0][1]);

        for (int day = 1; day < points.length; day++) {
            for (int j = 0; j < 3; j++) { //dp table/matrix filling
                int maxPoints = 0;
                for (int task = 0; task < 3; task++) {
                    if (j != task) {
                        //should be referring the previous day task(not j) as it is j!=task
                        maxPoints = Math.max(maxPoints, points[day][task] + dp[day - 1][task]);
                        dp[day][j] = maxPoints;
                    }
                }
            }
        }

        return dp[points.length - 1][points[0].length - 1];
    }

    //tabulation with Space Optimization
    public static int ninjaTrainings(int[][] points) {
        int p1 = points[0][0];
        int p2 = points[0][1];
        int p3 = points[0][2];

        for (int day = 1; day < points.length; day++) {
            int index1 = points[day][0] + Math.max(p2, p3);
            int index2 = points[day][1] + Math.max(p1, p3);
            int index3 = points[day][2] + Math.max(p1, p2);

            p1 = index1;
            p2 = index2;
            p3 = index3;
        }

        return Math.max(p1, Math.max(p2, p3));
    }

    //TUF tabulation with Space Optimization (1D array)
    public static int ninjaTrainingWith1DArray(int[][] points) {

        int[] prev = new int[points.length]; //new int[points.length][3]

        prev[0] = Math.max(points[0][1], points[0][2]);
        prev[1] = Math.max(points[0][0], points[0][2]);
        prev[2] = Math.max(points[0][0], points[0][1]);

        for (int day = 1; day < points.length; day++) {
            int[] temp = new int[points.length];
            for (int last = 0; last < 3; last++) { // for (int last = 0; last < points[0].length; last++)
                temp[last] = 0;
                for (int task = 0; task < 3; task++) {
                    if (task != last) {
                        temp[last] = Math.max(temp[last], points[day][task] + prev[task]); //prev[task] is already max of prev day tasks
                    }
                }
            }
            prev = temp;
        }

        return prev[2];
    }

    public static void main(String[] args) {
        int[][] points = {{134, 28, 5}, {9, 87, 14}, {3, 138, 3}};

        int n = points.length, taskSize = points[0].length;
        int result[][] = new int[n][taskSize + 1];//4

        for (int i = 0; i < result.length; i++) {
            result[i][0] = -1;
            result[i][1] = -1;
            result[i][2] = -1;
            result[i][3] = -1;
        }

        System.out.println("Max Earned Points Using Recursion(Bottom Up) " + ninjaTraining(n, 0, taskSize, -1, points));
        System.out.println("Max Earned Points Using Recursion(Top down) " + ninjaTraining(n - 1, 3, points));
        System.out.println("Max Earned Points Using Memorization " + ninjaTraining(n, 0, taskSize, 3, points, result));

        System.out.println("Max Earned Points Using Tabulation(DP Table) " + ninjaTraining(points));
        System.out.println("Max Earned Points Using Tabulation(Space Optimization(Variables)) " + ninjaTrainings(points));
        System.out.println("Max Earned Points Using Tabulation(TUF Space Optimization(1D Matrix)) " + ninjaTrainingWith1DArray(points));
    }
}
