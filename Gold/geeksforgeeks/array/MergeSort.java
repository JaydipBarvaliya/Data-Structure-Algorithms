package com.geeksforgeeks.array;

import com.util.LogUtil;

public class MergeSort {
    public static void main(String[] args) {
//    int[] sample = {-12, 11, -13, -5, 6, -7, 5, -3, -6};
//    int[] sample = {2, 4, 1, 3, 5};
//    int[] sample = {1, 20, 6, 4, 5};
        int[] sample = new int[]{5, 4, 3, 2, 1};
        smaller = new int[sample.length];
        System.out.println("Inversion Count is " + mergeSort(sample, 0, sample.length - 1));
        System.out.println("After sorting is ");
        ArrayRotation.printArray(sample);
        LogUtil.printArray(smaller);

    }


    static int[] smaller;

    public static int mergeSort(int[] arr, int low, int high) {
        int inversionCount = 0;
        if (low < high) {
            int mid = low + (high - low) / 2;

            inversionCount = mergeSort(arr, low, mid);
            inversionCount += mergeSort(arr, mid + 1, high);
            inversionCount += merge1(arr, low, mid, high);
        }
        return inversionCount;
    }

    // My Own Implementation + to handle Inversion Count as well
    private static int merge1(int[] arr, int low, int mid, int high) {
        int inversionCount = 0;
        int t1 = low;
        int t2 = mid + 1;
        int N1 = mid;
        int N2 = high;
        int[] sortedArr = new int[(high - low) + 1];
        int sortedCounter = 0;
        while (t1 <= N1 && t2 <= N2) {
            if (arr[t1] <= arr[t2]) {
                sortedArr[sortedCounter++] = arr[t1++];
            } else {
                sortedArr[sortedCounter++] = arr[t2++];

                // if arr[t1] > arr[t2] then there at total of Middle - t1 inversions.
                // as till t1 to middle is already sorted, So if arr[t1] > arr[t2] i.e. arr[t+1.....mid] > arr[t2]
                // So those many inversions
                for (int i = t1; i <= mid; i++) {
                    smaller[i] += 1;
                }
                inversionCount += mid + 1 - t1;
            }
        }

        // Let's check whether either of t1 and t2 is still left
        while (t1 <= N1) {
            sortedArr[sortedCounter++] = arr[t1++];
        }

        while (t2 <= N2) {
            sortedArr[sortedCounter++] = arr[t2++];
        }

        // Let's replace sortedArray values into original array
        for (int i = 0; i < sortedArr.length; i++) {
            arr[low++] = sortedArr[i];
        }
        return inversionCount;
    }

    private static void merge(int[] arr, int low, int mid, int high) {
        int t1 = low;
        int t2 = mid + 1;
        int[] sortedArray = new int[arr.length];
        int counter = 0;

        while (t1 <= mid && t2 <= high) {
            if (arr[t1] < arr[t2]) {
                sortedArray[counter++] = arr[t1];
                t1++;
            } else if (arr[t2] < arr[t1]) {
                sortedArray[counter++] = arr[t2];
                t2++;
            }
        }

        while (t1 <= mid) {
            sortedArray[counter++] = arr[t1++];
        }

        while (t2 <= high) {
            sortedArray[counter++] = arr[t2++];
        }
        counter = 0;
        for (int i = low; i <= high; i++) {
            arr[i] = sortedArray[counter++];
        }
    }
}
