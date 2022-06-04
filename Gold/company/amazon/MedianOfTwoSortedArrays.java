package com.company.amazon;

import java.util.Arrays;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class MedianOfTwoSortedArrays {

    public static void main(String[] args) {
        System.out.println(getMedian(new int[]{1, 12, 15, 26, 38}, new int[]{2, 13, 17, 30, 45}));
    }

    public static int getMedian(int[] arr1, int[] arr2) {
        int n = arr1.length;
        if (n == 0)
            return -1;
        if (n == 1)
            return (arr1[0] + arr2[0]) / 2;
        if (n == 2)
            return (max(arr1[0], arr2[0]) + min(arr1[0], arr2[0])) / 2;

        int m1 = arr1.length / 2;
        int m2 = arr2.length / 2;

        if (arr1[m1] > arr2[m2]) {
            return getMedian(Arrays.copyOfRange(arr1, 0, m1 + 1), Arrays.copyOfRange(arr2, m2 + 1, arr2.length));
        } else {
            return getMedian(Arrays.copyOfRange(arr1, m2, arr1.length), Arrays.copyOfRange(arr2, 0, m2 + 1));
        }
    }

    public static int median(int[] arr, int n) {
        if (n % 2 == 0) { // Even
            return (arr[n / 2] + arr[n / 2 - 1]) / 2; // Average
        } else { // Odd
            return arr[n / 2];
        }
    }
}
