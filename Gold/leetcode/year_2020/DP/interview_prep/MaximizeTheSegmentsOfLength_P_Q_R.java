package com.leetcode.year_2020.DP.interview_prep;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * @author neeraj on 06/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MaximizeTheSegmentsOfLength_P_Q_R {

    public static void main(String[] args) {
        getMaxCut(11, 2, 3, 5);
        getMaxCut(7, 2, 3, 5);
        getMaxCut(7, 2, 5, 5);
        getMaxCut(4, 1, 2, 4);
    }

    static int dp[]; // Memorization

    public static int getMaxCut(int length, int p, int q, int r) {
        /**
         * Top Down....we'll start with the length...
         * and check Max(maxCut(length - p), maxCut(length - q),maxCut(length - r)) + 1
         */
        dp = new int[length + 1];
        Arrays.fill(dp, -1);
        int maxCut = maxCutRecursively(p, q, r, length);
        LogUtil.logIt("Maximum Cut is " + maxCut);
        return maxCut;
    }

    private static int maxCutRecursively(int p, int q, int r, int length) {
        // Base Condition
        int minSegmentLength = Math.min(p, Math.min(q, r));
        if (length < minSegmentLength) return 0; // You can't make a cut of segment length 0.

        if (length % p == 0) {
            return length / p;
        }
        if (length % q == 0) {
            return length / q;
        }

        if (length % r == 0) {
            return length / r;
        }

        if (dp[length] != -1) return dp[length];

        // Hypothesis. // we have to try all cuts
        int maxCutBeforeCurrentLength = Math.max(
                maxCutRecursively(p, q, r, length - p),
                Math.max(
                        maxCutRecursively(p, q, r, length - q),
                        maxCutRecursively(p, q, r, length - r))
        );

        // we know whoever wins gives the max Segment we have to make 1 more of that cut.
        return dp[length] = 1 + maxCutBeforeCurrentLength;
    }
}
