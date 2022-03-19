package com.udemy.course.zerotoone;

import java.util.Arrays;

public class InsertionSort {
    public static void swap(int[] listToSort, int iIndex, int jIndex) {
        int temp = listToSort[iIndex];
        listToSort[iIndex] = listToSort[jIndex];
        listToSort[jIndex] = temp;
    }

    public static void print(int[] listToSort) {
        System.out.println(Arrays.toString(listToSort));
    }

    public static void insertionSort(int[] listToSort) {
        for (int i = 0; i < listToSort.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (listToSort[j] < listToSort[j - 1]) {
                    swap(listToSort, j, j - 1);
                } else {
                    break;
                }
            }
            System.out.print("Iteration " + (i + 1) + " : ");
            print(listToSort);
        }
    }

    public static void main(String[] args) {
        int[] listToSort = new int[]{4, 5, 6, 2, 1, 7, 10, 3, 8, 9};
        insertionSort(listToSort);
        // System.out.println(Arrays.toString(listToSort));
    }
}
