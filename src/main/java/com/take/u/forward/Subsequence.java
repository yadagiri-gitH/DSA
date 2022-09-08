package com.take.u.forward;

import java.util.ArrayList;
import java.util.List;

public class Subsequence {

    public static void findSequenceList(int index, int[] input, List<Integer> ds, List<List<Integer>> list) {

        if (index == input.length) {
            list.add(new ArrayList<>(ds));
            return;
        }

        ds.add(input[index]);
        findSequenceList(index + 1, input, ds, list);
        ds.remove(ds.size() - 1);
        findSequenceList(index + 1, input, ds, list);


    }

    public static void main(String[] args) {
        List<List<Integer>> list = new ArrayList<>();
        int[] input = {3,1,2};
        findSequenceList(0,  input, new ArrayList<>(), list);
        System.out.println(list);
    }
}
