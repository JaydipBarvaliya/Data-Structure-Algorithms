package com.leetcode.year_2020.array;

import com.util.LogUtil;

/**
 * @author neeraj on 29/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RearrangeArrayInAlternatingPositiveAndNegativeItems {

    public static void main(String[] args) {
        rearrange(new int[]{1, 2, 3, -4, -1, 4});
        rearrange(new int[]{-5, -2, 5, 2, 4, 7, 1, 8, 0, -8});
    }

    /**
     * We have to rearrange the array in negative first then positive and maintain the order as well.
     * Also Do it in O(1) space.
     */
    public static void rearrange(int[] arr) {
        /**
         * As per the question we need to put negative first and then positive elements.
         * So Negative Elements will come on Even Index and Positive Elements will come on Odd index.
         * Wherever we see this property is not being followed there, from that point find the next element with opposite
         * sign (-ve to +ve and vice-versa) and right-shift that block by 1.
         */
        boolean isMismatch = false;
        for (int i = 0; i < arr.length; i++) {
            isMismatch = i % 2 == 0 && arr[i] > 0 || i % 2 != 0 && arr[i] < 0; // Positive and Negative at even and Odd index

            if (isMismatch) {
                int nextIndex = i + 1;
                while (nextIndex < arr.length) {
                    if (arr[i] < 0) { // if ith position was negative
                        if (arr[nextIndex] > 0) { // we need to find positive
                            break;
                        }
                    } else { // If ith position was positive
                        if (arr[nextIndex] < 0) { // we need to find negative.
                            break;
                        }
                    }
                    nextIndex++;
                }

                if (nextIndex >= arr.length) break;
                rightRotate(arr, i, nextIndex);
            }
        }
        LogUtil.logIt("After Rearranging....");
        LogUtil.printArray(arr);
    }

    private static void rightRotate(int[] arr, int i, int nextIndex) {
        int itemAtNextIndex = arr[nextIndex];
        for (int k = nextIndex - 1; k >= i; k--) {
            arr[k + 1] = arr[k];
        }
        arr[i] = itemAtNextIndex;
    }
}
