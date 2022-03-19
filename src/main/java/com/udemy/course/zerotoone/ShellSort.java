package com.udemy.course.zerotoone;

import java.util.Arrays;

public class ShellSort {
    public static void swap(int[] listToSort, int iIndex, int jIndex) {
        int temp = listToSort[iIndex];
        listToSort[iIndex] = listToSort[jIndex];
        listToSort[jIndex] = temp;
    }

    public static void print(int[] listToSort) {
        System.out.println(Arrays.toString(listToSort));
    }

    public static void insertionSort(int[] listToSort, int startIndex, int increment) {

        //System.out.println("increment : " + increment);
        for (int i = startIndex; i < listToSort.length; i = i + increment) {
            for (int j = Math.min(i + increment, listToSort.length - 1); j - increment >= 0; j = j - increment) {
                // System.out.println(listToSort[j] + "," + listToSort[j - increment]);
                if (listToSort[j] < listToSort[j - increment]) {// if (listToSort[j] > listToSort[j - increment]) --Descending Order
                    swap(listToSort, j, j - increment);
                } else {
                    break;
                }
            }
            // System.out.print("Iteration " + (i + 1) + " : ");
            //print(listToSort);
        }


    }

    public static void shellSort(int[] listToSort) {
        int increment = listToSort.length / 2;

        while (increment >= 1) {
            for (int startIndex = 0; startIndex < increment; startIndex++) {
                //System.out.print("Iteration " + (startIndex + 1) + " with Increment " + increment + " :");
                // print(listToSort);
                insertionSort(listToSort, startIndex, increment);
            }
            increment = increment / 2;
        }
    }

    public static void main(String[] args) {
        int[] listToSort = new int[]{4, 5, 6, 2, 1, 7, 10, 3, 8, 9};
        shellSort(listToSort);
        System.out.println(Arrays.toString(listToSort));
    }

}
