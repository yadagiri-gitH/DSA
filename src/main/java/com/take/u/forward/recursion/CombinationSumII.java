package com.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationSumII {

    public static void findSumCombinationSet(int index, int target, int[] input, List<Integer> ds, List<List<Integer>> list) {
        if (target == 0) {
            list.add(new ArrayList<>(ds));
            return;
        }

        for (int i = index; i < input.length; i++) {
            if (i != index && input[i] == input[i - 1]) // if (i > index && input[i] == input[i - 1])
                continue;

            if (input[i] > target) {
                break;
            }

            ds.add(input[i]);
            findSumCombinationSet(i + 1, target - input[i], input, ds, list);
            ds.remove(ds.size() - 1);
        }

    }

    public static void main(String[] args) {
        int[] input = {1, 1, 1, 2, 2};
        Arrays.sort(input);
        List<List<Integer>> set = new ArrayList<>();
        findSumCombinationSet(0, 4, input, new ArrayList<>(), set);
        System.out.println(set);
    }
}
