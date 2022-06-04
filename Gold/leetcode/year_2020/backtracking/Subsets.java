package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * https://leetcode.com/problems/subsets/
 * https://leetcode.com/problems/subsets-ii/
 *
 * @author neeraj on 10/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class Subsets {

    public static void main(String[] args) {
        System.out.println(powerset(new int[]{1, 2, 3}));
        System.out.println(powerset(new int[]{1, 2, 2}));
        System.out.println(powerset(new int[]{1, 2, 3, 4}));
    }

    public static List<List<Integer>> powerset(int[] inputSet) {
        // In-order to avoid duplicates, let's sort the input first.
        // and we will skip the currentIndex if the previous index is same as current one.
        // We have to do this only when we are in a recurrence stack else we might miss the original one
        // Hence i>index check is needed and not the i > 0 check
        Arrays.sort(inputSet);

        List<List<Integer>> allPowerSets = new ArrayList<>();
        allPowerset(inputSet, 0, new ArrayList<>(), allPowerSets);
        return allPowerSets;
    }

    private static void allPowerset(int[] inputSet, int index, List<Integer> subset, List<List<Integer>> allPowerSets) {
        allPowerSets.add(new ArrayList<>(subset));

        for (int i = index; i < inputSet.length; i++) {
            // Here we will do our duplicate check.
            // For the first item there can't be duplicate before this
            // Hence we relax the check for 1st item which is index in any recursive call
            // And Next we are comparing "n" != "n-1"
            if (i == index || inputSet[i - 1] != inputSet[i]) {
                subset.add(inputSet[i]); // Choose
                allPowerset(inputSet, i + 1, subset, allPowerSets); // Recurse
                subset.remove(subset.size() - 1); // UnChoose
            }
        }
    }
}
