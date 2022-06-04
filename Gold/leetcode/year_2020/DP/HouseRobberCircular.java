package com.leetcode.year_2020.DP;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/house-robber-ii/
 * @author neeraj on 05/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class HouseRobberCircular {

    public static void main(String[] args) {
        System.out.println(rob(new int[]{1,2,3,1}));
        System.out.println(rob(new int[]{2,7,9,3,1}));
    }

    public static int rob(int[] nums) {
        if(nums.length == 0)
            return 0;
        if(nums.length == 1)
            return nums[0];
        if(nums.length == 2)
            return Math.max(nums[0], nums[1]);
        if(nums.length == 3)
            return Math.max(Math.max(nums[0], nums[1]), nums[2]);

        // Now the tricky part,
        // if i choose 1st House to Rob, i can't choose HouseNo(1) and HouseNo(totalHouses);
        // Since the houses are in circular fashion
        // So 2 condition
        return Math.max(
                nums[0] + robBasic(Arrays.copyOfRange(nums, 2, nums.length - 1)),
                robBasic(Arrays.copyOfRange(nums, 1, nums.length))
        );
    }

    public static int robBasic(int[] nums) {
        if(nums.length == 0) {
            return 0;
        }
        if(nums.length == 1) {
            return nums[0];
        }

        int [] dp = new int[nums.length];
        dp[0] = nums[0];
        dp[1] = Math.max(nums[0], nums[1]);

        for(int i=2;i<nums.length;i++) {
            dp[i] = Math.max((nums[i] + dp[i-2]), dp[i - 1]);
        }

        return dp[nums.length - 1];
    }

}
