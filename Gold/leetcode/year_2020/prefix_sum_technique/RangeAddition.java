package com.leetcode.year_2020.prefix_sum_technique;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * https://www.lintcode.com/problem/903/
 * Given:
 * length = 5,
 * updates =
 * [
 * [1,  3,  2],
 * [2,  4,  3],
 * [0,  2, -2]
 * ]
 * return [-2, 0, 3, 5, 3]
 * <p>
 * Explanation:
 * Initial state:
 * [ 0, 0, 0, 0, 0 ]
 * After applying operation [1, 3, 2]:
 * [ 0, 2, 2, 2, 0 ]
 * After applying operation [2, 4, 3]:
 * [ 0, 2, 5, 5, 3 ]
 * After applying operation [0, 2, -2]:
 * [-2, 0, 3, 5, 3 ]
 */
public class RangeAddition {

    public static void main(String[] args) {
        LogUtil.printArray(getModifiedArray(5, new int[][]{
                {1, 3, 2},
                {2, 4, 3},
                {0, 2, -2}
        }));

        LogUtil.printArray(getModifiedArray(10, new int[][]{
                {2, 4, 6},
                {5, 6, 8},
                {1, 9, -4}
        }));
    }

    public static int[] getModifiedArray(int length, int[][] updates) {
        /**
         * Inspiration https://www.youtube.com/watch?v=fBT0VKkqvtY [PepCoding]
         * okay so question, says we have to apply operation on start-endIndex(inclusive)
         *
         * So for N = 5
         *
         * | 0 | 0 | 0 | 0 | 0 |
         *  0   1   2   3   4  <----- Index
         * and operation [1,  3,  2],
         *
         * we have to increment +2 in location 1 to 3
         * So we can say we are creating a positive impact on 1 to 3, Now assume we are calculating prefixSum, do you think
         * this impact must be removed from the indexes which are not the part of this operation
         *
         * | 0 | 2 | 2 | 2 | 0 | 0 |
         *
         * Prefix Sum ===> | 0 | 2 | 4 | 4 | 4 | 4
         *                                 -------
         *                                    ||
         *                       See how that impact changed our prefix sum, and we don't want that so we can simply remove this impact
         *                       by keeping negation of operation at endIndex+1
         *
         *    0    1  2   3   4   5
         *  | 0 | 0 | 0 | 0 | 0 | 0        and operation   [1,  3,  2], we set +2 to index 1 and -2 at index 4 to remove the impact.
         *
         *       +2          -2
         *
         * Now take prefix sum
         *  | 0 | 2 | 2 | 2 | 0 | 0 | ==============> OUR valid answer
         */

        int[] prefixSumArr = new int[length + 1]; // Why +1 since we want to put the impact removal on endIndex+1 position

        for (int[] operation : updates) {
            prefixSumArr[operation[0]] += operation[2];
            prefixSumArr[operation[1] + 1] += -operation[2];
        }

        // Calculate prefixSum
        for (int i = 1; i < prefixSumArr.length; i++) {
            prefixSumArr[i] += prefixSumArr[i - 1];
        }
        return Arrays.copyOf(prefixSumArr, prefixSumArr.length - 1);
    }
}
