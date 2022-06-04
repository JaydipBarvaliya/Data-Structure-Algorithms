package com.leetcode.array;

import com.util.LogUtil;

/**
 * The Dutch National Flag Problem (The Quicksort "Band-Aid")
 * <p>
 * Question: Write a program that takes an array A and an index i into A, and rearranges the elements such that
 * <p>
 * 1.) All elements less than A[i] (the "pivot") appear first
 * 2.) Followed by elements equal to the pivot
 * 3.) Followed by elements greater than the pivot
 * <p>
 * Example
 * A = [0, 1, 2, 0, 2, 1, 1] (same array)
 * Pivot Index = 2
 * Valid Output: [0, 1, 0, 1, 1, 2, 2]
 * Valid Output: [0, 0, 1, 1, 1, 2, 2]   (a more perfect output)
 *
 * @author neeraj on 2019-05-21
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DutchNationalFlag {

    public static void main(String[] args) {
//        int[] input = new int[]{0, 1, 2, 0, 2, 1, 1};
        int[] input = new int[]{0, 2, 1, 2, 0};

        solveDutchNationalFlag(input, 1);
    }

    public static void solveDutchNationalFlag(int[] arr, int pivot) {
        LogUtil.logIt("Rearranging array using Dutch National Flag");
        LogUtil.printArray(arr);
        int pivotValue = arr[pivot];

        int positionPointer = 0;
        // Forward Pass
        // Find all the elements less than the pivot value and then replace them with the positionPointer.
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < pivotValue) {
                swapIt(arr, i, positionPointer++);
            }
        }

        // Backward Pass
        // Find all the elements greater than the pivot value and then replace them with the positionPointer.
        positionPointer = arr.length - 1;
        for (int i = arr.length - 1; i >= 0 && arr[i] >= pivotValue; i--) {
            if (arr[i] > pivotValue) {
                swapIt(arr, i, positionPointer--);
            }
        }
        LogUtil.printArray(arr);
    }

    private static void swapIt(int[] arr, int i, int position) {
        int temp = arr[i];
        arr[i] = arr[position];
        arr[position] = temp;
    }
}
