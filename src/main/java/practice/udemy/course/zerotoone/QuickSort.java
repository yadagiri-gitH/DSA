package practice.udemy.course.zerotoone;

import java.util.Arrays;

public class QuickSort {

    public static int partition(int[] arr, int low, int high) {
       // print(arr, low, high);
        int pivot = arr[low];
        int l = low;
        int h = high;
        while (l < h) {
            while (arr[l] <= pivot && l < h) {
                l++;
               // System.out.println("now lower index is " + l);
            }
            while (arr[h] > pivot) {
                h--;
               // System.out.println("now higher index is " + h);
            }
            if (l < h) {
                //System.out.println("swapping elements index are {" + l + "," + h + "} and list after swapping ");
                swap(arr, l, h);
               // print(arr);
            } else {
               // System.out.println("no swapping elements and coming out of the loop and now complete list is arranged as");
              //  print(arr);
            }
        }

        //System.out.println("finally pivot swapping indexes are {" + low + "," + h + "} and list after swapping ");
        swap(arr, low, h);
        //print(arr);
        return h;
    }

    public static void swap(int[] arr, int s, int p) {
        int temp = arr[s];
        arr[s] = arr[p];
        arr[p] = temp;
    }

    public static void quicksort(int[] arr, int l, int h) {
        System.out.println("recursive quick sort index range {" + l + "," + h + "}");

        if (l >= h) {
            return;
        }
        int pivotIndex = partition(arr, l, h);
        //System.out.println("return pivot index is " + pivotIndex);
        quicksort(arr, 0, pivotIndex - 1);
        quicksort(arr, pivotIndex + 1, h);
    }

    public static void print(int[] listToSort) {
        System.out.println(Arrays.toString(listToSort));
    }

    public static void print(int[] listToSort, int l, int h) {
        System.out.println(Arrays.toString(Arrays.copyOfRange(listToSort, l, h + 1)));
    }

    public static void main(String[] args) {
        //int[] listToSort = new int[]{4, 5, 6, 2, 1, 7, 10, 3, 8, 9};
        int[] listToSort = new int[]{8, 6, 4, 2};
        quicksort(listToSort, 0, listToSort.length - 1);
        print(listToSort);
    }
}
