package practice.grokking.coding.interview.twopointer;

import java.util.ArrayList;
import java.util.List;

public class SubarrayProductLessThanK {

    public static List<List<Integer>> findSubarrays(int[] arr, int target) {
        if (arr == null || arr.length == 0)
            throw new IllegalArgumentException();

        int start = 0, product = 1;
        List<List<Integer>> subArrayList = new ArrayList<>();

        for (int i = 0; i < arr.length; i++) {
            product *= arr[i];
            while (product >= target && start <= i) {
                product /= arr[start++];
            }

            List<Integer> currentList = new ArrayList<>();
            for (int j = i; j >= start; j--) {
                currentList.add(0, arr[j]);
                subArrayList.add(new ArrayList<>(currentList));
            }
        }
        return subArrayList;

    }

    public static void main(String[] args) {
        System.out.println(SubarrayProductLessThanK.findSubarrays(new int[]{2, 5, 3, 10}, 30));
        System.out.println(SubarrayProductLessThanK.findSubarrays(new int[]{8, 2, 6, 5}, 50));
    }
}
