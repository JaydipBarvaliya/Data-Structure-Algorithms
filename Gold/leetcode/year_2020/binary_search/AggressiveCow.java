package com.leetcode.year_2020.binary_search;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * @author neeraj on 24/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AggressiveCow {

    public static void main(String[] args) {
        maximizeTheMinimumDistance(new int[]{1, 2, 4, 8, 9}, 3);
    }

    private static int maximizeTheMinimumDistance(int[] stalls, int cows) {
        /**
         * This problem is exactly opposite of {@link AllocateMinimumNumberOfPages}
         * There it was asking to minimize the maximum number of pages a student will read.
         * Here we have to maximize the minimum distance we can place the cows
         */
        Arrays.sort(stalls);
        int start = stalls[0];
        int end = stalls[stalls.length - 1];
        while (start < end) {
            int mid = start + (end - start) / 2;

            if (canPlaceAllCowsWithThisDistanceInBetweenStalls(mid, stalls, cows)) {
                // If that's the case we need to move more on right to check if we
                // can increase that distance more
                start = mid + 1;
            } else {
                // Okay so if i can't place in mid then all mid+1 distance are useless for me
                // so let's skip them
                end = mid - 1;
            }
        }
        LogUtil.logIt("Maximum Distance where cow can stand .." + (start - 1));
        return start;
    }

    private static boolean canPlaceAllCowsWithThisDistanceInBetweenStalls(int minDistance, int[] stalls, int cows) {
        int cowsPlaced = 1; // We can always place 1st cow on 1st stall
        int prevCow = stalls[0];
        for (int i = 1; i < stalls.length; i++) {
            if (stalls[i] - prevCow >= minDistance) {
                cowsPlaced++;

                if (cowsPlaced == cows) {
                    return true;
                }
                prevCow = stalls[i];
            }
        }
        return false;
    }
}
