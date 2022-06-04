package com.leetcode.year_2020.DP.unbounded_knapsack;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/number-of-dice-rolls-with-target-sum/
 * @author neeraj on 09/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumberofDiceRollsWithTargetSum {
    static int MOD = 1000000000 + 7;

    public static void main(String[] args) {
        System.out.println(numRollsToTarget(1, 6, 3));
        System.out.println(numRollsToTarget(2, 6, 7));
        System.out.println(numRollsToTarget(2, 5, 10));
        System.out.println(numRollsToTarget(1, 2, 3));
        System.out.println(numRollsToTarget(30, 30, 500));
    }

    static int T[][]; // Memorization

    public static int numRollsToTarget(int noOfDices, int totalFacesInThisDice, int target) {
        /**
         * This problem is like {@link CoinChange_NumberOfWays}
         * with a twist that we don't have unlimited supply but instead.
         * we have only noOfDices worth supply.
         */
        // As we know number of dices and target is changing hence we need 2D matrix
        T = new int[noOfDices + 1][target + 1];
        for (int[] row : T) {
            Arrays.fill(row, -1);
        }
        return findNumberOfWays(noOfDices, totalFacesInThisDice, target);
    }

    private static int findNumberOfWays(int noOfDices, int totalFacesInThisDice, int target) {
        // Base Condition
        if(noOfDices == 0 && target == 0) return 1; // There is just 1 way when you have to achieve target 0 without any dice.
        if(noOfDices == 0 || target <= 0) return 0;


        if (T[noOfDices][target] != -1) return T[noOfDices][target];

        // For this dice we have to try all faces
        // and combine number of ways if we reached to the target sum
        int numberOfWays = 0;
        for (int faceValue = 1; faceValue <= totalFacesInThisDice; faceValue++) {
            numberOfWays = (numberOfWays +
                    findNumberOfWays(noOfDices - 1, totalFacesInThisDice, target - faceValue)) % MOD;
        }
        return T[noOfDices][target] = numberOfWays;
    }
}
