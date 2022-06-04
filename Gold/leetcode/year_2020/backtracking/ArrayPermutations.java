package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Both
 * https://leetcode.com/problems/permutations/ (Non Duplicate)
 * https://leetcode.com/problems/permutations-ii/submissions/ (Duplicate check).
 *
 * @author neeraj on 10/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ArrayPermutations {

    public static void main(String[] args) {
        System.out.println(permute(new int[]{1, 2, 3}));
        System.out.println(permute(new int[]{1, 2, 2}));
        System.out.println(permute(new int[]{3, 30, 34, 5, 9}));
    }

    public static List<List<Integer>> permute(int[] nums) {
        // To Avoid duplicates
        Arrays.sort(nums);
        List<List<Integer>> permutations = new ArrayList<>();
        boolean[] used = new boolean[nums.length]; // keep track of which indexes have we already visited.
        permuteUtil(nums, new ArrayList<>(), permutations, used);
        return permutations;
    }

    private static void permuteUtil(int[] nums, ArrayList<Integer> currentPermutation, List<List<Integer>> permutations,
                                    boolean[] used) {
        if (currentPermutation.size() == nums.length) {
            permutations.add(new ArrayList<>(currentPermutation));
            return;
        }
        for (int i = 0; i < nums.length; i++) {
            /**
             * This is the most important check
             *  We are keeping track of 2 things here
             *  1st. Since loop always runs from 0 to length
             *  We need to make sure that in the currentIteration we don't re-iterate on the same i
             *  hence for that we are using "i", we can also do   !currentPermutation.contains(nums[i]) check but that will take O(n)
             *  time hence we are saving time by doing o[1] check with extra space O[n] space.
             *
             *  Now For Duplicate check we need to make sure, assuming we sorted the Array before
             *  that the index[i] != index[i-1] if that's the case we should not recurse
             *  But here is the tweak we should only do this "No Recursion thing for Duplicate"
             *  Only if we are in a recurring call, not when it's the first call of itself
             *  Hence if you see we have "!used[i-1]" check, this is helpful since when we backtrack
             *  and reset i-1 index to false, only then we need to make sure that we are not recursing
             *  on a duplicate element.
             *
             *  Simple break down of this important logic
             *
             *  i/p --> [1 | 2 | 2]   used----> [false | false | false]   combination ----> ""
             *
             *  i/p --> [1 | 2 | 2]   used----> [true | false | false]   combination ----> "1"
             *           i=0
             *
             * i/p --> [1 | 2 | 2]   used----> [true | true | false]   combination ----> "12"
             *              i=1
             *
             * i/p --> [1 | 2 | 2]   used----> [true | true | true]   combination ----> "122" ... Now loop finish since i=3 == length.
             *                  i=2
             *
             * Now we backtrack ....  to i = 1, and set i=1 to false and remove from combination
             *
             * i/p --> [1 | 2 | 2]   used----> [true | false | false]   combination ----> "1"
             *              i=1
             * Now loop increment i = 2.
             *
             * i/p --> [1 | 2 | 2]   used----> [true | false | true]   combination ----> "12"
             *                 i=2
             *
             * Now notice carefully when you come back to running from 0 to 2 loop...and encounter i=1 is false you will use it
             * and final output will again be combination ----> "122" ...  hence
             *
             * What we are checking is
             * i > 0 (since you can't have duplicate on first index)
             * &&num[i-1] == num[i] (to check if this current i is duplicate of i-1
             * && !used[i-1].  this we saw at i=1 in example if we add this check we know when we are at i=2 this is duplicate
             * since we were in callStack when i=1 set to false.
             */
            if (used[i] || (i > 0 && nums[i] == nums[i - 1]) && !used[i - 1]) continue;
            used[i] = true;
            currentPermutation.add(nums[i]);
            permuteUtil(nums, currentPermutation, permutations, used);
            used[i] = false;
            currentPermutation.remove(currentPermutation.size() - 1);
        }

    }
}
