package com.leetcode.year_2020.array;

import com.util.LogUtil;

/**
 * @author neeraj on 23/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CountInversions {

    public static void main(String[] args) {
        int []arr = new int[]{5,4,3,2,1};
        System.out.println(mergeSortAndCountInversion(arr, 0, 4));
        LogUtil.printArray(arr);
    }

    public static int mergeSortAndCountInversion(int[] arr, int low, int high) {
        int inversionCount = 0;
        if (low < high) {
            int mid = low + (high - low) / 2;
            inversionCount += mergeSortAndCountInversion(arr, low, mid);
            inversionCount += mergeSortAndCountInversion(arr, mid + 1, high);
            inversionCount += merge(arr, low, mid, high);
        }
        return inversionCount;
    }

    private static int merge(int[] arr, int low, int mid, int high) {
        int inversionCount = 0;
        int t1 = low;
        int t2 = mid + 1;
        int M = mid;
        int N = high;
        int[] temp = new int[high - low + 1]; // we are only merging items between low and high

        int counter = 0;
        while (t1 <= M && t2 <= N) {
            if (arr[t1] < arr[t2]) {
                temp[counter++] = arr[t1++];
            } else {
                temp[counter++] = arr[t2++];

                // We are in merge sort
                // So we have 2 sorted arrays.
                // arr[low].......arr[mid] and arr[mid].......arr[high]
                // Now this both are sorted in Ascending order
                // So if arr[t1] > arr[t2]...... in low ------t1--------mid
                // all the numbers from t1+1 ........mid will also be greater than arr[t2]
                // so we need those many inversions.
                inversionCount += mid + 1 - t1;
            }
        }

        while (t1 <= M) {
            temp[counter++] = arr[t1++];
        }

        while (t2 <= N) {
            temp[counter++] = arr[t2++];
        }

        // Copy sorted to original
        for (int i : temp) {
            arr[low++] = i;
        }
        return inversionCount;
    }
}
