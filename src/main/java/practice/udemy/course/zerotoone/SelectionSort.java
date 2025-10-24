package practice.udemy.course.zerotoone;

import java.util.Arrays;

public class SelectionSort {

    public static void swap(int[] sortArray, int iIndex, int jIndex) {
        int temp = sortArray[iIndex];
        sortArray[iIndex] = sortArray[jIndex];
        sortArray[jIndex] = temp;
    }

    public static void selectionSort(int[] listToSort) {
        for (int i = 0; i < listToSort.length; i++) {
            for (int j = i + 1; j < listToSort.length; j++) {
                if (listToSort[i] > listToSort[j]) {
                    swap(listToSort, i, j);
                }
            }
            System.out.print("Iteration " + (i + 1) + " : ");
            print(listToSort);
        }
    }

    public static void print(int[] listToSort) {
        System.out.println(Arrays.toString(listToSort));
    }

    public static void main(String[] args) {
        int[] listToSort = new int[]{42, 5, 6, 1, 78, 3, 90, 32, 45};
        selectionSort(listToSort);
        // System.out.println(Arrays.toString(listToSort));
    }

}
