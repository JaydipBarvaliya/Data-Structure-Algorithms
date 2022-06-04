package com.leetcode.problems.medium;

import com.util.LogUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * This Class shows all examples of 3 common problems
 * 1) Subsets
 * <p>
 * <p>
 * 2) Permutations
 * <p>
 * <p>
 * <p>
 * 3) Combination Sum
 *
 * @author neeraj on 06/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicatedCode")
public class SubSet_Permutations_CombinationSum {

    public static void main(String[] args) {
        int[] nums = new int[]{1, 2, 3};

        LogUtil.logIt("Finding Subset of int array .....");
        System.out.println(subsets(nums));

        LogUtil.logIt("Finding Subset of int array with duplicates .....");
        System.out.println(subsetsWithDuplicates(new int[]{1, 2, 3, 1}));

        LogUtil.logIt("Finding Permutations of int array  .....");
        System.out.println(permute(nums));

        LogUtil.logIt("Finding Combination Sum......");
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));

        LogUtil.logIt("Finding Combination Sum II");

    }


    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        subsetUtil(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void subsetUtil(int[] nums, int pointer, List<Integer> current, List<List<Integer>> all) {
        all.add(new ArrayList<>(current));

        for (int i = pointer; i < nums.length; i++) {
            current.add(nums[i]); // Choose
            subsetUtil(nums, i + 1, current, all); // Explore
            current.remove(current.size() - 1); // Un-Choose
        }
    }

    public static List<List<Integer>> subsetsWithDuplicates(int[] nums) {
        Arrays.sort(nums); // Sorting them so that we can skip the duplicates
        List<List<Integer>> result = new ArrayList<>();
        subsetUtilForDuplicates(nums, 0, new ArrayList<>(), result);
        return result;
    }


    private static void subsetUtilForDuplicates(int[] nums, int pointer, List<Integer> current, List<List<Integer>> all) {
        all.add(new ArrayList<>(current));

        for (int i = pointer; i < nums.length; i++) {
            if (i != 0 && nums[i] == nums[i - 1]) continue;
            current.add(nums[i]); // Choose
            subsetUtil(nums, i + 1, current, all); // Explore
            current.remove(current.size() - 1); // Un-Choose
        }
    }


    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteUtil(nums, new ArrayList<>(), result);
        return result;
    }

    private static void permuteUtil(int[] nums, List<Integer> current, List<List<Integer>> all) {
        if (current.size() == nums.length) {
            all.add(new ArrayList<>(current));
            return;
        }

        for (int i = 0; i < nums.length; i++) {
            if (current.contains(nums[i])) continue;
            current.add(nums[i]); // Choose
            permuteUtil(nums, current, all); // Explore
            current.remove(current.size() - 1); // Un-choose
        }
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        combinationSumUtil(candidates, 0, target, new ArrayList<>(), result);
        return result;
    }

    private static void combinationSumUtil(int[] candidates, int pointer, int target, List<Integer> combination, List<List<Integer>> allCombination) {
        if (target < 0) return;
        if (target == 0) {
            allCombination.add(new ArrayList<>(combination));
        }
        for (int i = pointer; i < candidates.length; i++) {
            combination.add(candidates[i]);
            // Not incrementing i, since number can be re-used
            combinationSumUtil(candidates, i, target - candidates[i], combination, allCombination);
            combination.remove(combination.size() - 1);
        }
    }
}
