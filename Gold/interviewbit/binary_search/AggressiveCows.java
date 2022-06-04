package com.interviewbit.binary_search;

import java.util.Arrays;

import static com.util.LogUtil.getArrayAsString;
import static com.util.LogUtil.logIt;

/**
 * @author neeraj on 2019-08-03
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AggressiveCows {

    public static void main(String[] args) {
        findMinimumLargestDistance(new int[]{1, 2, 4, 8, 9}, 3);
        findMinimumLargestDistance(new int[]{5, 17, 100, 11}, 2);
        findMinimumLargestDistance(new int[]{82, 61, 38, 88, 7, 6, 12, 48, 8, 31, 90, 35, 5, 2, 66, 19, 96, 84, 95}, 8);
        findMinimumLargestDistance(new int[]{49, 35, 66, 23, 26, 22, 62, 21, 40, 60, 93, 27, 70, 15, 71, 29, 36, 4, 44, 43, 85, 9, 21, 9, 5, 21, 56, 81, 94, 55}, 9);

    }

    static int largestMinimumDistance = 0;

    public static void findMinimumLargestDistance(int[] stalls, int cows) {
        Arrays.sort(stalls);
        largestMinimumDistance = 0;

        // Why are we taking high as maxData instead of maxPosition
        // Because we want to include the stalls which might be very larger than the available
        // indexes, but might contribute to the larger distance
        // For Example : [5,11,17,100,] is 95
        // Here is we take just index we will pass 3 and the max Distance you will be getting is 3
        // but the actual distance is 95 [1st Cow at 5 and 2nd Cow at 100]
        findMinimumLargestDistance(stalls,
                0,
                stalls[stalls.length - 1] - stalls[0],
                cows);
        logIt("Finding Minimum Largest Distance for [" + cows + "] Cows arranged in " + getArrayAsString(stalls) + " is " + largestMinimumDistance);
    }

    private static int findMinimumLargestDistance(int[] stalls, int low, int high, int cows) {
        while (low <= high) {
            int mid = (low) + (high - low) / 2;

            if (isPlacementPossibleAtThisDistance(stalls, mid, cows)) {
                largestMinimumDistance = Math.max(largestMinimumDistance, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return -1;
    }

    private static boolean isPlacementPossibleAtThisDistance(int[] stalls, int minimumDistance, int cows) {
        int previousStall = stalls[0];
        cows--;
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - previousStall >= minimumDistance) {
                previousStall = stalls[i];
                cows--;

                if (cows == 0) {
                    return true;
                }
            }
        }
        return false;
    }
}
