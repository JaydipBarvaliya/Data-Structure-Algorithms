package com.leetcode.year_2020.DP;

/**
 * https://leetcode.com/problems/jump-game/
 * @author neeraj on 21/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class JumpGame {

    public static void main(String[] args) {
        System.out.println(canJump(new int[]{2, 3, 1, 1, 4}));
        System.out.println(canJump(new int[]{2, 3, 1, 0, 4}));
        System.out.println(canJump(new int[]{3, 2, 1, 0, 4}));
        System.out.println(canJump(new int[]{0,2,3}));
    }

    public static boolean canJump(int[] nums) {
        if(nums.length <=1 ) return true;
        /**
         * // Input: [2,3,1,1,4]
         *        // At each step we will check what is the max we can
         *       //reach from here. So Assume we are at index=0, max we can reach is
         *      // maxReach = index + input[index];
         *     // Now if during this iteration we reached at-least the last index
         *     // we return true; Also if the currentIndex is reaching to a value
         *     // which is less than what previousValue was able to achieve then
         *     // we should just update previousMaxReach
         */
        int maxToBeReachedFromThisFloor = 0;
        for (int i = 0; i <= maxToBeReachedFromThisFloor; i++) {
            maxToBeReachedFromThisFloor = Math.max(maxToBeReachedFromThisFloor, i + nums[i]);
            if (maxToBeReachedFromThisFloor >= nums.length - 1)
                return true;
        }
        return false;
    }
}
