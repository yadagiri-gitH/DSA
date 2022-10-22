package com.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.List;

public class CombinationSum {

    public static void findSumCombinationList(int index, int target, int[] input, List<Integer> ds, List<List<Integer>> list) {

        if (target < 0) {
            return;
        }

        if (index == input.length) {
            if (target == 0) {
                list.add(new ArrayList<>(ds));
            }
            return;
        }

        if (input[index] <= target) {
            ds.add(input[index]);
            findSumCombinationList(index, target - input[index], input, ds, list);
            ds.remove(ds.size() - 1);
        }

        findSumCombinationList(index + 1, target, input, ds, list);
    }


    public static void main(String[] args) {
        int[] input = {4, 5, 6, 7, 1, 2};
        List<List<Integer>> list = new ArrayList<>();
        findSumCombinationList(0, 7, input, new ArrayList<>(), list);
        System.out.println(list);
    }
}
