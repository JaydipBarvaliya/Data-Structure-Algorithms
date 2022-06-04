package com.leetcode.year_2020.DP.minimum_maximum_path_to_reach_to_target;

import java.util.Arrays;

/**
 * @author neeraj on 09/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class OnesAndZeros {

    public static void main(String[] args) {
        System.out.println(findMaxForm(new String[]{
                "10", "0001", "111001", "1", "0"
        }, 5, 3));
        System.out.println(findMaxForm(new String[]{
                "10", "0", "1"
        }, 1, 1));
        System.out.println(findMaxForm(new String[]{
                "0001", "0101", "1000", "1000"
        }, 9, 3));

        System.out.println(findMaxForm(new String[]{
                "11111", "100", "1101", "1101", "11000"
        }, 5, 7));
    }

    static int T[][][];// Memorization Cache

    public static int findMaxForm(String[] strs, int zerosLeft, int onesLeft) {
        /**
         * if you notice carefully this is a 0/1 Knapsack problem.
         * Now instead of amount we will use zerosLeft and onesLeft as our comparision
         * and we will work on our Frequency instead of real array.
         */
        // In this Memorization will be 3 dimensional, since we have 3 moving factors
        T = new int[strs.length + 1][zerosLeft + 2][onesLeft + 2];
        for (int[][] multiRow : T) {
            for (int[] row : multiRow) {
                Arrays.fill(row, -1);
            }
        }
        int[][] indexFreqArr = getIndexFreqArr(strs);
//        return solve(indexFreqArr, zerosLeft, onesLeft, indexFreqArr.length - 1);

        /**
         * We can solve this using Bottom Up Approach as well.
         */
        return solveUsingBottomUpApproach(strs,zerosLeft, onesLeft);
    }

    private static int solve(int[][] indexFreqArr, int zerosLeft, int onesLeft, int N) {
        // We will define the base case.
        if (N < 0) return 0;
        if (zerosLeft == 0 && onesLeft == 0)
            return 0; // Since we can't make any pair,if we don't have any zeros or ones left to be used.

        if (T[N][zerosLeft][onesLeft] != -1) return T[N][zerosLeft][onesLeft];

        int[] freqLeftIfWeUseThisItemAtIndexN = getFreqLeft(indexFreqArr[N], zerosLeft, onesLeft);

        if (freqLeftIfWeUseThisItemAtIndexN != null) { // means this index can be used
            return T[N][zerosLeft][onesLeft] = Math.max(1 + solve(indexFreqArr, freqLeftIfWeUseThisItemAtIndexN[0], freqLeftIfWeUseThisItemAtIndexN[1], N - 1),
                    solve(indexFreqArr, zerosLeft, onesLeft, N - 1));
        } else {
            // means we can't use item at this index at all.
            return T[N][zerosLeft][onesLeft] = solve(indexFreqArr, zerosLeft, onesLeft, N - 1);
        }
    }

    private static int[] getFreqLeft(int[] zeroAndOnesFreq, int zerosLeft, int onesLeft) {
        int[] freqLeftIfWeUseThisItemAtIndexN = null;
        if (zerosLeft > 0 && onesLeft > 0 && zeroAndOnesFreq[0] > 0 && zeroAndOnesFreq[1] > 0) {
            if ((zerosLeft - zeroAndOnesFreq[0]) >= 0 && (onesLeft - zeroAndOnesFreq[1]) >= 0) {
                zerosLeft = zerosLeft - zeroAndOnesFreq[0];
                onesLeft = onesLeft - zeroAndOnesFreq[1];
                freqLeftIfWeUseThisItemAtIndexN = new int[]{zerosLeft, onesLeft};
            }
        } else if (zerosLeft > 0 && zeroAndOnesFreq[0] > 0) {
            if ((zerosLeft - zeroAndOnesFreq[0]) >= 0 && zeroAndOnesFreq[1] == 0) {
                zerosLeft = zerosLeft - zeroAndOnesFreq[0];
                freqLeftIfWeUseThisItemAtIndexN = new int[]{zerosLeft, onesLeft};
            }
        } else if (onesLeft > 0 && zeroAndOnesFreq[1] > 0) {
            if ((onesLeft - zeroAndOnesFreq[1]) >= 0 && zeroAndOnesFreq[0] == 0) {
                onesLeft = onesLeft - zeroAndOnesFreq[1];
                freqLeftIfWeUseThisItemAtIndexN = new int[]{zerosLeft, onesLeft};
            }
        }
        return freqLeftIfWeUseThisItemAtIndexN;
    }

    private static int[][] getIndexFreqArr(String[] strs) {
        int[][] indexFreqArr = new int[strs.length][2];
        int[] zeroAndOneFreq = new int[2];
        for (int i = 0; i < strs.length; i++) {
            String str = strs[i];
            zeroAndOneFreq = new int[2];
            for (char c : str.toCharArray()) {
                if (c == '0')
                    zeroAndOneFreq[0]++;
                else
                    zeroAndOneFreq[1]++;
            }
            indexFreqArr[i] = zeroAndOneFreq;
        }
        return indexFreqArr;
    }

    private static int solveUsingBottomUpApproach(String[] strs, int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        for (String str : strs) {
            int one = 0;
            int zero = 0;
            for (char c : str.toCharArray()) {
                if (c == '1')
                    one++;
                else
                    zero++;
            }

            for (int i = m; i >= zero; i--) { // Representing zeros
                for (int j = n; j >= one; j--) { // Representing ones
                    if (one <= j && zero <= i) {
                        //Max of either we choose the item or we don't
                        dp[i][j] = Math.max(dp[i][j], dp[i - zero][j - one] + 1);
                    }
                }
            }
        }
        return dp[m][n];
    }
}
