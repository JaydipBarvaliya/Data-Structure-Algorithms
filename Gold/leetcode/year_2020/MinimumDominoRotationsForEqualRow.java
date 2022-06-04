package com.leetcode.year_2020;

/**
 * https://leetcode.com/problems/minimum-domino-rotations-for-equal-row/
 *
 * @author neeraj on 25/03/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumDominoRotationsForEqualRow {

    public static void main(String[] args) {
        System.out.println(minDominoRotations(new int[]{2, 1, 2, 4, 2, 2}, new int[]{5, 2, 6, 2, 3, 2}));
        System.out.println(minDominoRotations(new int[]{3, 5, 1, 3, 3}, new int[]{3, 3, 3, 2, 4}));
        System.out.println(minDominoRotations(new int[]{1, 2, 1, 1, 1, 2, 2, 2}, new int[]{2, 1, 2, 2, 2, 2, 2, 2}));
    }

    public static int minDominoRotations(int[] A, int[] B) {
        // So In Reality there are only 2 values which matters, i.e the first dominos face value
        // So for Example
        /**
         * A = [2,1,2,4,2,2], B = [5,2,6,2,3,2]
         *
         * the value which matters is 2 and 5 since, if you cannot make 2 or 5 in the whole row there
         * is no way we can achieve the complete same structure ==> THIS IS FOR IF WE WANT TO MAKE [A] THE SAME
         *
         * ===> PROCESS HAS TO BE REPEATED FOR [B] AS WELL
         *
         */
        // let's just check for [A] first.
        int minRotation = Math.min(minSwaps(A[0], A, B), minSwaps(B[0], A, B));

        // Now this is when we want to make [B] all same
        minRotation = Math.min(minSwaps(A[0], B, A), minRotation);
        minRotation = Math.min(minSwaps(B[0], B, A), minRotation);

        return minRotation == Integer.MAX_VALUE ? -1 : minRotation;
    }

    private static int minSwaps(int target, int[] a, int[] b) {
        int minRotation = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[i] != target && b[i] != target) {
                return Integer.MAX_VALUE;
            } else if (a[i] != target) {
                minRotation++;
            }
        }
        return minRotation;
    }

}
