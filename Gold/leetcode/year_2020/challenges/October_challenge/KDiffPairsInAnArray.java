package com.leetcode.year_2020.challenges.October_challenge;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author neeraj on 05/10/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class KDiffPairsInAnArray {

    public static void main(String[] args) {
        System.out.println(findPairs(new int[]{3, 1, 4, 1, 5}, 2));
        System.out.println(findPairs(new int[]{1, 2, 4, 4, 3, 3, 0, 9, 2, 3}, 3));
        System.out.println(findPairs(new int[]{1, 1, 1, 1, 1}, 0));
    }

    public static int findPairs(int[] nums, int k) {
        Arrays.sort(nums);
        Set<Integer> partner = new HashSet<>();
        Set<Integer> pairs = new HashSet<>();
        int count = 0;
        for (int i = 0; i < nums.length; i++) {
            if (partner.contains(nums[i])) {
                if (pairs.add(nums[i])) {
                    count++;
                }
            }
            partner.add(nums[i] + k);
        }
        return count;
    }
}
