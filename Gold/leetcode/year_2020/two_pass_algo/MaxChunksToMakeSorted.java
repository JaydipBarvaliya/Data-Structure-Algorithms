package com.leetcode.year_2020.two_pass_algo;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/max-chunks-to-make-sorted/
 *
 * @author neeraj on 15/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaxChunksToMakeSorted {

    public static void main(String[] args) {
        System.out.println(maxChunksToSorted(new int[]{0, 2, 1, 4, 3, 5, 7, 6}));
        System.out.println(maxChunksToSorted(new int[]{5, 4, 3, 2, 1}));

        System.out.println(maxChunksToSortedTwoPass(new int[]{0, 2, 1, 4, 3, 5, 7, 6}));
        System.out.println(maxChunksToSortedTwoPass(new int[]{5, 4, 3, 2, 1}));
        System.out.println(maxChunksToSortedTwoPass(new int[]{1, 0, 2, 3, 4}));
    }

    public static int maxChunksToSortedTwoPass(int[] arr) {
        /**
         * Wherever we are making cut, left side's max element should be less than the minimum element of the right
         * side section. only than eventually list will be sorted.
         * So if we maintain LeftMax and RightMin using 2 pass.
         * then we can know in the final pass where should we have to make a cut.
         * I/p :      [1,0,2,3,4]
         * LeftMax:   [1,1,2,3,4]
         * RightMin:  [0,0,2,3,4]
         *
         * Now compare leftMax[i] <= RightMin[i+1] and wherever it's true we have our cut.
         * This will also work when we have duplicate values in the array.
         * So this problem essentially solves : https://leetcode.com/problems/max-chunks-to-make-sorted-ii/
         * [1,1,0,0,1]
         */
        int[] leftMax = new int[arr.length];
        leftMax[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]); // maintaining left max;
        }

        int[] rightMin = new int[arr.length];
        rightMin[rightMin.length - 1] = arr[arr.length - 1];
        for (int i = arr.length - 2; i >= 0; i--) {
            rightMin[i] = Math.min(rightMin[i + 1], arr[i]); // maintaining right min;
        }

        // Final Pass
        int count = 0;
        for (int i = 0; i < arr.length - 1; i++) {
            if (leftMax[i] <= rightMin[i + 1]) count++;
        }
        return count + 1;
    }

    public static int maxChunksToSorted(int[] arr) {
        /**
         * This is one pass approach... not very intuitive.
         * We need to find the max Value of any chunk
         * if the maxValue of the chunk falls exactly on the same index as it would have if i sort the original array
         * congo we found a chunk
         *
         * Why? Okay so whenever we make a cut to divide in chunk, we are saying that all the values before this cut
         * should be small and sorted.
         * Now when we take the max of a chunk.... it should come in the last of the chunk once that individual chunk is sorted.
         * So whenever that last Index is same as the original sorted array index. so that means this is a perfect chunk
         * and we found a chunk which when sorted will result in similar to if we have sorted the original array.
         *
         * Example :
         * I/p     :  0, 2, 1, 4, 3, 5, 7, 6
         * max     :  0, 2, 2, 4, 4, 5, 7, 7  [ We calculate maximum value in left at any index.]
         * Sorted  :  0, 1, 2, 3, 4, 5, 6, 7
         *
         * Now if we notice these chunks...... the max value of any valid chunk is falling exactly on the sorted index.
         * [0] [2 1] [4 3] [5] [7 6]
         *
         */
        int[] leftMax = new int[arr.length];
        leftMax[0] = arr[0];

        for (int i = 1; i < arr.length; i++) {
            leftMax[i] = Math.max(leftMax[i - 1], arr[i]); // maintaining left max;
        }

        Arrays.sort(arr);
        int chunkCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] == leftMax[i]) chunkCount++;
        }
        return chunkCount;
    }
}
