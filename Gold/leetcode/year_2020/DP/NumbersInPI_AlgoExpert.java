package com.leetcode.year_2020.DP;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 21/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumbersInPI_AlgoExpert {

    public static void main(String[] args) {
        System.out.println(numbersInPI("3141592", new String[]{
                "3141",
                "5",
                "31",
                "2",
                "4159",
                "9",
                "42",
                "592"
        }));
    }

    static int dp[];

    public static int numbersInPI(String pi, String[] numbers) {
        // Prepare dictionary for quick lookup
        Map<String, String> dictionary = new HashMap<>();
        for (String i : numbers) {
            dictionary.put(i, i);
        }
        dp = new int[pi.length() + 1];
        Arrays.fill(dp, -1);
        return getMinimumCuts(pi, dictionary, 0);
    }

    private static int getMinimumCuts(String pi, Map<String, String> dictionary, int index) {
        if (index == pi.length()) {
            return 0;
        }
        if(dp[index] != -1) {
            return dp[index];
        }
        int minCuts = Integer.MAX_VALUE;
        for (int i = index; i < pi.length(); i++) {
            String prefixAfterCut = pi.substring(index, i + 1);
            if (dictionary.containsKey(prefixAfterCut)) {
                int minimumCutsForSuffix = getMinimumCuts(pi, dictionary, i + 1);
                minCuts = Math.min(minCuts, minimumCutsForSuffix + 1);
            }
        }
        return dp[index] = minCuts;
    }
}
