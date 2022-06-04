package com.leetcode.year_2020.DP.longest_common_subsequence;

import com.geeksforgeeks.dynamicProgramming.LongestCommonSubsequence;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * https://www.geeksforgeeks.org/dynamic-programming-building-bridges/
 *
 * @author neeraj on 25/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BuildingBridges {

    public static void main(String[] args) {
        System.out.println(findMaxBridges(new int[]{5, 3, 10}, new int[]{6, 4, 1}));
        System.out.println(findMaxBridges(new int[]{6, 4, 2, 1}, new int[]{2, 3, 6, 5}));
        System.out.println(findMaxBridges(new int[]{8, 1, 4, 3, 5, 2, 6, 7}, new int[]{1, 2, 3, 4, 5, 6, 7, 8}));
        System.out.println(findMaxBridges(new int[]{0, 2, 1, 1}, new int[]{1, 4, 4, 3}));
    }

    public static int findMaxBridges(int[] north, int[] south) {
        /**
         * This is a simply a {@link LongestCommonSubsequence} variation.
         * with a twist since we can't simply compare similar values here.
         *
         * Instead we have been provided pairs, north[i] can only create bridge with south[i]
         * north[1]--------------------------------------------north[i]
         *   ||                                                 ||
         * south[1]--------------------------------------------south[i]
         *
         * Now they might be not in the same position, so we can't just draw straight lines
         * 5      3         10
         * 6      4         1
         *
         * Now if you sort them
         *
         * 3     5    10
         *           /
         *         /
         *       /
         *     /
         *   /
         * 1    4      6
         *
         * Now if you see if we build 10---> 1 bridge we loose max bridge which can be built(3, 4) and (5,6)
         * So we know we have to find the longest common subsequence,
         * but wait Neeraj, there is nothing common
         * it is trust me ....... can you create a mapping from south-to-north and compare just them.
         *
         * North ----> 3        5       10
         * South ---> 1(10)     4(3)     6(5)
         *
         * Ghantiiiii Bajiiiiiii....Dikhe common.... Mauz karo..
         *
         */
        Map<Integer, Integer> southToNorthMapping = new HashMap<>();
        for (int i = 0; i < south.length; i++) {
            southToNorthMapping.put(south[i], north[i]);
        }

        Arrays.sort(north);
        Arrays.sort(south);

        return findLCS(north, south, southToNorthMapping);
    }

    private static int findLCS(int[] north, int[] south, Map<Integer, Integer> southToNorthMapping) {
        int[][] dp = new int[north.length + 1][south.length + 1];

        // Initialization.... leave it since dp is anyways 0 and when we don't have
        // any null string in either south or north we can't make anything common.

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                if (north[i - 1] == southToNorthMapping.get(south[j - 1])) {
                    dp[i][j] = 1 + dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                }
            }
        }
        return dp[dp.length - 1][dp[0].length - 1];
    }
}
