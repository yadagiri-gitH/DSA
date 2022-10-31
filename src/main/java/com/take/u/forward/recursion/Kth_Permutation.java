package com.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.List;

class Kth_Permutation {
    static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }

    static void find_kth_permutation(List<Integer> v, int k, StringBuilder result) {

        if (v.isEmpty()) {
            return;
        }

       // int n = v.size();
        // factorial is number of permutations starting with first digit
        int factorial = factorial(v.size() - 1);
        int selected = (k - 1) / factorial;

        result.append(v.get(selected));
        v.remove(selected);

        k = k - (factorial * selected);
        find_kth_permutation(v, k, result);
    }

    static String get_permutation(int n, int k) {
        List<Integer> v = new ArrayList<>();
        for (int i = 1; i <= n; ++i) {
            v.add(i);
        }

        StringBuilder result = new StringBuilder();
        find_kth_permutation(v, k, result);
        return result.toString();
    }

    public static void main(String[] args) {
       /* for (int i = 1; i <= factorial(4); ++i) {
            System.out.println(i + "th permutation = \t" + get_permutation(4, i));
        }*/
        System.out.println(17 + "th permutation = \t" + get_permutation(4, 17));
    }
}
