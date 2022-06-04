package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/permutation-sequence/
 * <p>
 * Given n and k, return the kth permutation sequence.
 *
 * @author neeraj on 20/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PermutationSequence {

    public static void main(String[] args) {
//        System.out.println(getPermutation(3, 3));
//        System.out.println(getPermutation(4, 9));
//        System.out.println(getPermutation(9, 94626));
//        System.out.println(getPermutation(9, 278082));
//        System.out.println(getPermutation(9, 233794));

        System.out.println(getPermutation(4, 14));
        System.out.println(getPermutationOptimized(4, 14));
    }

    /**
     * This might give you TLE, since here we are exhaustively searching for the entire permutations
     * A much better approach at the bottom {{@link #getPermutationOptimized(int, int)}}
     */
    public static String getPermutation(int n, int k) {
        List<String> all = new ArrayList<>();
        boolean[] used = new boolean[n];
        permuteFindK(n, k, new StringBuilder(), all, used);
        return all.get(k - 1);
    }

    public static void permuteFindK(int n, int k, StringBuilder currentPermutation, List<String> all, boolean[] used) {
        if (all.size() >= k) return;
        if (currentPermutation.length() == n) {
            all.add(currentPermutation.toString());
            return;
        }
        for (int i = 1; i <= n; i++) {
            if (used[i - 1]) continue;
            used[i - 1] = true;
            currentPermutation.append("" + i);
            permuteFindK(n, k, currentPermutation, all, used);
            currentPermutation.deleteCharAt(currentPermutation.length() - 1);
            used[i - 1] = false;
        }
    }


    /**
     * The best solution explained here
     * https://leetcode.com/problems/permutation-sequence/discuss/22507/%22Explain-like-I'm-five%22-Java-Solution-in-O(n)
     */
    public static String getPermutationOptimized(int n, int k) {
        // PreCalculate all factorial
        int[] factorial = new int[n + 1];
        factorial[0] = 1;

        for (int i = 1; i <= n; i++) {
            factorial[i] = factorial[i - 1] * i;
        }

        k--; // Reason 0 based indexing.

        // Preparing nums list from 1 to n
        List<Integer> nums = new ArrayList<>();
        for (int i = 1; i <= n; i++) {
            nums.add(i);
        }

        final StringBuilder result = new StringBuilder();
        int index = 0;

        for (int i = 1; i <= n; i++) {
            index = k / factorial[n - i];
            result.append(nums.get(index));
            // Now since till index k is used we have to find remaining kth permutation
            nums.remove(index); // since this is already used
            k = k - (index * factorial[n - i]);
        }
        return result.toString();
    }
}
