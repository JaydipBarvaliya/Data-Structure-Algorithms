package com.leetcode.problems.medium;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author neeraj on 07/09/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class CombinationSum_II_40 {

    public static void main(String[] args) {
        combinationSum2(new int[]{10, 1, 2, 7, 6, 1, 5}, 8);
        combinationSum2(new int[]{1, 1}, 1);

        distinctSubSet(new int[]{1, 2, 3});
    }

    public static List<List<Integer>> distinctSubSet(int[] arr) {
        List<List<Integer>> result = new ArrayList<>();
        doDFS(arr, 0, new ArrayList<>(), result);
        System.out.println(result);
        return result;
    }

    private static void doDFS(int[] arr, int pointer, List<Integer> subset, List<List<Integer>> result) {
        result.add(new ArrayList<>(subset));
        for (int i = pointer; i < arr.length; i++) {
            subset.add(arr[i]);
            doDFS(arr, i + 1, subset, result);
            subset.remove(subset.size() - 1);
        }
    }

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        // To avoid duplicates, Since once we sort if there are duplicate numbers
        // they will be together and we can simply check if candidates[i] == candidates[i-1];
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        doDFS(candidates, 0, new ArrayList<>(), result, target);
        System.out.println(result);
        return result;
    }

    private static void doDFS(int[] candidates, int pointer,
                              List<Integer> currentPath, List<List<Integer>> result, int target) {

        if (target == 0) {
            result.add(new ArrayList<>(currentPath));
            return;
        }
        if (target < 0) {
            return;
        }

        for (int i = pointer; i < candidates.length; i++) {
            // Why i == pointer, since we can't compare first element
            // with previousElement since there is no previousElement
            // And this check if for the case of I/p {1,1} target=1
            // If we don't check for duplicates we will end up with
            // [[1], [1]] instead answer should be [[1]]
            if (i == pointer || candidates[i] != candidates[i - 1]) {
                currentPath.add(candidates[i]);
                doDFS(candidates, i + 1, currentPath, result, target - candidates[i]);

                // Back track
                currentPath.remove(currentPath.size() - 1);
            }
        }
    }
}
