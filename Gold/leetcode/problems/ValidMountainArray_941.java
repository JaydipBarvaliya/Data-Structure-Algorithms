package com.leetcode.problems;

import static com.util.LogUtil.getArrayAsString;
import static com.util.LogUtil.logIt;

/**
 * @author neeraj on 2019-08-04
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ValidMountainArray_941 {

    public static void main(String[] args) {
        isValidMountainArray(new int[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        isValidMountainArray(new int[]{1, 2});
        isValidMountainArray(new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9});
        isValidMountainArray(new int[]{1, 2, 3, 4, 50, 4, 3, 2, 1});
    }

    public static void isValidMountainArray(int[] arr) {
        logIt("Is Valid Mountain Array " + getArrayAsString(arr) + " ? " + validMountainArrayEasyApproach(arr));
    }

    /**
     * Using 2 Pointer Approach
     *
     * @param sequence
     * @return
     */
    public static Boolean validMountainArrayEasyApproach(int[] sequence) {
        if (sequence == null || sequence.length < 3) {
            return false;
        }
        int i = 0;

        // Step 1) Find Increasing Sequence
        while (i < sequence.length - 1 && sequence[i + 1] > sequence[i]) {
            i++;
        }

        // After Step 1) our i will be at the element before which all the elements are smaller.
        // But we need to handle edge case
        // what is Sequence is strictly increasing/decreasing

        if (i == 0 || i + 1 >= sequence.length) {
            return false;
        }

        // Find Strictly decreasing sequence
        while (i < sequence.length - 1 && sequence[i + 1] < sequence[i]) {
            i++;
        }

        // If by this time we didn't reach the end of array that means this is not a valid mountain.
        if (sequence.length - i <= 1) {
            return true;
        } else {
            return false;
        }
    }

    public static Boolean validMountainArray(int[] A) {
        if (A == null || A.length < 3) {
            return false;
        }
        Boolean uptrend = true;
        Boolean isPeakValueAvailable = false;
        int counter = 1;
        int previousVal = A[0];
        while (counter < A.length) {
            if (uptrend) {
                if (A[counter] > previousVal) {
                    isPeakValueAvailable = true;
                    previousVal = A[counter];
                    counter++;
                } else {
                    // If the currentValue is less than previousVal, but
                    // Since if it's strictly decreasing sequence, so there might no peakValue
                    // found till now which means [0,1,....i-1] < Peak(ith Index)
                    // Hence in that situation we just return false.
                    if (!isPeakValueAvailable) {
                        return false;
                    } else {
                        // We found a peak value, so it's time find the downtrend
                        uptrend = false;
                    }
                }
            } else { // Now values are decreasing after reaching the peak.
                if (A[counter] < previousVal) {
                    previousVal = A[counter];
                    counter++;
                } else {
                    return false;
                }
            }
        }

        // If after whole traversal, we are still in uptrend, that means
        // the array is in strictly increasing shape.
        if (uptrend) {
            return false;
        } else {
            return true;
        }
    }
}
