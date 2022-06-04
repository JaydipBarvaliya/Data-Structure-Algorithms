package com.company.amazon;

import com.geeksforgeeks.array.ArrayRotation;
import com.geeksforgeeks.array.QuickSort;

public class SortZeroOneAndTwo {

    public static void main(String[] args) {
        sortZeroOneAndTwo(new int[] {0, 1, 2, 0, 1, 2});
        sortZeroOneAndTwo(new int[] {0, 1, 1, 0, 1, 2, 1, 2, 0, 0, 0, 1});
    }

    public static void sortZeroOneAndTwo(int[] arr) {
        int low = 0;
        int mid = 0;
        int high = arr.length - 1;

        while (mid <= high) {
            switch (arr[mid]) {
                case 0: {
                    QuickSort.swap(arr, low, mid);
                    low++;
                    mid++;
                    break;
                }
                case 1: {
                    mid++;
                    break;
                }
                case 2: {
                    QuickSort.swap(arr, mid, high);
                    high--;
                    break;
                }

            }
        }
        ArrayRotation.printArray(arr);
    }
}
