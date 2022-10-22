package com.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

   /* public static void listPermutations(int index, int[] nums, boolean[] isExist, List<Integer> permutation, List<List<Integer>> permutationList) {

        if (index == nums.length) {
            permutationList.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!isExist[i]) {
                isExist[i] = true;
                permutation.add(nums[i]);
                listPermutations(index + 1, nums, isExist, permutation, permutationList);
                isExist[i] = false;
                permutation.remove(permutation.size() - 1);
            }
        }

    }*/

    public static void listPermutations(int[] nums, boolean[] isExist, List<Integer> permutation, List<List<Integer>> permutationList) {

        if (permutation.size() == nums.length) {
            permutationList.add(new ArrayList<>(permutation));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (!isExist[i]) {
                isExist[i] = true;
                permutation.add(nums[i]);
                listPermutations(nums, isExist, permutation, permutationList);
                isExist[i] = false;
                permutation.remove(permutation.size() - 1);
            }
        }

    }


    public static void main(String[] args) {
        int[] nums = {0,1,2};
        List<List<Integer>> permutationList = new ArrayList<>();
        boolean[] isExist = new boolean[nums.length];
        // listPermutations(0,nums, isExist, new ArrayList<>(), permutationList);
        listPermutations(nums, isExist, new ArrayList<>(), permutationList);
        System.out.println(permutationList);
    }
}

