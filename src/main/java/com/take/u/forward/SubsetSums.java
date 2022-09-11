package com.take.u.forward;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SubsetSums {

    public static void findSubsetSums(int index, int sum, int[] nums, List<Integer> sumsList) {

        if (index == nums.length) {
            sumsList.add(sum);
            return;
        }

        //pick the element for subset
        findSubsetSums(index + 1, sum + nums[index], nums, sumsList);
        //not pick elements for subset
        findSubsetSums(index + 1, sum, nums, sumsList);

    }

    public static void main(String[] args) {
        int[] nums = {3, 2, 1};
        List<Integer> sumsList = new ArrayList<>();
        findSubsetSums(0, 0, nums, sumsList);
        Collections.sort(sumsList);
        System.out.println(sumsList);
    }
}
