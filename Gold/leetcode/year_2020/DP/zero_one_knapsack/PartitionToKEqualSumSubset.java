package com.leetcode.year_2020.DP.zero_one_knapsack;

/**
 * @author neeraj on 04/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PartitionToKEqualSumSubset {

    public static void main(String[] args) {
        System.out.println(canPartitionKSubsets(new int[]{2, 1, 4, 5, 6}, 3));
        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 4));
        System.out.println(canPartitionKSubsets(new int[]{4, 3, 2, 3, 5, 2, 1}, 5));
    }

    public static boolean canPartitionKSubsets(int[] nums, int k) {
        int sum = 0;
        for (int i : nums) {
            sum += i;
        }
        if (k == 0 || sum % k != 0) return false;
        return canPartitionKSubsets(nums, k, new boolean[nums.length], 0, 0, sum / k);
    }

    public static boolean canPartitionKSubsets(int[] nums, int bucket, boolean[] visited,
                                               int startIndex, int currentSum, int targetSum) {
        if (bucket == 1) { // If we have just 1 bucket so nums must be representing only those items.
            // hence we can partition into 1 subset, which is whole nums.
            return true;
        }

        if (currentSum == targetSum) { // This means we have completely filled the bucket.
            return canPartitionKSubsets(nums, bucket - 1, visited, 0, 0, targetSum);
        }

        for (int i = startIndex; i < nums.length; i++) {
            if (!visited[i]) {
                // If Sum is greater then just skip it

                if (currentSum + nums[i] > targetSum) continue;
                visited[i] = true;
                if (canPartitionKSubsets(nums, bucket, visited, i + 1, currentSum + nums[i], targetSum)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false; // We did not found any such subset where target sum can be achieved.
    }
}
