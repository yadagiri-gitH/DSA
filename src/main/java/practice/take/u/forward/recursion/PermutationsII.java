package practice.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.List;

public class PermutationsII {

    public static void findPermutations(int index, int[] nums, List<List<Integer>> permutationList) {
        if (index == nums.length) {
            List<Integer> permutation = new ArrayList<>();
            for (int i = 0; i < nums.length; i++) {
                permutation.add(nums[i]);
            }
            permutationList.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = index; i < nums.length; i++) { // for (int i = 0; i < nums.length; i++) {
            swap(i, index, nums);
            findPermutations(index + 1, nums, permutationList);
            swap(i, index, nums);
        }

    }

    public static void swap(int i, int j, int[] nums) {
        int num = nums[i];
        nums[i] = nums[j];
        nums[j] = num;
    }

    public static void main(String[] args) {
        List<List<Integer>> permutationList = new ArrayList<>();
        int[] nums = {1, 2, 3};
        findPermutations(0, nums, permutationList);
        System.out.println(permutationList);
    }
}
