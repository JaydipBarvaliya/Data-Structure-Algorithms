package com.leetcode.year_2020.DP.interview_prep;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * https://www.geeksforgeeks.org/minimum-time-write-characters-using-insert-delete-copy-operation/
 *
 * @author neeraj on 07/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumTimeToWriteCharactersUsingInsertDeleteAndCopyOperation {

    public static void main(String[] args) {
        getMinOperation(9, 1, 2, 1);
        getMinOperation(2, 2, 2, 1);
        getMinOperation(4, 2, 2, 1);
        getMinOperation(10, 2, 5, 4);

        getMinOperation(3, 100, 2, 10);
        getMinOperation(4, 100, 2, 10);
    }

    static int dp[];

    public static int getMinOperation(int N, int insertCost, int removalCost, int copyAndPasteCost) {
        dp = new int[1000]; // Memorization
        Arrays.fill(dp, -1);
        /**
         * we know that first operation will always be insert since you can't perform removal or CopyPaste without any character
         * present in the input
         */
        int currentPointer = 1;
        int cost = insertCost + getMinOperation(N, currentPointer, insertCost, removalCost, copyAndPasteCost);
        LogUtil.logIt("Minimum Cost required is " + cost);
        return cost;
    }

    private static int getMinOperation(int N, int currentPointer, int insertCost, int removalCost, int copyAndPasteCost) {

        if (currentPointer == N) {
            return 0; // We don't have to do anything.
        }
        int cost = Integer.MAX_VALUE;

        // Return from cache
        if (dp[currentPointer] != -1) return dp[currentPointer];

        if (currentPointer < N) { // Then we have just 2 choices, either insert or do a copyAndPaste
            // Try to insert
            cost = insertCost + getMinOperation(N, currentPointer + 1, insertCost, removalCost, copyAndPasteCost);

            /**
             * Try to do a copyAndPaste
             * Now whenever you do a copy Paste, current Pointer will move forward with "TWICE" the number of steps
             * of how far it was from the initial character.
             * Lets' say we have
             *              A A A A ===============> in the input.
             *                      ||
             *                      Pointer
             * Now i do copy and paste
             *              A A A A A A A A
             *                              ||
             *                              Pointer will come here now
             */
            cost = Math.min(cost,
                    (copyAndPasteCost + getMinOperation(N, 2 * currentPointer, insertCost, removalCost, copyAndPasteCost)));
        }
        if (currentPointer > N) {
            cost = Math.min(cost,
                    (removalCost + getMinOperation(N, currentPointer - 1, insertCost, removalCost, copyAndPasteCost)));
        }

        return dp[currentPointer] = cost;
    }
}
