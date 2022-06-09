package com.algoexpert;

import java.util.ArrayList;
import java.util.List;

/**
 * @author neeraj on 04/08/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CountOfSmallerNumbersAfterSelf {

    public static void main(String[] args) {
        System.out.println(countSmaller(new int[]{3, 4, 2, 1, 1, 2, 5, 3}));
        System.out.println(countSmaller(new int[]{5, 2, 6, 1}));
        System.out.println(countSmaller(new int[]{1, 2, 3, 4, 5}));
        System.out.println(countSmaller(new int[]{-1, -1}));
        System.out.println(countSmaller(new int[]{1, 1}));
    }

    static int[] smaller;

    static class Pair {
        int index;
        int val;

        public Pair(int index, int val) {
            this.index = index;
            this.val = val;
        }
    }

    public static List<Integer> countSmaller(int[] nums) {
        Pair[] pairs = new Pair[nums.length];
        int counter = 0;
        for (int i : nums) {
            pairs[counter] = new Pair(counter, i);
            counter++;
        }

        /**
         * So this problem is exactly like finding number of inversions within merge sort
         * so let's say we have an array
         *
         * [4,5,6,2,3,7]
         *
         * For 4 how many smaller elements we have only 2/- [2, 3]
         * Now see when we do merge sort
         *
         *          [4,5,6,2,3,7]
         *            /\
         *           /  \
         *          /    \
         *         /      \
         *        /        \
         *    [4,5,6]    [2,3,7]
         * Now you see 2 < 4.....so 2 will also be smaller than 5 and 6...
         * So this is similar to inversion and now we can utilize it to store the same
         * in a specific array.
         */
        smaller = new int[nums.length];
        mergeSort(pairs, 0, nums.length - 1);
        List<Integer> result = new ArrayList<>();
        for (int i : smaller) {
            result.add(i);
        }
        return result;
    }

    private static void mergeSort(Pair[] pairs, int low, int high) {
        if (low < high) {
            int mid = low + (high - low) / 2;
            mergeSort(pairs, low, mid);
            mergeSort(pairs, mid + 1, high);
            merge(pairs, low, mid, high);
        }
    }

    private static void merge(Pair[] pairs, int low, int mid, int high) {
        int t1 = low, t2 = mid + 1, m = mid, n = high, counter = 0;
        Pair[] sorted = new Pair[high - low + 1];

        while (t1 <= m && t2 <= n) {
            if (pairs[t1].val <= pairs[t2].val) {
                sorted[counter++] = pairs[t1++];
            } else {
                sorted[counter++] = pairs[t2];

                // So t1 > t2
                // t2 is smaller than all t1-------to--------mid
                for (int i = t1; i <= mid; i++) {
                    smaller[pairs[i].index] += 1; // So let's increment the smaller count for all items t1 to mid.
                }
                t2++;
            }
        }

        while (t1 <= m) {
            sorted[counter++] = pairs[t1++];
        }

        while (t2 <= n) {
            sorted[counter++] = pairs[t2++];
        }

        for (int i = 0; i < sorted.length; i++) {
            pairs[low++] = sorted[i];
        }
    }
}
