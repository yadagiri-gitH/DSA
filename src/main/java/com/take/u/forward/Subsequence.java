package com.take.u.forward;

import java.util.ArrayList;
import java.util.List;

public class Subsequence {

    public static void findSumCombinationList(int index, int target, int sum, int[] input, List<Integer> ds, List<List<Integer>> list) {

        if (sum > target) {
            return;
        }

        if (index == input.length) {
            if (sum == target) {
                list.add(new ArrayList<>(ds));
            }
            return;
        }

        ds.add(input[index]);
        sum += input[index];
        findSumCombinationList(index + 1, target, sum, input, ds, list);
        ds.remove(ds.size() - 1);
        sum -= input[index];
        findSumCombinationList(index + 1, target, sum, input, ds, list);

    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        int[] input = {3, 4, 6, 2, 5, 7, 8, 1};
        findSumCombinationList(0, 8, 0, input, new ArrayList<>(), list);
        System.out.println(list);
    }
}
