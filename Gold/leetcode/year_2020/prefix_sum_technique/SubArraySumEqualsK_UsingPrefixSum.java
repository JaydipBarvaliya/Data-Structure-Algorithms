package com.leetcode.year_2020.prefix_sum_technique;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/subarray-sum-equals-k/
 *
 * @author neeraj on 19/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SubArraySumEqualsK_UsingPrefixSum {

    public static void main(String[] args) {
        System.out.println(subarraySum(new int[]{3, 4, 7, 2, -3, 1, 4, 2}, 7));
        System.out.println(subarraySum(new int[]{1, 1, 1}, 2));
        System.out.println(subarraySum(new int[]{3, 4, 7, -2, 2, 1, 4, 2}, 7));
        System.out.println(subarraySum(new int[]{7, -7, 7, -7, 7}, 7));
        System.out.println(subarraySum(new int[]{4, 2, -3, 1, 6}, 0));
        System.out.println(subarraySum(new int[]{4, 2, 0, 1, 6}, 0));
        System.out.println(subarraySum(new int[]{-3, 2, 3, 1, 6}, 0));
    }

    public static int subarraySum(int[] nums, int targetValueK) {
        /**
         * This solution will also work for SubArray with Sum 0 problem.
         * https://www.geeksforgeeks.org/find-if-there-is-a-subarray-with-0-sum/
         *
         * Here we have been asked to find number of subarray's which
         * sums upto K
         *
         * I/p [3, 4, 7, 2, -3, 1, 4, 2]
         * 7-0 = 7 So Subarray = [3, 4]
         * 14 - 7 = 7 So SubArray = [7]
         * 14 - 7 = 7 So SubArray = [7, 2, -3, 1]
         *
         * Suppose we are at position y.
         * prefix_sum(y): Denotes the total sum from start(s) position.
         * prefix_sum(x): Denotes prefix_sum(y) - K.
         *
         *  //====> Starting position
         * ||
         *  s
         * [3, 4, 7, 2, -3, 1, 4, 2]
         *
         * Now let's take any position (y).
         *
         *
         * "--------------------------Prefix_Sum at (y)---------------------------"
         * "------Prefix_Sum at (x)------- + ------------targetSum----------------"
         *
         * Now we have only 1 unknown prefix_sum_x and
         * 2 knows are prefix_sum_y and the target sum
         *
         * So what we want to find is prefix_Sum_x = prefix_sum_y - targetSum
         *
         * Now if we find prefix_Sum_x exist then we do have such subarray else no. We use Map to check if we found such
         * prefix_sum_x before so that we know we have such subarray.
         *
         * Please refer CLCI : Paths with sum problem solution for visualization or this diagram
         * https://leetcode.com/problems/subarray-sum-equals-k/discuss/190674/Python-O(n)-Based-on-%22running_sum%22-concept-of-%22Cracking-the-coding-interview%22-book
         *
         * Explanation to why map.get(sum-k) is done than count++
         * https://leetcode.com/problems/subarray-sum-equals-k/discuss/535507/Explanation-to-why-map.get(sum-k)-is-done-than-count%2B%2B
         *
         * https://leetcode.com/problems/subarray-sum-equals-k/discuss/653721/Explanation%3A-WHY-count-%2B-map.get(sum-k)-by-visualization
         */
        int prefix_sum_y = 0;
        Map<Integer, Integer> prefix_sum_xFrequencyMap = new HashMap<>();
        prefix_sum_xFrequencyMap.put(0, 1);
        int totalSuchSubArrays = 0;
        int prefix_sum_x = 0;

        for (int i : nums) {
            prefix_sum_y += i;
            prefix_sum_x = prefix_sum_y - targetValueK;
            if (prefix_sum_xFrequencyMap.containsKey(prefix_sum_x)) {
                totalSuchSubArrays += prefix_sum_xFrequencyMap.get(prefix_sum_x);
            }
            prefix_sum_xFrequencyMap.put(prefix_sum_y, prefix_sum_xFrequencyMap.getOrDefault(prefix_sum_y, 0) + 1);
        }
        return totalSuchSubArrays;
    }
}
