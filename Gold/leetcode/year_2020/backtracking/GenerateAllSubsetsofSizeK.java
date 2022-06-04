package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * Given an integer value n and an integer value k, generate all subsets of the sequence from 1…n of size k.
 * <p>
 * https://backtobackswe.com/platform/content/generate-all-subsets-of-size-k
 * <p>
 * This is analogous to extracting all sets of size k from the sequence's power set (the set of all subsets).
 *
 * @author neeraj on 12/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class GenerateAllSubsetsofSizeK {

    public static void main(String[] args) {
        System.out.println(subsetsSizeK(10, 5));
    }

    public static List<List<Integer>> subsetsSizeK(int n, int k) {
        List<List<Integer>> subsets = new ArrayList<>();
        produceSubsets(n, k, 1, subsets, new ArrayList<>());
        /**
         * Time complexity: N choose k
         *
         * C(n, k) ===>   n!
         * *            -----------
         * *            k! * (n-k)!
         *
         */
        return subsets;
    }

    private static void produceSubsets(int n, int k, int currentPointer, List<List<Integer>> allSubSets, ArrayList<Integer> subset) {
        if (k == 0) {
            allSubSets.add(new ArrayList<>(subset));
            return;
        }
        for (int i = currentPointer; i <= n - k + 1; i++) {
            /**
             * Why is this i<= n - k + 1 important
             * Assume we have n = 5 and k = 4
             *
             * [_3_, _ ,_ ,_ ,_] =====> If we place 3 here do we have enough items left to be placed, in remaining 4 positions
             * *                             and i guess not because we have just [4,5] left to be placed, so there is no
             * *                            point pursuing recursion in that direction.
             * * Hence, [i <= n - k + 1], help us know if we have enough items left to be placed in remaining sections.
             * So we can place only upto i=2 in the first slot.
             * *
             */
            subset.add(i);
            produceSubsets(n, k - 1, i + 1, allSubSets, subset);
            subset.remove(subset.size() - 1);
        }
    }
}
