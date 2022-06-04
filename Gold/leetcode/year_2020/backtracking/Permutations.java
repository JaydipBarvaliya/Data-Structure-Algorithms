package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutations/
 *
 * @author neeraj on 06/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Permutations {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3, 4}));
        System.out.println(permute(new int[]{1, 2, 3, 4}));
        System.out.println(permute(new int[]{3, 2, 5}));
        System.out.println(permute(new int[]{1, 2, 4}));
        System.out.println(permute(new int[]{1, 2}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        invoked = 0;
        permuteUtil(nums, new ArrayList<>(), result);
        System.out.println("Invoked upto " + invoked);
        return result;
    }

    static int invoked = 0;

    private static void permuteUtil(int[] nums, List<Integer> current, List<List<Integer>> all) {
        if (current.size() == nums.length) {
            all.add(new ArrayList<>(current));
            return;
        }
        invoked++;

        for (int i = 0; i < nums.length; i++) {
            if (current.contains(nums[i])) continue;
            current.add(nums[i]); // Choose
            permuteUtil(nums, current, all); // Explore
            current.remove(current.size() - 1); // Un-choose
        }
    }


    /**
     * https://www.geeksforgeeks.org/print-all-possible-combinations-of-r-elements-in-a-given-array-of-size-n/
     */
    private static void permuteUtilUptoR(int[] nums, List<Integer> current, List<List<Integer>> all, int R) {
        if (current.size() >= R) {
            all.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (current.contains(nums[i])) continue;
            current.add(nums[i]);
            permuteUtilUptoR(nums, current, all, R);
            current.remove(current.size() - 1);
        }
    }
}
