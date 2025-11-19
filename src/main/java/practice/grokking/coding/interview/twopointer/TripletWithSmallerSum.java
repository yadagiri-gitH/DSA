package practice.grokking.coding.interview.twopointer;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TripletWithSmallerSum {
    // public static int searchTriplets(int[] arr, int target) {
    public static List<List<Integer>> searchTriplets(int[] arr, int target) {
        Arrays.sort(arr);
        int count = 0;
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            int left = i + 1, right = arr.length - 1;
            while (left < right) {
                if (arr[i] + arr[left] + arr[right] < target) {
                    for (int j = right; j > left; j--) {
                        //for (int j = left; j <= right; j++) {
                        //triplets.add(Arrays.asList(arr[i], arr[j], arr[right]));
                        triplets.add(Arrays.asList(arr[i], arr[left], arr[j]));
                    }
                    count += right - left;
                    left++;
                } else {
                    right--;
                }
            }
        }
        // return count;
        return triplets;
    }

    public static List<List<Integer>> searchTripletsList(int[] arr, int target) {
        Arrays.sort(arr);
        List<List<Integer>> triplets = new ArrayList<>();
        for (int i = 0; i < arr.length - 2; i++) {
            searchPair(arr, target - arr[i], i, triplets);
        }
        return triplets;
    }

    private static void searchPair(int[] arr, int targetSum, int first, List<List<Integer>> triplets) {
        int left = first + 1, right = arr.length - 1;
        while (left < right) {
            if (arr[left] + arr[right] < targetSum) { // found the triplet
                // since arr[right] >= arr[left], therefore, we can replace arr[right] by any number between
                // left and right to get a sum less than the target sum
                for (int i = right; i > left; i--)
                    triplets.add(Arrays.asList(arr[first], arr[left], arr[i]));
                left++;
            } else {
                right--; // we need a pair with a smaller sum
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(TripletWithSmallerSum.searchTriplets(new int[]{-1, 0, 2, 3}, 3));
        System.out.println(TripletWithSmallerSum.searchTriplets(new int[]{-1, 4, 2, 1, 3}, 5));
        System.out.println(TripletWithSmallerSum.searchTripletsList(new int[]{-1, 0, 2, 3}, 3));
        System.out.println(TripletWithSmallerSum.searchTripletsList(new int[]{-1, 4, 2, 1, 3}, 5));
    }
}
