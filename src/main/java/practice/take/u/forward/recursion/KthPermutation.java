package practice.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.List;

public class KthPermutation {
    static int factorial(int n) {
        if (n == 0 || n == 1) return 1;
        return n * factorial(n - 1);
    }
    public static String getPermutation(int n, int k) {
        List<Integer> nums = new ArrayList<>();
        String result = "";
        int fact = 1;
        for (int i = 1; i < n; i++) {
            fact = fact * i;
            nums.add(i);
        }
        nums.add(n);

        //we are using zero based indexing
        int indexK = k - 1;

        while (true) {
           /* result = result + nums.get(indexK / fact);
            nums.remove(indexK / fact);*/
            result = result + nums.remove(indexK / fact);
            if (nums.size() == 0) {
                break;
            }
            indexK = indexK % fact;
            //this is the right solution
            fact =factorial(nums.size()-1);
            // this is working only when n<=13
           // fact = fact / nums.size();
            //when fact becoming zero,its throwing the arithmetic exception due to line num 23
            // below one working correctly when k=1 but doesn't work for n=14,k=2
            //fact = (fact / nums.size()) == 0 ? 1 : fact / nums.size();
        }

        return result;
    }

    public static void main(String[] args) {
        int n = 14;
        int k = 2;
        String perm = getPermutation(n, k);
        System.out.println("permutation sequence " + k + "  in " + n + " numbers combination (start from 1 to " + n + ") : " + perm);
    }
}
