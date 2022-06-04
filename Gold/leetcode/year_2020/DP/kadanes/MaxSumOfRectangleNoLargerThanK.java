package com.leetcode.year_2020.DP.kadanes;

import com.util.LogUtil;

import java.util.TreeSet;

/**
 * https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/
 *
 * @author neeraj on 19/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaxSumOfRectangleNoLargerThanK {

    public static void main(String[] args) {
        int[][] inputs = new int[][]{
                {1, 2, -1, -4, -20},
                {-8, -3, 4, 2, 1},
                {3, 8, 10, 1, 3},
                {-4, -1, 1, 7, -6}
        };
        maxSumSubmatrix(inputs, 2);

        inputs = new int[][]{
                {1, 0, 1},
                {0, -2, 3}
        };
        maxSumSubmatrix(inputs, 2);

        inputs = new int[][]{{5, -4, -3, 4},
                {-3, -4, 4, 5},
                {5, 1, 5, -4}};
        maxSumSubmatrix(inputs, 10);

        inputs = new int[][]{{5, -4, -3, 4},
                {-3, -4, 4, 5},
                {5, 1, 5, -4}};
        maxSumSubmatrix(inputs, 8);
    }

    public static int maxSumSubmatrix(int[][] matrix, int k) {
        /**
         * This problem is similar to {@link MaximumSumRectangle}, with a twist of max sum not greater than k.
         * okay so i need to iterate through all the columns, by keeping a left and right limit and use kadane's
         * to find the sum of a sub-matrix or a rectangle.
         *
         * But the catch HERE <<<<<<<<<<<<<<<<<<<<< KADANE's WONT WORK FOR SUM < K problem and it won't give right solution
         * Exlanation https://leetcode.com/problems/max-sum-of-rectangle-no-larger-than-k/discuss/83599/Accepted-C++-codes-with-explanation-and-references/87932
         *
         *  Kadane's algorithm provides us with the maximum subarray sums for subarrays that end at every index in the original array.
         *  Suppose we have the list {−3,4,−1,2,1,−5,4} . The maximum sums ending at each index would be: {−3,4,3,5,6,1,5} .
         *  Suppose we're looking for sums less than 3. The best option we have adds up to 1. That subarray would be {4,−1,2,1,−5} .
         *  However, there exists a subarray whose sum is 2: {2,1,−5,4} . This is a more ideal solution, but the algorithm won't identify it.
         *  The reason why is that the algorithm is tuned to favor elements that maximize the final sum.
         *  If we are looking for a maximal sum that is bounded, we can't take the same greedy approach.
         *  When there is a bound, we sometimes have to select numbers that may seem like bad choices at first,
         *  but actually work out in the end.
         *
         * So to solve this we have to use TreeSet which help us find the rectangle with maxSum <= k with O(logN) time
         */
        int[] runningSumOfRectangle;
        int MAXIMUM_SUM_RECTANGLE = Integer.MIN_VALUE;
        for (int L = 0; L < matrix[0].length; L++) {
            runningSumOfRectangle = new int[matrix.length]; // This sum goes from top row to the bottom row,
            // and within left and right bound.

            for (int R = L; R < matrix[0].length; R++) {

                // Now let's calculate the runningSumOfRectangle from left to right bound.
                for (int row = 0; row < matrix.length; row++) {
                    runningSumOfRectangle[row] += matrix[row][R];
                }

                // Now we have the sum of rectangle, in the runningSumOfRectangle.
                // we need to find the sub-array with max sum less than <=k
                //    i--------------------------------------------------------------j
                //   prefixSum/CumulativeSum[i]                           prefixSum/CumulativeSum[j]
                //
                // we need to find out some (prefixSum[j] - prefixSum[i] <= k)
                // or we can say.............. (prefixSum[j] - k <= prefixSum[i])
                //
                // so for every (prefixSum[j] - k) we need to find a ceiling. since prefixSum[i] as per statement is greater than (prefixSum[j] - k)
                // and the fastest way to find a ceiling is TreeSet
                //

                // Let's use Tree Set to find the rectangle sum <= K
                TreeSet<Integer> treeSet = new TreeSet<>();
                treeSet.add(0); // For base case.
                int prefixSum = 0;
                for (int sum : runningSumOfRectangle) {
                    prefixSum += sum;

                    Integer ceiling = treeSet.ceiling(prefixSum - k);
                    if (ceiling != null) {
                        MAXIMUM_SUM_RECTANGLE = Math.max(MAXIMUM_SUM_RECTANGLE, prefixSum - ceiling);
                    }
                    treeSet.add(prefixSum);
                }
            }
        }
        LogUtil.printMultiDimensionArray(matrix);
        LogUtil.logIt("Sum is " + MAXIMUM_SUM_RECTANGLE);
        return MAXIMUM_SUM_RECTANGLE;
    }
}
