package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/combination-sum/
 *
 *  Find all unique combinations in candidates where the candidate numbers sums to target.
 *
 * @author neeraj on 10/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CombinationSum {

    public static void main(String[] args) {
        System.out.println(combinationSum(new int[]{2, 3, 6, 7}, 7));
        System.out.println(combinationSum(new int[]{2, 3, 5}, 8));
        System.out.println(combinationSum(new int[]{1, 2, 3}, 3));

        System.out.println(combinationSumUnique(new int[]{1, 2, 3}, 3));
        System.out.println(combinationSumUnique(new int[]{10, 1, 2, 7, 6, 1, 5}, 8));
    }

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> allCombinations = new ArrayList<>();
        combinationSum(candidates, 0, target, 0, new ArrayList<>(), allCombinations);
        return allCombinations;
    }

    private static void combinationSum(int[] candidates, int currentSum, int target,
                                       int currentIndex,
                                       ArrayList<Integer> combination,
                                       List<List<Integer>> allCombinations) {
        if (currentSum > target) return;
        if (currentSum == target) {
            allCombinations.add(new ArrayList<>(combination));
            return;
        }
        for (int i = currentIndex; i < candidates.length; i++) {
            combination.add(candidates[i]);
            combinationSum(candidates, currentSum + candidates[i], target, i, combination, allCombinations);
            combination.remove(combination.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSumUnique(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> allCombinations = new ArrayList<>();
        combinationSumUnique(candidates, 0, target, 0, new ArrayList<>(), allCombinations);
        return allCombinations;
    }


    private static void combinationSumUnique(int[] candidates, int currentSum, int target, int currentIndex,
                                             ArrayList<Integer> combination,
                                             List<List<Integer>> allCombinations) {
        if (currentSum > target) return;
        if (currentSum == target) {
            allCombinations.add(new ArrayList<>(combination));
            return;
        }
        for (int i = currentIndex; i < candidates.length; i++) {
            if (i == currentIndex || candidates[i] != candidates[i - 1]) {
                combination.add(candidates[i]);
                combinationSumUnique(candidates, currentSum + candidates[i], target, i + 1, combination, allCombinations);
                combination.remove(combination.size() - 1);
            }
        }
    }
}
