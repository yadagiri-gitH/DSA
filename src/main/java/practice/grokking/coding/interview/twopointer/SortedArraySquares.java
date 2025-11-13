package practice.grokking.coding.interview.twopointer;

class SortedArraySquares {

    public static int[] makeSquares(int[] arr) {
        int[] squares = new int[arr.length];
        int left = 0;
        int right = arr.length - 1;
        int highestSquareIndex = arr.length - 1;
        //If you use while (left < right),You’ll skip the middle element in cases of odd-length arrays (e.g., [-2, 0, 3]).So the final array will miss one value.
        while (left <= right) {
            int leftNum = arr[left] * arr[left];
            int rightNum = arr[right] * arr[right];
            if (leftNum < rightNum) {
                squares[highestSquareIndex--] = rightNum;
                right--;
            } else {
                squares[highestSquareIndex--] = leftNum;
                left++;
            }
        }
        return squares;
    }

    public static void main(String[] args) {

        int[] result = SortedArraySquares.makeSquares(new int[]{-2, -1, 0, 2, 3});
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();

        result = SortedArraySquares.makeSquares(new int[]{-3, -1, 0, 1, 2});
        for (int num : result)
            System.out.print(num + " ");
        System.out.println();
    }
}

