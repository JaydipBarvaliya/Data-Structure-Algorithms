package com.leetcode.year_2020.array;

/**
 * https://www.interviewbit.com/problems/perfect-peak-of-array/
 */
public class PerfectPeak {

    public static void main(String[] args) {
        System.out.println(perfectPeak(new int[]{5706, 26963, 24465, 29359, 16828, 26501, 28146, 18468, 9962, 2996, 492, 11479, 23282, 19170, 15725, 6335}));
    }

    public static int perfectPeak(int[] A) {
        int[] leftMax = new int[A.length];
        int[] rightMin = new int[A.length];
        leftMax[0] = A[0];
        rightMin[A.length - 1] = A[A.length - 1];

        for (int i = 1; i < A.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], A[i]);
        }
        for (int i = A.length - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], A[i]);
        }

        for (int i = 1; i < A.length - 1; i++) {
            if ((leftMax[i - 1] < A[i]) && (A[i] < rightMin[i + 1])) {
                return 1;
            }
        }
        return 0;
    }
}
