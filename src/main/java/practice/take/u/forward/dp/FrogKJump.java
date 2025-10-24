package practice.take.u.forward.dp;

import java.util.ArrayList;
import java.util.List;

public class FrogKJump {
    //recursion
    public static int frogKJumps(int ind, int k, int[] heights) {
        if (ind == 0) {
            return 0;
        }

        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= k; i++) {
            if (ind - i >= 0) {
                int energyLost = frogKJumps(ind - i, k, heights) + Math.abs(heights[ind] - heights[ind - i]);
                min = Math.min(energyLost, min);
            }
        }
        return min;
    }

    //memorization
    public static int frogKJumps(int ind, int k, int[] heights, int[] energy) {
        if (ind == 0) {
            return 0;
        }

        if (energy[ind] != -1) {
            return energy[ind];
        }

        int min = Integer.MAX_VALUE;

        for (int i = 1; i <= k; i++) {
            if (ind - i >= 0) {
                int e = frogKJumps(ind - i, k, heights) + Math.abs(heights[ind] - heights[ind - i]);
                min = Math.min(e, min);
            }
        }
        return energy[ind] = min;
    }

    //tabulation
    public static int frogKJumpsWithTabulation(int n, int k, int[] heights, int[] energy) {
        energy[0] = 0;

        for (int i = 1; i < n; i++) {
            int min = Integer.MAX_VALUE;//it should be inside loop
            for (int j = 1; j <= k; j++) {
                if (i - j >= 0) {
                    int e = energy[i - j] + Math.abs(heights[i] - heights[i - j]);
                    min = Math.min(e, min);
                }
            }
            energy[i] = min;
        }
        return energy[n - 1];
    }

    //tabulation optimization ,no difference from above one if k=n
    public static int frogKJumpsWithTabulation(int n, int k, int[] heights, List<Integer> lastKValues) {
        if (k > heights.length) {
            return 0;
        }

        for (int l = 0; l <= k; l++) {
            lastKValues.add(0);
        }

        for (int i = 1; i < n; i++) {
            int minAns = Integer.MAX_VALUE;
            for (int j = 1; j <= k && i >= j; j++) {
                //if (i - j >= 0) {//this covered as i>=j
                int ans = lastKValues.get(j) + Math.abs(heights[i] - heights[i - j]);
                minAns = Math.min(ans, minAns);
                //  }
            }
            if (i <= k) {
                lastKValues.set(i, minAns);
            } else {
                lastKValues.add(minAns);
                lastKValues.remove(1);//need to remove 1st index as we are ignoring the 0th index as l <=k
            }
        }

        return lastKValues.get(k);
    }

    public static void main(String[] args) {
        int n = 4;
        int[] heights = {10, 20, 30, 10};
        int[] energy = new int[n];
        for (int i = 0; i < energy.length; i++) {
            energy[i] = -1;
        }

        int jumps = 2;

        if (n > 0) {
            System.out.println("Minimum Height/Energy Lost that Frog takes " + frogKJumps(n - 1, jumps, heights) + " in " + n + " Steps from 0 to " + jumps + " jumps");
            System.out.println("Minimum Height/Energy Lost that Frog takes " + frogKJumps(n - 1, 2, heights, energy) + " in " + n + " Steps from 0 to " + jumps + " jumps");
            System.out.println("Minimum Height/Energy Lost that Frog takes " + frogKJumpsWithTabulation(n, jumps, heights, energy) + " in " + n + " Steps from 0 to " + jumps + " jumps");
            System.out.println("Minimum Height/Energy Lost that Frog takes " + frogKJumpsWithTabulation(n - 1, jumps, heights, new ArrayList<>()) + " in " + n + " Steps from 0 to " + jumps + " jumps");
        }

    }
}
