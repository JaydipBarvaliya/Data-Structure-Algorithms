package com.geeksforgeeks.array;

import static com.util.LogUtil.logIt;
import static com.util.LogUtil.printArray;

/**
 * https://www.youtube.com/watch?v=MHNTl_NvOj0&list=PLamzFoFxwoNjPfxzaWqs7cZGsPYy0x_gI&index=19
 * <p>
 * Given two sorted arrays a and b each of size n, find the median of the array obtained by merging these two arrays.
 * <p>
 * Example –
 * <p>
 * a = 1, 3, 5, 11, 17
 * b = 9, 10, 11, 13, 14
 * <p>
 * Sorted(a+b) = 1, 3, 5, 9, 10, 11, 11, 13, 14, 17
 * <p>
 * Median = (10 + 11)/2
 * = 10.5
 *
 * @author neeraj on 2019-07-20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Median__Of_Two_Sorted_Arrays_With_Explanation {

    public static void main(String[] args) {
        findMedian(new int[]{1, 3, 5, 11, 17}, new int[]{9, 10, 11, 13, 14});
        findMedian(new int[]{1, 3, 5, 7, 9}, new int[]{2, 4, 6, 8, 10});
    }

    public static void findMedian(int[] arrA, int[] arrB) {
        logIt("Finding Median of 2 sorted Arrays", true);
        printArray(arrA);
        printArray(arrB);
        logIt("Median is ==> " + findMedian(arrA, arrB, 0, arrA.length - 1, 0, arrB.length - 1));
    }

    public static double findMedian(int[] arrA, int[] arrB, int startA, int endA, int startB, int endB) {

        // If N == 2 // Total elements are 4 (2 in arrA, 2 in arrB)
        if ((endA - startA == 1) && (endB - startB == 1)) {
            /**
             * A[] = a1, a2
             * B[] = b1, b2
             *
             * Sorted(A,B) = Math.min(a1,b1), ?, ?, Math.max(a2,b2}
             *
             * Let's replace "?"
             *
             * Sorted(A,B) = Math.min(a1,b1), {Math.max(a1,b1), Math.min(a2,b2),} Math.max(a2,b2)
             */
            return (Math.max(arrA[startA], arrB[startB]) + Math.min(arrA[endA], arrB[endB])) / 2.0d;
        }

        // If N > 2
        int Median_A_Index = (endA + startA) / 2;
        int Median_B_Index = (endB + startB) / 2;

        int Median_A = arrA[Median_A_Index];
        int Median_B = arrB[Median_B_Index];

        /**
         * Now 3 cases
         * 1) Median_1 == Median_2
         * 2) Median_1 < Median_2
         * 3) Median_1 > Median_2
         */
        if (Median_A == Median_B) {
            return Median_A; // We can return Median_2 as well, no matter since both are same
        } else if (Median_A < Median_B) {
            return findMedian(arrA, arrB, Median_A_Index, endA, startB, Median_B_Index);
        } else // Median B < Median A
            return findMedian(arrA, arrB, startA, Median_A_Index, Median_B_Index, endB);
    }
}
