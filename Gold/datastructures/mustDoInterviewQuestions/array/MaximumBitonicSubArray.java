package com.datastructures.mustDoInterviewQuestions.array;

import java.util.Arrays;
import java.util.Collections;

public class MaximumBitonicSubArray {

    public static void main(String[] args) {
//        System.out.println(getMaximumLengthOfBitonicSubarray(new int[]{20, 4, 1, 2, 3, 4, 2, 10}));
        System.out.println(getMaxLengthOfBitonicSubarray(new int[]{12, 4, 78, 90, 45, 23}));
    }

    public static int getMaximumLengthOfBitonicSubarray(int[] arr) {

        // First find the element which is having i <= num >= i+1
        int bitonicElemIndex = 0;
        for (int i = 1; i < arr.length - 1; i++) {
            if (arr[i] > arr[i - 1] && arr[i] > arr[i + 1]) {
                bitonicElemIndex = i;
            }
        }
        int counter = 0;
        int leftSide = 0;
        for (int i = bitonicElemIndex - 1; i > 0; i--) {
            leftSide++;
            if (arr[i] < arr[i - 1]) {
                break;
            }

        }
        int rightSide = 0;
        for (int i = bitonicElemIndex + 1; i < arr.length; i++) {
            rightSide++;
            if (arr[i] < arr[i + 1]) {
                break;
            }
        }
        return leftSide + 1 + rightSide;
    }


    /**
     * Good Approach suggested on GeeksForGeeks
     *
     * @param arr
     */
    public static int getMaxLengthOfBitonicSubarray(int[] arr) {

        // Create Increasing array;
        int[] inc = new int[arr.length];

        // First one will always be smallest at least for itself
        inc[0] = 1;

        for (int i = 1; i < inc.length; i++) {
            inc[i] = arr[i] > arr[i - 1] ? inc[i - 1] + 1 : 1;
        }

        // Create Decreasing array
        int[] dec = new int[arr.length];

        // Last element will always be smallest at least for itself
        dec[arr.length - 1] = 1;

        for (int i = arr.length - 2; i >= 0; i--) {
            dec[i] = arr[i] > arr[i + 1] ? dec[i + 1] + 1 : 1;
        }

        return getMax(inc) + getMax(dec) - 1;
    }

    public static int getMax(int[] arr) {
        int MAX_VAL = Integer.MIN_VALUE;
        for (int i = 0; i < arr.length; i++) {
            if (MAX_VAL < arr[i]) {
                MAX_VAL = arr[i];
            }
        }
        return MAX_VAL;
    }
}
