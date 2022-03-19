package com.udemy.course.zerotoone;

import java.util.Arrays;

public class BubbleSort {

    public static void swap(int[] listToSort, int iIndex, int jIndex) {
        int temp = listToSort[iIndex];
        listToSort[iIndex] = listToSort[jIndex];
        listToSort[jIndex] = temp;
    }

    public static void print(int[] listToSort) {
        System.out.println(Arrays.toString(listToSort));
    }

    public static void bubbleSort(int[] listToSort) {
        for (int i = 0; i < listToSort.length; i++) {
            boolean swapped = false;
            for (int j = listToSort.length - 1; j > i; j--) {
                if (listToSort[j] < listToSort[j - 1]) {
                    swap(listToSort, j, j - 1);
                    swapped = true;
                }
            }
            System.out.print("Iteration " + (i + 1) + " : ");
            print(listToSort);
            if (!swapped) {
                break;
            }

        }
    }

    public static void bubbleSortAlgo(int[] listToSort) {
        for (int i = 0; i < listToSort.length; i++) {
            boolean swapped = false;
            for (int j = 1; j < listToSort.length; j++) {
                if (listToSort[j - 1] > listToSort[j]) {
                    swap(listToSort, j - 1, j);
                    swapped = true;
                }
            }
            System.out.print("Iteration " + (i + 1) + " : ");
            print(listToSort);
            if (!swapped) {
                break;
            }

        }
    }

    public static void main(String[] args) {
        //int[] listToSort = new int[]{42, 5, 6, 1, 78, 3, 90, 32, 45};
        int[] listToSort = new int[]{4, 5, 6, 2, 1, 7, 10, 3, 8, 9};
        bubbleSort(listToSort);
        // System.out.println(Arrays.toString(listToSort));
        // int[] lstToSort = new int[]{42, 5, 6, 1, 78, 3, 90, 32, 45};
        int[] lstToSort = new int[]{4, 5, 6, 2, 1, 7, 10, 3, 8, 9};
        bubbleSortAlgo(lstToSort);
        // System.out.println(Arrays.toString(listToSort));
    }
}
