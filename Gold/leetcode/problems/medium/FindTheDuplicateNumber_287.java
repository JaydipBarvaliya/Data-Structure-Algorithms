package com.leetcode.problems.medium;

/**
 * https://leetcode.com/problems/find-the-duplicate-number/
 *
 * @author neeraj on 08/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindTheDuplicateNumber_287 {

    public static void main(String[] args) {
        System.out.println(findDuplicate(new int[]{1, 3, 4, 2, 2}));
        System.out.println(findDuplicate(new int[]{2, 2, 2, 2, 2}));
        System.out.println(findDuplicate(new int[]{3, 1, 3, 4, 2}));
        System.out.println(findDuplicate(new int[]{1}));
    }

    public static int findDuplicate(int[] nums) {
        // Since the duplicate item can appear multiple times
        // and modification of the array is not allowed
        // So we can't use negating the value approach.

        // this problem is somewhat similar to finding cycle in linked-list
        // Floyd cycle.

        if (nums.length > 1) {

            int slow = nums[0];
            int fast = nums[nums[0]];

            // We know if there is a cycle so if fast pointer jumps twice
            // and slow pointer jump once, they both will meet at some point.
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[nums[fast]];
            }

            // Now we have to just find the point where the cycle starts
            // So for we have to just start the fast pointer from begining
            // and jump both at same rate
            fast = 0; // begining
            while (slow != fast) {
                slow = nums[slow];
                fast = nums[fast];
            }
            return slow;
        }

        return -1;
    }
}
