package practice.grokking.coding.interview.twopointer;

public class ShortestWindowSort {
    public static int sortWindow(int[] arr) {

        int low = 0, high = arr.length - 1;

        while (low < arr.length - 1 && arr[low] <= arr[low + 1])
            low++;

        if (low == arr.length - 1)
            return 0;

        while (high > 0 && arr[high] >= arr[high - 1])
            high--;

        int subMin = Integer.MAX_VALUE, subMax = Integer.MIN_VALUE;

        for (int k = low; k <= high; k++) {
            subMin = Math.min(arr[k], subMin);
            subMax = Math.max(arr[k], subMax);
        }

        while (low > 0 && arr[low - 1] > subMin)
            low--;

        while (high < arr.length - 1 && arr[high + 1] < subMax)
            high++;

        return high - low + 1;
    }

    public static void main(String[] args) {
        System.out.println(ShortestWindowSort.sortWindow(new int[]{1, 2, 5, 3, 7, 10, 9, 12}));
        System.out.println(ShortestWindowSort.sortWindow(new int[]{1, 3, 2, 0, -1, 7, 10}));
        System.out.println(ShortestWindowSort.sortWindow(new int[]{1, 2, 3}));
        System.out.println(ShortestWindowSort.sortWindow(new int[]{3, 2, 1}));
    }

}
