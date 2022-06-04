package com.leetcode.year_2020.two_pass_algo;

/**
 * @author neeraj on 14/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestMountainInArray {

    public static void main(String[] args) {
        System.out.println(longestMountain(new int[]{2, 2, 2}));
        System.out.println(longestMountain(new int[]{0, 2, 2}));
        System.out.println(longestMountain(new int[]{2, 1, 4, 7, 3, 2, 5}));
        System.out.println(longestMountain(new int[]{875, 884, 239, 731, 723, 685}));
    }

    public static int longestMountain(int[] A) {
        int longestMountain = 0;

        // Since we have to verify upHill and then downHill
        // So start with index 1.
        int hillTrekker = 1;

        while (hillTrekker < A.length) {

            // If the height is same we just skip those mountain on same level.
            while (hillTrekker < A.length && A[hillTrekker] == A[hillTrekker - 1]) {
                hillTrekker++;
            }

            // Now check for upHill Count
            int upHill = 0;
            while (hillTrekker < A.length && A[hillTrekker] > A[hillTrekker - 1]) {
                hillTrekker++;
                upHill++;
            }

            int downHill = 0;
            // Now check for downHill Count
            while (hillTrekker < A.length && A[hillTrekker] < A[hillTrekker - 1]) {
                downHill++;
                hillTrekker++;
            }

            if (upHill > 0 && downHill > 0) {
                longestMountain = Math.max(longestMountain, upHill + downHill + 1);
            }
        }
        return longestMountain;
    }
}
