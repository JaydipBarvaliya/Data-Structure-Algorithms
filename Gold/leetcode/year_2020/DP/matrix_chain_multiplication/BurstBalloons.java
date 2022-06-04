package com.leetcode.year_2020.DP.matrix_chain_multiplication;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/burst-balloons/
 *
 * @author neeraj on 10/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class BurstBalloons {

    public static void main(String[] args) {
        System.out.println(maxCoins(new int[]{3, 1, 5, 8}));
        System.out.println(maxCoins(new int[]{1, 2, 3}));
        System.out.println(maxCoins(new int[]{10}));
    }

    /**
     * This problem is a variation of {@link MatrixChainMultiplication}, here they are asking to collect maxCoins
     * in contrary to minOperations in {@link MatrixChainMultiplication}
     */
    static int t[][];

    public static int maxCoins(int[] nums) {
        // As problem statement says and i Quote..
        // "You may imagine nums[-1] = nums[n] = 1. They are not real therefore you can not burst them."
        // So let's just append 1 at both start and end.
        int[] numsWithStartAndEnd = new int[nums.length + 2];
        numsWithStartAndEnd[0] = 1;
        numsWithStartAndEnd[numsWithStartAndEnd.length - 1] = 1;
        int counter = 1;
        for (int i : nums) {
            numsWithStartAndEnd[counter++] = i;
        }

        t = new int[numsWithStartAndEnd.length][numsWithStartAndEnd.length];
        for (int[] row : t) {
            Arrays.fill(row, -1);
        }
        return solve(numsWithStartAndEnd, 1, numsWithStartAndEnd.length - 1);
    }

    private static int solve(int[] numsWithStartAndEnd, int i, int j) {
        if (t[i][j] > -1) return t[i][j];
        if (i >= j) {
            t[i][j] = 0;
            return 0; // There are no balloons to burst.
        }

        int MAX_COINS = Integer.MIN_VALUE;
//        LogUtil.logIt("========================== I : " + i + " j : " + j + "  =============================");
        for (int k = i; k < j; k++) {
            int maxCoinInLeft = t[i][k] != -1 ? t[i][k] : solve(numsWithStartAndEnd, i, k);
//            LogUtil.logIt(" Max Coin Gain At Left when k = " + k + " is " + maxCoinInLeft);
            int maxCoinInRight = t[k + 1][j] != -1 ? t[k + 1][j] : solve(numsWithStartAndEnd, k + 1, j);
//            LogUtil.logIt(" Max Coin Gain At Right when k = " + k + " is " + maxCoinInRight);
            int ifIShootAtK = maxCoinInLeft + maxCoinInRight +
                    (numsWithStartAndEnd[i - 1]
                            * numsWithStartAndEnd[k] * numsWithStartAndEnd[j]);
//            LogUtil.logIt(" If I Shoot At k = " + k + " is " + ifIShootAtK);
            MAX_COINS = Math.max(MAX_COINS, ifIShootAtK);
        }
        return t[i][j] = MAX_COINS;
    }
}
