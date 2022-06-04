package com.leetcode.year_2020.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * HARD:
 * https://leetcode.com/problems/subarrays-with-k-different-integers/
 * <p>
 * Good Discussion: https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/360147/Share-my-solution
 * <p>
 * Answer is inspired by : https://leetcode.com/problems/subarrays-with-k-different-integers/discuss/672979/Analysis-and-explanation-with-Visualization
 *
 * @author neeraj on 01/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SubArrayWithKDifferentIntegers {

    public static void main(String[] args) {
        System.out.println(subarraysWithKDistinct(new int[]{1, 2, 1, 2, 3}, 2));
    }

    /**
     * Before we start this problem, couple of concepts we need to refresh,
     * Concept 1:
     * <p>
     * Assume we have array of size(N) : For Example(N=4) : [1,2,3,4], How many total sub-arrays can be generated from this.
     * [1], [1,2], [1,2,3], [1,2,3,4], [2], [2,3], [2,3,4], [3], [3,4], [4] ====> Total of 10 sub-arrays,
     * <p>
     * Now we know this problem is asking about sub-arrays which might existing somewhere, So if i say for an array
     * [1,2,1,2,3], what is the size of maximum sub-array with k=2 distinct values, it's this [1,2,1,2].
     * <p>
     * Visual:
     * *  Array:       [1  2  1  2  3]
     * *  Index:       [0  1  2  3  4]
     * *                        /\
     * *                        ||
     * *                        ||
     * *                        High
     * Okay so our rightPointer of sliding window can go up-to where(index[3]).
     * <p>
     * Comeback on example [1,2,3,4] So let me ask you how many sub-arrays you can make:->
     * <p>
     * When high=index[3] and low = index[0]            [1  2  3  4]
     * *                                                ||        ||
     * *                                                low       high
     * *         [1], [1,2], [1,2,3], [1,2,3,4]   ====> high - low + 1 ==> 3 - 0 + 1 ==> 4 total sub-arrays
     * <p>
     * When high=index[3] and low = index[1]            [1  2  3  4]
     * * *                                                 ||     ||
     * * *                                                low     high
     * * *          [2], [2,3], [2,3,4]   ====> high - low + 1 ==> 3 - 1 + 1 ==> 3 total subarrays
     * <p>
     * When high=index[3] and low = index[2]            [1  2  3  4]
     * * *                                                    ||  ||
     * * *                                                   low  high
     * * *         [3], [3,4]   ====> high - low + 1 ==> 3 - 2 + 1 ==> 2 total subarrays
     * <p>
     * When high=index[3] and low = index[3]            [1  2  3  4]
     * * *                                                        ||
     * * *                                                      lowhigh (pointing to same)
     * * *         [4]   ====> high - low + 1 ==> 3 - 3 + 1 ==> 1 total subarrays
     * <p>
     * * Hence when you will see (high - low + 1), you can relate why we used that.
     * =======================================================================================================================
     * *
     * *  Concept 2:
     * <p>
     * <<<<Total Sub-arrays with At Most(k) distinct elements>>>> -  <<<<Total Sub-arrays with At Most(k-1) distinct elements>>>>
     * <p>
     * *    ===========================equals to==> <<<<Total Sub-arrays with exactly distinct elements>>>>
     * <p>
     * * Visual
     * *   Size of Subarray with At Most(k)    | Size of Subarray with At Most(k)
     * *               [1]                                  [1]
     * *               [1,2]                                [1,2]
     * *               [1,2,3]                              [1,2,3]
     * *                 .                                      .
     * *                 .                                      .
     * *               [1,2,3,4,.......k-1]                 [1,2,3,4,.......k-1]
     * *               [1,2,3,4,5......k]
     * <p>
     * If you subtract what's left only sub-array with exact k distinct elements what we are interested in.
     */

    public static int subarraysWithKDistinct(int[] arr, int K) {
        return subArrayWithAtMostK(arr, K) - subArrayWithAtMostK(arr, K - 1);
    }

    private static int subArrayWithAtMostK(final int[] arr, final int K) {
        /**
         * Similar to what we did in {@link MinimumWindowSubstring} use sliding window approach
         */

        final Map<Integer, Integer> countMap = new HashMap<>();
        int low = 0, high = 0;
        int totalSubArrays = 0;

        while (high < arr.length) {
            countMap.put(arr[high], countMap.getOrDefault(arr[high], 0) + 1);

            while (countMap.size() > K) {
                // Shrink the window
                countMap.put(arr[low], countMap.getOrDefault(arr[low], 0) - 1);
                if (countMap.get(arr[low]) == 0) {
                    countMap.remove(arr[low]);
                }
                low++;
            }

            totalSubArrays += high - low + 1;
            high++;
        }
        return totalSubArrays;
    }
}
