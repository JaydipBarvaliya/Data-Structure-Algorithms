package com.leetcode.array;

import static com.geeksforgeeks.array.QuickSort.swap;
import static com.util.LogUtil.logIt;
import static com.util.LogUtil.printArray;

/**
 * @author neeraj on 2019-07-17
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("Duplicates")
public class FindSmallestPositiveNumberMissingFromUnsortedArray {

    public static void main(String[] args) {
        /**
         * This is taking in assumption that all elements in the array are < size of array.
         * Traverse array from Left to Right
         * and ensure every positive value is at it's original index
         *
         * Assuming Array index start from 1 instead of 0
         * So for
         * ---------------
         * 3 | 4 | -1 | 1
         * ---------------
         * (1) (2) (3)  (4)
         * after ensuring the output should be
         * ---------------
         * 1 | -1 | 3 | 4
         * ---------------
         * (1) (2) (3)  (4)
         *
         * Now just traverse the array again and compare `index != arr[index]` that's our answer.
         *
         * Note: We can also handle the numbers greater than the size of array, by simply ignoring them for processing.
         *
         */
        FindSmallestPositiveMissingNumber(new int[]{3, 4, -1, 1});
        FindSmallestPositiveMissingNumber(new int[]{1, 2, 0});
        FindSmallestPositiveMissingNumber(new int[]{4, 4, -1, 4});

        // Testing Note
        // Note: We can also handle the numbers greater than the size of array, by simply ignoring them for processing.
        FindSmallestPositiveMissingNumber(new int[]{1, 3, 7, 6, 8, -1, -10, 15});
        FindSmallestPositiveMissingNumber(new int[]{7, 8, 9, 11, 12});
        FindSmallestPositiveMissingNumber(new int[]{1, 1, 1});
        FindSmallestPositiveMissingNumber(new int[]{-1, -1, -1});
        FindSmallestPositiveMissingNumber(new int[]{2, -1});
    }

    public static int FindSmallestPositiveMissingNumber(int[] arr) {
        logIt("Smallest Positive Missing Number in ", true);
        printArray(arr);

        for (int i = 0; i < arr.length; ) {
            // We have to take care of special case when we have duplicates
            // For Example with Input :  4	4	-1	4
            if (arr[i] - 1 < arr.length && arr[i] > 0 && arr[i] != i + 1 && arr[i] != arr[arr[i] - 1]) {
                swap(arr, i, arr[i] - 1);
            } else {
                i++;
            }
        }

        // Now let's traverse and find the answer
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != i + 1) {
                System.out.println("Answer ==>" + (i + 1));
                return i + 1;
            }
        }
        System.out.println("No missing number");
        return -1;
    }
}
