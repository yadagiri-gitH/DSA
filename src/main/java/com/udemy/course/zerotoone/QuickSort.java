package com.udemy.course.zerotoone;

import java.util.Arrays;

public class QuickSort {

    public static int partition(int[] arr, int low, int high) {
        int pivot = arr[low];
        int l = low;
        int h = high;
        while (l < h) {
            while (arr[l] <= pivot && l < h) {
                l++;
            }
            while (arr[h] > pivot) {
                h--;
            }
            if (l < h) {
                swap(arr, l, h);
            }
        }
        swap(arr, low, h);
        System.out.println("Pivot Index: " + h + " & With Value :" + pivot);
        return h;
    }

    public static void swap(int[] arr, int s, int p) {
        int temp = arr[s];
        arr[s] = arr[p];
        arr[p] = temp;
    }

    public static void quicksort(int[] arr, int l, int h) {
        System.out.print("Total list with index range {"+l+","+h+"} is :");
        print(arr, l, h);

        if (l >= h) {
            return;
        }
        int pivotIndex = partition(arr, l, h);
        //System.out.print("1st partition list is :");
        //print(arr, 0, pivotIndex - 1);
        quicksort(arr, 0, pivotIndex - 1);
        //System.out.print("2nd partition list is :");
        //print(arr, pivotIndex + 1, h);
        quicksort(arr, pivotIndex + 1, h);
    }

    public static void print(int[] listToSort) {
        System.out.println(Arrays.toString(listToSort));
    }

    public static void print(int[] listToSort, int l, int h) {
        System.out.println(Arrays.toString(Arrays.copyOfRange(listToSort, l, h + 1)));
    }

    public static void main(String[] args) {
        int[] listToSort = new int[]{4, 5, 6, 2, 1, 7, 10, 3, 8, 9};
        quicksort(listToSort, 0, listToSort.length - 1);
        print(listToSort);
    }
}
