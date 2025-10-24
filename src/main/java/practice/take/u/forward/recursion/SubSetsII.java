package practice.take.u.forward.recursion;

import java.util.ArrayList;
import java.util.List;

public class SubSetsII {

    public static void findUniqueSubsets(int index, int[] nums, ArrayList<Integer> subset, List<List<Integer>> subsetList) {

        subsetList.add(new ArrayList<>(subset));

        for (int i = index; i < nums.length; i++) {
            if (index != i && nums[i] == nums[i - 1]) {
                continue;
            }
            subset.add(nums[i]);
            findUniqueSubsets(i + 1, nums, subset, subsetList);
            subset.remove(subset.size() - 1);
        }

    }

    public static void main(String[] args) {
        int[] nums = {1, 2, 2};
        List<List<Integer>> subsetList = new ArrayList<>();
        findUniqueSubsets(0, nums, new ArrayList<>(), subsetList);
        System.out.println(subsetList);
    }
}
