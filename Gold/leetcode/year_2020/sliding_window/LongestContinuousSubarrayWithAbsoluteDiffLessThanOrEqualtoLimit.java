package com.leetcode.year_2020.sliding_window;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * https://leetcode.com/problems/longest-continuous-subarray-with-absolute-diff-less-than-or-equal-to-limit/
 * <p>
 * "Absolute difference between any two elements is less than or equal to limit" is basically =>
 * "Absolute difference between min and max elements of subarray"
 *
 * @author neeraj on 14/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LongestContinuousSubarrayWithAbsoluteDiffLessThanOrEqualtoLimit {

    public static void main(String[] args) {
        System.out.println(longestSubarray(new int[]{8, 2, 4, 7}, 4));
        System.out.println(longestSubarray(new int[]{10, 1, 2, 4, 7, 2}, 5));
        System.out.println(longestSubarray(new int[]{4, 2, 2, 2, 4, 4, 2, 2}, 0));
    }

    public static int longestSubarray(int[] nums, int limit) {
        /**
         * So idea is similar to {@link MaximumOfAllSubArraysOfSizeK}
         * but with 2 variations here along with maximum we are also interested in the minimum item of sliding window
         * also here we don't have any fix size instead we need to find the longest sliding window size
         * where max - min <= limit
         *
         * So we will use 2 Deque and whenever you find any item greater than it's previous one's just reject them
         * and vice-versa for smaller items
         */
        Deque<Integer> maxDeque = new ArrayDeque<>();
        Deque<Integer> minDeque = new ArrayDeque<>();
        int left = 0;
        int result = Integer.MIN_VALUE;

        for (int right = 0; right < nums.length; right++) {

            // Kick out smaller elements from last
            while (!maxDeque.isEmpty() && maxDeque.peekLast() < nums[right]) {
                maxDeque.removeLast();
            }

            maxDeque.addLast(nums[right]); // Why inserting at last because there might already be some items
            // from which current ith index is smaller.

            // Kick out bigger elements from last
            while (!minDeque.isEmpty() && minDeque.peekLast() > nums[right]) {
                minDeque.removeLast();
            }

            minDeque.addLast(nums[right]); // Adding smaller item in minQueue.

            // Shrink the window [left......right], Now either we will remove the large item or the smaller item
            // since they are the one breaking the equation
            if (maxDeque.peekFirst() - minDeque.peekFirst() > limit) {
                // Now either remove smaller item or large item
                // on whichever item my left is pointing
                if (maxDeque.peekFirst() == nums[left]) maxDeque.pollFirst();
                if (minDeque.peekFirst() == nums[left]) minDeque.pollFirst();

                left++; // Shrinking the window
            }
            result = Math.max(result, right - left + 1);
        }
        return result;
    }
}
