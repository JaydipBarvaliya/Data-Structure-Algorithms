package com.leetcode.year_2020.array;

/**
 * https://www.interviewbit.com/problems/minimum-lights-to-activate/
 */
public class MinimumLightsToActivate {

    public static void main(String[] args) {
        System.out.println(solve(new int[]{1, 1, 0, 0, 1, 1}, 1));
        System.out.println(solve(new int[]{0, 0, 1, 1, 1, 0, 0, 1}, 3));
        System.out.println(solve(new int[]{0, 0, 0, 1, 0}, 3));
        System.out.println(solve(new int[]{1, 1, 1, 1}, 3));
        System.out.println(solve(new int[]{0, 1, 1, 0, 1, 0, 1, 0, 1, 0, 0, 1, 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 1, 0, 0}, 12));
    }

    public static int solve(int[] A, int B) {
        int i = 0, totalBulb = 0;
        boolean foundBulb;
        while (i < A.length) {
            int rightBoundary = Math.min(i + B - 1, A.length - 1);
            int leftBoundary = Math.max(i - B + 1, 0);
            foundBulb = false;

            for (int k = rightBoundary; k >= leftBoundary; k--) {
                if (A[k] == 1) {
                    totalBulb++;
                    foundBulb = true;
                    i = k + B;
                    break;
                }
            }
            if (!foundBulb) {
                return -1;
            }
        }
        return totalBulb;
    }
}
