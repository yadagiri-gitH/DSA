package practice.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.List;

public class PermutationSequenceK {
    public static void findKthPermutation(int k, List<Integer> numsList, StringBuilder result) {
        if (numsList.size() == 0) {
            return;
        }
        int factorial = factorial(numsList.size() - 1);
        int selected = k / factorial;
        result.append(numsList.get(selected));
        numsList.remove(selected);
        findKthPermutation(k % factorial, numsList, result);
    }

    public static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;
        }
        return n * factorial(n - 1);
    }

    public static void main(String[] args) {
        int n = 4;
        int k = 17;

        List<Integer> numsList = new ArrayList<>();

        for (int i = 1; i <= n; i++) {
            numsList.add(i);
        }

        StringBuilder result = new StringBuilder();

        findKthPermutation(k - 1, numsList, result);

        System.out.println(k + "th permutation is " + result.toString());
    }

}
