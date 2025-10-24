package practice.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.List;

public class PowerSet {

    public static void findSubSet(int[] input, List<List<Integer>> list) { //Check Subsequence class for recursive approach
        int n = input.length;
        int pset = 1 << n;

        for (int i = 0; i < pset; i++) {//0-7
            ArrayList<Integer> ds = new ArrayList<>();
            for (int j = 0; j < n; j++) {//0-2
                if ((i & (1 << j)) > 0) {//i & 2^j(i & 1,i & 2,i & 4)
                    ds.add(input[j]);
                }
            }
            list.add(new ArrayList<>(ds));
        }
    }

    public static void main(String[] args) {

        List<List<Integer>> list = new ArrayList<>();
        int[] input = {3, 2, 1};
        findSubSet(input, list);
        System.out.println(list);
    }
}
